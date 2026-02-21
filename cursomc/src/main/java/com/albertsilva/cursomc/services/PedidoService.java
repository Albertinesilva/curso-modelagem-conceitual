package com.albertsilva.cursomc.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

/**
 * Serviço responsável pela orquestração das regras de negócio relacionadas à
 * entidade {@link Pedido}.
 *
 * <p>
 * Atua como Application Service na arquitetura em camadas, coordenando:
 * </p>
 * <ul>
 * <li>Recuperação de entidades via repositórios;</li>
 * <li>Criação e associação de agregados (Pedido, Pagamento e Itens);</li>
 * <li>Conversão entre DTOs e entidades de domínio através do
 * {@link PedidoMapper};</li>
 * <li>Controle transacional das operações de escrita.</li>
 * </ul>
 *
 * <p>
 * Esta classe garante consistência transacional ao manipular o agregado
 * {@link Pedido}, que funciona como Aggregate Root no contexto de vendas.
 * </p>
 *
 * @author
 */
@Service
public class PedidoService {

  private final PedidoRepository pedidoRepository;
  private final ClienteRepository clienteRepository;
  private final EnderecoRepository enderecoRepository;
  private final ProdutoRepository produtoRepository;
  private final PedidoMapper pedidoMapper;

  /**
   * Construtor para injeção de dependências.
   *
   * @param pedidoRepository   repositório responsável pela persistência de
   *                           {@link Pedido}
   * @param clienteRepository  repositório para acesso a {@link Cliente}
   * @param enderecoRepository repositório para acesso a {@link Endereco}
   * @param produtoRepository  repositório para acesso a {@link Produto}
   * @param pedidoMapper       componente responsável pelo mapeamento entre DTOs e
   *                           entidade
   */
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

  /**
   * Cria um novo pedido no sistema.
   *
   * <p>
   * Fluxo da operação:
   * </p>
   * <ol>
   * <li>Recupera cliente e endereço de entrega;</li>
   * <li>Cria a instância concreta de {@link Pagamento} conforme o tipo
   * informado;</li>
   * <li>Instancia o agregado {@link Pedido};</li>
   * <li>Cria e associa os {@link ItemPedido};</li>
   * <li>Persiste o agregado completo;</li>
   * <li>Retorna o DTO de resposta.</li>
   * </ol>
   *
   * @param dto dados necessários para criação do pedido
   * @return {@link PedidoResponse} representando o pedido criado
   *
   * @throws IllegalArgumentException se o tipo de pagamento for inválido
   */
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

  /**
   * Retorna uma página de pedidos.
   *
   * @param pageable informações de paginação
   * @return página contendo {@link PedidoResponse}
   */
  @Transactional(readOnly = true)
  public Page<PedidoResponse> findAllPaged(Pageable pageable) {
    return pedidoRepository.findAll(pageable)
        .map(pedidoMapper::toResponse);
  }

  /**
   * Busca um pedido pelo seu identificador.
   *
   * @param id identificador do pedido
   * @return {@link PedidoResponse} correspondente
   *
   * @throws ObjectNotFoundException caso o pedido não seja encontrado
   */
  @Transactional(readOnly = true)
  public PedidoResponse findById(Integer id) {
    return pedidoMapper.toResponse(findEntityById(id));
  }

  /**
   * Atualiza informações estruturais de um pedido existente.
   *
   * <p>
   * Permite alterar:
   * </p>
   * <ul>
   * <li>Cliente associado;</li>
   * <li>Endereço de entrega;</li>
   * <li>Estado do pagamento;</li>
   * <li>Itens do pedido.</li>
   * </ul>
   *
   * @param id  identificador do pedido
   * @param dto dados atualizados
   * @return {@link PedidoResponse} atualizado
   *
   * @throws ObjectNotFoundException se o pedido não existir
   * @throws IllegalStateException   se o pedido não possuir pagamento associado
   */
  @Transactional
  public PedidoResponse update(Integer id, PedidoUpdateRequest dto) {

    Pedido pedido = findEntityById(id);

    pedido.setCliente(clienteRepository.getReferenceById(dto.clienteId()));
    pedido.setEnderecoDeEntrega(enderecoRepository.getReferenceById(dto.enderecoId()));

    atualizarPagamento(pedido, dto.estadoPagamento());

    Map<Produto, Integer> novosItens = dto.itens().stream()
        .collect(Collectors.toMap(item -> produtoRepository.getReferenceById(item.produtoId()),
            ItemPedidoUpdateRequest::quantidade));

    pedido.atualizarItens(novosItens);

    return pedidoMapper.toResponse(pedido);
  }

