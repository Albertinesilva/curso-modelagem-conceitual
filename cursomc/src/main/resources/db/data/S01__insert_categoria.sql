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

INSERT INTO categoria (nome) VALUES
('Informática'),
('Escritório'),
('Eletrônicos'),
('Periféricos'),
('Móveis'),
('Limpeza'),
('Papelaria'),
('Ferramentas'),
('Automotivo'),
('Construção'),
('Segurança'),
('Iluminação'),
('Cozinha'),
('Jardim'),
('Telefonia'),
('Áudio'),
('Vídeo'),
('Eletrodomésticos'),
('Acessórios'),
('Serviços');
