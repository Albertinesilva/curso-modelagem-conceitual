package com.albertsilva.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.cursomc.domain.Cliente;

/**
 * Repositório responsável pela persistência da entidade {@link Cliente}.
 *
 * <p>
 * Implementa operações de CRUD, paginação e consultas derivadas
 * através da infraestrutura do Spring Data JPA.
 * </p>
 *
 * <p>
 * O método {@link #findAll(Pageable)} foi sobrescrito para utilizar
 * {@link EntityGraph}, garantindo o carregamento antecipado (fetch eager)
 * dos relacionamentos {@code enderecos} e {@code enderecos.cidade}.
 * </p>
 *
 * <p>
 * Essa estratégia evita o problema de N+1 queries e melhora o desempenho
 * em cenários de listagem paginada.
 * </p>
 *
 * @see EntityGraph
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

  /**
   * Retorna uma página de clientes com os relacionamentos
   * {@code enderecos} e {@code cidade} previamente carregados.
   *
   * @param pageable objeto de paginação e ordenação
   * @return página contendo clientes com seus endereços
   */
  @Override
  @EntityGraph(attributePaths = { "enderecos", "enderecos.cidade" })
  Page<Cliente> findAll(Pageable pageable);
}