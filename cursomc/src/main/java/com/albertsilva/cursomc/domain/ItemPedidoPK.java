package com.albertsilva.cursomc.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Classe que representa a chave primária composta da entidade
 * {@link ItemPedido}.
 *
 * <p>
 * Esta classe é anotada com {@link Embeddable}, indicando que seus atributos
 * serão incorporados (embedded) em outra entidade, neste caso
 * {@link ItemPedido}.
 * </p>
 *
 * <p>
 * A chave composta é formada pela associação entre:
 * </p>
 * <ul>
 * <li>{@link Pedido}</li>
 * <li>{@link Produto}</li>
 * </ul>
 *
 * <p>
 * Essa modelagem é utilizada para representar um relacionamento
 * Many-to-Many com atributos adicionais, onde {@code ItemPedido}
 * atua como entidade associativa.
 * </p>
 *
 * <p>
 * Implementa {@link Serializable} conforme exigido pela especificação JPA
 * para chaves compostas.
 * </p>
 *
 * @author Albert Silva
 */
@Embeddable
public class ItemPedidoPK implements Serializable {

  /**
   * Versão de serialização da classe.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Referência ao pedido.
   *
   * <p>
   * Mapeado como Many-to-One, representando a parte da chave
   * composta referente ao {@link Pedido}.
   * </p>
   */
  @ManyToOne
  @JoinColumn(name = "pedido_id")
  private Pedido pedido;

  /**
   * Referência ao produto.
   *
   * <p>
   * Mapeado como Many-to-One, representando a parte da chave
   * composta referente ao {@link Produto}.
   * </p>
   */
  @ManyToOne
  @JoinColumn(name = "produto_id")
  private Produto produto;

  /**
   * Retorna o pedido associado à chave composta.
   *
   * @return pedido
   */
  public Pedido getPedido() {
    return pedido;
  }

  /**
   * Define o pedido da chave composta.
   *
   * @param pedido pedido a ser associado
   */
  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  /**
   * Retorna o produto associado à chave composta.
   *
   * @return produto
   */
  public Produto getProduto() {
    return produto;
  }

  /**
   * Define o produto da chave composta.
   *
   * @param produto produto a ser associado
   */
  public void setProduto(Produto produto) {
    this.produto = produto;
  }

  /**
   * Gera o hash code baseado nas entidades que compõem a chave.
   *
   * @return valor hash da chave composta
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
    result = prime * result + ((produto == null) ? 0 : produto.hashCode());
    return result;
  }

  /**
   * Compara duas chaves compostas com base nas entidades
   * {@link Pedido} e {@link Produto}.
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
    ItemPedidoPK other = (ItemPedidoPK) obj;
    if (pedido == null) {
      if (other.pedido != null)
        return false;
    } else if (!pedido.equals(other.pedido))
      return false;
    if (produto == null) {
      if (other.produto != null)
        return false;
    } else if (!produto.equals(other.produto))
      return false;
    return true;
  }

}