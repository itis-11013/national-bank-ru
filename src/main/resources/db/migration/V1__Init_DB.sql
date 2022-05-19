--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Ubuntu 14.2-1.pgdg20.04+1+b1)
-- Dumped by pg_dump version 14.2 (Ubuntu 14.2-1.pgdg20.04+1+b1)

-- Started on 2022-05-19 14:26:41 MSK

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

--
-- TOC entry 211 (class 1259 OID 18527)
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
                                 id bigint NOT NULL,
                                 created_at timestamp without time zone NOT NULL,
                                 updated_at timestamp without time zone,
                                 email character varying(255) NOT NULL,
                                 password_hash character varying(255) NOT NULL,
                                 status integer NOT NULL,
                                 username character varying(255) NOT NULL
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 18534)
-- Name: accounts_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts_roles (
                                       user_id bigint NOT NULL,
                                       roles_id bigint NOT NULL
);


ALTER TABLE public.accounts_roles OWNER TO postgres;


--
-- TOC entry 210 (class 1259 OID 18526)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 18537)
-- Name: organization; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organization (
                                     id bigint NOT NULL,
                                     created_at timestamp without time zone NOT NULL,
                                     updated_at timestamp without time zone,
                                     address character varying(255) NOT NULL,
                                     inner_id uuid NOT NULL,
                                     name character varying(255) NOT NULL,
                                     user_id bigint
);


ALTER TABLE public.organization OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 18544)
-- Name: persistence_logins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persistence_logins (
                                           series character varying(255) NOT NULL,
                                           last_used timestamp without time zone NOT NULL,
                                           token character varying(255) NOT NULL,
                                           username character varying(255) NOT NULL
);


ALTER TABLE public.persistence_logins OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 18551)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
                              id bigint NOT NULL,
                              created_at timestamp without time zone NOT NULL,
                              updated_at timestamp without time zone,
                              name character varying(255) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 3197 (class 2606 OID 18533)
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);


--
-- TOC entry 3203 (class 2606 OID 18543)
-- Name: organization organization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);


--
-- TOC entry 3205 (class 2606 OID 18550)
-- Name: persistence_logins persistence_logins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persistence_logins
    ADD CONSTRAINT persistence_logins_pkey PRIMARY KEY (series);


--
-- TOC entry 3207 (class 2606 OID 18555)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3199 (class 2606 OID 18559)
-- Name: accounts uk_epgft9dnme9x7umykpknhpfdx; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT uk_epgft9dnme9x7umykpknhpfdx UNIQUE (status);


--
-- TOC entry 3201 (class 2606 OID 18557)
-- Name: accounts uk_n7ihswpy07ci568w34q0oi8he; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT uk_n7ihswpy07ci568w34q0oi8he UNIQUE (email);


--
-- TOC entry 3211 (class 2606 OID 18575)
-- Name: organization fkcy7ac2fjemd5yixt8ryt1w0k3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT fkcy7ac2fjemd5yixt8ryt1w0k3 FOREIGN KEY (id) REFERENCES public.accounts(id);


--
-- TOC entry 3208 (class 2606 OID 18560)
-- Name: accounts_roles fkg50bsugfce90c8wcshcj2k95m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts_roles
    ADD CONSTRAINT fkg50bsugfce90c8wcshcj2k95m FOREIGN KEY (roles_id) REFERENCES public.roles(id);


--
-- TOC entry 3210 (class 2606 OID 18570)
-- Name: organization fkq9cyx7nvcxdj4t0wyowma6gnw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organization
    ADD CONSTRAINT fkq9cyx7nvcxdj4t0wyowma6gnw FOREIGN KEY (user_id) REFERENCES public.accounts(id);


--
-- TOC entry 3209 (class 2606 OID 18565)
-- Name: accounts_roles fktgl0qa6lj8rr4aulbt1n4yl2h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts_roles
    ADD CONSTRAINT fktgl0qa6lj8rr4aulbt1n4yl2h FOREIGN KEY (user_id) REFERENCES public.accounts(id);


-- Completed on 2022-05-19 14:26:44 MSK

--
-- PostgreSQL database dump complete
--
