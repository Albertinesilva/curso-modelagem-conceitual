package com.albertsilva.cursomc.domain.enums;

public enum TipoPagamento {
  CARTAO(1, "Cartão de Crédito"),
  BOLETO(2, "Boleto");

  private Integer cod;
  private String descricao;

  private TipoPagamento(Integer cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  public Integer getCod() {
    return cod;
  }

  public String getDescricao() {
    return descricao;
  }

  public static TipoPagamento toEnum(Integer cod) {
    if (cod == null) {
      return null;
    }

    for (TipoPagamento x : TipoPagamento.values()) {
      if (cod.equals(x.getCod())) {
        return x;
      }
    }

    throw new IllegalArgumentException("Id inválido: " + cod);
  }
}
