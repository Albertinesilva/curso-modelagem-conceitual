package com.albertsilva.cursomc.domain;

import com.albertsilva.cursomc.domain.enums.EstadoPagamento;

import jakarta.persistence.Entity;

@Entity(name = "pagamento_com_cartao")
public class PagamentoComCartao extends Pagamento {
  private final static long serialVersionUID = 1L;

  private Integer numeroDeParcelas;

  public PagamentoComCartao() {
  }

  public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
    super(id, estado, pedido);
    this.numeroDeParcelas = numeroDeParcelas;
  }

  public Integer getNumeroDeParcelas() {
    return numeroDeParcelas;
  }

  public void setNumeroDeParcelas(Integer numeroDeParcelas) {
    this.numeroDeParcelas = numeroDeParcelas;
  }

}
