package com.albertsilva.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.cursomc.domain.Categoria;

/**
 * Repositório responsável pela persistência da entidade {@link Categoria}.
 *
 * <p>
 * Estende {@link JpaRepository}, herdando operações CRUD completas,
 * paginação, ordenação e mecanismos de consulta derivados de método.
 * </p>
 *
 * <p>
 * No contexto de Domain-Driven Design (DDD), atua como abstração
 * para acesso ao Aggregate Root {@code Categoria}, desacoplando
 * a camada de serviço da infraestrutura de persistência.
 * </p>
 *
 * @see JpaRepository
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}