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

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

  private final CategoriaService categoriaService;

  public CategoriaResource(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @PostMapping
  public ResponseEntity<CategoriaResponse> insert(@Valid @RequestBody CategoriaInsertRequest dto) {
    CategoriaResponse response = categoriaService.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.id()).toUri();
    return ResponseEntity.created(uri).body(response);
  }

  @GetMapping
  public ResponseEntity<Page<CategoriaResponse>> findAllPaged(Pageable pageable) {
    return ResponseEntity.ok(categoriaService.findAllPaged(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoriaResponse> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(categoriaService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoriaResponse> update(@PathVariable Integer id, @Valid @RequestBody CategoriaInsertRequest dto) {
    return ResponseEntity.ok().body(categoriaService.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    categoriaService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
