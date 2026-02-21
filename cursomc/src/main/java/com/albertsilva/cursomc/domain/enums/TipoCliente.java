package com.albertsilva.cursomc.domain.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeração que representa os tipos de cliente existentes no domínio da
 * aplicação.
 *
 * <p>
 * Cada tipo possui:
 * <ul>
 * <li>Um código numérico utilizado para persistência em banco de dados</li>
 * <li>Uma descrição textual utilizada para exibição e serialização JSON</li>
 * </ul>
 *
 * <p>
 * Esta enumeração garante tipagem forte no domínio, evitando o uso direto
 * de valores numéricos "mágicos" no código.
 * </p>
 *
 * <p>
 * <strong>Tipos disponíveis:</strong>
 * </p>
 * <ul>
 * <li>{@link #PESSOAFISICA} – Representa cliente pessoa física</li>
 * <li>{@link #PESSOAJURIDICA} – Representa cliente pessoa jurídica</li>
 * </ul>
 *
 * <p>
 * A anotação {@link JsonValue} aplicada ao método {@code getDescricao()}
 * define que, durante a serialização para JSON, será utilizada a descrição
 * textual do enum, e não o nome da constante.
 * </p>
 */
public enum TipoCliente {

  PESSOAFISICA(1, "Pessoa Física"),
  PESSOAJURIDICA(2, "Pessoa Jurídica");

  /**
   * Código numérico utilizado para persistência.
   */
  private int cod;

  /**
   * Descrição textual do tipo de cliente.
   */
  private String descricao;

  /**
   * Construtor da enumeração.
   *
   * @param cod       código numérico representativo do tipo
   * @param descricao descrição textual do tipo
   */
  private TipoCliente(int cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  /**
   * Retorna o código numérico associado ao tipo de cliente.
   *
   * @return código do tipo de cliente
   */
  public int getCod() {
    return cod;
  }

  /**
   * Retorna a descrição textual do tipo de cliente.
   *
   * <p>
   * Este método está anotado com {@link JsonValue}, fazendo com que
   * a descrição seja utilizada automaticamente durante a serialização
   * do enum para JSON.
   * </p>
   *
   * @return descrição do tipo de cliente
   */
  @JsonValue
  public String getDescricao() {
    return descricao;
  }

  /**
   * Converte um código numérico em seu respectivo {@code TipoCliente}.
   *
   * <p>
   * Este método é geralmente utilizado na conversão de dados vindos
   * da base de dados ou de camadas externas (DTOs).
   * </p>
   *
   * @param cod código numérico do tipo de cliente
   * @return tipo de cliente correspondente ao código informado
   * @throws IllegalArgumentException caso o código não corresponda
   *                                  a nenhum tipo válido
   */
  public static TipoCliente toEnum(Integer cod) {
    if (cod == null) {
      return null;
    }

    return Arrays.stream(TipoCliente.values())
        .filter(x -> cod.equals(x.getCod()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + cod));
  }
}