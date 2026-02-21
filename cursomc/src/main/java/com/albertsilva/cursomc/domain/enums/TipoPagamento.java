package com.albertsilva.cursomc.domain.enums;

/**
 * Enumeração que representa os tipos de pagamento suportados pelo domínio da
 * aplicação.
 *
 * <p>
 * Cada tipo de pagamento possui:
 * <ul>
 * <li>Um código numérico utilizado para persistência em banco de dados</li>
 * <li>Uma descrição textual para exibição em camadas externas (ex.: API,
 * UI)</li>
 * </ul>
 * </p>
 *
 * <p>
 * <strong>Tipos disponíveis:</strong>
 * </p>
 * <ul>
 * <li>{@link #CARTAO} – Pagamento realizado via cartão de crédito</li>
 * <li>{@link #BOLETO} – Pagamento realizado via boleto bancário</li>
 * </ul>
 *
 * <p>
 * Esta enumeração promove tipagem forte no domínio, evitando o uso direto
 * de valores numéricos no código e centralizando a regra de conversão
 * entre código persistido e representação em memória.
 * </p>
 */
public enum TipoPagamento {

  CARTAO(1, "Cartão de Crédito"),
  BOLETO(2, "Boleto");

  /**
   * Código numérico utilizado para representação persistente do tipo de
   * pagamento.
   */
  private Integer cod;

  /**
   * Descrição textual do tipo de pagamento.
   */
  private String descricao;

  /**
   * Construtor da enumeração.
   *
   * @param cod       código numérico identificador do tipo
   * @param descricao descrição textual do tipo de pagamento
   */
  private TipoPagamento(Integer cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  /**
   * Retorna o código numérico associado ao tipo de pagamento.
   *
   * @return código do tipo de pagamento
   */
  public Integer getCod() {
    return cod;
  }

  /**
   * Retorna a descrição textual do tipo de pagamento.
   *
   * @return descrição do tipo de pagamento
   */
  public String getDescricao() {
    return descricao;
  }

  /**
   * Converte um código numérico em seu respectivo {@code TipoPagamento}.
   *
   * <p>
   * Método utilitário normalmente utilizado na conversão de dados
   * provenientes da base de dados ou de objetos de transferência (DTOs).
   * </p>
   *
   * @param cod código numérico do tipo de pagamento
   * @return tipo de pagamento correspondente ao código informado
   * @throws IllegalArgumentException caso o código não corresponda
   *                                  a nenhum tipo válido
   */
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