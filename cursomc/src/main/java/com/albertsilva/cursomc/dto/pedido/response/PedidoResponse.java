package com.albertsilva.cursomc.dto.pedido.response;

import java.util.Date;
import java.util.Set;

public record PedidoResponse(
        Integer id,
        Date instante,
        String clienteNome,
        String estadoPagamento,
        Set<ItemPedidoResponse> itens,
        Double total) {
}