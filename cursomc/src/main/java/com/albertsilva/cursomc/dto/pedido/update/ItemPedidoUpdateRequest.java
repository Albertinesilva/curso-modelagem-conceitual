package com.albertsilva.cursomc.dto.pedido.update;

public record ItemPedidoUpdateRequest(
    Integer produtoId,
    Integer quantidade) {
}