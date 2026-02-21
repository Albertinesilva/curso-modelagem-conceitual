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

/**
 * Controller REST responsável pelos endpoints do recurso Pedido.
 *
 * <p>
 * Um Pedido representa uma agregação central do domínio
 * (Aggregate Root), sendo responsável por encapsular
 * seus itens, pagamento e regras de consistência.
 * </p>
 *
 * <p>
 * Esta classe expõe operações CRUD seguindo o padrão REST,
 * delegando as regras ao {@link PedidoService}.
 * </p>
 */
@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

  private final PedidoService pedidoService;

  /**
   * Construtor com injeção de dependência.
   */
  public PedidoResource(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  /**
   * Cria um novo pedido.
   *
   * @param dto dados para criação do pedido
   * @return 201 Created com URI do novo recurso
   */
  @PostMapping
  public ResponseEntity<PedidoResponse> insert(@RequestBody PedidoInsertRequest dto) {
    PedidoResponse response = pedidoService.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(response.id()).toUri();
    return ResponseEntity.created(uri).body(response);
  }

  /**
   * Retorna listagem paginada de pedidos.
   *
   * @param pageable paginação padrão (20 por página, ordenado por instante)
   */
  @GetMapping
  public ResponseEntity<Page<PedidoResponse>> findAllPaged(
      @PageableDefault(size = 20, sort = "instante") Pageable pageable) {
    return ResponseEntity.ok(pedidoService.findAllPaged(pageable));
  }

  /**
   * Busca pedido por ID.
   */
  @GetMapping("/{id}")
  public ResponseEntity<PedidoResponse> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(pedidoService.findById(id));
  }

  /**
   * Atualiza um pedido existente.
   *
   * @param id  identificador do pedido
   * @param dto dados atualizados
   */
  @PutMapping("/{id}")
  public ResponseEntity<PedidoResponse> update(@PathVariable Integer id,
      @Valid @RequestBody PedidoUpdateRequest dto) {
    return ResponseEntity.ok(pedidoService.update(id, dto));
  }

  /**
   * Remove pedido pelo ID.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    pedidoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}