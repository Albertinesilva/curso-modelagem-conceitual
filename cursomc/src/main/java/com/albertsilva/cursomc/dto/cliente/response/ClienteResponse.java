package com.albertsilva.cursomc.dto.cliente.response;

import java.util.List;
import java.util.Set;

/**
 * Data Transfer Object (DTO) responsável por representar
 * os dados de saída da entidade Cliente.
 *
 * <p>
 * Utilizado para exposição controlada de informações
 * ao consumidor da API.
 * </p>
 *
 * <p>
 * Inclui dados pessoais, tipo do cliente, telefones
 * e lista de endereços associados.
 * </p>
 *
 * @param id        Identificador único do cliente.
 * @param nome      Nome do cliente.
 * @param email     E-mail do cliente.
 * @param cpfOuCnpj Documento fiscal do cliente.
 * @param tipo      Código do tipo de cliente.
 * @param telefones Conjunto de telefones associados.
 * @param enderecos Lista de endereços vinculados ao cliente.
 */
public record ClienteResponse(
        Integer id,
        String nome,
        String email,
        String cpfOuCnpj,
        Integer tipo,
        Set<String> telefones,
        List<EnderecoResponse> enderecos) {
}