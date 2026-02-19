create table item_pedido (
  desconto float(53),
  pedido_id integer not null,
  preco float(53),
  produto_id integer not null,
  quantidade integer,
  primary key (pedido_id, produto_id)
);