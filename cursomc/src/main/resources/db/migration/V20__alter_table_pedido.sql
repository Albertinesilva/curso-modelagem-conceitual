alter table if exists pedido 
  add constraint FK30s8j2ktpay6of18lbyqn3632 
  foreign key (cliente_id) 
  references cliente;

alter table if exists pedido 
  add constraint FK1fihyy2fnocpuwc74674qmfkv 
  foreign key (endereco_de_entrega_id) 
  references endereco;