package com.albertsilva.cursomc.dto.pedido.request;

import java.util.Set;

/**
 * Data Transfer Object (DTO) responsável por representar
 * os dados necessários para criação de um Pedido.
 *
 * <p>
 * Contém referências às entidades relacionadas e
 * os itens que compõem o pedido.
 * </p>
 *
 * <p>
 * A conversão para a entidade {@code Pedido} é realizada
 * pelo {@code PedidoMapper}, após validação e carregamento
 * das entidades associadas.
 * </p>
 *
 * @param clienteId         Identificador do cliente.
 * @param enderecoEntregaId Identificador do endereço de entrega.
 * @param tipoPagamento     Código do tipo de pagamento.
 * @param numeroDeParcelas  Número de parcelas (quando aplicável).
 * @param itens             Conjunto de itens do pedido.
 */
public record PedidoInsertRequest(
        Integer clienteId,
        Integer enderecoEntregaId,
        Integer tipoPagamento,
        Integer numeroDeParcelas,
        Set<ItemPedidoRequest> itens) {
}