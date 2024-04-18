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
-- Name: create_post(bigint, text, bytea); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.create_post(IN in_user_id bigint, IN in_content text, IN in_image bytea)
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO "Post" ("User ID", Content, Image, "Created At")
    VALUES (in_user_id, in_content, in_image, NOW());
END;
$$;


ALTER PROCEDURE public.create_post(IN in_user_id bigint, IN in_content text, IN in_image bytea) OWNER TO postgres;

--
-- Name: create_user(character varying, character varying, character varying, character varying, bytea, text); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.create_user(IN in_phone character varying, IN in_user_name character varying, IN in_email character varying, IN in_password character varying, IN in_cover_image bytea, IN in_biography text)
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO "User" (Phone, "User Name", Email, Password, "Cover Image", Biography)
    VALUES (in_phone, in_user_name, in_email, in_password, in_cover_image, in_biography);
END;
$$;


ALTER PROCEDURE public.create_user(IN in_phone character varying, IN in_user_name character varying, IN in_email character varying, IN in_password character varying, IN in_cover_image bytea, IN in_biography text) OWNER TO postgres;

--
-- Name: delete_post(bigint); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.delete_post(IN in_post_id bigint)
    LANGUAGE plpgsql
    AS $$
BEGIN
    DELETE FROM "Post" WHERE "Post ID" = in_post_id;
END;
$$;


ALTER PROCEDURE public.delete_post(IN in_post_id bigint) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: Post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Post" (
    "Post ID" integer NOT NULL,
    "User ID" integer,
    "Content" text NOT NULL,
    "Image" bytea,
    "Created At" timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public."Post" OWNER TO postgres;

--
-- Name: get_all_posts(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_all_posts() RETURNS SETOF public."Post"
    LANGUAGE plpgsql
    AS $$
BEGIN
    RETURN QUERY SELECT * FROM "Post";
END;
$$;


ALTER FUNCTION public.get_all_posts() OWNER TO postgres;

--
-- Name: get_password_by_phone(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.get_password_by_phone(in_phone character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
DECLARE
    user_password VARCHAR;
BEGIN
    SELECT Password INTO user_password FROM "User" WHERE Phone = in_phone;
    RETURN user_password;
END;
$$;


ALTER FUNCTION public.get_password_by_phone(in_phone character varying) OWNER TO postgres;

--
-- Name: getuserbyphone(character varying); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.getuserbyphone(IN phone_number character varying)
    LANGUAGE plpgsql
    AS $$
BEGIN
    SELECT * FROM "User" WHERE phone = phone_number;
END;
$$;


ALTER PROCEDURE public.getuserbyphone(IN phone_number character varying) OWNER TO postgres;

--
-- Name: update_post(bigint, text); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.update_post(IN in_post_id bigint, IN in_content text)
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE "Post" SET Content = in_content WHERE "Post ID" = in_post_id;
END;
$$;


ALTER PROCEDURE public.update_post(IN in_post_id bigint, IN in_content text) OWNER TO postgres;

--
-- Name: Comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Comment" (
    "Comment ID" integer NOT NULL,
    "User ID" integer,
    "Post ID" integer,
    "Content" text NOT NULL,
    "Created At" timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public."Comment" OWNER TO postgres;

--
-- Name: Comment_Comment ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Comment_Comment ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Comment_Comment ID_seq" OWNER TO postgres;

--
-- Name: Comment_Comment ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Comment_Comment ID_seq" OWNED BY public."Comment"."Comment ID";


--
-- Name: Post_Post ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Post_Post ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."Post_Post ID_seq" OWNER TO postgres;

--
-- Name: Post_Post ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Post_Post ID_seq" OWNED BY public."Post"."Post ID";


--
-- Name: User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."User" (
    "User ID" integer NOT NULL,
    "User Name" character varying(255) NOT NULL,
    "Email" character varying(255) NOT NULL,
    "Password" character varying(255) NOT NULL,
    "Cover Image" bytea,
    "Biography" text,
    phone character varying(20)
);


ALTER TABLE public."User" OWNER TO postgres;

--
-- Name: User_User ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."User_User ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."User_User ID_seq" OWNER TO postgres;

--
-- Name: User_User ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."User_User ID_seq" OWNED BY public."User"."User ID";


--
-- Name: Comment Comment ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Comment" ALTER COLUMN "Comment ID" SET DEFAULT nextval('public."Comment_Comment ID_seq"'::regclass);


--
-- Name: Post Post ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Post" ALTER COLUMN "Post ID" SET DEFAULT nextval('public."Post_Post ID_seq"'::regclass);


--
-- Name: User User ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User" ALTER COLUMN "User ID" SET DEFAULT nextval('public."User_User ID_seq"'::regclass);


--
-- Name: Comment Comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Comment"
    ADD CONSTRAINT "Comment_pkey" PRIMARY KEY ("Comment ID");


--
-- Name: Post Post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Post"
    ADD CONSTRAINT "Post_pkey" PRIMARY KEY ("Post ID");


--
-- Name: User User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY ("User ID");


--
-- Name: Comment Comment_Post ID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Comment"
    ADD CONSTRAINT "Comment_Post ID_fkey" FOREIGN KEY ("Post ID") REFERENCES public."Post"("Post ID");


--
-- Name: Comment Comment_User ID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Comment"
    ADD CONSTRAINT "Comment_User ID_fkey" FOREIGN KEY ("User ID") REFERENCES public."User"("User ID");


--
-- Name: Post Post_User ID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Post"
    ADD CONSTRAINT "Post_User ID_fkey" FOREIGN KEY ("User ID") REFERENCES public."User"("User ID");


--
-- PostgreSQL database dump complete
--

