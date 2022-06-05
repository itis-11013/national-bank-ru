--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3 (Ubuntu 14.3-1.pgdg20.04+1)
-- Dumped by pg_dump version 14.3 (Ubuntu 14.3-1.pgdg20.04+1)

-- Started on 2022-05-28 13:54:21 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;


------------------------------<     TABLES CREATION     >------------------------------


CREATE TABLE public.organizations_roles
(
    organization_id bigint NOT NULL,
    roles_id        bigint NOT NULL
);


ALTER TABLE public.organizations_roles
    OWNER TO postgres;

CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence
    OWNER TO postgres;


CREATE TABLE public.organizations
(
    id             BIGINT           NOT NULL,
    name           VARCHAR(255)     NOT NULL,
    address        VARCHAR(255)     NOT NULL,
    password_hash  VARCHAR(255)     NOT NULL,
    status         VARCHAR(255)     NOT NULL,
    inner_id       UUID             NOT NULL,
    balance        DOUBLE PRECISION NOT NULL,
    frozen_balance DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_organizations PRIMARY KEY (id)
);

ALTER TABLE public.organizations
    OWNER TO postgres;


CREATE TABLE public.persistent_logins
(
    username  character varying(64)       NOT NULL,
    series    character varying(64)       NOT NULL,
    token     character varying(64)       NOT NULL,
    last_used timestamp without time zone NOT NULL
);


ALTER TABLE public.persistent_logins
    OWNER TO postgres;

CREATE TABLE public.roles
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
);


ALTER TABLE public.roles
    OWNER TO postgres;

------------------------------<     CONSTRAINTS CREATION     >------------------------------

-- PERSISTENCE LOGIN

ALTER TABLE ONLY public.persistent_logins
    ADD CONSTRAINT persistent_logins_pkey PRIMARY KEY (series);

-- AUDITS

-- ROLES

ALTER TABLE public.roles
    ADD CONSTRAINT uc_roles_name UNIQUE (name);

-- ORGANIZATIONS


ALTER TABLE public.organizations
    ADD CONSTRAINT uc_organizations_inner UNIQUE (inner_id);

ALTER TABLE public.organizations
    ADD CONSTRAINT uc_organizations_name UNIQUE (name);


ALTER TABLE ONLY public.organizations_roles
    ADD CONSTRAINT FK_ROLE FOREIGN KEY (roles_id) REFERENCES public.roles (id);

ALTER TABLE ONLY public.organizations_roles
    ADD CONSTRAINT FK_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES public.organizations (id);
