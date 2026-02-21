package com.albertsilva.cursomc.dto.pedido.response;

/**
 * Data Transfer Object (DTO) responsável por representar
 * os dados de saída de um item de pedido.
 *
 * <p>
 * Inclui informações consolidadas para apresentação,
 * como nome do produto e subtotal calculado.
 * </p>
 *
 * @param produtoNome Nome do produto.
 * @param quantidade  Quantidade adquirida.
 * @param preco       Preço unitário aplicado no momento da compra.
 * @param subtotal    Valor total do item (quantidade × preço).
 */
public record ItemPedidoResponse(
        String produtoNome,
        Integer quantidade,
        Double preco,
        Double subtotal) {
}