package com.albertsilva.cursomc.dto.pedido.request;

/**
 * Data Transfer Object (DTO) responsável por representar
 * um item de pedido na operação de criação.
 *
 * <p>
 * Contém apenas as informações necessárias para identificação
 * do produto e quantidade desejada.
 * </p>
 *
 * @param produtoId  Identificador do produto.
 * @param quantidade Quantidade solicitada do produto.
 */
public record ItemPedidoRequest(
        Integer produtoId,
        Integer quantidade) {
}