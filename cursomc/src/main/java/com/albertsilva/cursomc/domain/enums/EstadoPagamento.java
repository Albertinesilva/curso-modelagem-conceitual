package com.albertsilva.cursomc.domain.enums;

/**
 * Enumeração que representa os possíveis estados de um {@code Pagamento}
 * dentro do domínio da aplicação.
 *
 * <p>
 * Cada estado possui:
 * <ul>
 * <li>Um código numérico persistível em banco de dados</li>
 * <li>Uma descrição textual para exibição</li>
 * </ul>
 *
 * <p>
 * Esta enumeração é utilizada como camada de abstração entre o domínio
 * e a representação numérica armazenada na base de dados, garantindo
 * tipagem forte e segurança na manipulação dos estados do pagamento.
 *
 * <p>
 * <strong>Estados disponíveis:</strong>
 * </p>
 * <ul>
 * <li>{@link #PENDENTE} – Pagamento criado, mas ainda não confirmado</li>
 * <li>{@link #QUITADO} – Pagamento concluído com sucesso</li>
 * <li>{@link #CANCELADO} – Pagamento cancelado</li>
 * </ul>
 *
 * @author
 */
public enum EstadoPagamento {

  PENDENTE(1, "Pendente"),
  QUITADO(2, "Quitado"),
  CANCELADO(3, "Cancelado");

  /**
   * Código numérico utilizado para persistência no banco de dados.
   */
  private Integer cod;

  /**
   * Descrição textual do estado do pagamento.
   */
  private String descricao;

  /**
   * Construtor da enumeração.
   *
   * @param cod       código numérico representativo do estado
   * @param descricao descrição textual do estado
   */
  private EstadoPagamento(Integer cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  /**
   * Retorna o código numérico associado ao estado.
   *
   * @return código do estado
   */
  public Integer getCod() {
    return cod;
  }

  /**
   * Retorna a descrição textual do estado.
   *
   * @return descrição do estado
   */
  public String getDescricao() {
    return descricao;
  }

  /**
   * Converte um código numérico em seu respectivo {@code EstadoPagamento}.
   *
   * <p>
   * Este método é normalmente utilizado durante a conversão de dados
   * vindos da base de dados ou da camada de transporte (DTO).
   * </p>
   *
   * @param cod código numérico do estado
   * @return estado correspondente ao código informado
   * @throws IllegalArgumentException caso o código não corresponda a nenhum
   *                                  estado válido
   */
  public static EstadoPagamento toEnum(Integer cod) {
    if (cod == null) {
      return null;
    }

    for (EstadoPagamento x : EstadoPagamento.values()) {
      if (cod.equals(x.getCod())) {
        return x;
      }
    }

    throw new IllegalArgumentException("Id inválido: " + cod);
  }
}