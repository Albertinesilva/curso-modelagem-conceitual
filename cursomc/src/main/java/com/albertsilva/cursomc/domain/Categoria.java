package com.albertsilva.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.albertsilva.cursomc.dto.categoria.request.CategoriaInsertRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

/**
 * Entidade que representa uma Categoria de produtos no domínio da aplicação.
 *
 * <p>
 * Uma categoria é responsável por agrupar produtos de acordo com
 * critérios de classificação de negócio (ex.: Eletrônicos, Informática,
 * Livros).
 * </p>
 *
 * <p>
 * Esta entidade é mapeada para a tabela {@code categoria} no banco de dados
 * e participa de um relacionamento {@code ManyToMany} com a entidade
 * {@code Produto}.
 * </p>
 *
 * <p>
 * <strong>Responsabilidades no domínio:</strong>
 * </p>
 * <ul>
 * <li>Representar um agrupamento lógico de produtos</li>
 * <li>Manter a integridade do relacionamento bidirecional com Produto</li>
 * <li>Permitir atualização controlada via DTO</li>
 * </ul>
 *
 * <p>
 * A igualdade e o hashCode são baseados exclusivamente no identificador,
 * respeitando a identidade da entidade conforme boas práticas de JPA.
 * </p>
 */
@Entity(name = "categoria")
public class Categoria implements Serializable {

  /**
   * Identificador de versão para controle de serialização.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Identificador único da categoria.
   *
   * <p>
   * Gerado automaticamente pelo banco de dados.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Nome da categoria.
   */
  private String nome;

  /**
   * Lista de produtos associados à categoria.
   *
   * <p>
   * Relacionamento bidirecional {@code ManyToMany} mapeado pelo atributo
   * {@code categorias} na entidade {@code Produto}.
   * </p>
   *
   * <p>
   * A anotação {@link JsonIgnore} evita recursividade infinita
   * durante a serialização JSON.
   * </p>
   */
  @JsonIgnore
  @ManyToMany(mappedBy = "categorias")
  private List<Produto> produtos = new ArrayList<>();

  /**
   * Construtor padrão exigido pela especificação JPA.
   */
  public Categoria() {
  }

  /**
   * Construtor para criação manual da entidade.
   *
   * @param id   identificador da categoria
   * @param nome nome da categoria
   */
  public Categoria(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  /**
   * Atualiza os dados da entidade com base em um DTO de inserção.
   *
   * <p>
   * Método utilizado para aplicar mudanças controladas no estado
   * da entidade sem expor regras de negócio ao controller.
   * </p>
   *
   * @param dto objeto contendo os novos dados da categoria
   */
  public void updateFrom(CategoriaInsertRequest dto) {
    this.nome = dto.nome();
  }

  /**
   * Retorna o identificador da categoria.
   *
   * @return id da categoria
   */
  public Integer getId() {
    return id;
  }

  /**
   * Define o identificador da categoria.
   *
   * @param id identificador da categoria
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Retorna o nome da categoria.
   *
   * @return nome da categoria
   */
  public String getNome() {
    return nome;
  }

  /**
   * Define o nome da categoria.
   *
   * @param nome novo nome da categoria
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Retorna a lista de produtos associados à categoria.
   *
   * @return lista de produtos
   */
  public List<Produto> getProdutos() {
    return produtos;
  }

  /**
   * Define a lista de produtos associados à categoria.
   *
   * @param produtos lista de produtos
   */
  public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
  }

  /**
   * Calcula o hashCode com base no identificador da entidade.
   *
   * @return valor hash da categoria
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /**
   * Compara esta categoria com outro objeto.
   *
   * <p>
   * Duas categorias são consideradas iguais quando possuem o mesmo
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
    Categoria other = (Categoria) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}