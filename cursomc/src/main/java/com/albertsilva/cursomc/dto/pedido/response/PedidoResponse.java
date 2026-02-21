package com.albertsilva.cursomc.dto.pedido.response;

import java.util.Date;
import java.util.Set;

/**
 * Data Transfer Object (DTO) responsável por representar
 * os dados consolidados de um Pedido.
 *
 * <p>
 * Utilizado para exposição controlada de informações
 * ao consumidor da API.
 * </p>
 *
 * <p>
 * Contém informações básicas do pedido, estado do pagamento,
 * itens associados e valor total.
 * </p>
 *
 * @param id              Identificador único do pedido.
 * @param instante        Data e hora da criação do pedido.
 * @param clienteNome     Nome do cliente associado.
 * @param estadoPagamento Estado atual do pagamento.
 * @param itens           Conjunto de itens do pedido.
 * @param total           Valor total do pedido.
 */
public record PedidoResponse(
    Integer id,
    Date instante,
    String clienteNome,
    String estadoPagamento,
    Set<ItemPedidoResponse> itens,
    Double total) {
}