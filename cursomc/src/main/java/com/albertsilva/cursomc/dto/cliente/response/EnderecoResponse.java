package com.albertsilva.cursomc.dto.cliente.response;

/**
 * Data Transfer Object (DTO) responsável por representar
 * os dados de saída de um Endereço associado ao Cliente.
 *
 * <p>
 * Utilizado como componente interno do {@link ClienteResponse},
 * garantindo separação entre modelo de domínio e camada de API.
 * </p>
 *
 * @param id          Identificador único do endereço.
 * @param logradouro  Logradouro do endereço.
 * @param numero      Número do endereço.
 * @param complemento Complemento do endereço.
 * @param bairro      Bairro do endereço.
 * @param cep         Código de Endereçamento Postal.
 * @param cidadeId    Identificador da cidade associada.
 */
public record EnderecoResponse(
        Integer id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        Integer cidadeId) {
}