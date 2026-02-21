package com.albertsilva.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.cursomc.domain.Endereco;

/**
 * Repositório responsável pela manipulação da entidade {@link Endereco}.
 *
 * <p>
 * Disponibiliza operações básicas de persistência, remoção,
 * atualização e consulta por identificador.
 * </p>
 *
 * <p>
 * Em termos arquiteturais, isola a camada de domínio das
 * dependências diretas com a API de persistência.
 * </p>
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}