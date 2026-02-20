package com.albertsilva.cursomc.resources;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.albertsilva.cursomc.dto.cliente.request.ClienteInsertRequest;
import com.albertsilva.cursomc.dto.cliente.request.ClienteUpdateRequest;
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
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
    return ResponseEntity.created(uri).body(response);
  }

  @GetMapping
  public ResponseEntity<Page<ClienteResponse>> findAllPaged(@PageableDefault(size = 20, sort = "nome") Pageable pageable) {
    return ResponseEntity.ok(clienteService.findAllPaged(pageable));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ClienteResponse> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(clienteService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClienteResponse> update(@PathVariable Integer id, @RequestBody ClienteUpdateRequest dto) {
    return ResponseEntity.ok(clienteService.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    clienteService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
