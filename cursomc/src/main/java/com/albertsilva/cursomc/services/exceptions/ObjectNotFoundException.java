package com.albertsilva.cursomc.services.exceptions;

/**
 * Exceção lançada quando uma entidade esperada não é encontrada.
 *
 * <p>
 * Geralmente utilizada em operações de:
 * </p>
 * <ul>
 * <li>Busca por identificador</li>
 * <li>Atualização de recurso inexistente</li>
 * <li>Remoção de recurso inexistente</li>
 * </ul>
 *
 * <p>
 * Retorna o status HTTP {@code 404 - Not Found}.
 * </p>
 *
 * <p>
 * Representa uma falha operacional comum no fluxo de
 * consulta ao repositório.
 * </p>
 *
 * <p>
 * Observação: Pode coexistir com {@link ResourceNotFoundException},
 * dependendo da estratégia de nomenclatura adotada no projeto.
 * </p>
 */
public class ObjectNotFoundException extends BusinessException {

  /**
   * Constrói a exceção com a mensagem informada.
   *
   * @param message descrição do recurso não encontrado
   */
  public ObjectNotFoundException(String message) {
    super(message, 404);
  }
}