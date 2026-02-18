create table pagamento (
    estado integer,
    pedido_id integer not null,
    primary key (pedido_id)
);