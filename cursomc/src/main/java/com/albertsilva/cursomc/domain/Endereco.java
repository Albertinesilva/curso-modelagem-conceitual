package com.albertsilva.cursomc.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Entidade que representa um Endereço no domínio da aplicação.
 *
 * <p>
 * O endereço compõe o agregado {@link Cliente}, sendo utilizado
 * para representar localizações físicas associadas ao cliente,
 * como endereço residencial, comercial ou de entrega.
 * </p>
 *
 * <p>
 * Esta entidade é mapeada para a tabela {@code endereco} e possui
 * relacionamentos com {@link Cliente} e {@link Cidade}.
 * </p>
 *
 * <p>
 * <strong>Responsabilidades no domínio:</strong>
 * </p>
 * <ul>
 * <li>Representar informações detalhadas de localização</li>
 * <li>Manter vínculo com o cliente proprietário do endereço</li>
 * <li>Associar-se a uma cidade para composição geográfica completa</li>
 * </ul>
 *
 * <p>
 * A identidade da entidade é baseada exclusivamente no campo {@code id},
 * conforme boas práticas para entidades JPA.
 * </p>
 */
@Entity(name = "endereco")
public class Endereco implements Serializable {

  /**
   * Identificador de versão para controle de serialização.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Identificador único do endereço.
   *
   * <p>
   * Gerado automaticamente pelo banco de dados.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Logradouro do endereço (rua, avenida, etc.).
   */
  private String logradouro;

  /**
   * Número do endereço.
   */
  private String numero;

  /**
   * Complemento do endereço (apartamento, bloco, etc.).
   */
  private String complemento;

  /**
   * Bairro do endereço.
   */
  private String bairro;

  /**
   * Código de Endereçamento Postal (CEP).
   */
  private String cep;

  /**
   * Cliente ao qual o endereço pertence.
   *
   * <p>
   * Relacionamento {@code ManyToOne}, indicando que vários endereços
   * podem pertencer a um mesmo cliente.
   * </p>
   *
   * <p>
   * A anotação {@link JsonBackReference} evita recursividade infinita
   * na serialização JSON, em conjunto com {@code JsonManagedReference}
   * definido na entidade {@link Cliente}.
   * </p>
   */
  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  /**
   * Cidade associada ao endereço.
   *
   * <p>
   * Relacionamento {@code ManyToOne}, indicando que vários endereços
   * podem estar vinculados a uma mesma cidade.
   * </p>
   */
  @ManyToOne
  @JoinColumn(name = "cidade_id")
  private Cidade cidade;

  /**
   * Construtor padrão exigido pela especificação JPA.
   */
  public Endereco() {
  }

  /**
   * Construtor para criação manual da entidade.
   *
   * @param id          identificador do endereço
   * @param logradouro  logradouro do endereço
   * @param numero      número do endereço
   * @param complemento complemento do endereço
   * @param bairro      bairro do endereço
   * @param cep         código postal
   * @param cliente     cliente proprietário do endereço
   * @param cidade      cidade associada
   */
  public Endereco(Integer id, String logradouro, String numero, String complemento, String bairro, String cep,
      Cliente cliente, Cidade cidade) {
    this.id = id;
    this.logradouro = logradouro;
    this.numero = numero;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cep = cep;
    this.cliente = cliente;
    this.cidade = cidade;
  }

  /**
   * Retorna o identificador do endereço.
   *
   * @return id do endereço
   */
  public Integer getId() {
    return id;
  }

  /**
   * Define o identificador do endereço.
   *
   * @param id identificador do endereço
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Retorna o logradouro do endereço.
   *
   * @return logradouro
   */
  public String getLogradouro() {
    return logradouro;
  }

  /**
   * Define o logradouro do endereço.
   *
   * @param logradouro logradouro
   */
  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  /**
   * Retorna o número do endereço.
   *
   * @return número do endereço
   */
  public String getNumero() {
    return numero;
  }

  /**
   * Define o número do endereço.
   *
   * @param numero número do endereço
   */
  public void setNumero(String numero) {
    this.numero = numero;
  }

  /**
   * Retorna o complemento do endereço.
   *
   * @return complemento do endereço
   */
  public String getComplemento() {
    return complemento;
  }

  /**
   * Define o complemento do endereço.
   *
   * @param complemento complemento do endereço
   */
  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  /**
   * Retorna o bairro do endereço.
   *
   * @return bairro
   */
  public String getBairro() {
    return bairro;
  }

  /**
   * Define o bairro do endereço.
   *
   * @param bairro bairro
   */
  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  /**
   * Retorna o CEP do endereço.
   *
   * @return código postal
   */
  public String getCep() {
    return cep;
  }

  /**
   * Define o CEP do endereço.
   *
   * @param cep código postal
   */
  public void setCep(String cep) {
    this.cep = cep;
  }

  /**
   * Retorna o cliente proprietário do endereço.
   *
   * @return cliente associado
   */
  public Cliente getCliente() {
    return cliente;
  }

  /**
   * Define o cliente proprietário do endereço.
   *
   * @param cliente cliente associado
   */
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  /**
   * Retorna a cidade associada ao endereço.
   *
   * @return cidade do endereço
   */
  public Cidade getCidade() {
    return cidade;
  }

  /**
   * Define a cidade associada ao endereço.
   *
   * @param cidade cidade do endereço
   */
  public void setCidade(Cidade cidade) {
    this.cidade = cidade;
  }

  /**
   * Calcula o hashCode com base no identificador da entidade.
   *
   * @return valor hash do endereço
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /**
   * Compara este endereço com outro objeto.
   *
   * <p>
   * Dois endereços são considerados iguais quando possuem o mesmo identificador.
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
    Endereco other = (Endereco) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}