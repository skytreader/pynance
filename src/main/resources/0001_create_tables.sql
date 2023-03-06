-- liquibase formatted sql

-- changeset liquibase:1
--
-- PostgreSQL database dump
--

-- Dumped from database version 12.14 (Ubuntu 12.14-1.pgdg20.04+1)
-- Dumped by pg_dump version 15.2 (Ubuntu 15.2-1.pgdg20.04+1)

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
-- Name: discount_dates; Type: TABLE; Schema: public; Owner: pynance
--

CREATE TABLE public.discount_dates (
    id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL,
    modified_at timestamp with time zone NOT NULL,
    date_observed date NOT NULL,
    description character varying(255) NOT NULL,
    duration character varying(255) NOT NULL,
    shop character varying(255) NOT NULL
);


ALTER TABLE public.discount_dates OWNER TO pynance;

--
-- Name: discount_dates_id_seq; Type: SEQUENCE; Schema: public; Owner: pynance
--

CREATE SEQUENCE public.discount_dates_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discount_dates_id_seq OWNER TO pynance;

--
-- Name: discount_dates_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pynance
--

ALTER SEQUENCE public.discount_dates_id_seq OWNED BY public.discount_dates.id;


--
-- Name: expenses; Type: TABLE; Schema: public; Owner: pynance
--

CREATE TABLE public.expenses (
    id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL,
    modified_at timestamp with time zone NOT NULL,
    category character varying(255),
    comments character varying(255),
    cost real NOT NULL,
    expense_date date NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.expenses OWNER TO pynance;

--
-- Name: expenses_id_seq; Type: SEQUENCE; Schema: public; Owner: pynance
--

CREATE SEQUENCE public.expenses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.expenses_id_seq OWNER TO pynance;

--
-- Name: expenses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pynance
--

ALTER SEQUENCE public.expenses_id_seq OWNED BY public.expenses.id;


--
-- Name: installation_config; Type: TABLE; Schema: public; Owner: pynance
--

CREATE TABLE public.installation_config (
    id bigint NOT NULL,
    created_at timestamp with time zone NOT NULL,
    modified_at timestamp with time zone NOT NULL,
    key character varying(255) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.installation_config OWNER TO pynance;

--
-- Name: installation_config_id_seq; Type: SEQUENCE; Schema: public; Owner: pynance
--

CREATE SEQUENCE public.installation_config_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.installation_config_id_seq OWNER TO pynance;

--
-- Name: installation_config_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: pynance
--

ALTER SEQUENCE public.installation_config_id_seq OWNED BY public.installation_config.id;


--
-- Name: discount_dates id; Type: DEFAULT; Schema: public; Owner: pynance
--

ALTER TABLE ONLY public.discount_dates ALTER COLUMN id SET DEFAULT nextval('public.discount_dates_id_seq'::regclass);


--
-- Name: expenses id; Type: DEFAULT; Schema: public; Owner: pynance
--

ALTER TABLE ONLY public.expenses ALTER COLUMN id SET DEFAULT nextval('public.expenses_id_seq'::regclass);


--
-- Name: installation_config id; Type: DEFAULT; Schema: public; Owner: pynance
--

ALTER TABLE ONLY public.installation_config ALTER COLUMN id SET DEFAULT nextval('public.installation_config_id_seq'::regclass);


--
-- Name: discount_dates discount_dates_pkey; Type: CONSTRAINT; Schema: public; Owner: pynance
--

ALTER TABLE ONLY public.discount_dates
    ADD CONSTRAINT discount_dates_pkey PRIMARY KEY (id);


--
-- Name: expenses expenses_pkey; Type: CONSTRAINT; Schema: public; Owner: pynance
--

ALTER TABLE ONLY public.expenses
    ADD CONSTRAINT expenses_pkey PRIMARY KEY (id);


--
-- Name: installation_config installation_config_pkey; Type: CONSTRAINT; Schema: public; Owner: pynance
--

ALTER TABLE ONLY public.installation_config
    ADD CONSTRAINT installation_config_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

