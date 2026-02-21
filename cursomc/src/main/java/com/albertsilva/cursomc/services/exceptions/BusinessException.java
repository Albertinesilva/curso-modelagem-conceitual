package com.albertsilva.cursomc.services.exceptions;

/**
 * Classe base abstrata para exceções de regra de negócio da aplicação.
 *
 * <p>
 * Representa falhas decorrentes de violações de regras do domínio
 * ou inconsistências operacionais que devem ser comunicadas à camada
 * de apresentação com um código HTTP apropriado.
 * </p>
 *
 * <p>
 * Essa classe encapsula:
 * </p>
 * <ul>
 * <li>Mensagem descritiva da falha</li>
 * <li>Status HTTP associado à exceção</li>
 * </ul>
 *
 * <p>
 * É projetada para ser utilizada em conjunto com um
 * {@code GlobalExceptionHandler}, que converte essas exceções
 * em respostas HTTP padronizadas.
 * </p>
 *
 * <p>
 * Por estender {@link RuntimeException}, não exige declaração
 * obrigatória em assinatura de métodos (unchecked exception).
 * </p>
 */
public abstract class BusinessException extends RuntimeException {

  /**
   * Código de status HTTP que será retornado ao cliente.
   */
  private final int status;

  /**
   * Construtor protegido para subclasses.
   *
   * @param message descrição detalhada do erro
   * @param status  código HTTP correspondente
   */
  protected BusinessException(String message, int status) {
    super(message);
    this.status = status;
  }

  /**
   * Retorna o código HTTP associado à exceção.
   *
   * @return status HTTP
   */
  public int getStatus() {
    return status;
  }
}