package com.albertsilva.cursomc.resources;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.albertsilva.cursomc.dto.cliente.request.ClienteInsertRequest;
import com.albertsilva.cursomc.dto.cliente.request.ClienteUpdateRequest;
import com.albertsilva.cursomc.dto.cliente.response.ClienteResponse;
import com.albertsilva.cursomc.services.ClienteService;

/**
 * Controller REST responsável pelos endpoints do recurso Cliente.
 *
 * <p>
 * Representa a camada de entrada HTTP da aplicação para operações
 * relacionadas à entidade Cliente.
 * </p>
 *
 * <p>
 * Implementa paginação padrão e separação entre DTO de criação
 * e DTO de atualização.
 * </p>
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

  private final ClienteService clienteService;

  /**
   * Construtor com injeção de dependência.
   */
  public ClienteResource(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  /**
   * Cria um novo cliente.
   *
   * @param dto dados para criação
   * @return 201 Created com URI do novo recurso
   */
  @PostMapping
  public ResponseEntity<ClienteResponse> insert(@RequestBody ClienteInsertRequest dto) {
    ClienteResponse response = clienteService.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(response.id()).toUri();
    return ResponseEntity.created(uri).body(response);
  }

  /**
   * Retorna listagem paginada de clientes.
   *
   * @param pageable parâmetros de paginação (default: 20 por página, ordenado por
   *                 nome)
   */
  @GetMapping
  public ResponseEntity<Page<ClienteResponse>> findAllPaged(
      @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
    return ResponseEntity.ok(clienteService.findAllPaged(pageable));
  }

  /**
   * Busca cliente por ID.
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<ClienteResponse> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(clienteService.findById(id));
  }

  /**
   * Atualiza dados de um cliente.
   */
  @PutMapping("/{id}")
  public ResponseEntity<ClienteResponse> update(@PathVariable Integer id,
      @RequestBody ClienteUpdateRequest dto) {
    return ResponseEntity.ok(clienteService.update(id, dto));
  }

  /**
   * Remove cliente pelo ID.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    clienteService.delete(id);
    return ResponseEntity.noContent().build();
  }
}