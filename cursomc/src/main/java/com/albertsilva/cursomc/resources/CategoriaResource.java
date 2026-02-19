package com.albertsilva.cursomc.resources;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.albertsilva.cursomc.domain.Categoria;
import com.albertsilva.cursomc.dto.categoria.request.CategoriaRequestDTO;
import com.albertsilva.cursomc.dto.categoria.response.CategoriaResponseDTO;
import com.albertsilva.cursomc.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

  private final CategoriaService categoriaService;

  public CategoriaResource(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<CategoriaResponseDTO> insert(@Valid @RequestBody CategoriaRequestDTO dto) {

    Categoria categoria = categoriaService.fromRequestDTO(dto);
    categoria = categoriaService.insert(categoria);

    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(categoria.getId())
        .toUri();

    return ResponseEntity.created(uri).body(categoriaService.toResponseDTO(categoria));
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Page<CategoriaResponseDTO>> findAllPaged(Pageable pageable) {
    return ResponseEntity.ok(categoriaService.findAllPaged(pageable));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(categoriaService.findById(id));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Integer id,
      @Valid @RequestBody CategoriaRequestDTO dto) {

    Categoria categoria = categoriaService.fromRequestDTO(dto);
    categoria = categoriaService.update(id, categoria);

    return ResponseEntity.ok().body(categoriaService.toResponseDTO(categoria));
  }

}
