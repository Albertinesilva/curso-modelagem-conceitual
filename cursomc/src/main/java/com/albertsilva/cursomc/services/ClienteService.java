package com.albertsilva.cursomc.services;

import org.springframework.stereotype.Service;

import com.albertsilva.cursomc.domain.Cliente;
import com.albertsilva.cursomc.repositories.ClienteRepository;
import com.albertsilva.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;

  public ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  public Cliente buscar(Integer id) {
    return clienteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
        "Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
  }
}
