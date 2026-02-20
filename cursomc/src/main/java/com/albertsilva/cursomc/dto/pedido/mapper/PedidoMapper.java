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

@Component
public class PedidoMapper {

  public Pedido fromInsertRequest(PedidoInsertRequest dto, Cliente cliente, Endereco endereco, Set<ItemPedido> itens,
      Pagamento pagamento) {
    Pedido pedido = new Pedido(null, new Date(), cliente, endereco);
    pedido.setPagamento(pagamento);
    pedido.setItens(itens);
    return pedido;
  }

  public PedidoResponse toResponse(Pedido pedido) {

    Set<ItemPedidoResponse> itens = pedido.getItens().stream()
        .map(ip -> new ItemPedidoResponse(ip.getProduto().getNome(),
            ip.getQuantidade(), ip.getPreco(), ip.getQuantidade() * ip.getPreco()))
        .collect(Collectors.toSet());

    return new PedidoResponse(pedido.getId(), pedido.getInstante(), pedido.getCliente().getNome(),
        pedido.getPagamento().getEstado().name(), itens, pedido.getTotal());
  }
}