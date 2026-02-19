alter table if exists pagamento 
  add constraint FKthad9tkw4188hb3qo1lm5ueb0 
  foreign key (pedido_id) 
  references pedido;