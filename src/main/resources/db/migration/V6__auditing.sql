-- CREATE TABLE public.audits
-- (
--     id         BIGINT                      NOT NULL,
--     created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
--     updated_at TIMESTAMP WITHOUT TIME ZONE,
--     created_by BIGINT,
--     updated_by BIGINT,
--     CONSTRAINT pk_audits PRIMARY KEY (id)
-- );

-- ALTER TABLE public.audits
--     OWNER TO postgres;

-- ALTER TABLE public.audits
--     ADD CONSTRAINT FK_AUDITS_ON_CREATED_BY FOREIGN KEY (created_by) REFERENCES public.organizations (id);
--
-- ALTER TABLE public.audits
--     ADD CONSTRAINT FK_AUDITS_ON_UPDATED_BY FOREIGN KEY (updated_by) REFERENCES public.organizations (id);

-- ALTER TABLE public.organizations ADD COLUMN audit_id BIGINT NOT NULL;

-- ALTER TABLE public.organizations
--     ADD CONSTRAINT uc_organizations_audit UNIQUE (audit_id);

-- ALTER TABLE public.organizations
--     ADD CONSTRAINT FK_ORGANIZATIONS_ON_AUDIT FOREIGN KEY (audit_id) REFERENCES public.audits (id);

-- ALTER TABLE public.products ADD COLUMN audit_id BIGINT NOT NULL;

-- ALTER TABLE products
--     ADD CONSTRAINT uc_products_audit UNIQUE (audit_id);
--
-- ALTER TABLE products
--     ADD CONSTRAINT FK_PRODUCTS_ON_AUDIT FOREIGN KEY (audit_id) REFERENCES audits (id);

-- ALTER TABLE public.contracts ADD COLUMN audit_id BIGINT NOT NULL;

-- ALTER TABLE contracts
--     ADD CONSTRAINT uc_contracts_audit UNIQUE (audit_id);
--
-- ALTER TABLE contracts
--     ADD CONSTRAINT FK_CONTRACTS_ON_AUDIT FOREIGN KEY (audit_id) REFERENCES audits (id);


-- alter table products
--     alter column audit_id drop not null;
--
-- alter table contracts
--     alter column audit_id drop not null;
--
-- alter table organizations
--     alter column audit_id drop not null;
--
-- alter table audits
--     drop constraint fk_audits_on_created_by;
--
-- alter table audits
--     drop constraint fk_audits_on_updated_by;
--
-- alter table audits
--     drop column created_by;
--
-- alter table audits
--     drop column updated_by;


ALTER TABLE organizations
    ADD COLUMN created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL default current_timestamp;

ALTER TABLE organizations
    ADD COLUMN updated_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE products
    ADD COLUMN created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL default current_timestamp;

ALTER TABLE products
    ADD COLUMN updated_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE contracts
    ADD COLUMN created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL default current_timestamp;

ALTER TABLE contracts
    ADD COLUMN updated_at TIMESTAMP WITHOUT TIME ZONE;
