package com.albertsilva.cursomc.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.albertsilva.cursomc.domain.Cliente;
import com.albertsilva.cursomc.domain.Endereco;
import com.albertsilva.cursomc.domain.ItemPedido;
import com.albertsilva.cursomc.domain.Pagamento;
import com.albertsilva.cursomc.domain.PagamentoComBoleto;
import com.albertsilva.cursomc.domain.PagamentoComCartao;
import com.albertsilva.cursomc.domain.Pedido;
import com.albertsilva.cursomc.domain.Produto;
import com.albertsilva.cursomc.domain.enums.EstadoPagamento;
import com.albertsilva.cursomc.domain.enums.TipoPagamento;
import com.albertsilva.cursomc.dto.pedido.mapper.PedidoMapper;
import com.albertsilva.cursomc.dto.pedido.request.ItemPedidoRequest;
import com.albertsilva.cursomc.dto.pedido.request.PedidoInsertRequest;
import com.albertsilva.cursomc.dto.pedido.response.PedidoResponse;
import com.albertsilva.cursomc.dto.pedido.update.ItemPedidoUpdateRequest;
import com.albertsilva.cursomc.dto.pedido.update.PedidoUpdateRequest;
import com.albertsilva.cursomc.repositories.ClienteRepository;
import com.albertsilva.cursomc.repositories.EnderecoRepository;
import com.albertsilva.cursomc.repositories.PedidoRepository;
import com.albertsilva.cursomc.repositories.ProdutoRepository;
import com.albertsilva.cursomc.services.exceptions.ObjectNotFoundException;
import com.albertsilva.cursomc.services.exceptions.ResourceNotFoundException;

@Service
public class PedidoService {

  private final PedidoRepository pedidoRepository;
  private final ClienteRepository clienteRepository;
  private final EnderecoRepository enderecoRepository;
  private final ProdutoRepository produtoRepository;
  private final PedidoMapper pedidoMapper;

  public PedidoService(
      PedidoRepository pedidoRepository,
      ClienteRepository clienteRepository,
      EnderecoRepository enderecoRepository,
      ProdutoRepository produtoRepository,
      PedidoMapper pedidoMapper) {

    this.pedidoRepository = pedidoRepository;
    this.clienteRepository = clienteRepository;
    this.enderecoRepository = enderecoRepository;
    this.produtoRepository = produtoRepository;
    this.pedidoMapper = pedidoMapper;
  }

  @Transactional
  public PedidoResponse insert(PedidoInsertRequest dto) {

    Cliente cliente = searchCustomer(dto);
    Endereco endereco = searchAddress(dto);

    Pagamento pagamento = createPayment(dto);

    Pedido pedido = createOrder(dto, cliente, endereco, pagamento);

    Set<ItemPedido> itens = createItems(dto, pedido);

    pedido.setItens(itens);

    pedido = pedidoRepository.save(pedido);

    return pedidoMapper.toResponse(pedido);
  }

  @Transactional(readOnly = true)
  public Page<PedidoResponse> findAllPaged(Pageable pageable) {
    return pedidoRepository.findAll(pageable)
        .map(pedidoMapper::toResponse);
  }

  @Transactional(readOnly = true)
  public PedidoResponse findById(Integer id) {
    return pedidoMapper.toResponse(findEntityById(id));
  }

  @Transactional
  public PedidoResponse update(Integer id, PedidoUpdateRequest dto) {

    Pedido pedido = pedidoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));

    Cliente cliente = clienteRepository.getReferenceById(dto.clienteId());
    Endereco endereco = enderecoRepository.getReferenceById(dto.enderecoId());

    pedido.setCliente(cliente);
    pedido.setEnderecoDeEntrega(endereco);

    atualizarPagamento(pedido, dto.estadoPagamento());
    atualizarItens(pedido, dto.itens());

    return pedidoMapper.toResponse(pedido);
  }

  @Transactional
  public void delete(Integer id) {
    Pedido pedido = findEntityById(id);
    pedidoRepository.delete(pedido);
  }

  private Pedido findEntityById(Integer id) {
    return pedidoRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! Id: " + id));
  }

  private Cliente searchCustomer(PedidoInsertRequest dto) {
    return clienteRepository.getReferenceById(dto.clienteId());
  }

  private Endereco searchAddress(PedidoInsertRequest dto) {
    return enderecoRepository.getReferenceById(dto.enderecoEntregaId());
  }

  private Pagamento createPayment(PedidoInsertRequest dto) {

    TipoPagamento tipo = TipoPagamento.toEnum(dto.tipoPagamento());

    Pagamento pagamento = switch (tipo) {
      case CARTAO -> new PagamentoComCartao(null, EstadoPagamento.PENDENTE, null, dto.numeroDeParcelas());

      case BOLETO -> new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, null, null, null);

      default -> throw new IllegalArgumentException("Tipo inválido: " + tipo);
    };

    return pagamento;
  }

  private Pedido createOrder(PedidoInsertRequest dto, Cliente cliente, Endereco endereco, Pagamento pagamento) {

    Pedido pedido = pedidoMapper.fromInsertRequest(dto, cliente, endereco, new HashSet<>(), pagamento);

    pagamento.setPedido(pedido);

    return pedido;
  }

  private Set<ItemPedido> createItems(PedidoInsertRequest dto, Pedido pedido) {

    Set<ItemPedido> itens = new HashSet<>();

    for (ItemPedidoRequest itemDto : dto.itens()) {

      Produto produto = produtoRepository.getReferenceById(itemDto.produtoId());

      ItemPedido item = new ItemPedido(pedido, produto, 0.0, itemDto.quantidade(), produto.getPreco());

      itens.add(item);
    }

    return itens;
  }

  private void atualizarPagamento(Pedido pedido, Integer estado) {

    EstadoPagamento estadoEnum = EstadoPagamento.toEnum(estado);

    Pagamento pagamento = pedido.getPagamento();

    if (pagamento == null) {
      throw new IllegalStateException("Pedido não possui pagamento associado");
    }

    pagamento.setEstado(estadoEnum);
  }

  private void atualizarItens(Pedido pedido, Set<ItemPedidoUpdateRequest> itensDto) {

    Map<Integer, ItemPedido> itensExistentes = pedido.getItens().stream()
        .collect(Collectors.toMap(item -> item.getProduto().getId(),
            Function.identity()));

    // Remover itens que não vieram mais no DTO
    pedido.getItens()
        .removeIf(item -> itensDto.stream().noneMatch(dto -> dto.produtoId().equals(item.getProduto().getId())));

    // Atualizar ou inserir
    for (ItemPedidoUpdateRequest dto : itensDto) {

      ItemPedido existente = itensExistentes.get(dto.produtoId());

      if (existente != null) {
        existente.setQuantidade(dto.quantidade());
      } else {
        Produto produto = produtoRepository.getReferenceById(dto.produtoId());

        ItemPedido novo = new ItemPedido(pedido, produto, 0.0, dto.quantidade(), produto.getPreco());

        pedido.getItens().add(novo);
      }
    }
  }

}