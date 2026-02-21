package com.albertsilva.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.cursomc.domain.Estado;

/**
 * Repositório responsável pela persistência da entidade {@link Estado}.
 *
 * <p>
 * Herda funcionalidades de acesso a dados da interface
 * {@link JpaRepository}, incluindo:
 * </p>
 *
 * <ul>
 * <li>Operações CRUD</li>
 * <li>Paginação e ordenação</li>
 * <li>Consultas derivadas por convenção de nome</li>
 * </ul>
 *
 * <p>
 * Representa o ponto de acesso à tabela de estados na base de dados.
 * </p>
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}