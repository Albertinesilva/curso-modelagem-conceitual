package com.albertsilva.cursomc.dto.categoria.mappper;

import org.springframework.stereotype.Component;

import com.albertsilva.cursomc.domain.Categoria;
import com.albertsilva.cursomc.dto.categoria.request.CategoriaInsertRequest;
import com.albertsilva.cursomc.dto.categoria.response.CategoriaResponse;

/**
 * Componente responsável pela conversão entre a entidade {@link Categoria}
 * e seus respectivos objetos de transferência de dados (DTOs).
 *
 * <p>
 * Implementa a lógica de mapeamento manual entre:
 * </p>
 * <ul>
 * <li>{@link CategoriaInsertRequest} → {@link Categoria}</li>
 * <li>{@link Categoria} → {@link CategoriaResponse}</li>
 * </ul>
 *
 * <p>
 * Centralizar o mapeamento nesta classe promove separação de responsabilidades,
 * reduz acoplamento entre camadas e facilita manutenção futura.
 * </p>
 */
@Component
public class CategoriaMapper {

  /**
   * Converte um DTO de inserção em uma nova entidade {@link Categoria}.
   *
   * @param dto objeto contendo os dados da requisição de criação
   * @return nova instância de {@link Categoria} preenchida
   */
  public Categoria fromInsertRequest(CategoriaInsertRequest dto) {
    Categoria categoria = new Categoria();
    categoria.setNome(dto.nome());
    return categoria;
  }

  /**
   * Converte uma entidade {@link Categoria} em um DTO de resposta.
   *
   * @param categoria entidade a ser convertida
   * @return DTO contendo os dados expostos ao cliente
   */
  public CategoriaResponse toResponse(Categoria categoria) {
    return new CategoriaResponse(
        categoria.getId(),
        categoria.getNome());
  }

  /**
   * Atualiza uma entidade {@link Categoria} existente com base
   * nos dados fornecidos no DTO de requisição.
   *
   * <p>
   * Utilizado em operações de atualização (update), evitando
   * recriação da entidade.
   * </p>
   *
   * @param dto       objeto contendo os novos dados
   * @param categoria entidade a ser atualizada
   */
  public void updateEntityFromRequest(CategoriaInsertRequest dto, Categoria categoria) {
    categoria.setNome(dto.nome());
  }
}