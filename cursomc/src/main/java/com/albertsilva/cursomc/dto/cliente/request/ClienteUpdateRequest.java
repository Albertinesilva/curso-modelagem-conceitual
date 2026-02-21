package com.albertsilva.cursomc.dto.cliente.request;

/**
 * Data Transfer Object (DTO) utilizado para atualização
 * de dados básicos de um Cliente.
 *
 * <p>
 * Restrito a informações editáveis após a criação do cliente,
 * respeitando regras de negócio que impedem alteração de
 * atributos sensíveis como documento fiscal ou tipo.
 * </p>
 *
 * @param nome  Novo nome do cliente.
 * @param email Novo endereço de e-mail do cliente.
 */
public record ClienteUpdateRequest(
        String nome,
        String email) {
}