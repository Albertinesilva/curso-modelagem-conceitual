alter table if exists endereco 
  add constraint FK8b1kcb3wucapb8dejshyn5fsx 
  foreign key (cidade_id) 
  references cidade;
alter table if exists endereco 
  add constraint FK8s7ivtl4foyhrfam9xqom73n9 
  foreign key (cliente_id) 
  references cliente;