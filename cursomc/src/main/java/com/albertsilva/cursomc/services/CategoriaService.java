package com.albertsilva.cursomc.services;

import org.springframework.stereotype.Service;

import com.albertsilva.cursomc.domain.Categoria;
import com.albertsilva.cursomc.repositories.CategoriaRepository;
import com.albertsilva.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public Categoria buscar(Integer id) {
    return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
        "Categoria não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
  }
}
