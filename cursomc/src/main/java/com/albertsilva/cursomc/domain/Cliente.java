package com.albertsilva.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.albertsilva.cursomc.domain.enums.TipoCliente;
import com.albertsilva.cursomc.dto.cliente.request.ClienteUpdateRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * Entidade que representa um Cliente no domínio da aplicação.
 *
 * <p>
 * O cliente é um dos principais Aggregate Roots do contexto de vendas,
 * sendo responsável por manter seus dados cadastrais, endereços,
 * telefones e pedidos associados.
 * </p>
 *
 * <p>
 * Esta entidade é mapeada para a tabela {@code cliente} e possui
 * relacionamentos com {@code Endereco} e {@code Pedido}.
 * </p>
 *
 * <p>
 * <strong>Responsabilidades no domínio:</strong>
 * </p>
 * <ul>
 * <li>Representar os dados cadastrais do cliente</li>
 * <li>Gerenciar seus endereços e telefones</li>
 * <li>Manter associação com seus pedidos</li>
 * <li>Controlar o tipo de cliente (Pessoa Física ou Jurídica)</li>
 * </ul>
 *
 * <p>
 * A identidade da entidade é baseada exclusivamente no campo {@code id},
 * conforme boas práticas de modelagem com JPA.
 * </p>
 */
@Entity(name = "cliente")
public class Cliente implements Serializable {

  /**
   * Identificador de versão para controle de serialização.
   */
  private final static long serialVersionUID = 1L;

  /**
   * Identificador único do cliente.
   *
   * <p>
   * Gerado automaticamente pelo banco de dados.
   * </p>
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * Nome completo do cliente.
   */
  private String nome;

  /**
   * Endereço de e-mail do cliente.
   */
  private String email;

  /**
   * Documento de identificação do cliente (CPF ou CNPJ).
   */
  private String cpfOuCnpj;

  /**
   * Código numérico que representa o tipo do cliente.
   *
   * <p>
   * Internamente armazenado como {@code Integer} para persistência,
   * sendo convertido para {@link TipoCliente} via método {@code toEnum()}.
   * </p>
   */
  private Integer tipo;

  /**
   * Lista de endereços associados ao cliente.
   *
   * <p>
   * Relacionamento {@code OneToMany} com cascata total e remoção órfã,
   * indicando que o ciclo de vida do endereço depende do cliente.
   * </p>
   */
  @JsonManagedReference
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Endereco> enderecos = new ArrayList<>();

  /**
   * Conjunto de telefones do cliente.
   *
   * <p>
   * Persistido como {@code ElementCollection} em tabela própria
   * denominada {@code TELEFONE}.
   * </p>
   */
  @ElementCollection
  @CollectionTable(name = "TELEFONE")
  private Set<String> telefones = new HashSet<>();

  /**
   * Lista de pedidos realizados pelo cliente.
   *
   * <p>
   * Relacionamento {@code OneToMany} sem cascata,
   * pois o pedido possui ciclo de vida próprio.
   * </p>
   */
  @JsonBackReference
  @OneToMany(mappedBy = "cliente")
  private List<Pedido> pedidos = new ArrayList<>();

  /**
   * Construtor padrão exigido pela especificação JPA.
   */
  public Cliente() {
  }

  /**
   * Construtor para criação manual da entidade.
   *
   * @param id        identificador do cliente
   * @param nome      nome do cliente
   * @param email     e-mail do cliente
   * @param cpfOuCnpj documento do cliente
   * @param tipo      tipo do cliente
   */
  public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpfOuCnpj = cpfOuCnpj;
    this.tipo = (tipo == null) ? null : tipo.getCod();
  }

  /**
   * Método fábrica para criação de um novo cliente.
   *
   * @param nome      nome do cliente
   * @param email     e-mail do cliente
   * @param cpfOuCnpj documento do cliente
   * @param tipo      tipo do cliente
   * @return nova instância de {@code Cliente}
   */
  public static Cliente create(String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
    return new Cliente(null, nome, email, cpfOuCnpj, tipo);
  }

  /**
   * Atualiza os dados do cliente com base em um DTO de atualização.
   *
   * @param dto objeto contendo os novos dados do cliente
   */
  public void updateFrom(ClienteUpdateRequest dto) {
    this.nome = dto.nome();
    this.email = dto.email();
  }

  /**
   * Adiciona um endereço ao cliente.
   *
   * <p>
   * Garante a consistência do relacionamento bidirecional,
   * associando o cliente ao endereço.
   * </p>
   *
   * @param endereco endereço a ser adicionado
   */
  public void addEndereco(Endereco endereco) {
    endereco.setCliente(this);
    this.enderecos.add(endereco);
  }

  /**
   * Adiciona um telefone ao conjunto de telefones do cliente.
   *
   * @param telefone número de telefone
   */
  public void addTelefone(String telefone) {
    this.telefones.add(telefone);
  }

  /**
   * Retorna o identificador do cliente.
   *
   * @return id do cliente
   */
  public Integer getId() {
    return id;
  }

  /**
   * Define o identificador do cliente.
   *
   * @param id identificador do cliente
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Retorna o nome do cliente.
   *
   * @return nome do cliente
   */
  public String getNome() {
    return nome;
  }

  /**
   * Define o nome do cliente.
   *
   * @param nome nome do cliente
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Retorna o e-mail do cliente.
   *
   * @return e-mail do cliente
   */
  public String getEmail() {
    return email;
  }

  /**
   * Define o e-mail do cliente.
   *
   * @param email e-mail do cliente
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Retorna o documento (CPF ou CNPJ) do cliente.
   *
   * @return documento do cliente
   */
  public String getCpfOuCnpj() {
    return cpfOuCnpj;
  }

  /**
   * Define o documento (CPF ou CNPJ) do cliente.
   *
   * @param cpfOuCnpj documento do cliente
   */
  public void setCpfOuCnpj(String cpfOuCnpj) {
    this.cpfOuCnpj = cpfOuCnpj;
  }

  /**
   * Retorna o tipo do cliente convertido para {@link TipoCliente}.
   *
   * @return tipo do cliente
   */
  public TipoCliente getTipo() {
    return TipoCliente.toEnum(tipo);
  }

  /**
   * Define o tipo do cliente.
   *
   * @param tipo tipo do cliente
   */
  public void setTipo(TipoCliente tipo) {
    this.tipo = tipo.getCod();
  }

  /**
   * Retorna a lista de endereços do cliente.
   *
   * @return lista de endereços
   */
  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  /**
   * Define a lista de endereços do cliente.
   *
   * @param enderecos lista de endereços
   */
  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }

  /**
   * Retorna o conjunto de telefones do cliente.
   *
   * @return conjunto de telefones
   */
  public Set<String> getTelefones() {
    return telefones;
  }

  /**
   * Define o conjunto de telefones do cliente.
   *
   * @param telefones conjunto de telefones
   */
  public void setTelefones(Set<String> telefones) {
    this.telefones = telefones;
  }

  /**
   * Retorna a lista de pedidos do cliente.
   *
   * @return lista de pedidos
   */
  public List<Pedido> getPedidos() {
    return pedidos;
  }

  /**
   * Define a lista de pedidos do cliente.
   *
   * @param pedidos lista de pedidos
   */
  public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
  }

  /**
   * Calcula o hashCode com base no identificador da entidade.
   *
   * @return valor hash do cliente
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  /**
   * Compara este cliente com outro objeto.
   *
   * <p>
   * Dois clientes são considerados iguais quando possuem o mesmo identificador.
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
    Cliente other = (Cliente) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}