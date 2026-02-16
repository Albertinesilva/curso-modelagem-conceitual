alter table if exists cidade 
  add constraint FKkworrwk40xj58kevvh3evi500 
  foreign key (estado_id) 
  references estado;

alter table if exists produto_categoria 
  add constraint FK1c0y58d3n6x3m6euv2j3h64vt 
  foreign key (produto_id) 
  references produto;