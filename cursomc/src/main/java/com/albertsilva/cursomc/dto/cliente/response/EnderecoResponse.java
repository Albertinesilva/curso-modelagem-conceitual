package com.albertsilva.cursomc.dto.cliente.response;

public record EnderecoResponse(
    Integer id,
    String logradouro,
    String numero,
    String complemento,
    String bairro,
    String cep,
    Integer cidadeId) {
}