package com.albertsilva.cursomc.dto.categoria.mappper;

import org.springframework.stereotype.Component;

import com.albertsilva.cursomc.domain.Categoria;
import com.albertsilva.cursomc.dto.categoria.request.CategoriaInsertRequest;
import com.albertsilva.cursomc.dto.categoria.response.CategoriaResponse;

@Component
public class CategoriaMapper {

  public Categoria fromInsertRequest(CategoriaInsertRequest dto) {
    Categoria categoria = new Categoria();
    categoria.setNome(dto.nome());
    return categoria;
  }

  public CategoriaResponse toResponse(Categoria categoria) {
    return new CategoriaResponse(
        categoria.getId(),
        categoria.getNome());
  }

  public void updateEntityFromRequest(CategoriaInsertRequest dto, Categoria categoria) {
    categoria.setNome(dto.nome());
  }
}