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

@Component
public class ClienteMapper {

  public Cliente fromInsertRequest(ClienteInsertRequest dto, Cidade cidade) {

    Cliente cliente = Cliente.create(dto.nome(), dto.email(), dto.cpfOuCnpj(), TipoCliente.toEnum(dto.tipo()));

    dto.telefones().forEach(cliente::addTelefone);

    Endereco endereco = new Endereco(null, dto.logradouro(), dto.numero(), dto.complemento(), dto.bairro(), dto.cep(),
        null, cidade);

    cliente.addEndereco(endereco);

    return cliente;
  }

  public ClienteResponse toResponse(Cliente cliente) {

    List<EnderecoResponse> enderecos = cliente.getEnderecos().stream().map(this::toEnderecoResponse).toList();

    return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpfOuCnpj(),
        cliente.getTipo().getCod(), cliente.getTelefones(), enderecos);
  }

  private EnderecoResponse toEnderecoResponse(Endereco e) {
    return new EnderecoResponse(e.getId(), e.getLogradouro(), e.getNumero(), e.getComplemento(), e.getBairro(),
        e.getCep(), e.getCidade().getId());
  }
}