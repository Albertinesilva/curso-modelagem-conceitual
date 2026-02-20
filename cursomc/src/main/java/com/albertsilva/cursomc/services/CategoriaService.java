package com.albertsilva.cursomc.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.albertsilva.cursomc.domain.Categoria;
import com.albertsilva.cursomc.dto.categoria.mappper.CategoriaMapper;
import com.albertsilva.cursomc.dto.categoria.request.CategoriaInsertRequest;
import com.albertsilva.cursomc.dto.categoria.response.CategoriaResponse;
import com.albertsilva.cursomc.repositories.CategoriaRepository;
import com.albertsilva.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;
  private final CategoriaMapper categoriaMapper;

  public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
    this.categoriaRepository = categoriaRepository;
    this.categoriaMapper = categoriaMapper;
  }

  @Transactional
  public CategoriaResponse insert(CategoriaInsertRequest dto) {
    Categoria categoria = categoriaMapper.fromRequest(dto);
    categoria = categoriaRepository.save(categoria);
    return categoriaMapper.toResponse(categoria);
  }

  @Transactional(readOnly = true)
  public Page<CategoriaResponse> findAllPaged(Pageable pageable) {
    return categoriaRepository.findAll(pageable).map(categoriaMapper::toResponse);
  }

  @Transactional(readOnly = true)
  public CategoriaResponse findById(Integer id) {
    Categoria categoria = findEntityById(id);
    return categoriaMapper.toResponse(categoria);
  }

  @Transactional
  public CategoriaResponse update(Integer id, CategoriaInsertRequest dto) {
    Categoria categoria = findEntityById(id);
    categoriaMapper.updateEntityFromRequest(dto, categoria);
    return categoriaMapper.toResponse(categoria);
  }

  @Transactional
  public void delete(Integer id) {
    Categoria obj = findEntityById(id);
    categoriaRepository.delete(obj);
  }

  private Categoria findEntityById(Integer id) {
    return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
        "Categoria não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
  }

}
