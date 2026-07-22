CREATE TABLE tb_customers (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(150),
    phone_number VARCHAR(20)
);