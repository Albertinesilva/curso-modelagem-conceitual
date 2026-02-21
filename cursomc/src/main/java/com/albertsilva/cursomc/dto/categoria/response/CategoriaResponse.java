package com.albertsilva.cursomc.dto.categoria.response;

/**
 * Data Transfer Object (DTO) responsável por representar
 * os dados de saída da entidade Categoria.
 *
 * <p>
 * Utilizado para expor informações ao cliente de forma controlada,
 * desacoplando o modelo de domínio da camada de apresentação.
 * </p>
 *
 * <p>
 * Implementado como {@code record}, garantindo imutabilidade,
 * clareza estrutural e redução de boilerplate, conforme práticas
 * modernas do Java 17+.
 * </p>
 *
 * <h2>Responsabilidade</h2>
 * <ul>
 * <li>Transportar dados da camada de serviço para a camada de API.</li>
 * <li>Evitar exposição direta da entidade de domínio.</li>
 * <li>Permitir versionamento e evolução independente do modelo de domínio.</li>
 * </ul>
 *
 * @param id   Identificador único da categoria.
 * @param nome Nome da categoria.
 */
public record CategoriaResponse(

    /**
     * Identificador único da categoria.
     */
    Integer id,

    /**
     * Nome da categoria.
     */
    String nome) {
}