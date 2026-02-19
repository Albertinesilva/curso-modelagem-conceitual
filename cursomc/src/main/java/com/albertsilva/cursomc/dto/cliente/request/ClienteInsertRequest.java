package com.albertsilva.cursomc.dto.cliente.request;

import java.util.Set;

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
