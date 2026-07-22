CREATE TABLE tb_orders (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    order_time TIMESTAMP NOT NULL DEFAULT now(),
    payment_key TEXT,
    notes TEXT,
    status VARCHAR(20) CHECK ( status IN ('CREATED', 'PAID', 'INVOICED', 'SENT', 'PAYMENT_ERROR', 'SENDING_PREPARATION') ),
    total_amount DECIMAL(16,2) NOT NULL,
    tracking_code VARCHAR(255),
    nf_url TEXT
);

CREATE TABLE tb_order_items (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES tb_orders(id),
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_amount DECIMAL(16,2) NOT NULL
);