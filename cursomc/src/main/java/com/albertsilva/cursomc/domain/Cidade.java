package com.albertsilva.cursomc.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Entidade que representa uma Cidade no domínio da aplicação.
 *
 * <p>
 * Uma cidade pertence a um Estado, compondo a estrutura geográfica
 * utilizada para cadastro de endereços de clientes e demais operações
 * relacionadas à localização.
 * </p>
 *
 * <p>
 * Esta entidade é mapeada para a tabela {@code cidade} no banco de dados
 * e possui um relacionamento {@code ManyToOne} com a entidade {@code Estado}.
 * </p>
 *
 * <p>
 * <strong>Responsabilidades no domínio:</strong>
 * </p>
 * <ul>
 * <li>Representar uma unidade geográfica municipal</li>
 * <li>Manter vínculo obrigatório com um Estado</li>
 * <li>Servir como referência para composições de endereço</li>
 * </ul>
 *
 * <p>
 * A igualdade e o hashCode são baseados exclusivamente no identificador,
 * seguindo boas práticas para entidades JPA.
 * </p>
 */
@Entity(name = "cidade")
public class Cidade implements Serializable {

  /**
   * Identificador de versão para controle de serialização.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Identificador único da cidade.
   *
   * <p>
   * Gerado automaticamente pelo banco de dados.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Nome da cidade.
   */
  private String nome;

  /**
   * Estado ao qual a cidade pertence.
   *
   * <p>
   * Relacionamento {@code ManyToOne} indicando que várias cidades
   * podem pertencer a um mesmo estado.
   * </p>
   *
   * <p>
   * A anotação {@link JsonManagedReference} é utilizada para controle
   * de serialização JSON em conjunto com {@code JsonBackReference}
   * na entidade {@code Estado}, evitando recursividade infinita.
   * </p>
   */
  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "estado_id")
  private Estado estado;

  /**
   * Construtor padrão exigido pela especificação JPA.
   */
  public Cidade() {
  }

  /**
   * Construtor para criação manual da entidade.
   *
   * @param id     identificador da cidade
   * @param nome   nome da cidade
   * @param estado estado ao qual a cidade pertence
   */
  public Cidade(Integer id, String nome, Estado estado) {
    this.id = id;
    this.nome = nome;
    this.estado = estado;
  }

  /**
   * Retorna o identificador da cidade.
   *
   * @return id da cidade
   */
  public Integer getId() {
    return id;
  }

  /**
   * Define o identificador da cidade.
   *
   * @param id identificador da cidade
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Retorna o nome da cidade.
   *
   * @return nome da cidade
   */
  public String getNome() {
    return nome;
  }

  /**
   * Define o nome da cidade.
   *
   * @param nome novo nome da cidade
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Retorna o estado associado à cidade.
   *
   * @return estado da cidade
   */
  public Estado getEstado() {
    return estado;
  }

  /**
   * Define o estado associado à cidade.
   *
   * @param estado estado da cidade
   */
  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  /**
   * Calcula o hashCode com base no identificador da entidade.
   *
   * @return valor hash da cidade
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /**
   * Compara esta cidade com outro objeto.
   *
   * <p>
   * Duas cidades são consideradas iguais quando possuem o mesmo
   * identificador.
   * </p>
   *
   * @param obj objeto a ser comparado
   * @return {@code true} se forem iguais, {@code false} caso contrário
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Cidade other = (Cidade) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}