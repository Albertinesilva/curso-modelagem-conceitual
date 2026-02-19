package com.albertsilva.cursomc.dto.cliente.request;

public record ClienteUpdateRequest(
    String nome,
    String email) {
}