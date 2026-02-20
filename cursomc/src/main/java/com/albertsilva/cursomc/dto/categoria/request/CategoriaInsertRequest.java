package com.albertsilva.cursomc.dto.categoria.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO responsável por representar os dados de entrada
 * para criação ou atualização de Categoria.
 */
public record CategoriaInsertRequest(
  
  @NotBlank(message = "O nome da categoria é obrigatório.") 
  @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.") 
  String nome
) {}