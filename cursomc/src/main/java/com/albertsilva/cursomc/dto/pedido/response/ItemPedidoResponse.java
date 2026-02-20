package com.albertsilva.cursomc.dto.pedido.response;

public record ItemPedidoResponse(
    String produtoNome,
    Integer quantidade,
    Double preco,
    Double subtotal) {
}