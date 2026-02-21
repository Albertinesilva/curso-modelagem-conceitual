package com.albertsilva.cursomc.resources;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.albertsilva.cursomc.dto.categoria.request.CategoriaInsertRequest;
import com.albertsilva.cursomc.dto.categoria.response.CategoriaResponse;
import com.albertsilva.cursomc.services.CategoriaService;

import jakarta.validation.Valid;

/**
 * Controller REST responsável pela exposição dos endpoints
 * relacionados ao recurso Categoria.
 *
 * <p>
 * Atua como camada de interface HTTP da aplicação,
 * delegando as regras de negócio para {@link CategoriaService}.
 * </p>
 *
 * <p>
 * Segue o padrão RESTful para operações CRUD:
 * </p>
 * <ul>
 * <li>POST → Criar</li>
 * <li>GET → Consultar</li>
 * <li>PUT → Atualizar</li>
 * <li>DELETE → Remover</li>
 * </ul>
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

  private final CategoriaService categoriaService;

  /**
   * Construtor com injeção de dependência.
   */
  public CategoriaResource(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  /**
   * Cria uma nova categoria.
   *
   * @param dto dados de entrada validados
   * @return 201 Created com URI do novo recurso
   */
  @PostMapping
  public ResponseEntity<CategoriaResponse> insert(@Valid @RequestBody CategoriaInsertRequest dto) {
    CategoriaResponse response = categoriaService.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(response.id()).toUri();
    return ResponseEntity.created(uri).body(response);
  }

  /**
   * Retorna uma listagem paginada de categorias.
   *
   * @param pageable parâmetros de paginação
   * @return página de categorias
   */
  @GetMapping
  public ResponseEntity<Page<CategoriaResponse>> findAllPaged(Pageable pageable) {
    return ResponseEntity.ok(categoriaService.findAllPaged(pageable));
  }

  /**
   * Busca uma categoria pelo identificador.
   *
   * @param id identificador da categoria
   * @return categoria correspondente
   */
  @GetMapping("/{id}")
  public ResponseEntity<CategoriaResponse> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(categoriaService.findById(id));
  }

  /**
   * Atualiza uma categoria existente.
   *
   * @param id  identificador da categoria
   * @param dto dados atualizados
   * @return categoria atualizada
   */
  @PutMapping("/{id}")
  public ResponseEntity<CategoriaResponse> update(@PathVariable Integer id,
      @Valid @RequestBody CategoriaInsertRequest dto) {
    return ResponseEntity.ok().body(categoriaService.update(id, dto));
  }

  /**
   * Remove uma categoria.
   *
   * @param id identificador da categoria
   * @return 204 No Content
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    categoriaService.delete(id);
    return ResponseEntity.noContent().build();
  }
}