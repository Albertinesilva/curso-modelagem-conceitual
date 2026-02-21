package com.albertsilva.cursomc.domain;

import java.util.Date;

import com.albertsilva.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;

/**
 * Entidade que representa a especialização de {@link Pagamento}
 * para pagamentos realizados via boleto bancário.
 *
 * <p>
 * Implementa a estratégia de herança {@code JOINED}, definida na classe base
 * {@link Pagamento}, permitindo a separação das informações específicas
 * deste tipo de pagamento em tabela própria no banco de dados.
 * </p>
 *
 * <p>
 * Contém informações relacionadas ao ciclo de vida do boleto:
 * data de vencimento e data efetiva de pagamento.
 * </p>
 *
 * @author Albert Silva
 */
@Entity(name = "pagamento_com_boleto")
public class PagamentoComBoleto extends Pagamento {

  private final static long serialVersionUID = 1L;

  /**
   * Data de vencimento do boleto.
   *
   * <p>
   * Utiliza o padrão {@code dd/MM/yyyy} para serialização JSON.
   * </p>
   */
  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date dataVencimento;

  /**
   * Data em que o pagamento do boleto foi efetivamente realizado.
   *
   * <p>
   * Utiliza o padrão {@code dd/MM/yyyy} para serialização JSON.
   * Pode ser {@code null} enquanto o boleto não estiver pago.
   * </p>
   */
  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date dataPagamento;

  /**
   * Construtor padrão exigido pela JPA.
   */
  public PagamentoComBoleto() {
  }

  /**
   * Construtor completo da entidade.
   *
   * @param id             identificador do pagamento
   * @param estado         estado atual do pagamento
   * @param pedido         pedido associado ao pagamento
   * @param dataVencimento data de vencimento do boleto
   * @param dataPagamento  data efetiva de pagamento
   */
  public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido,
      Date dataVencimento, Date dataPagamento) {
    super(id, estado, pedido);
    this.dataVencimento = dataVencimento;
    this.dataPagamento = dataPagamento;
  }

  /**
   * Retorna a data de vencimento do boleto.
   *
   * @return data de vencimento
   */
  public Date getDataVencimento() {
    return dataVencimento;
  }

  /**
   * Define a data de vencimento do boleto.
   *
   * @param dataVencimento nova data de vencimento
   */
  public void setDataVencimento(Date dataVencimento) {
    this.dataVencimento = dataVencimento;
  }

  /**
   * Retorna a data efetiva de pagamento do boleto.
   *
   * @return data de pagamento ou {@code null} caso ainda não pago
   */
  public Date getDataPagamento() {
    return dataPagamento;
  }

  /**
   * Define a data efetiva de pagamento do boleto.
   *
   * @param dataPagamento nova data de pagamento
   */
  public void setDataPagamento(Date dataPagamento) {
    this.dataPagamento = dataPagamento;
  }

}