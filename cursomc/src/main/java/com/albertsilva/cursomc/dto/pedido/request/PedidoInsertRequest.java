package com.albertsilva.cursomc.dto.pedido.request;

import java.util.Set;

public record PedidoInsertRequest(
    Integer clienteId,
    Integer enderecoEntregaId,
    Integer tipoPagamento,
    Integer numeroDeParcelas,
    Set<ItemPedidoRequest> itens) {
}