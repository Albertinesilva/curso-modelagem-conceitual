-- Inserção de dados na tabela categoria, se não existir nenhum registro
-- DO $$
-- BEGIN
--   IF NOT EXISTS (SELECT 1 FROM categoria) THEN
--     INSERT INTO categoria (id, nome) VALUES
--     (1, 'Informática'),
--     (2, 'Escritório');
--   END IF;
-- END
-- $$;

INSERT INTO estado (nome) VALUES
('Minas Gerais'),
('São Paulo'),
('Rio de Janeiro'),
('Paraná'),
('Bahia');

