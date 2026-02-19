package com.albertsilva.cursomc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.albertsilva.cursomc.domain.Cidade;
import com.albertsilva.cursomc.domain.Cliente;
import com.albertsilva.cursomc.domain.Endereco;
import com.albertsilva.cursomc.domain.enums.TipoCliente;
import com.albertsilva.cursomc.dto.cliente.request.ClienteInsertRequest;
import com.albertsilva.cursomc.dto.cliente.request.ClienteUpdateRequest;
import com.albertsilva.cursomc.dto.cliente.response.ClienteResponse;
import com.albertsilva.cursomc.dto.cliente.response.EnderecoResponse;
import com.albertsilva.cursomc.repositories.CidadeRepository;
import com.albertsilva.cursomc.repositories.ClienteRepository;
import com.albertsilva.cursomc.repositories.EnderecoRepository;
import com.albertsilva.cursomc.services.exceptions.ObjectNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;
  private final CidadeRepository cidadeRepository;
  private final EnderecoRepository enderecoRepository;

  public ClienteService(ClienteRepository clienteRepository, CidadeRepository cidadeRepository,
      EnderecoRepository enderecoRepository) {
    this.clienteRepository = clienteRepository;
    this.cidadeRepository = cidadeRepository;
    this.enderecoRepository = enderecoRepository;
  }

  @Transactional
  public ClienteResponse insert(ClienteInsertRequest dto) {

    Cliente cliente = new Cliente(null, dto.nome(), dto.email(), dto.cpfOuCnpj(), TipoCliente.toEnum(dto.tipo()));

    cliente.getTelefones().addAll(dto.telefones());

    Cidade cidade = cidadeRepository.findById(dto.cidadeId())
        .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada"));

    Endereco endereco = new Endereco(null, dto.logradouro(), dto.numero(), dto.complemento(), dto.bairro(), dto.cep(),
        cliente, cidade);

    cliente.getEnderecos().add(endereco);

    cliente = clienteRepository.save(cliente);
    enderecoRepository.save(endereco);

    return toResponse(cliente);
  }

  public Cliente findById(Integer id) {
    return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
        "Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
  }

  @Transactional
  public ClienteResponse update(Integer id, ClienteUpdateRequest dto) {

    Cliente cliente = clienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

    cliente.setNome(dto.nome());
    cliente.setEmail(dto.email());

    cliente = clienteRepository.save(cliente);

    return toResponse(cliente);
  }

  private ClienteResponse toResponse(Cliente cliente) {

    List<EnderecoResponse> enderecos = cliente.getEnderecos().stream().map(e -> new EnderecoResponse(
        e.getId(),
        e.getLogradouro(),
        e.getNumero(),
        e.getComplemento(),
        e.getBairro(),
        e.getCep(),
        e.getCidade().getId())).collect(Collectors.toList());

    return new ClienteResponse(cliente.getId(), cliente.getNome(),
        cliente.getEmail(),
        cliente.getCpfOuCnpj(),
        cliente.getTipo().getCod(),
        cliente.getTelefones(),
        enderecos);
  }
}
