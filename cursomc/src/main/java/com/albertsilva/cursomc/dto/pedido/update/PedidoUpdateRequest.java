package com.albertsilva.cursomc.dto.pedido.update;

import java.util.Set;

public record PedidoUpdateRequest(
    Integer clienteId,
    Integer enderecoId,
    Integer estadoPagamento,
    Set<ItemPedidoUpdateRequest> itens) {
}