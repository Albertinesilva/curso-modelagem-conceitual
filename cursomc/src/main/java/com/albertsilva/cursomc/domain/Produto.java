package com.albertsilva.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

/**
 * Entidade que representa um Produto no domínio do sistema.
 *
 * <p>
 * Um produto possui nome, preço e pode estar associado a múltiplas
 * {@link Categoria}s. Além disso, pode compor diversos {@link Pedido}s
 * por meio da entidade associativa {@link ItemPedido}.
 * </p>
 *
 * <p>
 * O relacionamento com {@link Pedido} é indireto e obtido através dos itens,
 * preservando a modelagem de muitos-para-muitos com atributos adicionais
 * (quantidade, desconto, subtotal).
 * </p>
 */
@Entity(name = "produto")
public class Produto implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Identificador único do produto.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Nome do produto.
   */
  private String nome;

  /**
   * Preço unitário do produto.
   */
  private Double preco;

  /**
   * Categorias às quais o produto pertence.
   *
   * <p>
   * Relacionamento muitos-para-muitos mapeado pela tabela intermediária
   * {@code PRODUTO_CATEGORIA}.
   * </p>
   */
  @ManyToMany
  @JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
  private List<Categoria> categorias = new ArrayList<>();

  /**
   * Conjunto de itens de pedido que referenciam este produto.
   *
   * <p>
   * Utilizado para materializar o relacionamento com {@link Pedido}
   * através da entidade {@link ItemPedido}.
   * </p>
   */
  @JsonIgnore
  @OneToMany(mappedBy = "id.produto")
  private Set<ItemPedido> itens = new HashSet<>();

  /**
   * Construtor padrão exigido pela JPA.
   */
  public Produto() {
  }

  /**
   * Construtor para criação de produto.
   *
   * @param id    identificador do produto
   * @param nome  nome do produto
   * @param preco preço unitário
   */
  public Produto(Integer id, String nome, Double preco) {
    this.id = id;
    this.nome = nome;
    this.preco = preco;
  }

  /**
   * Retorna a lista de pedidos que contêm este produto.
   *
   * <p>
   * A obtenção ocorre através da navegação pelos {@link ItemPedido}s.
   * Método ignorado na serialização JSON para evitar recursividade.
   * </p>
   *
   * @return lista de pedidos associados ao produto
   */
  @JsonIgnore
  public List<Pedido> getPedidos() {
    List<Pedido> lista = new ArrayList<>();
    for (ItemPedido x : itens) {
      lista.add(x.getPedido());
    }
    return lista;
  }

  /**
   * Retorna o identificador do produto.
   *
   * @return id do produto
   */
  public Integer getId() {
    return id;
  }

  /**
   * Define o identificador do produto.
   *
   * @param id novo identificador
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Retorna o nome do produto.
   *
   * @return nome
   */
  public String getNome() {
    return nome;
  }

  /**
   * Define o nome do produto.
   *
   * @param nome novo nome
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Retorna o preço unitário do produto.
   *
   * @return preço
   */
  public Double getPreco() {
    return preco;
  }

  /**
   * Define o preço unitário do produto.
   *
   * @param preco novo preço
   */
  public void setPreco(Double preco) {
    this.preco = preco;
  }

  /**
   * Retorna as categorias associadas ao produto.
   *
   * @return lista de categorias
   */
  public List<Categoria> getCategorias() {
    return categorias;
  }

  /**
   * Define as categorias associadas ao produto.
   *
   * @param categorias nova lista de categorias
   */
  public void setCategorias(List<Categoria> categorias) {
    this.categorias = categorias;
  }

  /**
   * Retorna os itens de pedido associados ao produto.
   *
   * @return conjunto de itens
   */
  public Set<ItemPedido> getItens() {
    return itens;
  }

  /**
   * Define os itens de pedido associados ao produto.
   *
   * @param itens novo conjunto de itens
   */
  public void setItens(Set<ItemPedido> itens) {
    this.itens = itens;
  }

  /**
   * Implementação baseada exclusivamente no identificador da entidade.
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
    Produto other = (Produto) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}