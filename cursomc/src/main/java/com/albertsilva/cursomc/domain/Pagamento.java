package com.albertsilva.cursomc.domain;

import java.io.Serializable;

import com.albertsilva.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

/**
 * Entidade abstrata que representa um Pagamento no sistema.
 *
 * <p>
 * Esta classe é a superclasse da hierarquia de pagamentos e utiliza
 * estratégia de herança {@link InheritanceType#JOINED}, onde cada
 * subclasse possui sua própria tabela no banco de dados.
 * </p>
 *
 * <p>
 * O pagamento está associado a exatamente um {@link Pedido},
 * mantendo um relacionamento One-to-One com compartilhamento
 * de chave primária por meio da anotação {@link MapsId}.
 * </p>
 *
 * <p>
 * O estado do pagamento é representado pelo enum
 * {@link EstadoPagamento}, armazenado internamente como código inteiro.
 * </p>
 *
 * <p>
 * Implementa {@link Serializable} conforme recomendação da especificação JPA.
 * </p>
 *
 * @author Albert Silva
 */
@Entity(name = "pagamento")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

  /**
   * Versão de serialização da classe.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Identificador do pagamento.
   *
   * <p>
   * Compartilhado com a entidade {@link Pedido} através de {@link MapsId},
   * caracterizando chave primária compartilhada.
   * </p>
   */
  @Id
  private Integer id;

  /**
   * Código do estado do pagamento.
   *
   * <p>
   * Representa internamente o enum {@link EstadoPagamento}.
   * </p>
   */
  private Integer estado;

  /**
   * Pedido associado ao pagamento.
   *
   * <p>
   * Relacionamento One-to-One com chave primária compartilhada.
   * </p>
   *
   * <p>
   * A anotação {@link JsonBackReference} evita loop infinito
   * durante a serialização JSON.
   * </p>
   */
  @JsonBackReference
  @OneToOne
  @JoinColumn(name = "pedido_id")
  @MapsId
  private Pedido pedido;

  /**
   * Construtor padrão exigido pela JPA.
   */
  public Pagamento() {
  }

  /**
   * Construtor com parâmetros.
   *
   * @param id     identificador do pagamento
   * @param estado estado do pagamento
   * @param pedido pedido associado
   */
  public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
    this.id = id;
    this.estado = estado.getCod();
    this.pedido = pedido;
  }

  /**
   * Retorna o identificador do pagamento.
   *
   * @return id do pagamento
   */
  public Integer getId() {
    return id;
  }

  /**
   * Define o identificador do pagamento.
   *
   * @param id novo identificador
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Retorna o estado do pagamento como enum.
   *
   * @return estado do pagamento
   */
  public EstadoPagamento getEstado() {
    return EstadoPagamento.toEnum(estado);
  }

  /**
   * Define o estado do pagamento.
   *
   * @param estado novo estado do pagamento
   */
  public void setEstado(EstadoPagamento estado) {
    this.estado = estado.getCod();
  }

  /**
   * Retorna o pedido associado ao pagamento.
   *
   * @return pedido associado
   */
  public Pedido getPedido() {
    return pedido;
  }

  /**
   * Define o pedido associado ao pagamento.
   *
   * @param pedido pedido a ser associado
   */
  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  /**
   * Gera o hash code baseado no identificador.
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
   * Compara dois pagamentos com base no identificador.
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
    Pagamento other = (Pagamento) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}