package com.albertsilva.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * Entidade que representa um Pedido no domínio do sistema.
 *
 * ============================================================
 * AGGREGATE ROOT (DDD)
 * ============================================================
 *
 * <p>
 * {@code Pedido} é a raiz do agregado no contexto de vendas.
 * Isso significa que:
 * </p>
 *
 * <ul>
 * <li>É o único ponto de entrada para modificação dos {@link ItemPedido};</li>
 * <li>Controla o ciclo de vida do {@link Pagamento};</li>
 * <li>Garante consistência interna do conjunto de itens;</li>
 * <li>Protege invariantes de domínio contra manipulação externa;</li>
 * <li>Deve ser persistido e carregado como unidade transacional.</li>
 * </ul>
 *
 * <p>
 * Nenhum {@link ItemPedido} deve ser criado, alterado ou removido fora
 * desta raiz de agregado.
 * </p>
 *
 * ============================================================
 * COMPOSIÇÃO DO AGREGADO
 * ============================================================
 *
 * <ul>
 * <li>{@link Pagamento} (1–1, com cascade ALL)</li>
 * <li>{@link ItemPedido} (1–N, com orphanRemoval = true)</li>
 * </ul>
 *
 * <p>
 * {@link Cliente} e {@link Endereco} são referências externas ao agregado.
 * </p>
 *
 * ============================================================
 * INVARIANTES DE DOMÍNIO
 * ============================================================
 *
 * <ul>
 * <li>Um item nunca pode ter quantidade ≤ 0;</li>
 * <li>Um produto não pode existir duplicado no pedido;</li>
 * <li>O total do pedido deve sempre refletir a soma dos subtotais;</li>
 * <li>Remoções estruturais devem refletir no banco (orphanRemoval);</li>
 * <li>A manipulação de itens deve ocorrer exclusivamente via métodos de
 * domínio.</li>
 * </ul>
 *
 * <p>
 * A entidade segue o padrão <strong>Rich Domain Model</strong>,
 * encapsulando comportamento e evitando modelo anêmico.
 * </p>
 */
@Entity(name = "pedido")
public class Pedido implements Serializable {

  private final static long serialVersionUID = 1L;

  /**
   * Identificador único do pedido.
   * Representa a identidade do Aggregate Root.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Data e hora em que o pedido foi realizado.
   */
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private Date instante;

  /**
   * Pagamento associado ao pedido.
   *
   * <p>
   * Pertence ao agregado e tem seu ciclo de vida controlado pelo Pedido.
   * </p>
   */
  @JsonManagedReference
  @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
  private Pagamento pagamento;

  /**
   * Cliente responsável pelo pedido.
   *
   * <p>
   * Referência externa ao agregado.
   * </p>
   */
  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  /**
   * Endereço de entrega.
   *
   * <p>
   * Referência externa ao agregado.
   * </p>
   */
  @ManyToOne
  @JoinColumn(name = "endereco_de_entrega_id")
  private Endereco enderecoDeEntrega;

  /**
   * Conjunto de itens do pedido.
   *
   * <p>
   * Esta coleção é parte interna do agregado.
   * </p>
   *
   * <p>
   * orphanRemoval = true garante consistência estrutural:
   * itens removidos do agregado são removidos da persistência.
   * </p>
   */
  @OneToMany(mappedBy = "id.pedido", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ItemPedido> itens = new HashSet<>();

  /**
   * Construtor padrão exigido pela JPA.
   */
  public Pedido() {
  }

  /**
   * Construtor para criação do agregado.
   */
  public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
    this.id = id;
    this.instante = instante;
    this.cliente = cliente;
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  /**
   * Adiciona um produto ao agregado.
   *
   * <p>
   * Regras aplicadas:
   * </p>
   *
   * <ul>
   * <li>Quantidade deve ser maior que zero;</li>
   * <li>Não pode haver duplicidade de produto;</li>
   * <li>Se já existir, incrementa quantidade;</li>
   * <li>Criação de ItemPedido é responsabilidade do Aggregate Root.</li>
   * </ul>
   *
   * @throws IllegalArgumentException se quantidade inválida
   */
  public void adicionarItem(Produto produto, Integer quantidade) {

    validarQuantidade(quantidade);

    ItemPedido existente = buscarItemPorProduto(produto.getId());

    if (existente != null) {
      existente.setQuantidade(existente.getQuantidade() + quantidade);
    } else {
      ItemPedido novo = new ItemPedido(this, produto, 0.0, quantidade, produto.getPreco());
      itens.add(novo);
    }
  }

  /**
   * Atualiza completamente o conjunto de itens do agregado.
   *
   * <p>
   * Estratégia:
   * </p>
   * <ol>
   * <li>Remove itens ausentes no novo conjunto;</li>
   * <li>Atualiza quantidades existentes;</li>
   * <li>Adiciona novos itens;</li>
   * </ol>
   *
   * <p>
   * Garante integridade estrutural do agregado.
   * </p>
   */
  public void atualizarItens(Map<Produto, Integer> novosItens) {

    itens.removeIf(item -> novosItens.keySet().stream()
        .noneMatch(prod -> prod.getId().equals(item.getProduto().getId())));

    for (Map.Entry<Produto, Integer> entry : novosItens.entrySet()) {

      Produto produto = entry.getKey();
      Integer quantidade = entry.getValue();

      validarQuantidade(quantidade);

      ItemPedido existente = buscarItemPorProduto(produto.getId());

      if (existente != null) {
        existente.setQuantidade(quantidade);
      } else {
        adicionarItem(produto, quantidade);
      }
    }
  }

  /**
   * Busca item por produto.
   *
   * Método interno ao agregado.
   */
  private ItemPedido buscarItemPorProduto(Integer produtoId) {
    return itens.stream()
        .filter(i -> i.getProduto().getId().equals(produtoId))
        .findFirst()
        .orElse(null);
  }

  /**
   * Valida invariante de domínio:
   * quantidade deve ser > 0.
   */
  private void validarQuantidade(Integer quantidade) {
    if (quantidade == null || quantidade <= 0) {
      throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
    }
  }

  /**
   * Propriedade derivada.
   *
   * <p>
   * O total não é armazenado, é calculado dinamicamente
   * para evitar inconsistência.
   * </p>
   */
  public double getTotal() {
    return itens.stream()
        .mapToDouble(ItemPedido::getSubTotal)
        .sum();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getInstante() {
    return instante;
  }

  public void setInstante(Date instante) {
    this.instante = instante;
  }

  public Pagamento getPagamento() {
    return pagamento;
  }

  public void setPagamento(Pagamento pagamento) {
    this.pagamento = pagamento;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Endereco getEnderecoDeEntrega() {
    return enderecoDeEntrega;
  }

  public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  public Set<ItemPedido> getItens() {
    return itens;
  }

  public void setItens(Set<ItemPedido> itens) {
    this.itens = itens;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Pedido other = (Pedido) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}