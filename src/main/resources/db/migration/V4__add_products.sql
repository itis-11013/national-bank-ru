CREATE TABLE products
(
    id         UUID                        NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    code       VARCHAR(255)                NOT NULL,
    name       VARCHAR(255)                NOT NULL,
    inner_id   UUID                        NOT NULL,
    count      DOUBLE PRECISION            NOT NULL,
    price      DOUBLE PRECISION            NOT NULL,
    seller_id  UUID,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_SELLER FOREIGN KEY (seller_id) REFERENCES organizations (id);
