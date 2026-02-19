package com.albertsilva.cursomc.resources;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertsilva.cursomc.dto.cliente.request.ClienteInsertRequest;
import com.albertsilva.cursomc.dto.cliente.response.ClienteResponse;
import com.albertsilva.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

  private final ClienteService clienteService;

  public ClienteResource(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @PostMapping
  public ResponseEntity<ClienteResponse> insert(@RequestBody ClienteInsertRequest dto) {
    ClienteResponse response = clienteService.insert(dto);
    URI uri = URI.create("/clientes/" + response.id());
    return ResponseEntity.created(uri).body(response);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(clienteService.buscar(id));
  }

}
