CREATE TABLE funcionario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    tipo_limitacao VARCHAR(50) NOT NULL,
    preferencia_acessibilidade VARCHAR(50) NOT NULL,
    area_funcao VARCHAR(100) NOT NULL,
    cep VARCHAR(8),
    logradouro VARCHAR(255),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf VARCHAR(2)
);