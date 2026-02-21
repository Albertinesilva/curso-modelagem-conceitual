package com.albertsilva.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * Entidade que representa um Estado no sistema.
 *
 * <p>
 * Esta classe é uma entidade JPA mapeada para a tabela {@code estado}.
 * Um Estado possui um identificador único, um nome e um conjunto
 * de cidades associadas.
 * </p>
 *
 * <p>
 * Relacionamento:
 * <ul>
 * <li>Um Estado possui várias {@link Cidade}</li>
 * <li>Mapeamento bidirecional One-to-Many</li>
 * </ul>
 * </p>
 *
 * <p>
 * Implementa {@link Serializable} para permitir que instâncias
 * sejam convertidas em fluxo de bytes, conforme recomendado
 * para entidades JPA.
 * </p>
 *
 * @author Albert Silva
 */
@Entity(name = "estado")
public class Estado implements Serializable {

  /**
   * Versão de serialização da classe.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Identificador único do Estado.
   *
   * <p>
   * Gerado automaticamente pelo banco de dados utilizando
   * estratégia {@link GenerationType#IDENTITY}.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Nome do Estado.
   */
  private String nome;

  /**
   * Lista de cidades pertencentes ao Estado.
   *
   * <p>
   * Relacionamento One-to-Many bidirecional, onde a entidade
   * {@link Cidade} é o lado proprietário da relação.
   * </p>
   *
   * <p>
   * A anotação {@link JsonBackReference} evita loop infinito
   * durante a serialização JSON.
   * </p>
   */
  @JsonBackReference
  @OneToMany(mappedBy = "estado")
  private List<Cidade> cidades = new ArrayList<>();

  /**
   * Construtor padrão exigido pela JPA.
   */
  public Estado() {
  }

  /**
   * Construtor com parâmetros.
   *
   * @param id   identificador do Estado
   * @param nome nome do Estado
   */
  public Estado(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  /**
   * Retorna o identificador do Estado.
   *
   * @return id do Estado
   */
  public Integer getId() {
    return id;
  }

  /**
   * Define o identificador do Estado.
   *
   * @param id novo identificador
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Retorna o nome do Estado.
   *
   * @return nome do Estado
   */
  public String getNome() {
    return nome;
  }

  /**
   * Define o nome do Estado.
   *
   * @param nome novo nome do Estado
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Retorna a lista de cidades associadas ao Estado.
   *
   * @return lista de cidades
   */
  public List<Cidade> getCidades() {
    return cidades;
  }

  /**
   * Define a lista de cidades do Estado.
   *
   * @param cidades nova lista de cidades
   */
  public void setCidades(List<Cidade> cidades) {
    this.cidades = cidades;
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
   * Compara dois objetos Estado com base no identificador.
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
    Estado other = (Estado) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}