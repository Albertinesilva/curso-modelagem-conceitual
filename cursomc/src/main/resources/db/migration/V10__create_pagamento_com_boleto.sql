create table pagamento_com_boleto (
    pedido_id integer not null,
    data_pagamento timestamp(6),
    data_vencimento timestamp(6),
    primary key (pedido_id)
);