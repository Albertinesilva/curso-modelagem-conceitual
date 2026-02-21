package com.albertsilva.cursomc.dto.pedido.mapper;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.albertsilva.cursomc.domain.Cliente;
import com.albertsilva.cursomc.domain.Endereco;
import com.albertsilva.cursomc.domain.ItemPedido;
import com.albertsilva.cursomc.domain.Pagamento;
import com.albertsilva.cursomc.domain.Pedido;
import com.albertsilva.cursomc.dto.pedido.request.PedidoInsertRequest;
import com.albertsilva.cursomc.dto.pedido.response.ItemPedidoResponse;
import com.albertsilva.cursomc.dto.pedido.response.PedidoResponse;

/**
 * Componente responsável pelo mapeamento entre a entidade {@link Pedido}
 * e seus respectivos objetos de transferência de dados (DTOs).
 *
 * <p>
 * Atua como camada de adaptação entre API e domínio,
 * garantindo isolamento da modelagem interna do sistema.
 * </p>
 *
 * <h2>Conversões suportadas</h2>
 * <ul>
 * <li>{@link PedidoInsertRequest} → {@link Pedido}</li>
 * <li>{@link Pedido} → {@link PedidoResponse}</li>
 * </ul>
 */
@Component
public class PedidoMapper {

  /**
   * Converte um {@link PedidoInsertRequest} em uma nova instância
   * da entidade {@link Pedido}.
   *
   * <p>
   * O método associa ao pedido:
   * </p>
   * <ul>
   * <li>O {@link Cliente} previamente carregado;</li>
   * <li>O {@link Endereco} de entrega;</li>
   * <li>Os {@link ItemPedido} já construídos;</li>
   * <li>O {@link Pagamento} correspondente.</li>
   * </ul>
   *
   * <p>
   * A data do pedido é definida no momento da criação utilizando {@link Date}.
   * </p>
   *
   * @param dto       objeto de requisição
   * @param cliente   cliente associado ao pedido
   * @param endereco  endereço de entrega
   * @param itens     conjunto de itens do pedido
   * @param pagamento pagamento vinculado ao pedido
   * @return instância de {@link Pedido} pronta para persistência
   */
  public Pedido fromInsertRequest(PedidoInsertRequest dto, Cliente cliente, Endereco endereco, Set<ItemPedido> itens,
      Pagamento pagamento) {

    Pedido pedido = new Pedido(null, new Date(), cliente, endereco);
    pedido.setPagamento(pagamento);
    pedido.setItens(itens);

    return pedido;
  }

  /**
   * Converte uma entidade {@link Pedido} em {@link PedidoResponse}.
   *
   * <p>
   * Realiza também a transformação dos {@link ItemPedido}
   * em {@link ItemPedidoResponse}, incluindo o cálculo
   * do subtotal de cada item.
   * </p>
   *
   * @param pedido entidade a ser convertida
   * @return DTO contendo os dados consolidados do pedido
   */
  public PedidoResponse toResponse(Pedido pedido) {

    Set<ItemPedidoResponse> itens = pedido.getItens().stream()
        .map(ip -> new ItemPedidoResponse(
            ip.getProduto().getNome(),
            ip.getQuantidade(),
            ip.getPreco(),
            ip.getQuantidade() * ip.getPreco()))
        .collect(Collectors.toSet());

    return new PedidoResponse(
        pedido.getId(),
        pedido.getInstante(),
        pedido.getCliente().getNome(),
        pedido.getPagamento().getEstado().name(),
        itens,
        pedido.getTotal());
  }
}