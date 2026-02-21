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

/**
 * Serviço responsável pelas regras de negócio relacionadas à entidade
 * {@link Categoria}.
 *
 * <p>
 * Atua como camada intermediária entre o Controller e o Repository,
 * garantindo:
 * </p>
 * <ul>
 * <li>Orquestração de transações</li>
 * <li>Validação de existência de entidade</li>
 * <li>Conversão entre entidade e DTO</li>
 * </ul>
 *
 * <p>
 * A entidade {@link Categoria} é tratada como Aggregate Root no contexto
 * do domínio, sendo manipulada exclusivamente através deste serviço.
 * </p>
 */
@Service
public class CategoriaService {

  private final CategoriaRepository categoriaRepository;
  private final CategoriaMapper categoriaMapper;

  /**
   * Construtor com injeção de dependências via construtor.
   *
   * @param categoriaRepository repositório de persistência
   * @param categoriaMapper     responsável por conversão entidade ↔ DTO
   */
  public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
    this.categoriaRepository = categoriaRepository;
    this.categoriaMapper = categoriaMapper;
  }

  /**
   * Insere uma nova categoria.
   *
   * @param dto dados de entrada
   * @return representação da categoria persistida
   */
  @Transactional
  public CategoriaResponse insert(CategoriaInsertRequest dto) {
    Categoria categoria = categoriaMapper.fromInsertRequest(dto);
    categoria = categoriaRepository.save(categoria);
    return categoriaMapper.toResponse(categoria);
  }

  /**
   * Retorna categorias paginadas.
   *
   * @param pageable parâmetros de paginação e ordenação
   * @return página de categorias
   */
  @Transactional(readOnly = true)
  public Page<CategoriaResponse> findAllPaged(Pageable pageable) {
    return categoriaRepository.findAll(pageable).map(categoriaMapper::toResponse);
  }

  /**
   * Busca categoria por identificador.
   *
   * @param id identificador da categoria
   * @return categoria encontrada
   * @throws ObjectNotFoundException caso não exista
   */
  @Transactional(readOnly = true)
  public CategoriaResponse findById(Integer id) {
    Categoria categoria = findEntityById(id);
    return categoriaMapper.toResponse(categoria);
  }

  /**
   * Atualiza uma categoria existente.
   *
   * @param id  identificador
   * @param dto novos dados
   * @return categoria atualizada
   */
  @Transactional
  public CategoriaResponse update(Integer id, CategoriaInsertRequest dto) {
    Categoria categoria = findEntityById(id);
    categoriaMapper.updateEntityFromRequest(dto, categoria);
    return categoriaMapper.toResponse(categoria);
  }

  /**
   * Remove categoria por ID.
   *
   * @param id identificador da categoria
   */
  @Transactional
  public void delete(Integer id) {
    Categoria obj = findEntityById(id);
    categoriaRepository.delete(obj);
  }

  /**
   * Método interno para recuperação da entidade.
   *
   * @param id identificador
   * @return entidade persistida
   * @throws ObjectNotFoundException caso não exista
   */
  private Categoria findEntityById(Integer id) {
    return categoriaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
        "Categoria não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
  }
}