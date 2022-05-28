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

--
-- TOC entry 212 (class 1259 OID 18843)
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
                                 id uuid NOT NULL,
                                 created_at timestamp without time zone NOT NULL,
                                 updated_at timestamp without time zone,
                                 email character varying(255) NOT NULL,
                                 password_hash character varying(255) NOT NULL,
                                 status character varying(255) NOT NULL
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 18850)
-- Name: accounts_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts_roles (
                                       user_id uuid NOT NULL,
                                       roles_id uuid NOT NULL
);


ALTER TABLE public.accounts_roles OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 18678)
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
-- TOC entry 214 (class 1259 OID 18853)
-- Name: organizations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organizations (
                                      id uuid NOT NULL,
                                      created_at timestamp without time zone NOT NULL,
                                      updated_at timestamp without time zone,
                                      address character varying(255) NOT NULL,
                                      inner_id uuid NOT NULL,
                                      name character varying(255) NOT NULL,
                                      status character varying(255) NOT NULL,
                                      user_id uuid
);


ALTER TABLE public.organizations OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 18838)
-- Name: persistent_logins; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persistent_logins (
                                          username character varying(64) NOT NULL,
                                          series character varying(64) NOT NULL,
                                          token character varying(64) NOT NULL,
                                          last_used timestamp without time zone NOT NULL
);


ALTER TABLE public.persistent_logins OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 18860)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
                              id uuid NOT NULL,
                              created_at timestamp without time zone NOT NULL,
                              updated_at timestamp without time zone,
                              name character varying(255) unique NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 3199 (class 2606 OID 18849)
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);

--
-- TOC entry 3203 (class 2606 OID 18859)
-- Name: organizations organizations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT organizations_pkey PRIMARY KEY (id);


--
-- TOC entry 3197 (class 2606 OID 18842)
-- Name: persistent_logins persistent_logins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persistent_logins
    ADD CONSTRAINT persistent_logins_pkey PRIMARY KEY (series);


--
-- TOC entry 3205 (class 2606 OID 18864)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 3201 (class 2606 OID 18866)
-- Name: accounts uk_n7ihswpy07ci568w34q0oi8he; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT uk_n7ihswpy07ci568w34q0oi8he UNIQUE (email);


--
-- TOC entry 3208 (class 2606 OID 18877)
-- Name: organizations fk5rjjflmlyyuw2sgg2pqy2v4qt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT fk5rjjflmlyyuw2sgg2pqy2v4qt FOREIGN KEY (user_id) REFERENCES public.accounts(id);


--
-- TOC entry 3206 (class 2606 OID 18867)
-- Name: accounts_roles fkg50bsugfce90c8wcshcj2k95m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts_roles
    ADD CONSTRAINT fkg50bsugfce90c8wcshcj2k95m FOREIGN KEY (roles_id) REFERENCES public.roles(id);


--
-- TOC entry 3209 (class 2606 OID 18882)
-- Name: organizations fki8dxpcmdwugbggd2ooyddjtda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organizations
    ADD CONSTRAINT fki8dxpcmdwugbggd2ooyddjtda FOREIGN KEY (id) REFERENCES public.accounts(id);


--
-- TOC entry 3207 (class 2606 OID 18872)
-- Name: accounts_roles fktgl0qa6lj8rr4aulbt1n4yl2h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts_roles
    ADD CONSTRAINT fktgl0qa6lj8rr4aulbt1n4yl2h FOREIGN KEY (user_id) REFERENCES public.accounts(id);


-- Completed on 2022-05-28 13:54:22 MSK

--
-- PostgreSQL database dump complete
--
