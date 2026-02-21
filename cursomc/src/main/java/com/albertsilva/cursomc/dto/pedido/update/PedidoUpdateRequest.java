package com.albertsilva.cursomc.dto.pedido.update;

import java.util.Set;

/**
 * Data Transfer Object (DTO) utilizado para atualização
 * de um Pedido existente.
 *
 * <p>
 * Permite alteração de:
 * </p>
 * <ul>
 * <li>Cliente associado;</li>
 * <li>Endereço de entrega;</li>
 * <li>Estado do pagamento;</li>
 * <li>Itens do pedido.</li>
 * </ul>
 *
 * <p>
 * A aplicação das alterações e validações de regras de negócio
 * devem ocorrer na camada de serviço.
 * </p>
 *
 * @param clienteId       Identificador do cliente.
 * @param enderecoId      Identificador do endereço.
 * @param estadoPagamento Código representando o novo estado do pagamento.
 * @param itens           Conjunto atualizado de itens do pedido.
 */
public record PedidoUpdateRequest(
        Integer clienteId,
        Integer enderecoId,
        Integer estadoPagamento,
        Set<ItemPedidoUpdateRequest> itens) {
}