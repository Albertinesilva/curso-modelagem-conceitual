package com.albertsilva.cursomc.dto.cliente.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.albertsilva.cursomc.domain.Cidade;
import com.albertsilva.cursomc.domain.Cliente;
import com.albertsilva.cursomc.domain.Endereco;
import com.albertsilva.cursomc.domain.enums.TipoCliente;
import com.albertsilva.cursomc.dto.cliente.request.ClienteInsertRequest;
import com.albertsilva.cursomc.dto.cliente.response.ClienteResponse;
import com.albertsilva.cursomc.dto.cliente.response.EnderecoResponse;

/**
 * Componente responsável pelo mapeamento entre a entidade {@link Cliente}
 * e seus respectivos objetos de transferência de dados (DTOs).
 *
 * <p>
 * Centraliza a lógica de conversão entre a camada de API e o domínio,
 * garantindo desacoplamento estrutural e aderência ao princípio
 * da responsabilidade única (SRP).
 * </p>
 *
 * <h2>Conversões suportadas</h2>
 * <ul>
 * <li>{@link ClienteInsertRequest} → {@link Cliente}</li>
 * <li>{@link Cliente} → {@link ClienteResponse}</li>
 * </ul>
 */
@Component
public class ClienteMapper {

  /**
   * Converte um {@link ClienteInsertRequest} em uma nova instância
   * da entidade {@link Cliente}.
   *
   * <p>
   * O método:
   * </p>
   * <ul>
   * <li>Cria o cliente utilizando o método de fábrica
   * {@code Cliente.create(...)}.</li>
   * <li>Converte o código do tipo para {@link TipoCliente}.</li>
   * <li>Adiciona os telefones informados.</li>
   * <li>Cria e associa um {@link Endereco} vinculado à {@link Cidade}
   * informada.</li>
   * </ul>
   *
   * @param dto    objeto contendo os dados da requisição
   * @param cidade cidade previamente carregada do domínio
   * @return nova instância de {@link Cliente} pronta para persistência
   */
  public Cliente fromInsertRequest(ClienteInsertRequest dto, Cidade cidade) {

    Cliente cliente = Cliente.create(
        dto.nome(),
        dto.email(),
        dto.cpfOuCnpj(),
        TipoCliente.toEnum(dto.tipo()));

    dto.telefones().forEach(cliente::addTelefone);

    Endereco endereco = new Endereco(
        null,
        dto.logradouro(),
        dto.numero(),
        dto.complemento(),
        dto.bairro(),
        dto.cep(),
        null,
        cidade);

    cliente.addEndereco(endereco);

    return cliente;
  }

  /**
   * Converte uma entidade {@link Cliente} em {@link ClienteResponse},
   * incluindo seus endereços associados.
   *
   * @param cliente entidade a ser convertida
   * @return DTO contendo os dados consolidados do cliente
   */
  public ClienteResponse toResponse(Cliente cliente) {

    List<EnderecoResponse> enderecos = cliente.getEnderecos()
        .stream()
        .map(this::toEnderecoResponse)
        .toList();

    return new ClienteResponse(
        cliente.getId(),
        cliente.getNome(),
        cliente.getEmail(),
        cliente.getCpfOuCnpj(),
        cliente.getTipo().getCod(),
        cliente.getTelefones(),
        enderecos);
  }

  /**
   * Converte uma entidade {@link Endereco} em {@link EnderecoResponse}.
   *
   * <p>
   * Método auxiliar utilizado exclusivamente pelo mapper para
   * conversão interna de coleções.
   * </p>
   *
   * @param e entidade de endereço
   * @return DTO representando o endereço
   */
  private EnderecoResponse toEnderecoResponse(Endereco e) {
    return new EnderecoResponse(
        e.getId(),
        e.getLogradouro(),
        e.getNumero(),
        e.getComplemento(),
        e.getBairro(),
        e.getCep(),
        e.getCidade().getId());
  }
}