package com.albertsilva.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertsilva.cursomc.domain.Pedido;

/**
 * Repositório de persistência da entidade {@link Pedido}.
 *
 * <p>
 * Como Aggregate Root do domínio de pedidos, esta interface
 * é responsável por centralizar as operações de acesso
 * ao ciclo de vida da entidade Pedido.
 * </p>
 *
 * <p>
 * Todos os relacionamentos associados (itens, pagamento, cliente)
 * são gerenciados através da entidade principal.
 * </p>
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}