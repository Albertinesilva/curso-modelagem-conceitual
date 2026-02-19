package com.albertsilva.cursomc.dto.cliente.response;

import java.util.List;
import java.util.Set;

public record ClienteResponse(
    Integer id,
    String nome,
    String email,
    String cpfOuCnpj,
    Integer tipo,
    Set<String> telefones,
    List<EnderecoResponse> enderecos) {
}
