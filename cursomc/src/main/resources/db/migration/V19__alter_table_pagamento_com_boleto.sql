alter table if exists pagamento_com_boleto 
  add constraint FKcr74vrxf8nfph0knq2bho8doo 
  foreign key (pedido_id) 
  references pagamento;