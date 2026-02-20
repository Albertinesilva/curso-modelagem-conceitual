package com.albertsilva.cursomc.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.albertsilva.cursomc.domain.Cidade;
import com.albertsilva.cursomc.domain.Cliente;
import com.albertsilva.cursomc.dto.cliente.mapper.ClienteMapper;
import com.albertsilva.cursomc.dto.cliente.request.ClienteInsertRequest;
import com.albertsilva.cursomc.dto.cliente.request.ClienteUpdateRequest;
import com.albertsilva.cursomc.dto.cliente.response.ClienteResponse;
import com.albertsilva.cursomc.repositories.CidadeRepository;
import com.albertsilva.cursomc.repositories.ClienteRepository;
import com.albertsilva.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;
  private final CidadeRepository cidadeRepository;
  private final ClienteMapper clienteMapper;

  public ClienteService(ClienteRepository clienteRepository, CidadeRepository cidadeRepository,
      ClienteMapper clienteMapper) {
    this.clienteRepository = clienteRepository;
    this.cidadeRepository = cidadeRepository;
    this.clienteMapper = clienteMapper;
  }

  @Transactional
  public ClienteResponse insert(ClienteInsertRequest dto) {

    // Cidade cidade = cidadeRepository.findById(dto.cidadeId())
    // .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada"));
    Cidade cidade = cidadeRepository.getReferenceById(dto.cidadeId());

    Cliente cliente = clienteMapper.fromInsertRequest(dto, cidade);

    cliente = clienteRepository.save(cliente);

    return clienteMapper.toResponse(cliente);
  }

  @Transactional(readOnly = true)
  public Page<ClienteResponse> findAllPaged(Pageable pageable) {
    return clienteRepository.findAll(pageable).map(clienteMapper::toResponse);
  }

  @Transactional(readOnly = true)
  public ClienteResponse findById(Integer id) {
    return clienteMapper.toResponse(findEntityById(id));
  }

  @Transactional
  public ClienteResponse update(Integer id, ClienteUpdateRequest dto) {
    Cliente cliente = findEntityById(id);
    cliente.updateFrom(dto);
    return clienteMapper.toResponse(cliente);
  }

  @Transactional
  public void delete(Integer id) {
    Cliente cliente = findEntityById(id);
    clienteRepository.delete(cliente);
  }

  private Cliente findEntityById(Integer id) {
    return clienteRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + id));
  }

}
