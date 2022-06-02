CREATE TABLE contracts
(
    id           UUID                        NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    inner_id     UUID                        NOT NULL,
    is_paid      BOOLEAN                     NOT NULL,
    payment_date TIMESTAMP WITHOUT TIME ZONE,
    count        INTEGER                     NOT NULL,
    buyer_id     UUID,
    seller_id    UUID,
    product_id   UUID,
    CONSTRAINT pk_contracts PRIMARY KEY (id)
);

ALTER TABLE contracts
    ADD CONSTRAINT FK_CONTRACTS_ON_BUYER FOREIGN KEY (buyer_id) REFERENCES organizations (id);

ALTER TABLE contracts
    ADD CONSTRAINT FK_CONTRACTS_ON_SELLER FOREIGN KEY (seller_id) REFERENCES organizations (id);

alter table organizations
    add column balance double precision;

alter table organizations
    add column frozen_balance double precision;
