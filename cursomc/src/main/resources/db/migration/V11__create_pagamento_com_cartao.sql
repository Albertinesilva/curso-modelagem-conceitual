create table pagamento_com_cartao (
    numero_de_parcelas integer,
    pedido_id integer not null,
    primary key (pedido_id)
);