package com.albertsilva.cursomc.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.albertsilva.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

  private final ClienteService clienteService;

  public ClienteResource(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> find(@PathVariable Integer id) {
    return ResponseEntity.ok().body(clienteService.buscar(id));
  }

}
