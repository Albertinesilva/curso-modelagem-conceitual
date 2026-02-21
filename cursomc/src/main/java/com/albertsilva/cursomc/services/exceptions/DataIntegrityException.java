package com.albertsilva.cursomc.services.exceptions;

/**
 * Exceção lançada quando ocorre violação de integridade de dados.
 *
 * <p>
 * Normalmente associada a:
 * </p>
 * <ul>
 * <li>Violação de restrição de chave estrangeira</li>
 * <li>Violação de unicidade (unique constraint)</li>
 * <li>Tentativa de exclusão de entidade com dependências</li>
 * </ul>
 *
 * <p>
 * Retorna o status HTTP {@code 409 - Conflict}, indicando que
 * a requisição não pôde ser processada devido a conflito com
 * o estado atual do recurso.
 * </p>
 *
 * <p>
 * Essa exceção encapsula erros oriundos da camada de persistência,
 * traduzindo-os para uma semântica apropriada ao domínio.
 * </p>
 */
public class DataIntegrityException extends BusinessException {

  /**
   * Constrói a exceção com a mensagem informada.
   *
   * @param message descrição do conflito de integridade
   */
  public DataIntegrityException(String message) {
    super(message, 409);
  }
}