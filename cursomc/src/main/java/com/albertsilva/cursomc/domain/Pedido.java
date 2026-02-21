package com.albertsilva.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "pedido")
public class Pedido implements Serializable {
  private final static long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
  private Date instante;

  @JsonManagedReference
  @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
  private Pagamento pagamento;

  @JsonManagedReference
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "endereco_de_entrega_id")
  private Endereco enderecoDeEntrega;

  @OneToMany(mappedBy = "id.pedido", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ItemPedido> itens = new HashSet<>();

  public Pedido() {
  }

  public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
    this.id = id;
    this.instante = instante;
    this.cliente = cliente;
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  // ===============================
  // REGRAS DE DOMÍNIO
  // ===============================

  public void adicionarItem(Produto produto, Integer quantidade) {

    validarQuantidade(quantidade);

    ItemPedido existente = buscarItemPorProduto(produto.getId());

    if (existente != null) {
      existente.setQuantidade(existente.getQuantidade() + quantidade);
    } else {
      ItemPedido novo = new ItemPedido(this, produto, 0.0, quantidade, produto.getPreco());
      itens.add(novo);
    }
  }

  public void atualizarItens(Map<Produto, Integer> novosItens) {

    itens.removeIf(item -> novosItens.keySet().stream()
        .noneMatch(prod -> prod.getId().equals(item.getProduto().getId())));

    for (Map.Entry<Produto, Integer> entry : novosItens.entrySet()) {

      Produto produto = entry.getKey();
      Integer quantidade = entry.getValue();

      validarQuantidade(quantidade);

      ItemPedido existente = buscarItemPorProduto(produto.getId());

      if (existente != null) {
        existente.setQuantidade(quantidade);
      } else {
        adicionarItem(produto, quantidade);
      }
    }
  }

  private ItemPedido buscarItemPorProduto(Integer produtoId) {
    return itens.stream()
        .filter(i -> i.getProduto().getId().equals(produtoId))
        .findFirst()
        .orElse(null);
  }

  private void validarQuantidade(Integer quantidade) {
    if (quantidade == null || quantidade <= 0) {
      throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
    }
  }

  public double getTotal() {
    return itens.stream().mapToDouble(ItemPedido::getSubTotal).sum();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getInstante() {
    return instante;
  }

  public void setInstante(Date instante) {
    this.instante = instante;
  }

  public Pagamento getPagamento() {
    return pagamento;
  }

  public void setPagamento(Pagamento pagamento) {
    this.pagamento = pagamento;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Endereco getEnderecoDeEntrega() {
    return enderecoDeEntrega;
  }

  public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  public Set<ItemPedido> getItens() {
    return itens;
  }

  public void setItens(Set<ItemPedido> itens) {
    this.itens = itens;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Pedido other = (Pedido) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
