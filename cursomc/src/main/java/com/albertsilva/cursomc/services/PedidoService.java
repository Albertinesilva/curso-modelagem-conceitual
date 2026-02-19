package com.albertsilva.cursomc.services;

import org.springframework.stereotype.Service;

import com.albertsilva.cursomc.domain.Pedido;
import com.albertsilva.cursomc.repositories.PedidoRepository;
import com.albertsilva.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

  private final PedidoRepository pedidoRepository;

  public PedidoService(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }

  public Pedido buscar(Integer id) {
    return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
        "Pedido não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
  }
}
