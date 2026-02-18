alter table if exists pagamento_com_cartao 
  add constraint FKta3cdnuuxclwfh52t4qi432ow 
  foreign key (pedido_id) 
  references pagamento;