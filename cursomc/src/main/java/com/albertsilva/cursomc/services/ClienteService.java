package com.albertsilva.cursomc.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.albertsilva.cursomc.domain.Cidade;
import com.albertsilva.cursomc.domain.Cliente;
import com.albertsilva.cursomc.dto.cliente.mapper.ClienteMapper;
import com.albertsilva.cursomc.dto.cliente.request.ClienteInsertRequest;
import com.albertsilva.cursomc.dto.cliente.request.ClienteUpdateRequest;
import com.albertsilva.cursomc.dto.cliente.response.ClienteResponse;
import com.albertsilva.cursomc.repositories.CidadeRepository;
import com.albertsilva.cursomc.repositories.ClienteRepository;
import com.albertsilva.cursomc.services.exceptions.ObjectNotFoundException;

/**
 * Serviço responsável pela orquestração das operações de negócio
 * relacionadas à entidade {@link Cliente}.
 *
 * <p>
 * Atua como camada intermediária entre a camada de apresentação
 * (Controllers) e a camada de persistência (Repositories),
 * garantindo:
 * </p>
 *
 * <ul>
 * <li>Controle transacional</li>
 * <li>Validação de existência de entidade</li>
 * <li>Conversão entre DTOs e entidades de domínio</li>
 * <li>Encapsulamento da lógica de aplicação</li>
 * </ul>
 *
 * <p>
 * A entidade {@link Cliente} é tratada como Aggregate Root dentro
 * do contexto de clientes, sendo manipulada exclusivamente por
 * meio deste serviço.
 * </p>
 */
@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;
  private final CidadeRepository cidadeRepository;
  private final ClienteMapper clienteMapper;

  /**
   * Construtor com injeção de dependências via construtor.
   *
   * @param clienteRepository repositório responsável pela persistência de
   *                          clientes
   * @param cidadeRepository  repositório responsável pela persistência de cidades
   * @param clienteMapper     componente responsável pela conversão entre entidade
   *                          e DTO
   */
  public ClienteService(ClienteRepository clienteRepository, CidadeRepository cidadeRepository,
      ClienteMapper clienteMapper) {
    this.clienteRepository = clienteRepository;
    this.cidadeRepository = cidadeRepository;
    this.clienteMapper = clienteMapper;
  }

  /**
   * Insere um novo cliente no sistema.
   *
   * <p>
   * O método:
   * </p>
   * <ol>
   * <li>Obtém uma referência da entidade {@link Cidade}</li>
   * <li>Cria a entidade {@link Cliente} a partir do DTO</li>
   * <li>Persiste o agregado</li>
   * <li>Retorna a representação de saída</li>
   * </ol>
   *
   * <p>
   * Utiliza {@code getReferenceById} para evitar consulta imediata ao banco,
   * delegando a verificação de integridade à camada de persistência.
   * </p>
   *
   * @param dto dados necessários para criação do cliente
   * @return {@link ClienteResponse} representando o cliente persistido
   */
  @Transactional
  public ClienteResponse insert(ClienteInsertRequest dto) {

    // Cidade cidade = cidadeRepository.findById(dto.cidadeId())
    // .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada"));
    Cidade cidade = cidadeRepository.getReferenceById(dto.cidadeId());

    Cliente cliente = clienteMapper.fromInsertRequest(dto, cidade);

    cliente = clienteRepository.save(cliente);

    return clienteMapper.toResponse(cliente);
  }

  /**
   * Retorna uma lista paginada de clientes.
   *
   * @param pageable parâmetros de paginação e ordenação
   * @return página contendo {@link ClienteResponse}
   */
  @Transactional(readOnly = true)
  public Page<ClienteResponse> findAllPaged(Pageable pageable) {
    return clienteRepository.findAll(pageable).map(clienteMapper::toResponse);
  }

  /**
   * Busca um cliente pelo identificador.
   *
   * @param id identificador do cliente
   * @return representação do cliente encontrado
   * @throws ObjectNotFoundException caso o cliente não exista
   */
  @Transactional(readOnly = true)
  public ClienteResponse findById(Integer id) {
    return clienteMapper.toResponse(findEntityById(id));
  }

  /**
   * Atualiza dados básicos de um cliente existente.
   *
   * <p>
   * A atualização delega à própria entidade {@link Cliente}
   * a responsabilidade de aplicar as mudanças, respeitando
   * o encapsulamento e as invariantes do domínio.
   * </p>
   *
   * @param id  identificador do cliente
   * @param dto dados atualizados
   * @return cliente atualizado
   * @throws ObjectNotFoundException caso o cliente não exista
   */
  @Transactional
  public ClienteResponse update(Integer id, ClienteUpdateRequest dto) {
    Cliente cliente = findEntityById(id);
    cliente.updateFrom(dto);
    return clienteMapper.toResponse(cliente);
  }

  /**
   * Remove um cliente do sistema.
   *
   * @param id identificador do cliente
   * @throws ObjectNotFoundException caso o cliente não exista
   */
  @Transactional
  public void delete(Integer id) {
    Cliente cliente = findEntityById(id);
    clienteRepository.delete(cliente);
  }

  /**
   * Recupera a entidade {@link Cliente} pelo identificador.
   *
   * <p>
   * Método utilitário interno responsável por centralizar
   * a lógica de validação de existência da entidade.
   * </p>
   *
   * @param id identificador do cliente
   * @return entidade persistida
   * @throws ObjectNotFoundException caso não seja encontrado
   */
  private Cliente findEntityById(Integer id) {
    return clienteRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + id));
  }

}