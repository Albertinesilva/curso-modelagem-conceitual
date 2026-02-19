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

INSERT INTO cidade (nome, estado_id) VALUES
('Uberlândia', 1),
('Belo Horizonte', 1),
('São Paulo', 2),
('Campinas', 2),
('Rio de Janeiro', 3),
('Niterói', 3),
('Curitiba', 4),
('Londrina', 4),
('Salvador', 5),
('Feira de Santana', 5);