  /**
   * Remove um pedido do sistema.
   *
   * @param id identificador do pedido
   *
   * @throws ObjectNotFoundException se o pedido não existir
   */
  @Transactional
  public void delete(Integer id) {
    Pedido pedido = findEntityById(id);
    pedidoRepository.delete(pedido);
  }

  /**
   * Recupera a entidade {@link Pedido} pelo id.
   *
   * @param id identificador
   * @return entidade encontrada
   * @throws ObjectNotFoundException se não encontrada
   */
  private Pedido findEntityById(Integer id) {
    return pedidoRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! Id: " + id));
  }

  /**
   * Recupera o cliente associado ao pedido.
   */
  private Cliente searchCustomer(PedidoInsertRequest dto) {
    return clienteRepository.getReferenceById(dto.clienteId());
  }

  /**
   * Recupera o endereço de entrega associado ao pedido.
   */
  private Endereco searchAddress(PedidoInsertRequest dto) {
    return enderecoRepository.getReferenceById(dto.enderecoEntregaId());
  }

  /**
   * Cria a instância concreta de {@link Pagamento} de acordo com o tipo
   * informado.
   *
   * @param dto dados do pedido
   * @return instância de {@link Pagamento}
   *
   * @throws IllegalArgumentException se o tipo for inválido
   */
  private Pagamento createPayment(PedidoInsertRequest dto) {

    TipoPagamento tipo = TipoPagamento.toEnum(dto.tipoPagamento());

    Pagamento pagamento = switch (tipo) {
      case CARTAO -> new PagamentoComCartao(null, EstadoPagamento.PENDENTE, null, dto.numeroDeParcelas());
      case BOLETO -> new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, null, null, null);
      default -> throw new IllegalArgumentException("Tipo inválido: " + tipo);
    };

    return pagamento;
  }

  /**
   * Cria o agregado {@link Pedido} associando cliente, endereço e pagamento.
   */
  private Pedido createOrder(PedidoInsertRequest dto, Cliente cliente, Endereco endereco, Pagamento pagamento) {

    Pedido pedido = pedidoMapper.fromInsertRequest(dto, cliente, endereco, new HashSet<>(), pagamento);

    pagamento.setPedido(pedido);

    return pedido;
  }

  /**
   * Cria os itens do pedido a partir do DTO.
   */
  private Set<ItemPedido> createItems(PedidoInsertRequest dto, Pedido pedido) {

    Set<ItemPedido> itens = new HashSet<>();

    for (ItemPedidoRequest itemDto : dto.itens()) {

      Produto produto = produtoRepository.getReferenceById(itemDto.produtoId());

      ItemPedido item = new ItemPedido(pedido, produto, 0.0, itemDto.quantidade(), produto.getPreco());

      itens.add(item);
    }

    return itens;
  }

  /**
   * Atualiza o estado do pagamento associado ao pedido.
   *
   * @param pedido pedido alvo
   * @param estado código do novo estado
   *
   * @throws IllegalStateException se não houver pagamento vinculado
   */
  private void atualizarPagamento(Pedido pedido, Integer estado) {

    EstadoPagamento estadoEnum = EstadoPagamento.toEnum(estado);

    Pagamento pagamento = pedido.getPagamento();

    if (pagamento == null) {
      throw new IllegalStateException("Pedido não possui pagamento associado");
    }

    pagamento.setEstado(estadoEnum);
  }

}