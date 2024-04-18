--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

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

--
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."User" ("User ID", "User Name", "Email", "Password", "Cover Image", "Biography", phone) FROM stdin;
1	Justin Chien	justin@example.com	password123	\N	This is a testing Data	0912345678
\.


--
-- Data for Name: Post; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Post" ("Post ID", "User ID", "Content", "Image", "Created At") FROM stdin;
\.


--
-- Data for Name: Comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Comment" ("Comment ID", "User ID", "Post ID", "Content", "Created At") FROM stdin;
\.


--
-- Name: Comment_Comment ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Comment_Comment ID_seq"', 1, false);


--
-- Name: Post_Post ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Post_Post ID_seq"', 1, false);


--
-- Name: User_User ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."User_User ID_seq"', 1, true);


--
-- PostgreSQL database dump complete
--

