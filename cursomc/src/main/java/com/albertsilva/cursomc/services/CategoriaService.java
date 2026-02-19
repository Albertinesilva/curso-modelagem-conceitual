package com.albertsilva.cursomc.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.albertsilva.cursomc.domain.Categoria;
import com.albertsilva.cursomc.dto.categoria.request.CategoriaRequestDTO;
import com.albertsilva.cursomc.dto.categoria.response.CategoriaResponseDTO;
import com.albertsilva.cursomc.repositories.CategoriaRepository;
import com.albertsilva.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  @Transactional
  public Categoria insert(Categoria obj) {
    obj.setId(null);
    return categoriaRepository.save(obj);
  }

  @Transactional(readOnly = true)
  public Page<CategoriaResponseDTO> findAllPaged(Pageable pageable) {

    Page<Categoria> page = categoriaRepository.findAll(pageable);

    return page.map(this::toResponseDTO);
  }

  @Transactional(readOnly = true)
  public CategoriaResponseDTO findById(Integer id) {
    Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
        "Categoria não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    return toResponseDTO(categoria);
  }

  @Transactional
  public Categoria update(Integer id, Categoria obj) {
    Categoria entity = categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
        "Categoria não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    updateData(entity, obj);
    return categoriaRepository.save(entity);
  }

  @Transactional
  public void delete(Integer id) {
    Categoria obj = categoriaRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));

    categoriaRepository.delete(obj);
  }

  private void updateData(Categoria entity, Categoria obj) {
    entity.setNome(obj.getNome());
  }

  public Categoria fromRequestDTO(CategoriaRequestDTO dto) {
    Categoria categoria = new Categoria();
    categoria.setNome(dto.nome());
    return categoria;
  }

  public CategoriaResponseDTO toResponseDTO(Categoria categoria) {
    return new CategoriaResponseDTO(
        categoria.getId(),
        categoria.getNome());
  }

}
