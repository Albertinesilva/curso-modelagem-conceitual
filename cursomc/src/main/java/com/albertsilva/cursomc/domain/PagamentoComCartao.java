package com.albertsilva.cursomc.domain;

import com.albertsilva.cursomc.domain.enums.EstadoPagamento;

import jakarta.persistence.Entity;

/**
 * Entidade que representa a especialização de {@link Pagamento}
 * para pagamentos realizados via cartão de crédito.
 *
 * <p>
 * Implementa a estratégia de herança {@code JOINED}, definida na classe base
 * {@link Pagamento}, permitindo armazenar atributos específicos
 * deste tipo de pagamento em tabela própria.
 * </p>
 *
 * <p>
 * Contém informações relacionadas ao parcelamento da compra.
 * </p>
 *
 * @author Albert Silva
 */
@Entity(name = "pagamento_com_cartao")
public class PagamentoComCartao extends Pagamento {

  private final static long serialVersionUID = 1L;

  /**
   * Número de parcelas em que o pagamento foi dividido.
   */
  private Integer numeroDeParcelas;

  /**
   * Construtor padrão exigido pela JPA.
   */
  public PagamentoComCartao() {
  }

  /**
   * Construtor completo da entidade.
   *
   * @param id               identificador do pagamento
   * @param estado           estado atual do pagamento
   * @param pedido           pedido associado ao pagamento
   * @param numeroDeParcelas quantidade de parcelas
   */
  public PagamentoComCartao(Integer id, EstadoPagamento estado,
      Pedido pedido, Integer numeroDeParcelas) {
    super(id, estado, pedido);
    this.numeroDeParcelas = numeroDeParcelas;
  }

  /**
   * Retorna o número de parcelas do pagamento.
   *
   * @return quantidade de parcelas
   */
  public Integer getNumeroDeParcelas() {
    return numeroDeParcelas;
  }

  /**
   * Define o número de parcelas do pagamento.
   *
   * @param numeroDeParcelas nova quantidade de parcelas
   */
  public void setNumeroDeParcelas(Integer numeroDeParcelas) {
    this.numeroDeParcelas = numeroDeParcelas;
  }

}