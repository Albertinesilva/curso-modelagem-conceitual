package com.albertsilva.cursomc.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.albertsilva.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

  private final CategoriaService categoriaService;

  public CategoriaResource(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<?> find(@PathVariable Integer id) {
    return ResponseEntity.ok().body(categoriaService.buscar(id));
  }

}
