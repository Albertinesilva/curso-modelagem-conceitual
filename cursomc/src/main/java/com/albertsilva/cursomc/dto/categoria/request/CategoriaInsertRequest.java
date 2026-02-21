package com.albertsilva.cursomc.dto.categoria.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) utilizado para representar os dados de entrada
 * necessários para criação ou atualização de uma Categoria.
 *
 * <p>
 * Implementado como {@code record}, garantindo imutabilidade,
 * concisão e clareza semântica, conforme boas práticas do Java 17+.
 * </p>
 *
 * <p>
 * As validações são realizadas através das anotações da Bean Validation
 * (Jakarta Validation), sendo automaticamente processadas pelo Spring
 * quando utilizadas em conjunto com {@code @Valid} nos controllers.
 * </p>
 *
 * <h2>Regras de validação</h2>
 * <ul>
 * <li>O nome não pode ser nulo ou vazio.</li>
 * <li>O nome deve possuir entre 3 e 100 caracteres.</li>
 * </ul>
 *
 * @param nome Nome da categoria.
 */
public record CategoriaInsertRequest(

    /**
     * Nome da categoria.
     *
     * <p>
     * Campo obrigatório. Deve conter entre 3 e 100 caracteres.
     * </p>
     */
    @NotBlank(message = "O nome da categoria é obrigatório.") @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.") String nome) {
}