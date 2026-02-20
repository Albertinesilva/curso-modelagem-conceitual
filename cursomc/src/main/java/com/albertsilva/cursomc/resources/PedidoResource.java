package com.albertsilva.cursomc.resources;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.albertsilva.cursomc.dto.pedido.request.PedidoInsertRequest;
import com.albertsilva.cursomc.dto.pedido.response.PedidoResponse;
import com.albertsilva.cursomc.dto.pedido.update.PedidoUpdateRequest;
import com.albertsilva.cursomc.services.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

  private final PedidoService pedidoService;

  public PedidoResource(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  @PostMapping
  public ResponseEntity<PedidoResponse> insert(@RequestBody PedidoInsertRequest dto) {
    PedidoResponse response = pedidoService.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
    return ResponseEntity.created(uri).body(response);
  }

  @GetMapping
  public ResponseEntity<Page<PedidoResponse>> findAllPaged(
      @PageableDefault(size = 20, sort = "instante") Pageable pageable) {
    return ResponseEntity.ok(pedidoService.findAllPaged(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PedidoResponse> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(pedidoService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PedidoResponse> update(@PathVariable Integer id, @Valid @RequestBody PedidoUpdateRequest dto) {
    return ResponseEntity.ok(pedidoService.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    pedidoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}