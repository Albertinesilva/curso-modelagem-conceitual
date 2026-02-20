package com.albertsilva.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

  @Override
  @EntityGraph(attributePaths = { "enderecos", "enderecos.cidade" })
  Page<Cliente> findAll(Pageable pageable);
}
