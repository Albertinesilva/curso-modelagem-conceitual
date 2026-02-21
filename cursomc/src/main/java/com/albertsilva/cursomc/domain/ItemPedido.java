package com.albertsilva.cursomc.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

/**
 * Entidade que representa um Item de Pedido no sistema.
 *
 * <p>
 * Esta entidade modela a associação entre {@link Pedido} e {@link Produto},
 * representando um relacionamento Many-to-Many com atributos adicionais.
 * </p>
 *
 * <p>
 * A chave primária é composta, sendo representada pela classe
 * {@link ItemPedidoPK}, anotada com {@link EmbeddedId}.
 * </p>
 *
 * <p>
 * Além das referências ao pedido e ao produto, o item armazena
 * informações específicas da transação, como:
 * </p>
 * <ul>
 * <li>Desconto aplicado</li>
 * <li>Quantidade do produto</li>
 * <li>Preço no momento da compra</li>
 * </ul>
 *
 * <p>
 * Implementa {@link Serializable} conforme recomendado para entidades JPA.
 * </p>
 *
 * @author Albert Silva
 */
@Entity(name = "item_pedido")
public class ItemPedido implements Serializable {

  /**
   * Versão de serialização da classe.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Identificador composto do ItemPedido.
   *
   * <p>
   * Representa a chave primária composta formada por
   * {@link Pedido} e {@link Produto}.
   * </p>
   *
   * <p>
   * A anotação {@link JsonIgnore} evita exposição da estrutura
   * interna da chave composta na serialização JSON.
   * </p>
   */
  @JsonIgnore
  @EmbeddedId
  private ItemPedidoPK id = new ItemPedidoPK();

  /**
   * Valor do desconto aplicado ao item.
   */
  private Double desconto;

  /**
   * Quantidade do produto no pedido.
   */
  private Integer quantidade;

  /**
   * Preço unitário do produto no momento da compra.
   */
  private Double preco;

  /**
   * Construtor padrão exigido pela JPA.
   */
  public ItemPedido() {
  }

  /**
   * Construtor com parâmetros.
   *
   * @param pedido     pedido associado
   * @param produto    produto associado
   * @param desconto   valor de desconto aplicado
   * @param quantidade quantidade do produto
   * @param preco      preço unitário do produto
   */
  public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
    id.setPedido(pedido);
    id.setProduto(produto);
    this.desconto = desconto;
    this.quantidade = quantidade;
    this.preco = preco;
  }

  /**
   * Calcula o subtotal do item.
   *
   * <p>
   * O subtotal corresponde ao produto da quantidade pelo preço unitário.
   * </p>
   *
   * @return valor total do item (sem considerar desconto explícito)
   */
  public double getSubTotal() {
    return quantidade * preco;
  }

  /**
   * Retorna o pedido associado ao item.
   *
   * <p>
   * A anotação {@link JsonIgnore} evita serialização recursiva.
   * </p>
   *
   * @return pedido associado
   */
  @JsonIgnore
  public Pedido getPedido() {
    return id.getPedido();
  }

  /**
   * Retorna o produto associado ao item.
   *
   * @return produto associado
   */
  public Produto getProduto() {
    return id.getProduto();
  }

  /**
   * Retorna o identificador composto do item.
   *
   * @return chave composta
   */
  public ItemPedidoPK getId() {
    return id;
  }

  /**
   * Define o identificador composto do item.
   *
   * @param id nova chave composta
   */
  public void setId(ItemPedidoPK id) {
    this.id = id;
  }

  /**
   * Retorna o desconto aplicado ao item.
   *
   * @return valor do desconto
   */
  public Double getDesconto() {
    return desconto;
  }

  /**
   * Define o desconto do item.
   *
   * @param desconto novo valor de desconto
   */
  public void setDesconto(Double desconto) {
    this.desconto = desconto;
  }

  /**
   * Retorna a quantidade do produto.
   *
   * @return quantidade
   */
  public Integer getQuantidade() {
    return quantidade;
  }

  /**
   * Define a quantidade do produto.
   *
   * @param quantidade nova quantidade
   */
  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  /**
   * Retorna o preço unitário do produto.
   *
   * @return preço unitário
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
   * Gera o hash code com base na chave composta.
   *
   * @return valor hash da entidade
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /**
   * Compara dois objetos ItemPedido com base na chave composta.
   *
   * @param obj objeto a ser comparado
   * @return true se forem iguais, false caso contrário
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ItemPedido other = (ItemPedido) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}