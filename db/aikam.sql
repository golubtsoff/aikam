--
-- PostgreSQL database dump
--

-- Dumped from database version 11.7
-- Dumped by pg_dump version 11.7

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

SET default_with_oids = false;

--
-- Name: buyers; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.buyers (
    id bigint NOT NULL,
    firstname character varying NOT NULL,
    lastname character varying
);


ALTER TABLE public.buyers OWNER TO "user";

--
-- Name: buyers_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.buyers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.buyers_id_seq OWNER TO "user";

--
-- Name: buyers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user
--

ALTER SEQUENCE public.buyers_id_seq OWNED BY public.buyers.id;


--
-- Name: goods; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.goods (
    id bigint NOT NULL,
    title character varying NOT NULL,
    price numeric
);


ALTER TABLE public.goods OWNER TO "user";

--
-- Name: goods_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.goods_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.goods_id_seq OWNER TO "user";

--
-- Name: goods_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user
--

ALTER SEQUENCE public.goods_id_seq OWNED BY public.goods.id;


--
-- Name: purchases; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.purchases (
    id bigint NOT NULL,
    buyer_id bigint NOT NULL,
    good_id bigint NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.purchases OWNER TO "user";

--
-- Name: purchases_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.purchases_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.purchases_id_seq OWNER TO "user";

--
-- Name: purchases_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user
--

ALTER SEQUENCE public.purchases_id_seq OWNED BY public.purchases.id;


--
-- Name: buyers id; Type: DEFAULT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.buyers ALTER COLUMN id SET DEFAULT nextval('public.buyers_id_seq'::regclass);


--
-- Name: goods id; Type: DEFAULT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.goods ALTER COLUMN id SET DEFAULT nextval('public.goods_id_seq'::regclass);


--
-- Name: purchases id; Type: DEFAULT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.purchases ALTER COLUMN id SET DEFAULT nextval('public.purchases_id_seq'::regclass);


--
-- Data for Name: buyers; Type: TABLE DATA; Schema: public; Owner: user
--

INSERT INTO public.buyers VALUES (1, 'Росс', 'Геллер');
INSERT INTO public.buyers VALUES (2, 'Моника', 'Геллер');
INSERT INTO public.buyers VALUES (3, 'Фиби', 'Буффе');
INSERT INTO public.buyers VALUES (4, 'Джоуи', 'Триббиани');
INSERT INTO public.buyers VALUES (5, 'Чендлер', 'Бинг');
INSERT INTO public.buyers VALUES (6, 'Рейчел', 'Грин');
INSERT INTO public.buyers VALUES (7, 'Вася', 'Иванов');


--
-- Data for Name: goods; Type: TABLE DATA; Schema: public; Owner: user
--

INSERT INTO public.goods VALUES (1, 'Хлеб', 22);
INSERT INTO public.goods VALUES (2, 'Молоко', 40);
INSERT INTO public.goods VALUES (3, 'Колбаса', 300);
INSERT INTO public.goods VALUES (4, 'Сыр', 500);
INSERT INTO public.goods VALUES (5, 'Вино', 990);
INSERT INTO public.goods VALUES (6, 'Шоколад', 60);
INSERT INTO public.goods VALUES (7, 'Яблоки', 125);
INSERT INTO public.goods VALUES (8, 'Помидоры', 100);


--
-- Data for Name: purchases; Type: TABLE DATA; Schema: public; Owner: user
--

INSERT INTO public.purchases VALUES (7, 4, 8, '2020-02-02');
INSERT INTO public.purchases VALUES (8, 2, 3, '2020-02-02');
INSERT INTO public.purchases VALUES (9, 4, 3, '2020-02-02');
INSERT INTO public.purchases VALUES (10, 6, 8, '2020-02-02');
INSERT INTO public.purchases VALUES (11, 6, 3, '2020-02-02');
INSERT INTO public.purchases VALUES (12, 4, 3, '2020-02-02');
INSERT INTO public.purchases VALUES (13, 5, 4, '2020-02-02');
INSERT INTO public.purchases VALUES (14, 5, 3, '2020-02-02');
INSERT INTO public.purchases VALUES (15, 6, 2, '2020-02-02');
INSERT INTO public.purchases VALUES (16, 3, 4, '2020-02-03');
INSERT INTO public.purchases VALUES (17, 1, 1, '2020-02-03');
INSERT INTO public.purchases VALUES (18, 4, 2, '2020-02-03');
INSERT INTO public.purchases VALUES (19, 4, 1, '2020-02-03');
INSERT INTO public.purchases VALUES (20, 4, 6, '2020-02-03');
INSERT INTO public.purchases VALUES (21, 5, 1, '2020-02-03');
INSERT INTO public.purchases VALUES (22, 6, 8, '2020-02-03');
INSERT INTO public.purchases VALUES (23, 6, 3, '2020-02-03');
INSERT INTO public.purchases VALUES (24, 6, 3, '2020-02-03');
INSERT INTO public.purchases VALUES (25, 2, 4, '2020-02-03');
INSERT INTO public.purchases VALUES (26, 2, 6, '2020-02-03');
INSERT INTO public.purchases VALUES (27, 5, 1, '2020-02-03');
INSERT INTO public.purchases VALUES (28, 5, 4, '2020-02-03');
INSERT INTO public.purchases VALUES (29, 5, 8, '2020-02-03');
INSERT INTO public.purchases VALUES (30, 1, 7, '2020-02-03');
INSERT INTO public.purchases VALUES (31, 4, 6, '2020-02-03');
INSERT INTO public.purchases VALUES (32, 3, 2, '2020-02-03');
INSERT INTO public.purchases VALUES (33, 6, 6, '2020-02-03');
INSERT INTO public.purchases VALUES (34, 2, 2, '2020-02-03');
INSERT INTO public.purchases VALUES (35, 6, 7, '2020-02-03');
INSERT INTO public.purchases VALUES (36, 5, 2, '2020-02-03');
INSERT INTO public.purchases VALUES (37, 2, 2, '2020-02-03');
INSERT INTO public.purchases VALUES (38, 6, 5, '2020-02-03');
INSERT INTO public.purchases VALUES (39, 2, 4, '2020-02-03');
INSERT INTO public.purchases VALUES (40, 4, 1, '2020-02-03');
INSERT INTO public.purchases VALUES (41, 6, 8, '2020-02-03');
INSERT INTO public.purchases VALUES (42, 4, 5, '2020-02-03');
INSERT INTO public.purchases VALUES (43, 6, 8, '2020-02-03');
INSERT INTO public.purchases VALUES (44, 4, 5, '2020-02-03');
INSERT INTO public.purchases VALUES (45, 4, 6, '2020-02-03');
INSERT INTO public.purchases VALUES (46, 5, 4, '2020-02-04');
INSERT INTO public.purchases VALUES (47, 1, 2, '2020-02-04');
INSERT INTO public.purchases VALUES (48, 1, 6, '2020-02-04');
INSERT INTO public.purchases VALUES (49, 3, 1, '2020-02-04');
INSERT INTO public.purchases VALUES (50, 1, 1, '2020-02-04');
INSERT INTO public.purchases VALUES (51, 2, 6, '2020-02-04');
INSERT INTO public.purchases VALUES (52, 6, 4, '2020-02-04');
INSERT INTO public.purchases VALUES (53, 3, 2, '2020-02-04');
INSERT INTO public.purchases VALUES (54, 6, 1, '2020-02-04');
INSERT INTO public.purchases VALUES (55, 4, 3, '2020-02-04');
INSERT INTO public.purchases VALUES (56, 2, 6, '2020-02-04');
INSERT INTO public.purchases VALUES (57, 6, 6, '2020-02-04');
INSERT INTO public.purchases VALUES (58, 3, 8, '2020-02-04');
INSERT INTO public.purchases VALUES (59, 2, 2, '2020-02-04');
INSERT INTO public.purchases VALUES (60, 1, 6, '2020-02-04');
INSERT INTO public.purchases VALUES (61, 3, 4, '2020-02-04');
INSERT INTO public.purchases VALUES (62, 5, 6, '2020-02-04');
INSERT INTO public.purchases VALUES (63, 5, 4, '2020-02-04');
INSERT INTO public.purchases VALUES (64, 4, 6, '2020-02-04');
INSERT INTO public.purchases VALUES (65, 1, 8, '2020-02-04');
INSERT INTO public.purchases VALUES (66, 4, 1, '2020-02-04');
INSERT INTO public.purchases VALUES (67, 3, 2, '2020-02-04');
INSERT INTO public.purchases VALUES (68, 5, 4, '2020-02-04');
INSERT INTO public.purchases VALUES (69, 4, 1, '2020-02-04');
INSERT INTO public.purchases VALUES (70, 2, 3, '2020-02-04');
INSERT INTO public.purchases VALUES (71, 6, 5, '2020-02-05');
INSERT INTO public.purchases VALUES (72, 6, 6, '2020-02-05');
INSERT INTO public.purchases VALUES (73, 5, 4, '2020-02-05');
INSERT INTO public.purchases VALUES (74, 1, 7, '2020-02-05');
INSERT INTO public.purchases VALUES (75, 1, 6, '2020-02-05');
INSERT INTO public.purchases VALUES (76, 4, 6, '2020-02-05');
INSERT INTO public.purchases VALUES (77, 2, 3, '2020-02-05');
INSERT INTO public.purchases VALUES (78, 5, 7, '2020-02-05');
INSERT INTO public.purchases VALUES (79, 4, 8, '2020-02-05');
INSERT INTO public.purchases VALUES (80, 4, 8, '2020-02-05');
INSERT INTO public.purchases VALUES (81, 5, 3, '2020-02-05');
INSERT INTO public.purchases VALUES (82, 2, 6, '2020-02-05');
INSERT INTO public.purchases VALUES (83, 5, 4, '2020-02-05');
INSERT INTO public.purchases VALUES (84, 3, 1, '2020-02-05');
INSERT INTO public.purchases VALUES (85, 4, 6, '2020-02-05');
INSERT INTO public.purchases VALUES (86, 3, 4, '2020-02-05');
INSERT INTO public.purchases VALUES (87, 3, 3, '2020-02-05');
INSERT INTO public.purchases VALUES (88, 6, 8, '2020-02-05');
INSERT INTO public.purchases VALUES (89, 6, 3, '2020-02-05');
INSERT INTO public.purchases VALUES (90, 2, 4, '2020-02-05');
INSERT INTO public.purchases VALUES (91, 5, 3, '2020-02-05');
INSERT INTO public.purchases VALUES (92, 3, 6, '2020-02-05');
INSERT INTO public.purchases VALUES (93, 1, 2, '2020-02-05');
INSERT INTO public.purchases VALUES (94, 3, 8, '2020-02-05');
INSERT INTO public.purchases VALUES (95, 5, 1, '2020-02-05');
INSERT INTO public.purchases VALUES (96, 5, 3, '2020-02-05');
INSERT INTO public.purchases VALUES (97, 1, 6, '2020-02-05');
INSERT INTO public.purchases VALUES (98, 2, 7, '2020-02-05');
INSERT INTO public.purchases VALUES (99, 2, 3, '2020-02-05');
INSERT INTO public.purchases VALUES (100, 6, 4, '2020-02-05');
INSERT INTO public.purchases VALUES (101, 2, 5, '2020-02-05');
INSERT INTO public.purchases VALUES (102, 4, 1, '2020-02-05');
INSERT INTO public.purchases VALUES (103, 4, 1, '2020-02-05');
INSERT INTO public.purchases VALUES (104, 1, 3, '2020-02-05');
INSERT INTO public.purchases VALUES (105, 5, 3, '2020-02-06');
INSERT INTO public.purchases VALUES (106, 3, 1, '2020-02-06');
INSERT INTO public.purchases VALUES (107, 5, 3, '2020-02-06');
INSERT INTO public.purchases VALUES (108, 3, 7, '2020-02-06');
INSERT INTO public.purchases VALUES (109, 2, 6, '2020-02-06');
INSERT INTO public.purchases VALUES (110, 2, 3, '2020-02-06');
INSERT INTO public.purchases VALUES (111, 2, 4, '2020-02-06');
INSERT INTO public.purchases VALUES (112, 3, 2, '2020-02-06');
INSERT INTO public.purchases VALUES (113, 3, 6, '2020-02-06');
INSERT INTO public.purchases VALUES (114, 3, 2, '2020-02-06');
INSERT INTO public.purchases VALUES (115, 2, 4, '2020-02-06');
INSERT INTO public.purchases VALUES (116, 3, 8, '2020-02-06');
INSERT INTO public.purchases VALUES (117, 5, 7, '2020-02-06');
INSERT INTO public.purchases VALUES (118, 5, 6, '2020-02-06');
INSERT INTO public.purchases VALUES (119, 5, 7, '2020-02-06');
INSERT INTO public.purchases VALUES (120, 3, 8, '2020-02-06');
INSERT INTO public.purchases VALUES (121, 5, 1, '2020-02-06');
INSERT INTO public.purchases VALUES (122, 5, 8, '2020-02-06');
INSERT INTO public.purchases VALUES (123, 1, 7, '2020-02-06');
INSERT INTO public.purchases VALUES (124, 6, 7, '2020-02-06');
INSERT INTO public.purchases VALUES (125, 3, 1, '2020-02-06');
INSERT INTO public.purchases VALUES (126, 4, 5, '2020-02-06');
INSERT INTO public.purchases VALUES (127, 3, 7, '2020-02-06');
INSERT INTO public.purchases VALUES (128, 3, 6, '2020-02-06');
INSERT INTO public.purchases VALUES (129, 6, 3, '2020-02-06');
INSERT INTO public.purchases VALUES (130, 3, 3, '2020-02-06');
INSERT INTO public.purchases VALUES (131, 6, 6, '2020-02-06');
INSERT INTO public.purchases VALUES (132, 5, 6, '2020-02-06');
INSERT INTO public.purchases VALUES (133, 4, 7, '2020-02-06');
INSERT INTO public.purchases VALUES (134, 3, 1, '2020-02-06');
INSERT INTO public.purchases VALUES (135, 3, 1, '2020-02-06');
INSERT INTO public.purchases VALUES (136, 2, 8, '2020-02-06');
INSERT INTO public.purchases VALUES (137, 4, 4, '2020-02-06');
INSERT INTO public.purchases VALUES (138, 5, 8, '2020-02-06');
INSERT INTO public.purchases VALUES (139, 1, 5, '2020-02-06');
INSERT INTO public.purchases VALUES (140, 3, 7, '2020-02-06');
INSERT INTO public.purchases VALUES (141, 3, 4, '2020-02-06');
INSERT INTO public.purchases VALUES (142, 6, 7, '2020-02-06');
INSERT INTO public.purchases VALUES (143, 6, 7, '2020-02-07');
INSERT INTO public.purchases VALUES (144, 6, 2, '2020-02-07');
INSERT INTO public.purchases VALUES (145, 5, 1, '2020-02-07');
INSERT INTO public.purchases VALUES (146, 6, 6, '2020-02-07');
INSERT INTO public.purchases VALUES (147, 4, 6, '2020-02-07');
INSERT INTO public.purchases VALUES (148, 4, 6, '2020-02-07');
INSERT INTO public.purchases VALUES (149, 3, 3, '2020-02-08');
INSERT INTO public.purchases VALUES (150, 5, 2, '2020-02-08');
INSERT INTO public.purchases VALUES (151, 6, 1, '2020-02-08');
INSERT INTO public.purchases VALUES (152, 1, 4, '2020-02-08');
INSERT INTO public.purchases VALUES (153, 6, 4, '2020-02-08');
INSERT INTO public.purchases VALUES (154, 6, 5, '2020-02-08');
INSERT INTO public.purchases VALUES (155, 5, 1, '2020-02-08');
INSERT INTO public.purchases VALUES (156, 6, 3, '2020-02-08');
INSERT INTO public.purchases VALUES (157, 2, 6, '2020-02-08');
INSERT INTO public.purchases VALUES (158, 3, 4, '2020-02-08');
INSERT INTO public.purchases VALUES (159, 3, 6, '2020-02-08');
INSERT INTO public.purchases VALUES (160, 3, 8, '2020-02-08');
INSERT INTO public.purchases VALUES (161, 4, 3, '2020-02-08');
INSERT INTO public.purchases VALUES (162, 4, 5, '2020-02-08');
INSERT INTO public.purchases VALUES (163, 4, 5, '2020-02-08');
INSERT INTO public.purchases VALUES (164, 4, 3, '2020-02-08');
INSERT INTO public.purchases VALUES (165, 1, 7, '2020-02-08');
INSERT INTO public.purchases VALUES (166, 4, 3, '2020-02-08');
INSERT INTO public.purchases VALUES (167, 2, 7, '2020-02-08');
INSERT INTO public.purchases VALUES (168, 2, 1, '2020-02-08');
INSERT INTO public.purchases VALUES (169, 1, 4, '2020-02-08');
INSERT INTO public.purchases VALUES (170, 5, 8, '2020-02-08');
INSERT INTO public.purchases VALUES (171, 2, 3, '2020-02-08');
INSERT INTO public.purchases VALUES (172, 5, 8, '2020-02-08');
INSERT INTO public.purchases VALUES (173, 2, 3, '2020-02-08');
INSERT INTO public.purchases VALUES (174, 4, 4, '2020-02-09');
INSERT INTO public.purchases VALUES (175, 3, 8, '2020-02-09');
INSERT INTO public.purchases VALUES (176, 5, 1, '2020-02-09');
INSERT INTO public.purchases VALUES (177, 5, 2, '2020-02-09');
INSERT INTO public.purchases VALUES (178, 2, 2, '2020-02-09');
INSERT INTO public.purchases VALUES (179, 2, 5, '2020-02-09');
INSERT INTO public.purchases VALUES (180, 1, 7, '2020-02-09');
INSERT INTO public.purchases VALUES (181, 5, 3, '2020-02-09');
INSERT INTO public.purchases VALUES (182, 2, 6, '2020-02-09');
INSERT INTO public.purchases VALUES (183, 6, 2, '2020-02-09');
INSERT INTO public.purchases VALUES (184, 4, 3, '2020-02-09');
INSERT INTO public.purchases VALUES (185, 5, 6, '2020-02-09');
INSERT INTO public.purchases VALUES (186, 3, 7, '2020-02-09');
INSERT INTO public.purchases VALUES (187, 2, 2, '2020-02-09');
INSERT INTO public.purchases VALUES (188, 5, 8, '2020-02-09');
INSERT INTO public.purchases VALUES (189, 4, 8, '2020-02-09');
INSERT INTO public.purchases VALUES (190, 2, 5, '2020-02-09');
INSERT INTO public.purchases VALUES (191, 2, 6, '2020-02-09');
INSERT INTO public.purchases VALUES (192, 6, 3, '2020-02-09');
INSERT INTO public.purchases VALUES (193, 1, 8, '2020-02-09');
INSERT INTO public.purchases VALUES (194, 1, 3, '2020-02-09');
INSERT INTO public.purchases VALUES (195, 1, 5, '2020-02-09');
INSERT INTO public.purchases VALUES (196, 6, 2, '2020-02-09');
INSERT INTO public.purchases VALUES (197, 3, 4, '2020-02-09');
INSERT INTO public.purchases VALUES (198, 4, 7, '2020-02-09');
INSERT INTO public.purchases VALUES (199, 6, 5, '2020-02-09');
INSERT INTO public.purchases VALUES (200, 6, 6, '2020-02-09');
INSERT INTO public.purchases VALUES (201, 3, 8, '2020-02-09');
INSERT INTO public.purchases VALUES (202, 2, 1, '2020-02-09');
INSERT INTO public.purchases VALUES (203, 1, 8, '2020-02-09');
INSERT INTO public.purchases VALUES (204, 5, 2, '2020-02-09');
INSERT INTO public.purchases VALUES (205, 3, 4, '2020-02-09');
INSERT INTO public.purchases VALUES (206, 6, 3, '2020-02-09');
INSERT INTO public.purchases VALUES (207, 6, 5, '2020-02-09');
INSERT INTO public.purchases VALUES (208, 5, 4, '2020-02-09');
INSERT INTO public.purchases VALUES (209, 2, 3, '2020-02-09');
INSERT INTO public.purchases VALUES (210, 3, 2, '2020-02-10');
INSERT INTO public.purchases VALUES (211, 4, 1, '2020-02-10');
INSERT INTO public.purchases VALUES (212, 2, 8, '2020-02-10');
INSERT INTO public.purchases VALUES (213, 4, 8, '2020-02-10');
INSERT INTO public.purchases VALUES (214, 3, 8, '2020-02-10');
INSERT INTO public.purchases VALUES (215, 3, 2, '2020-02-10');
INSERT INTO public.purchases VALUES (216, 3, 3, '2020-02-10');
INSERT INTO public.purchases VALUES (217, 6, 1, '2020-02-10');
INSERT INTO public.purchases VALUES (218, 1, 8, '2020-02-10');
INSERT INTO public.purchases VALUES (219, 1, 6, '2020-02-10');
INSERT INTO public.purchases VALUES (220, 4, 7, '2020-02-10');
INSERT INTO public.purchases VALUES (221, 1, 8, '2020-02-10');
INSERT INTO public.purchases VALUES (222, 1, 8, '2020-02-10');
INSERT INTO public.purchases VALUES (223, 2, 5, '2020-02-10');
INSERT INTO public.purchases VALUES (224, 2, 7, '2020-02-10');
INSERT INTO public.purchases VALUES (225, 2, 5, '2020-02-10');
INSERT INTO public.purchases VALUES (226, 1, 5, '2020-02-10');
INSERT INTO public.purchases VALUES (227, 1, 3, '2020-02-11');
INSERT INTO public.purchases VALUES (228, 4, 7, '2020-02-11');
INSERT INTO public.purchases VALUES (229, 6, 2, '2020-02-11');
INSERT INTO public.purchases VALUES (230, 3, 4, '2020-02-11');
INSERT INTO public.purchases VALUES (231, 1, 2, '2020-02-11');
INSERT INTO public.purchases VALUES (232, 6, 2, '2020-02-11');
INSERT INTO public.purchases VALUES (233, 4, 2, '2020-02-11');
INSERT INTO public.purchases VALUES (234, 1, 1, '2020-02-11');
INSERT INTO public.purchases VALUES (235, 1, 1, '2020-02-11');
INSERT INTO public.purchases VALUES (236, 3, 2, '2020-02-11');
INSERT INTO public.purchases VALUES (237, 1, 7, '2020-02-11');
INSERT INTO public.purchases VALUES (238, 3, 8, '2020-02-11');
INSERT INTO public.purchases VALUES (239, 3, 8, '2020-02-11');
INSERT INTO public.purchases VALUES (240, 3, 1, '2020-02-11');
INSERT INTO public.purchases VALUES (241, 6, 7, '2020-02-11');
INSERT INTO public.purchases VALUES (242, 2, 3, '2020-02-11');
INSERT INTO public.purchases VALUES (243, 3, 7, '2020-02-11');
INSERT INTO public.purchases VALUES (244, 4, 8, '2020-02-11');
INSERT INTO public.purchases VALUES (245, 1, 1, '2020-02-11');
INSERT INTO public.purchases VALUES (246, 6, 3, '2020-02-11');
INSERT INTO public.purchases VALUES (247, 1, 5, '2020-02-11');
INSERT INTO public.purchases VALUES (248, 3, 1, '2020-02-11');
INSERT INTO public.purchases VALUES (249, 5, 5, '2020-02-11');
INSERT INTO public.purchases VALUES (250, 6, 4, '2020-02-11');
INSERT INTO public.purchases VALUES (251, 6, 2, '2020-02-11');
INSERT INTO public.purchases VALUES (252, 1, 4, '2020-02-11');
INSERT INTO public.purchases VALUES (253, 2, 7, '2020-02-11');
INSERT INTO public.purchases VALUES (254, 6, 6, '2020-02-11');
INSERT INTO public.purchases VALUES (255, 6, 4, '2020-02-11');
INSERT INTO public.purchases VALUES (256, 2, 7, '2020-02-11');
INSERT INTO public.purchases VALUES (257, 4, 7, '2020-02-11');
INSERT INTO public.purchases VALUES (258, 1, 3, '2020-02-11');
INSERT INTO public.purchases VALUES (259, 1, 8, '2020-02-11');
INSERT INTO public.purchases VALUES (260, 4, 1, '2020-02-12');
INSERT INTO public.purchases VALUES (261, 1, 5, '2020-02-12');
INSERT INTO public.purchases VALUES (262, 4, 1, '2020-02-12');
INSERT INTO public.purchases VALUES (263, 1, 2, '2020-02-12');
INSERT INTO public.purchases VALUES (264, 2, 6, '2020-02-12');
INSERT INTO public.purchases VALUES (265, 6, 5, '2020-02-12');
INSERT INTO public.purchases VALUES (266, 2, 1, '2020-02-12');
INSERT INTO public.purchases VALUES (267, 3, 3, '2020-02-12');
INSERT INTO public.purchases VALUES (268, 3, 5, '2020-02-12');
INSERT INTO public.purchases VALUES (269, 2, 1, '2020-02-12');
INSERT INTO public.purchases VALUES (270, 1, 3, '2020-02-12');
INSERT INTO public.purchases VALUES (271, 5, 6, '2020-02-12');
INSERT INTO public.purchases VALUES (272, 2, 6, '2020-02-12');
INSERT INTO public.purchases VALUES (273, 1, 3, '2020-02-12');
INSERT INTO public.purchases VALUES (274, 4, 3, '2020-02-13');
INSERT INTO public.purchases VALUES (275, 4, 5, '2020-02-13');
INSERT INTO public.purchases VALUES (276, 4, 6, '2020-02-13');
INSERT INTO public.purchases VALUES (277, 5, 6, '2020-02-13');
INSERT INTO public.purchases VALUES (278, 5, 2, '2020-02-13');
INSERT INTO public.purchases VALUES (279, 2, 4, '2020-02-13');
INSERT INTO public.purchases VALUES (280, 5, 7, '2020-02-13');
INSERT INTO public.purchases VALUES (281, 1, 2, '2020-02-13');
INSERT INTO public.purchases VALUES (282, 1, 5, '2020-02-13');
INSERT INTO public.purchases VALUES (283, 4, 4, '2020-02-13');
INSERT INTO public.purchases VALUES (284, 4, 3, '2020-02-13');
INSERT INTO public.purchases VALUES (285, 4, 3, '2020-02-13');
INSERT INTO public.purchases VALUES (286, 2, 5, '2020-02-13');
INSERT INTO public.purchases VALUES (287, 4, 7, '2020-02-13');
INSERT INTO public.purchases VALUES (288, 5, 2, '2020-02-13');
INSERT INTO public.purchases VALUES (289, 5, 7, '2020-02-13');
INSERT INTO public.purchases VALUES (290, 4, 5, '2020-02-13');
INSERT INTO public.purchases VALUES (291, 1, 5, '2020-02-13');
INSERT INTO public.purchases VALUES (292, 2, 7, '2020-02-13');
INSERT INTO public.purchases VALUES (293, 3, 1, '2020-02-13');
INSERT INTO public.purchases VALUES (294, 6, 8, '2020-02-13');
INSERT INTO public.purchases VALUES (295, 3, 3, '2020-02-13');
INSERT INTO public.purchases VALUES (296, 1, 5, '2020-02-13');
INSERT INTO public.purchases VALUES (297, 1, 4, '2020-02-13');
INSERT INTO public.purchases VALUES (298, 2, 5, '2020-02-13');
INSERT INTO public.purchases VALUES (299, 5, 7, '2020-02-13');
INSERT INTO public.purchases VALUES (300, 1, 8, '2020-02-13');
INSERT INTO public.purchases VALUES (301, 2, 3, '2020-02-13');
INSERT INTO public.purchases VALUES (302, 3, 2, '2020-02-13');
INSERT INTO public.purchases VALUES (303, 5, 7, '2020-02-13');
INSERT INTO public.purchases VALUES (304, 2, 2, '2020-02-13');
INSERT INTO public.purchases VALUES (305, 4, 1, '2020-02-13');
INSERT INTO public.purchases VALUES (306, 1, 3, '2020-02-13');
INSERT INTO public.purchases VALUES (307, 6, 3, '2020-02-13');
INSERT INTO public.purchases VALUES (308, 4, 7, '2020-02-13');
INSERT INTO public.purchases VALUES (309, 2, 7, '2020-02-13');
INSERT INTO public.purchases VALUES (310, 4, 5, '2020-02-13');
INSERT INTO public.purchases VALUES (311, 4, 4, '2020-02-13');
INSERT INTO public.purchases VALUES (312, 3, 2, '2020-02-14');
INSERT INTO public.purchases VALUES (313, 4, 5, '2020-02-14');
INSERT INTO public.purchases VALUES (314, 2, 6, '2020-02-14');
INSERT INTO public.purchases VALUES (315, 6, 7, '2020-02-14');
INSERT INTO public.purchases VALUES (316, 4, 8, '2020-02-14');
INSERT INTO public.purchases VALUES (317, 1, 3, '2020-02-14');
INSERT INTO public.purchases VALUES (318, 6, 5, '2020-02-14');
INSERT INTO public.purchases VALUES (319, 3, 4, '2020-02-14');
INSERT INTO public.purchases VALUES (320, 6, 8, '2020-02-14');
INSERT INTO public.purchases VALUES (321, 6, 7, '2020-02-14');
INSERT INTO public.purchases VALUES (322, 1, 7, '2020-02-14');
INSERT INTO public.purchases VALUES (323, 2, 8, '2020-02-14');
INSERT INTO public.purchases VALUES (324, 2, 5, '2020-02-14');
INSERT INTO public.purchases VALUES (325, 1, 2, '2020-02-14');
INSERT INTO public.purchases VALUES (326, 1, 8, '2020-02-14');
INSERT INTO public.purchases VALUES (327, 1, 1, '2020-02-14');
INSERT INTO public.purchases VALUES (328, 1, 6, '2020-02-14');
INSERT INTO public.purchases VALUES (329, 5, 4, '2020-02-14');
INSERT INTO public.purchases VALUES (330, 1, 1, '2020-02-14');
INSERT INTO public.purchases VALUES (331, 1, 5, '2020-02-14');
INSERT INTO public.purchases VALUES (332, 1, 3, '2020-02-14');
INSERT INTO public.purchases VALUES (333, 3, 5, '2020-02-14');
INSERT INTO public.purchases VALUES (334, 2, 2, '2020-02-14');
INSERT INTO public.purchases VALUES (335, 3, 4, '2020-02-14');
INSERT INTO public.purchases VALUES (336, 6, 3, '2020-02-14');
INSERT INTO public.purchases VALUES (337, 1, 8, '2020-02-14');
INSERT INTO public.purchases VALUES (338, 4, 2, '2020-02-14');
INSERT INTO public.purchases VALUES (339, 6, 1, '2020-02-14');
INSERT INTO public.purchases VALUES (340, 5, 2, '2020-02-14');
INSERT INTO public.purchases VALUES (341, 5, 2, '2020-02-15');
INSERT INTO public.purchases VALUES (342, 2, 2, '2020-02-15');
INSERT INTO public.purchases VALUES (343, 1, 3, '2020-02-15');
INSERT INTO public.purchases VALUES (344, 5, 7, '2020-02-15');
INSERT INTO public.purchases VALUES (345, 6, 3, '2020-02-15');
INSERT INTO public.purchases VALUES (346, 4, 5, '2020-02-15');
INSERT INTO public.purchases VALUES (347, 1, 4, '2020-02-15');
INSERT INTO public.purchases VALUES (348, 6, 4, '2020-02-15');
INSERT INTO public.purchases VALUES (349, 6, 5, '2020-02-16');
INSERT INTO public.purchases VALUES (350, 4, 4, '2020-02-16');
INSERT INTO public.purchases VALUES (351, 6, 8, '2020-02-16');
INSERT INTO public.purchases VALUES (352, 6, 7, '2020-02-16');
INSERT INTO public.purchases VALUES (353, 1, 2, '2020-02-16');
INSERT INTO public.purchases VALUES (354, 5, 2, '2020-02-16');
INSERT INTO public.purchases VALUES (355, 1, 7, '2020-02-16');
INSERT INTO public.purchases VALUES (356, 3, 1, '2020-02-16');
INSERT INTO public.purchases VALUES (357, 6, 5, '2020-02-16');
INSERT INTO public.purchases VALUES (358, 2, 7, '2020-02-16');
INSERT INTO public.purchases VALUES (359, 6, 6, '2020-02-16');
INSERT INTO public.purchases VALUES (360, 4, 3, '2020-02-16');
INSERT INTO public.purchases VALUES (361, 6, 8, '2020-02-16');
INSERT INTO public.purchases VALUES (362, 5, 4, '2020-02-16');
INSERT INTO public.purchases VALUES (363, 6, 7, '2020-02-16');
INSERT INTO public.purchases VALUES (364, 6, 6, '2020-02-16');
INSERT INTO public.purchases VALUES (365, 3, 8, '2020-02-16');
INSERT INTO public.purchases VALUES (366, 6, 1, '2020-02-16');
INSERT INTO public.purchases VALUES (367, 2, 4, '2020-02-16');
INSERT INTO public.purchases VALUES (368, 5, 3, '2020-02-16');
INSERT INTO public.purchases VALUES (369, 4, 5, '2020-02-16');
INSERT INTO public.purchases VALUES (370, 3, 4, '2020-02-16');
INSERT INTO public.purchases VALUES (371, 6, 7, '2020-02-16');
INSERT INTO public.purchases VALUES (372, 4, 3, '2020-02-16');
INSERT INTO public.purchases VALUES (373, 6, 3, '2020-02-16');
INSERT INTO public.purchases VALUES (374, 1, 2, '2020-02-16');
INSERT INTO public.purchases VALUES (375, 2, 4, '2020-02-16');
INSERT INTO public.purchases VALUES (376, 5, 4, '2020-02-16');
INSERT INTO public.purchases VALUES (377, 3, 4, '2020-02-16');
INSERT INTO public.purchases VALUES (378, 3, 5, '2020-02-16');
INSERT INTO public.purchases VALUES (379, 5, 6, '2020-02-16');
INSERT INTO public.purchases VALUES (380, 1, 4, '2020-02-17');
INSERT INTO public.purchases VALUES (381, 5, 1, '2020-02-17');
INSERT INTO public.purchases VALUES (382, 5, 3, '2020-02-17');
INSERT INTO public.purchases VALUES (383, 4, 7, '2020-02-17');
INSERT INTO public.purchases VALUES (384, 4, 6, '2020-02-17');
INSERT INTO public.purchases VALUES (385, 5, 3, '2020-02-17');
INSERT INTO public.purchases VALUES (386, 3, 5, '2020-02-17');
INSERT INTO public.purchases VALUES (387, 5, 4, '2020-02-17');
INSERT INTO public.purchases VALUES (388, 3, 8, '2020-02-17');
INSERT INTO public.purchases VALUES (389, 3, 8, '2020-02-17');
INSERT INTO public.purchases VALUES (390, 1, 4, '2020-02-17');
INSERT INTO public.purchases VALUES (391, 6, 6, '2020-02-17');
INSERT INTO public.purchases VALUES (392, 3, 3, '2020-02-17');
INSERT INTO public.purchases VALUES (393, 4, 8, '2020-02-17');
INSERT INTO public.purchases VALUES (394, 2, 2, '2020-02-17');
INSERT INTO public.purchases VALUES (395, 3, 6, '2020-02-17');
INSERT INTO public.purchases VALUES (396, 6, 4, '2020-02-17');
INSERT INTO public.purchases VALUES (397, 2, 8, '2020-02-17');
INSERT INTO public.purchases VALUES (398, 1, 2, '2020-02-17');
INSERT INTO public.purchases VALUES (399, 4, 3, '2020-02-17');
INSERT INTO public.purchases VALUES (400, 4, 3, '2020-02-17');
INSERT INTO public.purchases VALUES (401, 5, 3, '2020-02-17');
INSERT INTO public.purchases VALUES (402, 3, 1, '2020-02-17');
INSERT INTO public.purchases VALUES (403, 2, 5, '2020-02-17');
INSERT INTO public.purchases VALUES (404, 1, 5, '2020-02-17');
INSERT INTO public.purchases VALUES (405, 6, 1, '2020-02-17');
INSERT INTO public.purchases VALUES (406, 3, 3, '2020-02-18');
INSERT INTO public.purchases VALUES (407, 3, 2, '2020-02-18');
INSERT INTO public.purchases VALUES (408, 3, 4, '2020-02-18');
INSERT INTO public.purchases VALUES (409, 2, 3, '2020-02-18');
INSERT INTO public.purchases VALUES (410, 1, 5, '2020-02-18');
INSERT INTO public.purchases VALUES (411, 2, 8, '2020-02-18');
INSERT INTO public.purchases VALUES (412, 4, 5, '2020-02-18');
INSERT INTO public.purchases VALUES (413, 2, 3, '2020-02-18');
INSERT INTO public.purchases VALUES (414, 5, 2, '2020-02-18');
INSERT INTO public.purchases VALUES (415, 6, 4, '2020-02-18');
INSERT INTO public.purchases VALUES (416, 5, 3, '2020-02-18');
INSERT INTO public.purchases VALUES (417, 3, 3, '2020-02-18');
INSERT INTO public.purchases VALUES (418, 4, 4, '2020-02-18');
INSERT INTO public.purchases VALUES (419, 1, 4, '2020-02-18');
INSERT INTO public.purchases VALUES (420, 2, 2, '2020-02-18');
INSERT INTO public.purchases VALUES (421, 3, 2, '2020-02-18');
INSERT INTO public.purchases VALUES (422, 5, 7, '2020-02-18');
INSERT INTO public.purchases VALUES (423, 2, 8, '2020-02-18');
INSERT INTO public.purchases VALUES (424, 5, 7, '2020-02-18');
INSERT INTO public.purchases VALUES (425, 6, 4, '2020-02-18');
INSERT INTO public.purchases VALUES (426, 4, 6, '2020-02-18');
INSERT INTO public.purchases VALUES (427, 2, 5, '2020-02-18');
INSERT INTO public.purchases VALUES (428, 3, 3, '2020-02-18');
INSERT INTO public.purchases VALUES (429, 2, 6, '2020-02-18');
INSERT INTO public.purchases VALUES (430, 2, 7, '2020-02-18');
INSERT INTO public.purchases VALUES (431, 2, 7, '2020-02-18');
INSERT INTO public.purchases VALUES (432, 6, 5, '2020-02-18');
INSERT INTO public.purchases VALUES (433, 5, 6, '2020-02-18');
INSERT INTO public.purchases VALUES (434, 3, 8, '2020-02-18');
INSERT INTO public.purchases VALUES (435, 4, 5, '2020-02-18');
INSERT INTO public.purchases VALUES (436, 6, 3, '2020-02-18');
INSERT INTO public.purchases VALUES (437, 4, 6, '2020-02-18');
INSERT INTO public.purchases VALUES (438, 1, 1, '2020-02-18');
INSERT INTO public.purchases VALUES (439, 1, 5, '2020-02-18');
INSERT INTO public.purchases VALUES (440, 4, 5, '2020-02-18');
INSERT INTO public.purchases VALUES (441, 3, 3, '2020-02-18');
INSERT INTO public.purchases VALUES (442, 1, 1, '2020-02-18');
INSERT INTO public.purchases VALUES (443, 3, 1, '2020-02-18');
INSERT INTO public.purchases VALUES (444, 6, 2, '2020-02-18');
INSERT INTO public.purchases VALUES (445, 5, 5, '2020-02-19');
INSERT INTO public.purchases VALUES (446, 4, 8, '2020-02-19');
INSERT INTO public.purchases VALUES (447, 4, 5, '2020-02-19');
INSERT INTO public.purchases VALUES (448, 3, 6, '2020-02-19');
INSERT INTO public.purchases VALUES (449, 1, 1, '2020-02-19');
INSERT INTO public.purchases VALUES (450, 1, 3, '2020-02-19');
INSERT INTO public.purchases VALUES (451, 3, 7, '2020-02-19');
INSERT INTO public.purchases VALUES (452, 5, 8, '2020-02-19');
INSERT INTO public.purchases VALUES (453, 3, 7, '2020-02-19');
INSERT INTO public.purchases VALUES (454, 6, 8, '2020-02-19');
INSERT INTO public.purchases VALUES (455, 1, 6, '2020-02-19');
INSERT INTO public.purchases VALUES (456, 5, 8, '2020-02-19');
INSERT INTO public.purchases VALUES (457, 4, 7, '2020-02-19');
INSERT INTO public.purchases VALUES (458, 5, 2, '2020-02-19');
INSERT INTO public.purchases VALUES (459, 3, 1, '2020-02-19');
INSERT INTO public.purchases VALUES (460, 5, 3, '2020-02-19');
INSERT INTO public.purchases VALUES (461, 3, 5, '2020-02-19');
INSERT INTO public.purchases VALUES (462, 5, 4, '2020-02-19');
INSERT INTO public.purchases VALUES (463, 2, 7, '2020-02-19');
INSERT INTO public.purchases VALUES (464, 6, 3, '2020-02-19');
INSERT INTO public.purchases VALUES (465, 2, 6, '2020-02-19');
INSERT INTO public.purchases VALUES (466, 2, 6, '2020-02-19');
INSERT INTO public.purchases VALUES (467, 3, 2, '2020-02-19');
INSERT INTO public.purchases VALUES (468, 4, 5, '2020-02-19');
INSERT INTO public.purchases VALUES (469, 4, 5, '2020-02-19');
INSERT INTO public.purchases VALUES (470, 1, 7, '2020-02-19');
INSERT INTO public.purchases VALUES (471, 4, 2, '2020-02-19');
INSERT INTO public.purchases VALUES (472, 2, 1, '2020-02-19');
INSERT INTO public.purchases VALUES (473, 5, 6, '2020-02-19');
INSERT INTO public.purchases VALUES (474, 2, 8, '2020-02-19');
INSERT INTO public.purchases VALUES (475, 1, 7, '2020-02-20');
INSERT INTO public.purchases VALUES (476, 6, 7, '2020-02-20');
INSERT INTO public.purchases VALUES (477, 5, 2, '2020-02-20');
INSERT INTO public.purchases VALUES (478, 4, 8, '2020-02-20');
INSERT INTO public.purchases VALUES (479, 6, 4, '2020-02-20');
INSERT INTO public.purchases VALUES (480, 4, 1, '2020-02-20');
INSERT INTO public.purchases VALUES (481, 4, 2, '2020-02-20');
INSERT INTO public.purchases VALUES (482, 2, 3, '2020-02-20');
INSERT INTO public.purchases VALUES (483, 4, 6, '2020-02-20');
INSERT INTO public.purchases VALUES (484, 6, 6, '2020-02-20');
INSERT INTO public.purchases VALUES (485, 6, 5, '2020-02-20');
INSERT INTO public.purchases VALUES (486, 4, 5, '2020-02-20');
INSERT INTO public.purchases VALUES (487, 4, 3, '2020-02-20');
INSERT INTO public.purchases VALUES (488, 3, 1, '2020-02-20');
INSERT INTO public.purchases VALUES (489, 3, 8, '2020-02-20');
INSERT INTO public.purchases VALUES (490, 5, 3, '2020-02-20');
INSERT INTO public.purchases VALUES (491, 6, 1, '2020-02-20');
INSERT INTO public.purchases VALUES (492, 1, 4, '2020-02-20');
INSERT INTO public.purchases VALUES (493, 5, 7, '2020-02-20');
INSERT INTO public.purchases VALUES (494, 2, 4, '2020-02-20');
INSERT INTO public.purchases VALUES (495, 5, 7, '2020-02-20');
INSERT INTO public.purchases VALUES (496, 4, 8, '2020-02-20');
INSERT INTO public.purchases VALUES (497, 6, 2, '2020-02-20');
INSERT INTO public.purchases VALUES (498, 2, 6, '2020-02-20');
INSERT INTO public.purchases VALUES (499, 4, 8, '2020-02-21');
INSERT INTO public.purchases VALUES (500, 4, 2, '2020-02-21');
INSERT INTO public.purchases VALUES (501, 4, 8, '2020-02-21');
INSERT INTO public.purchases VALUES (502, 1, 1, '2020-02-21');
INSERT INTO public.purchases VALUES (503, 6, 1, '2020-02-21');
INSERT INTO public.purchases VALUES (504, 1, 5, '2020-02-21');
INSERT INTO public.purchases VALUES (505, 6, 3, '2020-02-21');
INSERT INTO public.purchases VALUES (506, 2, 5, '2020-02-21');
INSERT INTO public.purchases VALUES (507, 1, 4, '2020-02-21');
INSERT INTO public.purchases VALUES (508, 3, 8, '2020-02-21');
INSERT INTO public.purchases VALUES (509, 6, 6, '2020-02-21');
INSERT INTO public.purchases VALUES (510, 6, 5, '2020-02-21');
INSERT INTO public.purchases VALUES (511, 2, 2, '2020-02-21');
INSERT INTO public.purchases VALUES (512, 5, 8, '2020-02-21');
INSERT INTO public.purchases VALUES (513, 5, 7, '2020-02-21');
INSERT INTO public.purchases VALUES (514, 6, 7, '2020-02-21');
INSERT INTO public.purchases VALUES (515, 4, 8, '2020-02-21');
INSERT INTO public.purchases VALUES (516, 6, 3, '2020-02-21');
INSERT INTO public.purchases VALUES (517, 6, 6, '2020-02-21');
INSERT INTO public.purchases VALUES (518, 6, 2, '2020-02-21');
INSERT INTO public.purchases VALUES (519, 4, 2, '2020-02-21');
INSERT INTO public.purchases VALUES (520, 1, 4, '2020-02-21');
INSERT INTO public.purchases VALUES (521, 2, 2, '2020-02-21');
INSERT INTO public.purchases VALUES (522, 6, 8, '2020-02-21');
INSERT INTO public.purchases VALUES (523, 2, 1, '2020-02-21');
INSERT INTO public.purchases VALUES (524, 3, 5, '2020-02-21');
INSERT INTO public.purchases VALUES (525, 2, 2, '2020-02-21');
INSERT INTO public.purchases VALUES (526, 4, 3, '2020-02-21');
INSERT INTO public.purchases VALUES (527, 5, 3, '2020-02-21');
INSERT INTO public.purchases VALUES (528, 4, 1, '2020-02-21');
INSERT INTO public.purchases VALUES (529, 3, 8, '2020-02-21');
INSERT INTO public.purchases VALUES (530, 1, 1, '2020-02-21');
INSERT INTO public.purchases VALUES (531, 4, 2, '2020-02-21');
INSERT INTO public.purchases VALUES (532, 5, 7, '2020-02-21');
INSERT INTO public.purchases VALUES (533, 2, 5, '2020-02-21');
INSERT INTO public.purchases VALUES (534, 2, 8, '2020-02-21');
INSERT INTO public.purchases VALUES (535, 3, 3, '2020-02-21');
INSERT INTO public.purchases VALUES (536, 3, 4, '2020-02-21');
INSERT INTO public.purchases VALUES (537, 5, 2, '2020-02-21');
INSERT INTO public.purchases VALUES (538, 1, 3, '2020-02-21');
INSERT INTO public.purchases VALUES (539, 5, 7, '2020-02-21');
INSERT INTO public.purchases VALUES (540, 2, 1, '2020-02-21');
INSERT INTO public.purchases VALUES (541, 3, 7, '2020-02-21');
INSERT INTO public.purchases VALUES (542, 4, 5, '2020-02-21');
INSERT INTO public.purchases VALUES (543, 3, 7, '2020-02-21');
INSERT INTO public.purchases VALUES (544, 2, 2, '2020-02-22');
INSERT INTO public.purchases VALUES (545, 5, 6, '2020-02-22');
INSERT INTO public.purchases VALUES (546, 6, 3, '2020-02-22');
INSERT INTO public.purchases VALUES (547, 6, 8, '2020-02-22');
INSERT INTO public.purchases VALUES (548, 4, 8, '2020-02-22');
INSERT INTO public.purchases VALUES (549, 6, 1, '2020-02-22');
INSERT INTO public.purchases VALUES (550, 5, 3, '2020-02-22');
INSERT INTO public.purchases VALUES (551, 6, 2, '2020-02-22');
INSERT INTO public.purchases VALUES (552, 5, 8, '2020-02-22');
INSERT INTO public.purchases VALUES (553, 6, 4, '2020-02-22');
INSERT INTO public.purchases VALUES (554, 3, 5, '2020-02-22');
INSERT INTO public.purchases VALUES (555, 4, 1, '2020-02-22');
INSERT INTO public.purchases VALUES (556, 2, 3, '2020-02-22');
INSERT INTO public.purchases VALUES (557, 5, 6, '2020-02-22');
INSERT INTO public.purchases VALUES (558, 6, 6, '2020-02-22');
INSERT INTO public.purchases VALUES (559, 1, 1, '2020-02-22');
INSERT INTO public.purchases VALUES (560, 2, 5, '2020-02-22');
INSERT INTO public.purchases VALUES (561, 3, 5, '2020-02-22');
INSERT INTO public.purchases VALUES (562, 1, 2, '2020-02-22');
INSERT INTO public.purchases VALUES (563, 4, 8, '2020-02-22');
INSERT INTO public.purchases VALUES (564, 1, 5, '2020-02-22');
INSERT INTO public.purchases VALUES (565, 4, 2, '2020-02-22');
INSERT INTO public.purchases VALUES (566, 5, 5, '2020-02-22');
INSERT INTO public.purchases VALUES (567, 6, 2, '2020-02-22');
INSERT INTO public.purchases VALUES (568, 5, 1, '2020-02-22');
INSERT INTO public.purchases VALUES (569, 1, 8, '2020-02-22');
INSERT INTO public.purchases VALUES (570, 5, 4, '2020-02-22');
INSERT INTO public.purchases VALUES (571, 5, 4, '2020-02-22');
INSERT INTO public.purchases VALUES (572, 4, 1, '2020-02-22');
INSERT INTO public.purchases VALUES (573, 5, 8, '2020-02-22');
INSERT INTO public.purchases VALUES (574, 4, 1, '2020-02-22');
INSERT INTO public.purchases VALUES (575, 3, 7, '2020-02-22');
INSERT INTO public.purchases VALUES (576, 5, 3, '2020-02-22');
INSERT INTO public.purchases VALUES (577, 4, 4, '2020-02-22');
INSERT INTO public.purchases VALUES (578, 2, 5, '2020-02-22');
INSERT INTO public.purchases VALUES (579, 3, 2, '2020-02-22');
INSERT INTO public.purchases VALUES (580, 2, 8, '2020-02-22');
INSERT INTO public.purchases VALUES (581, 6, 3, '2020-02-22');
INSERT INTO public.purchases VALUES (582, 2, 7, '2020-02-22');
INSERT INTO public.purchases VALUES (583, 4, 3, '2020-02-22');
INSERT INTO public.purchases VALUES (584, 1, 6, '2020-02-22');
INSERT INTO public.purchases VALUES (585, 5, 6, '2020-02-22');
INSERT INTO public.purchases VALUES (586, 4, 3, '2020-02-22');
INSERT INTO public.purchases VALUES (587, 1, 5, '2020-02-22');
INSERT INTO public.purchases VALUES (588, 2, 4, '2020-02-22');
INSERT INTO public.purchases VALUES (589, 1, 8, '2020-02-23');
INSERT INTO public.purchases VALUES (590, 5, 4, '2020-02-23');
INSERT INTO public.purchases VALUES (591, 5, 8, '2020-02-23');
INSERT INTO public.purchases VALUES (592, 3, 6, '2020-02-23');
INSERT INTO public.purchases VALUES (593, 1, 1, '2020-02-23');
INSERT INTO public.purchases VALUES (594, 4, 6, '2020-02-23');
INSERT INTO public.purchases VALUES (595, 3, 3, '2020-02-23');
INSERT INTO public.purchases VALUES (596, 3, 1, '2020-02-23');
INSERT INTO public.purchases VALUES (597, 5, 4, '2020-02-23');
INSERT INTO public.purchases VALUES (598, 5, 3, '2020-02-23');
INSERT INTO public.purchases VALUES (599, 2, 2, '2020-02-23');
INSERT INTO public.purchases VALUES (600, 3, 4, '2020-02-23');
INSERT INTO public.purchases VALUES (601, 2, 5, '2020-02-23');
INSERT INTO public.purchases VALUES (602, 6, 2, '2020-02-23');
INSERT INTO public.purchases VALUES (603, 5, 3, '2020-02-23');
INSERT INTO public.purchases VALUES (604, 6, 5, '2020-02-23');
INSERT INTO public.purchases VALUES (605, 4, 7, '2020-02-23');
INSERT INTO public.purchases VALUES (606, 2, 2, '2020-02-24');
INSERT INTO public.purchases VALUES (607, 5, 2, '2020-02-24');
INSERT INTO public.purchases VALUES (608, 5, 2, '2020-02-24');
INSERT INTO public.purchases VALUES (609, 3, 2, '2020-02-24');
INSERT INTO public.purchases VALUES (610, 5, 3, '2020-02-24');
INSERT INTO public.purchases VALUES (611, 2, 2, '2020-02-24');
INSERT INTO public.purchases VALUES (612, 6, 8, '2020-02-24');
INSERT INTO public.purchases VALUES (613, 5, 8, '2020-02-24');
INSERT INTO public.purchases VALUES (614, 2, 5, '2020-02-24');
INSERT INTO public.purchases VALUES (615, 2, 6, '2020-02-24');
INSERT INTO public.purchases VALUES (616, 6, 8, '2020-02-24');
INSERT INTO public.purchases VALUES (617, 3, 3, '2020-02-24');
INSERT INTO public.purchases VALUES (618, 1, 7, '2020-02-24');
INSERT INTO public.purchases VALUES (619, 2, 3, '2020-02-24');
INSERT INTO public.purchases VALUES (620, 1, 3, '2020-02-24');
INSERT INTO public.purchases VALUES (621, 3, 6, '2020-02-24');
INSERT INTO public.purchases VALUES (622, 6, 3, '2020-02-24');
INSERT INTO public.purchases VALUES (623, 2, 1, '2020-02-24');
INSERT INTO public.purchases VALUES (624, 5, 7, '2020-02-24');
INSERT INTO public.purchases VALUES (625, 5, 4, '2020-02-24');
INSERT INTO public.purchases VALUES (626, 6, 3, '2020-02-24');
INSERT INTO public.purchases VALUES (627, 5, 3, '2020-02-24');
INSERT INTO public.purchases VALUES (628, 3, 8, '2020-02-24');
INSERT INTO public.purchases VALUES (629, 1, 6, '2020-02-24');
INSERT INTO public.purchases VALUES (630, 1, 7, '2020-02-24');
INSERT INTO public.purchases VALUES (631, 1, 1, '2020-02-24');
INSERT INTO public.purchases VALUES (632, 1, 3, '2020-02-24');
INSERT INTO public.purchases VALUES (633, 5, 7, '2020-02-24');
INSERT INTO public.purchases VALUES (634, 6, 1, '2020-02-24');
INSERT INTO public.purchases VALUES (635, 3, 5, '2020-02-24');
INSERT INTO public.purchases VALUES (636, 6, 7, '2020-02-24');
INSERT INTO public.purchases VALUES (637, 5, 7, '2020-02-24');
INSERT INTO public.purchases VALUES (638, 6, 2, '2020-02-24');
INSERT INTO public.purchases VALUES (639, 1, 8, '2020-02-24');
INSERT INTO public.purchases VALUES (640, 1, 8, '2020-02-24');
INSERT INTO public.purchases VALUES (641, 3, 7, '2020-02-25');
INSERT INTO public.purchases VALUES (642, 1, 3, '2020-02-25');
INSERT INTO public.purchases VALUES (643, 2, 1, '2020-02-25');
INSERT INTO public.purchases VALUES (644, 6, 7, '2020-02-25');
INSERT INTO public.purchases VALUES (645, 4, 8, '2020-02-25');
INSERT INTO public.purchases VALUES (646, 4, 3, '2020-02-25');
INSERT INTO public.purchases VALUES (647, 4, 1, '2020-02-25');
INSERT INTO public.purchases VALUES (648, 6, 1, '2020-02-25');
INSERT INTO public.purchases VALUES (649, 5, 6, '2020-02-25');
INSERT INTO public.purchases VALUES (650, 6, 8, '2020-02-25');
INSERT INTO public.purchases VALUES (651, 2, 6, '2020-02-25');
INSERT INTO public.purchases VALUES (652, 2, 8, '2020-02-25');
INSERT INTO public.purchases VALUES (653, 3, 7, '2020-02-25');
INSERT INTO public.purchases VALUES (654, 3, 6, '2020-02-25');
INSERT INTO public.purchases VALUES (655, 6, 3, '2020-02-25');
INSERT INTO public.purchases VALUES (656, 6, 1, '2020-02-25');
INSERT INTO public.purchases VALUES (657, 5, 5, '2020-02-25');
INSERT INTO public.purchases VALUES (658, 2, 3, '2020-02-25');
INSERT INTO public.purchases VALUES (659, 3, 7, '2020-02-25');
INSERT INTO public.purchases VALUES (660, 2, 6, '2020-02-25');
INSERT INTO public.purchases VALUES (661, 2, 2, '2020-02-25');
INSERT INTO public.purchases VALUES (662, 5, 1, '2020-02-25');
INSERT INTO public.purchases VALUES (663, 1, 5, '2020-02-25');
INSERT INTO public.purchases VALUES (664, 3, 5, '2020-02-25');
INSERT INTO public.purchases VALUES (665, 4, 2, '2020-02-25');
INSERT INTO public.purchases VALUES (666, 5, 7, '2020-02-25');
INSERT INTO public.purchases VALUES (667, 1, 5, '2020-02-25');
INSERT INTO public.purchases VALUES (668, 5, 3, '2020-02-25');
INSERT INTO public.purchases VALUES (669, 5, 3, '2020-02-25');
INSERT INTO public.purchases VALUES (670, 5, 3, '2020-02-25');
INSERT INTO public.purchases VALUES (671, 2, 4, '2020-02-25');
INSERT INTO public.purchases VALUES (672, 3, 7, '2020-02-25');
INSERT INTO public.purchases VALUES (673, 4, 2, '2020-02-25');
INSERT INTO public.purchases VALUES (674, 4, 5, '2020-02-25');
INSERT INTO public.purchases VALUES (675, 3, 1, '2020-02-25');
INSERT INTO public.purchases VALUES (676, 1, 4, '2020-02-25');
INSERT INTO public.purchases VALUES (677, 3, 7, '2020-02-25');
INSERT INTO public.purchases VALUES (678, 2, 6, '2020-02-25');
INSERT INTO public.purchases VALUES (679, 2, 1, '2020-02-26');
INSERT INTO public.purchases VALUES (680, 6, 8, '2020-02-26');
INSERT INTO public.purchases VALUES (681, 6, 7, '2020-02-26');
INSERT INTO public.purchases VALUES (682, 2, 3, '2020-02-26');
INSERT INTO public.purchases VALUES (683, 1, 8, '2020-02-26');
INSERT INTO public.purchases VALUES (684, 2, 8, '2020-02-26');
INSERT INTO public.purchases VALUES (685, 1, 3, '2020-02-26');
INSERT INTO public.purchases VALUES (686, 1, 5, '2020-02-26');
INSERT INTO public.purchases VALUES (687, 5, 5, '2020-02-26');
INSERT INTO public.purchases VALUES (688, 4, 8, '2020-02-26');
INSERT INTO public.purchases VALUES (689, 5, 8, '2020-02-26');
INSERT INTO public.purchases VALUES (690, 5, 4, '2020-02-26');
INSERT INTO public.purchases VALUES (691, 2, 2, '2020-02-26');
INSERT INTO public.purchases VALUES (692, 5, 3, '2020-02-26');
INSERT INTO public.purchases VALUES (693, 2, 5, '2020-02-26');
INSERT INTO public.purchases VALUES (694, 2, 4, '2020-02-26');
INSERT INTO public.purchases VALUES (695, 3, 3, '2020-02-26');
INSERT INTO public.purchases VALUES (696, 3, 5, '2020-02-26');
INSERT INTO public.purchases VALUES (697, 4, 8, '2020-02-26');
INSERT INTO public.purchases VALUES (698, 4, 8, '2020-02-26');
INSERT INTO public.purchases VALUES (699, 2, 6, '2020-02-26');
INSERT INTO public.purchases VALUES (700, 2, 6, '2020-02-26');
INSERT INTO public.purchases VALUES (701, 4, 8, '2020-02-26');
INSERT INTO public.purchases VALUES (702, 3, 8, '2020-02-26');
INSERT INTO public.purchases VALUES (703, 1, 3, '2020-02-26');
INSERT INTO public.purchases VALUES (704, 2, 2, '2020-02-26');
INSERT INTO public.purchases VALUES (705, 5, 5, '2020-02-28');
INSERT INTO public.purchases VALUES (706, 6, 4, '2020-02-28');
INSERT INTO public.purchases VALUES (707, 6, 8, '2020-02-28');
INSERT INTO public.purchases VALUES (708, 3, 7, '2020-02-28');
INSERT INTO public.purchases VALUES (709, 4, 3, '2020-02-28');
INSERT INTO public.purchases VALUES (710, 5, 2, '2020-02-28');
INSERT INTO public.purchases VALUES (711, 1, 4, '2020-02-28');
INSERT INTO public.purchases VALUES (712, 4, 6, '2020-02-28');
INSERT INTO public.purchases VALUES (713, 6, 1, '2020-02-28');
INSERT INTO public.purchases VALUES (714, 3, 6, '2020-02-28');
INSERT INTO public.purchases VALUES (715, 6, 1, '2020-02-28');
INSERT INTO public.purchases VALUES (716, 2, 8, '2020-02-28');
INSERT INTO public.purchases VALUES (717, 4, 2, '2020-02-28');
INSERT INTO public.purchases VALUES (718, 2, 6, '2020-02-28');
INSERT INTO public.purchases VALUES (719, 4, 5, '2020-02-28');
INSERT INTO public.purchases VALUES (720, 2, 8, '2020-02-29');
INSERT INTO public.purchases VALUES (721, 2, 7, '2020-02-29');
INSERT INTO public.purchases VALUES (722, 3, 1, '2020-02-29');
INSERT INTO public.purchases VALUES (723, 1, 6, '2020-02-29');
INSERT INTO public.purchases VALUES (724, 1, 5, '2020-02-29');
INSERT INTO public.purchases VALUES (725, 4, 6, '2020-02-29');
INSERT INTO public.purchases VALUES (726, 5, 3, '2020-02-29');
INSERT INTO public.purchases VALUES (727, 6, 3, '2020-02-29');
INSERT INTO public.purchases VALUES (728, 5, 1, '2020-02-29');
INSERT INTO public.purchases VALUES (729, 1, 3, '2020-02-29');
INSERT INTO public.purchases VALUES (730, 2, 2, '2020-02-29');
INSERT INTO public.purchases VALUES (731, 6, 7, '2020-02-29');


--
-- Name: buyers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.buyers_id_seq', 1, false);


--
-- Name: goods_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.goods_id_seq', 1, false);


--
-- Name: purchases_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.purchases_id_seq', 731, true);


--
-- Name: buyers buyers_pk; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.buyers
    ADD CONSTRAINT buyers_pk PRIMARY KEY (id);


--
-- Name: goods goods_pk; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.goods
    ADD CONSTRAINT goods_pk PRIMARY KEY (id);


--
-- Name: purchases purchases_pk; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_pk PRIMARY KEY (id);


--
-- Name: buyers_id_uindex; Type: INDEX; Schema: public; Owner: user
--

CREATE UNIQUE INDEX buyers_id_uindex ON public.buyers USING btree (id);


--
-- Name: goods_id_uindex; Type: INDEX; Schema: public; Owner: user
--

CREATE UNIQUE INDEX goods_id_uindex ON public.goods USING btree (id);


--
-- Name: goods_title_uindex; Type: INDEX; Schema: public; Owner: user
--

CREATE UNIQUE INDEX goods_title_uindex ON public.goods USING btree (title);


--
-- Name: purchases_id_uindex; Type: INDEX; Schema: public; Owner: user
--

CREATE UNIQUE INDEX purchases_id_uindex ON public.purchases USING btree (id);


--
-- Name: purchases purchases_buyers_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_buyers_id_fk FOREIGN KEY (buyer_id) REFERENCES public.buyers(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: purchases purchases_goods_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_goods_id_fk FOREIGN KEY (good_id) REFERENCES public.goods(id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

