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
 * <p>
 * Um pedido é realizado por um {@link Cliente}, possui um instante de criação,
 * um {@link Pagamento} associado, um {@link Endereco} de entrega e um conjunto
 * de {@link ItemPedido}.
 * </p>
 *
 * <p>
 * Esta entidade também encapsula regras de domínio relacionadas à manipulação
 * de itens e ao cálculo do valor total do pedido, seguindo princípios de
 * modelagem orientada a domínio (DDD).
 * </p>
 */
@Entity(name = "pedido")
public class Pedido implements Serializable {

  private final static long serialVersionUID = 1L;

  /**
   * Identificador único do pedido.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Data e hora em que o pedido foi realizado.
   * Serializado no padrão {@code dd/MM/yyyy HH:mm}.
   */
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private Date instante;

  /**
   * Pagamento associado ao pedido.
   * Relacionamento bidirecional com cascata total.
   */
  @JsonManagedReference
  @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
  private Pagamento pagamento;

  /**
   * Cliente responsável pelo pedido.
   */
  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  /**
   * Endereço onde o pedido deverá ser entregue.
   */
  @ManyToOne
  @JoinColumn(name = "endereco_de_entrega_id")
  private Endereco enderecoDeEntrega;

  /**
   * Conjunto de itens que compõem o pedido.
   *
   * <p>
   * A configuração {@code orphanRemoval = true} garante que itens removidos
   * do conjunto também sejam removidos do banco de dados.
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
   * Construtor para criação de um pedido.
   *
   * @param id                identificador do pedido
   * @param instante          data e hora da realização
   * @param cliente           cliente responsável
   * @param enderecoDeEntrega endereço de entrega
   */
  public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
    this.id = id;
    this.instante = instante;
    this.cliente = cliente;
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  /**
   * Adiciona um produto ao pedido.
   *
   * <p>
   * Caso o produto já exista no pedido, a quantidade é incrementada.
   * Caso contrário, um novo {@link ItemPedido} é criado.
   * </p>
   *
   * @param produto    produto a ser adicionado
   * @param quantidade quantidade desejada (deve ser maior que zero)
   *
   * @throws IllegalArgumentException caso a quantidade seja inválida
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
   * Atualiza os itens do pedido com base em um novo conjunto de produtos e
   * quantidades.
   *
   * <p>
   * Remove itens que não estejam presentes no novo mapa e atualiza ou adiciona
   * os demais conforme necessário.
   * </p>
   *
   * @param novosItens mapa contendo produtos e respectivas quantidades
   *
   * @throws IllegalArgumentException caso alguma quantidade seja inválida
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
   * Busca um item do pedido pelo identificador do produto.
   *
   * @param produtoId identificador do produto
   * @return item correspondente ou {@code null} se não encontrado
   */
  private ItemPedido buscarItemPorProduto(Integer produtoId) {
    return itens.stream()
        .filter(i -> i.getProduto().getId().equals(produtoId))
        .findFirst()
        .orElse(null);
  }

  /**
   * Valida a quantidade informada.
   *
   * @param quantidade quantidade a ser validada
   *
   * @throws IllegalArgumentException caso seja nula ou menor/igual a zero
   */
  private void validarQuantidade(Integer quantidade) {
    if (quantidade == null || quantidade <= 0) {
      throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
    }
  }

  /**
   * Calcula o valor total do pedido com base nos subtotais dos itens.
   *
   * @return valor total do pedido
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

  /**
   * Implementação baseada no identificador da entidade.
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /**
   * Comparação baseada exclusivamente no identificador.
   */
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