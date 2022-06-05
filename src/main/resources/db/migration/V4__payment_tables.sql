CREATE TABLE contracts
(
    id           BIGINT  NOT NULL,
    inner_id     UUID    NOT NULL,
    is_paid      BOOLEAN NOT NULL,
    payment_date TIMESTAMP WITHOUT TIME ZONE,
    count        INTEGER NOT NULL,
    product_id   BIGINT,
    buyer_id     BIGINT,
    seller_id    BIGINT,
    CONSTRAINT pk_contracts PRIMARY KEY (id)
);

ALTER TABLE contracts
    ADD CONSTRAINT FK_CONTRACTS_ON_BUYER FOREIGN KEY (buyer_id) REFERENCES organizations (id);

ALTER TABLE contracts
    ADD CONSTRAINT FK_CONTRACTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE contracts
    ADD CONSTRAINT FK_CONTRACTS_ON_SELLER FOREIGN KEY (seller_id) REFERENCES organizations (id);
