package com.albertsilva.cursomc.services.exceptions;

/**
 * Exceção lançada quando um recurso exposto pela API
 * não é encontrado.
 *
 * <p>
 * Semanticamente equivalente ao status HTTP
 * {@code 404 - Not Found}.
 * </p>
 *
 * <p>
 * Pode ser utilizada como alternativa mais alinhada
 * à nomenclatura REST, diferenciando-se de
 * {@link ObjectNotFoundException} quando se deseja
 * separar conceitos internos do domínio de conceitos
 * expostos externamente via API.
 * </p>
 *
 * <p>
 * Recomenda-se padronizar o uso de apenas uma das
 * exceções 404 no projeto para evitar redundância.
 * </p>
 */
public class ResourceNotFoundException extends BusinessException {

  /**
   * Constrói a exceção com a mensagem informada.
   *
   * @param message descrição do recurso não encontrado
   */
  public ResourceNotFoundException(String message) {
    super(message, 404);
  }
}