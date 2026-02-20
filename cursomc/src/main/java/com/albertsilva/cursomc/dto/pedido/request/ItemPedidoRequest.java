package com.albertsilva.cursomc.dto.pedido.request;

public record ItemPedidoRequest(
    Integer produtoId,
    Integer quantidade) {
}