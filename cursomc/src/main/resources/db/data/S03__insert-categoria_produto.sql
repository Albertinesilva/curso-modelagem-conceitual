-- Inserção de dados na tabela categoria_produto, se não existir nenhum registro
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM categoria_produto) THEN
    INSERT INTO categoria_produto (categoria_id, produto_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 2);
  END IF;
END
$$;
