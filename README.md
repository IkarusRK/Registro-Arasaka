## [Login: admin] [Senha: 1234]

CREATE DATABASE IF NOT EXISTS arasaka_db;
USE arasaka_db;
DROP TABLE IF EXISTS cidadao;
CREATE TABLE cidadao (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    hwidSoc VARCHAR(25) NOT NULL UNIQUE,
    modeloSoc VARCHAR(100),
    criadoraSoc ENUM('ARASAKA', 'MILITECH', 'BIOTECH', 'HYDRA', 'TENZO') NOT NULL,
    classe ENUM('HACKER', 'NEKO', 'ELFA', 'HUMANO', 'EXECUTIVA', 'CIVIL', 'MILITAR', 'PROCURADO', 'CAÇADO', 'REALEZA', 'IMIGRANTE', 'REFUGIADO') NOT NULL,
    nivelAcesso ENUM('PUBLICO', 'EMPRESARIAL', 'MILITAR', 'REALEZA') NOT NULL,
    caminhoFoto VARCHAR(255)
);

CREATE USER IF NOT EXISTS 'IkarusRK'@'%' IDENTIFIED BY '12345';
GRANT ALL PRIVILEGES ON arasaka_db.* TO 'IkarusRK'@'%';
FLUSH PRIVILEGES;

select * from cidadao;


-- Aqui pra baixo é apenas tests
CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    classe ENUM('HACKER', 'NEKO', 'ELFA', 'HUMANO', 'EXECUTIVA', 'CIVIL', 'MILITAR', 'PROCURADO', 'INIMIGO_DO_ESTADO', 'REALEZA', 'IMIGRANTE', 'REFUGIADO') NOT NULL,
    criadora_soc ENUM('ARASAKA', 'MILITECH', 'BIOTECH', 'HYDRA', 'KANGTOO') NOT NULL,
    nivel_acesso ENUM('PUBLICO', 'EMPRESARIAL', 'MILITAR', 'REALEZA') NOT NULL
);

INSERT INTO pessoa (nome, classe, criadora_soc, nivel_acesso)
VALUES 
('Kara Mitsuki', 'NEKO', 'ARASAKA', 'EMPRESARIAL'),
('Drake Zahir', 'HUMANO', 'MILITECH', 'MILITAR'),
('Elisia Vorn', 'ELFA', 'BIOTECH', 'PUBLICO'),
('Nikolai Draegur', 'EXECUTIVA', 'HYDRA', 'REALEZA'),
('Faye Rina', 'PROCURADO', 'KANGTOO', 'PUBLICO');



