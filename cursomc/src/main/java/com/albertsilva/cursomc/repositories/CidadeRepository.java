package com.albertsilva.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.cursomc.domain.Cidade;

/**
 * Repositório de acesso a dados da entidade {@link Cidade}.
 *
 * <p>
 * Fornece operações padrão de persistência e recuperação de dados
 * utilizando Spring Data JPA.
 * </p>
 *
 * <p>
 * Atua como gateway para manipulação de cidades no banco de dados,
 * preservando o princípio da inversão de dependência ao ocultar
 * detalhes da implementação de persistência.
 * </p>
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}