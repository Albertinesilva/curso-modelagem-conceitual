package com.albertsilva.cursomc.dto.pedido.update;

/**
 * Data Transfer Object (DTO) responsável por representar
 * um item de pedido na operação de atualização.
 *
 * <p>
 * Permite ajuste de quantidade mantendo a referência
 * ao produto original.
 * </p>
 *
 * @param produtoId  Identificador do produto.
 * @param quantidade Nova quantidade do item.
 */
public record ItemPedidoUpdateRequest(
        Integer produtoId,
        Integer quantidade) {
}