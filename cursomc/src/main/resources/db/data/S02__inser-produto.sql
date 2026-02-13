-- Inserção de dados na tabela produto, se não existir nenhum registro
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM produto) THEN
    INSERT INTO produto (id, nome, preco) VALUES
    (1, 'Computador', 2000.00),
    (2, 'Impressora', 800.00),
    (3, 'Mouse', 80.00);
  END IF;
END
$$;
