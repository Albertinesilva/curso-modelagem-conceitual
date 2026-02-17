package com.albertsilva.cursomc.domain.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoCliente {
  PESSOAFISICA(1, "Pessoa Física"),
  PESSOAJURIDICA(2, "Pessoa Jurídica");

  private int cod;
  private String descricao;

  private TipoCliente(int cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  public int getCod() {
    return cod;
  }

  @JsonValue
  public String getDescricao() {
    return descricao;
  }

  public static TipoCliente toEnum(Integer cod) {
    if (cod == null) {
      return null;
    }

    return Arrays.stream(TipoCliente.values())
        .filter(x -> cod.equals(x.getCod()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + cod));

    // for (TipoCliente x : TipoCliente.values()) {
    // if (cod.equals(x.getCod())) {
    // return x;
    // }
    // }
    // throw new IllegalArgumentException("Id inválido: " + cod);
  }
}
