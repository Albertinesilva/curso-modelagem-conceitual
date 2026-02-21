package com.albertsilva.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.cursomc.domain.Produto;

/**
 * Repositório responsável pela entidade {@link Produto}.
 *
 * <p>
 * Fornece operações de persistência baseadas na abstração
 * {@link JpaRepository}, permitindo integração transparente
 * com o mecanismo ORM (Hibernate).
 * </p>
 *
 * <p>
 * Pode ser estendido futuramente com métodos de consulta
 * específicos, como busca por nome, categoria ou faixa de preço.
 * </p>
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}