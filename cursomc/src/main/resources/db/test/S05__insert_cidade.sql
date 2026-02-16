-- Inserção de dados na tabela cidade, se não existir nenhum registro
-- DO $$
-- BEGIN
--     IF NOT EXISTS (SELECT 1 FROM cidade) THEN
--         INSERT INTO cidade (id, nome, estado_id) VALUES
--         (1, 'Uberlândia', 1),
--         (2, 'São Paulo', 2),
--         (3, 'Campinas', 2);
--     END IF;
-- END
-- $$;


INSERT INTO cidade (id, nome, estado_id) VALUES(1, 'Uberlândia', 1),(2, 'São Paulo', 2),(3, 'Campinas', 2);
