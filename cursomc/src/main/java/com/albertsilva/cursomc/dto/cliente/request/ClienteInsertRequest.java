package com.albertsilva.cursomc.dto.cliente.request;

import java.util.Set;

/**
 * Data Transfer Object (DTO) responsável por representar
 * os dados de entrada para criação de um Cliente.
 *
 * <p>
 * Este objeto concentra informações pessoais, dados fiscais,
 * telefones e endereço inicial do cliente.
 * </p>
 *
 * <p>
 * É utilizado na camada de API e posteriormente convertido
 * para a entidade de domínio {@code Cliente} através do mapper.
 * </p>
 *
 * @param nome        Nome completo do cliente.
 * @param email       Endereço de e-mail do cliente.
 * @param cpfOuCnpj   Documento fiscal (CPF ou CNPJ).
 * @param tipo        Código do tipo de cliente.
 * @param logradouro  Logradouro do endereço.
 * @param numero      Número do endereço.
 * @param complemento Complemento do endereço.
 * @param bairro      Bairro do endereço.
 * @param cep         CEP do endereço.
 * @param cidadeId    Identificador da cidade associada ao endereço.
 * @param telefones   Conjunto de telefones do cliente.
 */
public record ClienteInsertRequest(
        String nome,
        String email,
        String cpfOuCnpj,
        Integer tipo,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        Integer cidadeId,
        Set<String> telefones) {
}