PGDMP     8    3            	    {            CovidCenterService    14.5    14.5                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            	           1262    28506    CovidCenterService    DATABASE     q   CREATE DATABASE "CovidCenterService" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
 $   DROP DATABASE "CovidCenterService";
                postgres    false            �            1259    28508 
   assistants    TABLE     �  CREATE TABLE public.assistants (
    id bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    ip character varying(15),
    last_enter_date date,
    type integer DEFAULT 0 NOT NULL,
    last_name character varying(255) NOT NULL,
    patronymic character varying(255),
    last_enter_time time without time zone
);
    DROP TABLE public.assistants;
       public         heap    postgres    false            �            1259    28526    patients_info    TABLE     Y  CREATE TABLE public.patients_info (
    id bigint NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    birthday date,
    patronymic character varying(50) NOT NULL,
    passport bigint NOT NULL,
    phone_number character varying(15),
    email text,
    insurance_policy bigint NOT NULL
);
 !   DROP TABLE public.patients_info;
       public         heap    postgres    false            �            1259    28525    patients_id_seq    SEQUENCE     x   CREATE SEQUENCE public.patients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.patients_id_seq;
       public          postgres    false    213            
           0    0    patients_id_seq    SEQUENCE OWNED BY     H   ALTER SEQUENCE public.patients_id_seq OWNED BY public.patients_info.id;
          public          postgres    false    212            �            1259    28518    services    TABLE     �   CREATE TABLE public.services (
    code integer NOT NULL,
    service_name character varying(255) NOT NULL,
    price double precision NOT NULL,
    deadline integer,
    "avп_deviation" double precision
);
    DROP TABLE public.services;
       public         heap    postgres    false            �            1259    28507    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    210                       0    0    users_id_seq    SEQUENCE OWNED BY     B   ALTER SEQUENCE public.users_id_seq OWNED BY public.assistants.id;
          public          postgres    false    209            e           2604    28511    assistants id    DEFAULT     i   ALTER TABLE ONLY public.assistants ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 <   ALTER TABLE public.assistants ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209    210            g           2604    28529    patients_info id    DEFAULT     o   ALTER TABLE ONLY public.patients_info ALTER COLUMN id SET DEFAULT nextval('public.patients_id_seq'::regclass);
 ?   ALTER TABLE public.patients_info ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    213    212    213                       0    28508 
   assistants 
   TABLE DATA           �   COPY public.assistants (id, first_name, login, password, ip, last_enter_date, type, last_name, patronymic, last_enter_time) FROM stdin;
    public          postgres    false    210   �                 0    28526    patients_info 
   TABLE DATA           �   COPY public.patients_info (id, first_name, last_name, birthday, patronymic, passport, phone_number, email, insurance_policy) FROM stdin;
    public          postgres    false    213   �                 0    28518    services 
   TABLE DATA           Y   COPY public.services (code, service_name, price, deadline, "avп_deviation") FROM stdin;
    public          postgres    false    211   �                  0    0    patients_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.patients_id_seq', 1, false);
          public          postgres    false    212                       0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 1, false);
          public          postgres    false    209            s           2606    28533    patients_info patients_pk 
   CONSTRAINT     W   ALTER TABLE ONLY public.patients_info
    ADD CONSTRAINT patients_pk PRIMARY KEY (id);
 C   ALTER TABLE ONLY public.patients_info DROP CONSTRAINT patients_pk;
       public            postgres    false    213            m           2606    28524    services services_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public.services
    ADD CONSTRAINT services_pk PRIMARY KEY (code);
 >   ALTER TABLE ONLY public.services DROP CONSTRAINT services_pk;
       public            postgres    false    211            j           2606    28516    assistants users_pk 
   CONSTRAINT     Q   ALTER TABLE ONLY public.assistants
    ADD CONSTRAINT users_pk PRIMARY KEY (id);
 =   ALTER TABLE ONLY public.assistants DROP CONSTRAINT users_pk;
       public            postgres    false    210            o           1259    28534    patients_email_uindex    INDEX     W   CREATE UNIQUE INDEX patients_email_uindex ON public.patients_info USING btree (email);
 )   DROP INDEX public.patients_email_uindex;
       public            postgres    false    213            p           1259    28535    patients_id_uindex    INDEX     Q   CREATE UNIQUE INDEX patients_id_uindex ON public.patients_info USING btree (id);
 &   DROP INDEX public.patients_id_uindex;
       public            postgres    false    213            q           1259    28536     patients_insurance_policy_uindex    INDEX     m   CREATE UNIQUE INDEX patients_insurance_policy_uindex ON public.patients_info USING btree (insurance_policy);
 4   DROP INDEX public.patients_insurance_policy_uindex;
       public            postgres    false    213            k           1259    28521    services_code_uindex    INDEX     P   CREATE UNIQUE INDEX services_code_uindex ON public.services USING btree (code);
 (   DROP INDEX public.services_code_uindex;
       public            postgres    false    211            n           1259    28522    services_service_uindex    INDEX     [   CREATE UNIQUE INDEX services_service_uindex ON public.services USING btree (service_name);
 +   DROP INDEX public.services_service_uindex;
       public            postgres    false    211            h           1259    28517    users_id_uindex    INDEX     K   CREATE UNIQUE INDEX users_id_uindex ON public.assistants USING btree (id);
 #   DROP INDEX public.users_id_uindex;
       public            postgres    false    210                   x������ � �            x������ � �            x������ � �     