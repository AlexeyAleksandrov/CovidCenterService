PGDMP     /    4            	    {            CovidCenterService    14.5    14.5 z    �
           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �
           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �
           1262    28895    CovidCenterService    DATABASE     q   CREATE DATABASE "CovidCenterService" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
 $   DROP DATABASE "CovidCenterService";
                postgres    false            �            1259    28896    analyzer_results    TABLE     ,  CREATE TABLE public.analyzer_results (
    id bigint NOT NULL,
    analyzer_id bigint NOT NULL,
    result double precision DEFAULT 0 NOT NULL,
    execution_time bigint DEFAULT 0,
    competition_time bigint DEFAULT 0,
    status character varying(20),
    accepted boolean,
    member_id bigint
);
 $   DROP TABLE public.analyzer_results;
       public         heap    postgres    false            �            1259    28902    analyzer_results_id_seq    SEQUENCE     �   CREATE SEQUENCE public.analyzer_results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.analyzer_results_id_seq;
       public          postgres    false    209            �
           0    0    analyzer_results_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.analyzer_results_id_seq OWNED BY public.analyzer_results.id;
          public          postgres    false    210            �            1259    28903 	   analyzers    TABLE     d   CREATE TABLE public.analyzers (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.analyzers;
       public         heap    postgres    false            �            1259    28906    analyzers_id_seq    SEQUENCE     y   CREATE SEQUENCE public.analyzers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.analyzers_id_seq;
       public          postgres    false    211            �
           0    0    analyzers_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.analyzers_id_seq OWNED BY public.analyzers.id;
          public          postgres    false    212            �            1259    28907    insurance_policy_companies    TABLE     9  CREATE TABLE public.insurance_policy_companies (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    individual_tax_number bigint NOT NULL,
    checking_account bigint NOT NULL,
    bank_identification_code bigint NOT NULL,
    policy_code bigint
);
 .   DROP TABLE public.insurance_policy_companies;
       public         heap    postgres    false            �            1259    28912    insurance_policy_company_id_seq    SEQUENCE     �   CREATE SEQUENCE public.insurance_policy_company_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.insurance_policy_company_id_seq;
       public          postgres    false    213            �
           0    0    insurance_policy_company_id_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.insurance_policy_company_id_seq OWNED BY public.insurance_policy_companies.id;
          public          postgres    false    214            �            1259    28913    issued_invoices    TABLE     �   CREATE TABLE public.issued_invoices (
    id bigint NOT NULL,
    company bigint NOT NULL,
    value bigint NOT NULL,
    status character varying(255),
    user bigint NOT NULL,
    order_service_id bigint
);
 #   DROP TABLE public.issued_invoices;
       public         heap    postgres    false            �            1259    28916    issued_invoices_id_seq    SEQUENCE        CREATE SEQUENCE public.issued_invoices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.issued_invoices_id_seq;
       public          postgres    false    215            �
           0    0    issued_invoices_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.issued_invoices_id_seq OWNED BY public.issued_invoices.id;
          public          postgres    false    216            �            1259    28917 
   login_history    TABLE     �   CREATE TABLE public.login_history (
    successful boolean,
    id bigint NOT NULL,
    enter_date bigint NOT NULL,
    user_login character varying(255)
);
 !   DROP TABLE public.login_history;
       public         heap    postgres    false            �            1259    28920    login_history_id_seq    SEQUENCE     }   CREATE SEQUENCE public.login_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.login_history_id_seq;
       public          postgres    false    217            �
           0    0    login_history_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.login_history_id_seq OWNED BY public.login_history.id;
          public          postgres    false    218            �            1259    28921    medical_services    TABLE     �   CREATE TABLE public.medical_services (
    code bigint NOT NULL,
    service_name character varying(255) NOT NULL,
    price double precision NOT NULL,
    deadline integer,
    avg_deviation double precision
);
 $   DROP TABLE public.medical_services;
       public         heap    postgres    false            �            1259    28924    members    TABLE       CREATE TABLE public.users (
    id bigint NOT NULL,
    full_name character varying(255) NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    ip character varying(15),
    role bigint DEFAULT 0 NOT NULL,
    last_enter_time bigint
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    28930    members_allow_services    TABLE     w   CREATE TABLE public.members_allow_services (
    member_id bigint NOT NULL,
    allow_services_code bigint NOT NULL
);
 *   DROP TABLE public.members_allow_services;
       public         heap    postgres    false            �            1259    28933    orders    TABLE     �   CREATE TABLE public.orders (
    id bigint NOT NULL,
    create_date date NOT NULL,
    status character varying(255) NOT NULL,
    completion_date date,
    patient_id bigint NOT NULL
);
    DROP TABLE public.orders;
       public         heap    postgres    false            �            1259    28936 
   orders_id_seq    SEQUENCE     v   CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.orders_id_seq;
       public          postgres    false    222            �
           0    0 
   orders_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;
          public          postgres    false    223            �            1259    28937    orders_services    TABLE     �   CREATE TABLE public.orders_services (
    id bigint NOT NULL,
    order_id bigint NOT NULL,
    service_id bigint NOT NULL,
    analyzer_result bigint
);
 #   DROP TABLE public.orders_services;
       public         heap    postgres    false            �            1259    28940    orders_services_id_seq    SEQUENCE        CREATE SEQUENCE public.orders_services_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.orders_services_id_seq;
       public          postgres    false    224            �
           0    0    orders_services_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.orders_services_id_seq OWNED BY public.orders_services.id;
          public          postgres    false    225            �            1259    28941    patients    TABLE     �  CREATE TABLE public.patients (
    id bigint NOT NULL,
    full_name character varying(50) NOT NULL,
    patronymic character varying(50) NOT NULL,
    passport character varying(10) NOT NULL,
    phone_number character varying(15),
    email character varying(255),
    insurance_policy bigint NOT NULL,
    login character varying(20),
    password character varying(255),
    birthday bigint
);
    DROP TABLE public.patients;
       public         heap    postgres    false            �            1259    28946    patients_id_seq    SEQUENCE     x   CREATE SEQUENCE public.patients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.patients_id_seq;
       public          postgres    false    226            �
           0    0    patients_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.patients_id_seq OWNED BY public.patients.id;
          public          postgres    false    227            �            1259    28947    permissions    TABLE     ]   CREATE TABLE public.permissions (
    id bigint NOT NULL,
    name character varying(255)
);
    DROP TABLE public.permissions;
       public         heap    postgres    false            �            1259    28950    permissions_id_seq    SEQUENCE     {   CREATE SEQUENCE public.permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.permissions_id_seq;
       public          postgres    false    228            �
           0    0    permissions_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.permissions_id_seq OWNED BY public.permissions.id;
          public          postgres    false    229            �            1259    28951    roles    TABLE     W   CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(255)
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    28954    roles_id_seq    SEQUENCE     u   CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    230            �
           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    231            �            1259    28955    roles_permissions    TABLE     k   CREATE TABLE public.roles_permissions (
    role_id bigint NOT NULL,
    permissions_id bigint NOT NULL
);
 %   DROP TABLE public.roles_permissions;
       public         heap    postgres    false            �            1259    28958    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    220            �
           0    0    users_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    233            �           2604    28959    analyzer_results id    DEFAULT     z   ALTER TABLE ONLY public.analyzer_results ALTER COLUMN id SET DEFAULT nextval('public.analyzer_results_id_seq'::regclass);
 B   ALTER TABLE public.analyzer_results ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209            �           2604    28960    analyzers id    DEFAULT     l   ALTER TABLE ONLY public.analyzers ALTER COLUMN id SET DEFAULT nextval('public.analyzers_id_seq'::regclass);
 ;   ALTER TABLE public.analyzers ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211            �           2604    28961    insurance_policy_companies id    DEFAULT     �   ALTER TABLE ONLY public.insurance_policy_companies ALTER COLUMN id SET DEFAULT nextval('public.insurance_policy_company_id_seq'::regclass);
 L   ALTER TABLE public.insurance_policy_companies ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    213            �           2604    28962    issued_invoices id    DEFAULT     x   ALTER TABLE ONLY public.issued_invoices ALTER COLUMN id SET DEFAULT nextval('public.issued_invoices_id_seq'::regclass);
 A   ALTER TABLE public.issued_invoices ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            �           2604    28963    login_history id    DEFAULT     t   ALTER TABLE ONLY public.login_history ALTER COLUMN id SET DEFAULT nextval('public.login_history_id_seq'::regclass);
 ?   ALTER TABLE public.login_history ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            �           2604    28964 
   members id    DEFAULT     f   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 9   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    220            �           2604    28965 	   orders id    DEFAULT     f   ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
 8   ALTER TABLE public.orders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222            �           2604    28966    orders_services id    DEFAULT     x   ALTER TABLE ONLY public.orders_services ALTER COLUMN id SET DEFAULT nextval('public.orders_services_id_seq'::regclass);
 A   ALTER TABLE public.orders_services ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    224            �           2604    28967    patients id    DEFAULT     j   ALTER TABLE ONLY public.patients ALTER COLUMN id SET DEFAULT nextval('public.patients_id_seq'::regclass);
 :   ALTER TABLE public.patients ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    226            �           2604    28968    permissions id    DEFAULT     p   ALTER TABLE ONLY public.permissions ALTER COLUMN id SET DEFAULT nextval('public.permissions_id_seq'::regclass);
 =   ALTER TABLE public.permissions ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    229    228            �           2604    28969    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    231    230            n
          0    28896    analyzer_results 
   TABLE DATA           �   COPY public.analyzer_results (id, analyzer_id, result, execution_time, competition_time, status, accepted, member_id) FROM stdin;
    public          postgres    false    209   ?�       p
          0    28903 	   analyzers 
   TABLE DATA           -   COPY public.analyzers (id, name) FROM stdin;
    public          postgres    false    211   \�       r
          0    28907    insurance_policy_companies 
   TABLE DATA           �   COPY public.insurance_policy_companies (id, name, address, individual_tax_number, checking_account, bank_identification_code, policy_code) FROM stdin;
    public          postgres    false    213   y�       t
          0    28913    issued_invoices 
   TABLE DATA           _   COPY public.issued_invoices (id, company, value, status, user, order_service_id) FROM stdin;
    public          postgres    false    215   ��       v
          0    28917 
   login_history 
   TABLE DATA           O   COPY public.login_history (successful, id, enter_date, user_login) FROM stdin;
    public          postgres    false    217   ��       x
          0    28921    medical_services 
   TABLE DATA           ^   COPY public.medical_services (code, service_name, price, deadline, avg_deviation) FROM stdin;
    public          postgres    false    219   В       y
          0    28924    members 
   TABLE DATA           \   COPY public.users (id, full_name, login, password, ip, role, last_enter_time) FROM stdin;
    public          postgres    false    220   Q�       z
          0    28930    members_allow_services 
   TABLE DATA           P   COPY public.members_allow_services (member_id, allow_services_code) FROM stdin;
    public          postgres    false    221   n�       {
          0    28933    orders 
   TABLE DATA           V   COPY public.orders (id, create_date, status, completion_date, patient_id) FROM stdin;
    public          postgres    false    222   ��       }
          0    28937    orders_services 
   TABLE DATA           T   COPY public.orders_services (id, order_id, service_id, analyzer_result) FROM stdin;
    public          postgres    false    224   ��       
          0    28941    patients 
   TABLE DATA           �   COPY public.patients (id, full_name, patronymic, passport, phone_number, email, insurance_policy, login, password, birthday) FROM stdin;
    public          postgres    false    226   Ŕ       �
          0    28947    permissions 
   TABLE DATA           /   COPY public.permissions (id, name) FROM stdin;
    public          postgres    false    228   �       �
          0    28951    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          postgres    false    230   �       �
          0    28955    roles_permissions 
   TABLE DATA           D   COPY public.roles_permissions (role_id, permissions_id) FROM stdin;
    public          postgres    false    232   6�       �
           0    0    analyzer_results_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.analyzer_results_id_seq', 1, false);
          public          postgres    false    210            �
           0    0    analyzers_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.analyzers_id_seq', 1, false);
          public          postgres    false    212            �
           0    0    insurance_policy_company_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.insurance_policy_company_id_seq', 1, false);
          public          postgres    false    214            �
           0    0    issued_invoices_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.issued_invoices_id_seq', 1, false);
          public          postgres    false    216            �
           0    0    login_history_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.login_history_id_seq', 1, false);
          public          postgres    false    218            �
           0    0 
   orders_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.orders_id_seq', 1, false);
          public          postgres    false    223            �
           0    0    orders_services_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.orders_services_id_seq', 1, false);
          public          postgres    false    225            �
           0    0    patients_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.patients_id_seq', 1, false);
          public          postgres    false    227            �
           0    0    permissions_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.permissions_id_seq', 1, true);
          public          postgres    false    229            �
           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 2, true);
          public          postgres    false    231            �
           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 1, false);
          public          postgres    false    233            �           2606    28971 $   analyzer_results analyzer_results_pk 
   CONSTRAINT     b   ALTER TABLE ONLY public.analyzer_results
    ADD CONSTRAINT analyzer_results_pk PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.analyzer_results DROP CONSTRAINT analyzer_results_pk;
       public            postgres    false    209            �           2606    28973    analyzers analyzers_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public.analyzers
    ADD CONSTRAINT analyzers_pk PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.analyzers DROP CONSTRAINT analyzers_pk;
       public            postgres    false    211            �           2606    28975 6   insurance_policy_companies insurance_policy_company_pk 
   CONSTRAINT     t   ALTER TABLE ONLY public.insurance_policy_companies
    ADD CONSTRAINT insurance_policy_company_pk PRIMARY KEY (id);
 `   ALTER TABLE ONLY public.insurance_policy_companies DROP CONSTRAINT insurance_policy_company_pk;
       public            postgres    false    213            �           2606    28977 "   issued_invoices issued_invoices_pk 
   CONSTRAINT     `   ALTER TABLE ONLY public.issued_invoices
    ADD CONSTRAINT issued_invoices_pk PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.issued_invoices DROP CONSTRAINT issued_invoices_pk;
       public            postgres    false    215            �           2606    28979    login_history login_history_pk 
   CONSTRAINT     \   ALTER TABLE ONLY public.login_history
    ADD CONSTRAINT login_history_pk PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.login_history DROP CONSTRAINT login_history_pk;
       public            postgres    false    217            �           2606    28981    orders orders_pk 
   CONSTRAINT     N   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pk;
       public            postgres    false    222            �           2606    28983 "   orders_services orders_services_pk 
   CONSTRAINT     `   ALTER TABLE ONLY public.orders_services
    ADD CONSTRAINT orders_services_pk PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.orders_services DROP CONSTRAINT orders_services_pk;
       public            postgres    false    224            �           2606    28985    patients patients_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_pk PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.patients DROP CONSTRAINT patients_pk;
       public            postgres    false    226            �           2606    28987    permissions permissions_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_pk PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.permissions DROP CONSTRAINT permissions_pk;
       public            postgres    false    228            �           2606    28989    roles roles_pk 
   CONSTRAINT     L   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pk PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pk;
       public            postgres    false    230            �           2606    28991    medical_services services_pk 
   CONSTRAINT     \   ALTER TABLE ONLY public.medical_services
    ADD CONSTRAINT services_pk PRIMARY KEY (code);
 F   ALTER TABLE ONLY public.medical_services DROP CONSTRAINT services_pk;
       public            postgres    false    219            �           2606    28993    members users_pk 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pk;
       public            postgres    false    220            �           1259    28994    analyzer_results_id_uindex    INDEX     \   CREATE UNIQUE INDEX analyzer_results_id_uindex ON public.analyzer_results USING btree (id);
 .   DROP INDEX public.analyzer_results_id_uindex;
       public            postgres    false    209            �           1259    28995    analyzers_id_uindex    INDEX     N   CREATE UNIQUE INDEX analyzers_id_uindex ON public.analyzers USING btree (id);
 '   DROP INDEX public.analyzers_id_uindex;
       public            postgres    false    211            �           1259    28996    analyzers_name_uindex    INDEX     R   CREATE UNIQUE INDEX analyzers_name_uindex ON public.analyzers USING btree (name);
 )   DROP INDEX public.analyzers_name_uindex;
       public            postgres    false    211            �           1259    28997 8   insurance_policy_company_bank_identification_code_uindex    INDEX     �   CREATE UNIQUE INDEX insurance_policy_company_bank_identification_code_uindex ON public.insurance_policy_companies USING btree (bank_identification_code);
 L   DROP INDEX public.insurance_policy_company_bank_identification_code_uindex;
       public            postgres    false    213            �           1259    28998 0   insurance_policy_company_checking_account_uindex    INDEX     �   CREATE UNIQUE INDEX insurance_policy_company_checking_account_uindex ON public.insurance_policy_companies USING btree (checking_account);
 D   DROP INDEX public.insurance_policy_company_checking_account_uindex;
       public            postgres    false    213            �           1259    28999 "   insurance_policy_company_id_uindex    INDEX     n   CREATE UNIQUE INDEX insurance_policy_company_id_uindex ON public.insurance_policy_companies USING btree (id);
 6   DROP INDEX public.insurance_policy_company_id_uindex;
       public            postgres    false    213            �           1259    29000 5   insurance_policy_company_individual_tax_number_uindex    INDEX     �   CREATE UNIQUE INDEX insurance_policy_company_individual_tax_number_uindex ON public.insurance_policy_companies USING btree (individual_tax_number);
 I   DROP INDEX public.insurance_policy_company_individual_tax_number_uindex;
       public            postgres    false    213            �           1259    29001 $   insurance_policy_company_name_uindex    INDEX     r   CREATE UNIQUE INDEX insurance_policy_company_name_uindex ON public.insurance_policy_companies USING btree (name);
 8   DROP INDEX public.insurance_policy_company_name_uindex;
       public            postgres    false    213            �           1259    29002    issued_invoices_id_uindex    INDEX     Z   CREATE UNIQUE INDEX issued_invoices_id_uindex ON public.issued_invoices USING btree (id);
 -   DROP INDEX public.issued_invoices_id_uindex;
       public            postgres    false    215            �           1259    29003    login_history_id_uindex    INDEX     V   CREATE UNIQUE INDEX login_history_id_uindex ON public.login_history USING btree (id);
 +   DROP INDEX public.login_history_id_uindex;
       public            postgres    false    217            �           1259    29004    orders_id_uindex    INDEX     H   CREATE UNIQUE INDEX orders_id_uindex ON public.orders USING btree (id);
 $   DROP INDEX public.orders_id_uindex;
       public            postgres    false    222            �           1259    29005    orders_services_id_uindex    INDEX     Z   CREATE UNIQUE INDEX orders_services_id_uindex ON public.orders_services USING btree (id);
 -   DROP INDEX public.orders_services_id_uindex;
       public            postgres    false    224            �           1259    29006    patients_email_uindex    INDEX     R   CREATE UNIQUE INDEX patients_email_uindex ON public.patients USING btree (email);
 )   DROP INDEX public.patients_email_uindex;
       public            postgres    false    226            �           1259    29007    patients_id_uindex    INDEX     L   CREATE UNIQUE INDEX patients_id_uindex ON public.patients USING btree (id);
 &   DROP INDEX public.patients_id_uindex;
       public            postgres    false    226            �           1259    29008    permissions_id_uindex    INDEX     R   CREATE UNIQUE INDEX permissions_id_uindex ON public.permissions USING btree (id);
 )   DROP INDEX public.permissions_id_uindex;
       public            postgres    false    228            �           1259    29009    roles_id_uindex    INDEX     F   CREATE UNIQUE INDEX roles_id_uindex ON public.roles USING btree (id);
 #   DROP INDEX public.roles_id_uindex;
       public            postgres    false    230            �           1259    29010    services_code_uindex    INDEX     X   CREATE UNIQUE INDEX services_code_uindex ON public.medical_services USING btree (code);
 (   DROP INDEX public.services_code_uindex;
       public            postgres    false    219            �           1259    29011    services_service_uindex    INDEX     c   CREATE UNIQUE INDEX services_service_uindex ON public.medical_services USING btree (service_name);
 +   DROP INDEX public.services_service_uindex;
       public            postgres    false    219            �           1259    29012    users_id_uindex    INDEX     H   CREATE UNIQUE INDEX users_id_uindex ON public.users USING btree (id);
 #   DROP INDEX public.users_id_uindex;
       public            postgres    false    220            �           2606    29013 1   analyzer_results analyzer_results_analyzers_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.analyzer_results
    ADD CONSTRAINT analyzer_results_analyzers_id_fk FOREIGN KEY (analyzer_id) REFERENCES public.analyzers(id);
 [   ALTER TABLE ONLY public.analyzer_results DROP CONSTRAINT analyzer_results_analyzers_id_fk;
       public          postgres    false    211    209    3247            �           2606    29018 /   analyzer_results analyzer_results_members_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.analyzer_results
    ADD CONSTRAINT analyzer_results_members_id_fk FOREIGN KEY (member_id) REFERENCES public.users(id);
 Y   ALTER TABLE ONLY public.analyzer_results DROP CONSTRAINT analyzer_results_members_id_fk;
       public          postgres    false    220    209    3267            �           2606    29023 -   roles_permissions fk570wuy6sacdnrw8wdqjfh7j0q 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.roles_permissions
    ADD CONSTRAINT fk570wuy6sacdnrw8wdqjfh7j0q FOREIGN KEY (permissions_id) REFERENCES public.permissions(id);
 W   ALTER TABLE ONLY public.roles_permissions DROP CONSTRAINT fk570wuy6sacdnrw8wdqjfh7j0q;
       public          postgres    false    228    3280    232            �           2606    29028 2   members_allow_services fkjok1f6bll2buubeblijqvyou7 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.members_allow_services
    ADD CONSTRAINT fkjok1f6bll2buubeblijqvyou7 FOREIGN KEY (allow_services_code) REFERENCES public.medical_services(code);
 \   ALTER TABLE ONLY public.members_allow_services DROP CONSTRAINT fkjok1f6bll2buubeblijqvyou7;
       public          postgres    false    3263    221    219            �           2606    29033 2   members_allow_services fkkb46bmpdak9nrr2sbhw1g2udn 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.members_allow_services
    ADD CONSTRAINT fkkb46bmpdak9nrr2sbhw1g2udn FOREIGN KEY (member_id) REFERENCES public.users(id);
 \   ALTER TABLE ONLY public.members_allow_services DROP CONSTRAINT fkkb46bmpdak9nrr2sbhw1g2udn;
       public          postgres    false    3267    221    220            �           2606    29038 -   roles_permissions fkqi9odri6c1o81vjox54eedwyh 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.roles_permissions
    ADD CONSTRAINT fkqi9odri6c1o81vjox54eedwyh FOREIGN KEY (role_id) REFERENCES public.roles(id);
 W   ALTER TABLE ONLY public.roles_permissions DROP CONSTRAINT fkqi9odri6c1o81vjox54eedwyh;
       public          postgres    false    3283    232    230            �           2606    29043 @   issued_invoices issued_invoices_insurance_policy_companies_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.issued_invoices
    ADD CONSTRAINT issued_invoices_insurance_policy_companies_id_fk FOREIGN KEY (company) REFERENCES public.insurance_policy_companies(id);
 j   ALTER TABLE ONLY public.issued_invoices DROP CONSTRAINT issued_invoices_insurance_policy_companies_id_fk;
       public          postgres    false    213    3254    215            �           2606    29048 -   issued_invoices issued_invoices_members_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.issued_invoices
    ADD CONSTRAINT issued_invoices_members_id_fk FOREIGN KEY (user) REFERENCES public.users(id);
 W   ALTER TABLE ONLY public.issued_invoices DROP CONSTRAINT issued_invoices_members_id_fk;
       public          postgres    false    215    3267    220            �           2606    29053 5   issued_invoices issued_invoices_orders_services_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.issued_invoices
    ADD CONSTRAINT issued_invoices_orders_services_id_fk FOREIGN KEY (order_service_id) REFERENCES public.orders_services(id);
 _   ALTER TABLE ONLY public.issued_invoices DROP CONSTRAINT issued_invoices_orders_services_id_fk;
       public          postgres    false    224    215    3273            �           2606    29058    members members_roles_id_fk 
   FK CONSTRAINT     w   ALTER TABLE ONLY public.users
    ADD CONSTRAINT members_roles_id_fk FOREIGN KEY (role) REFERENCES public.roles(id);
 E   ALTER TABLE ONLY public.users DROP CONSTRAINT members_roles_id_fk;
       public          postgres    false    3283    220    230            �           2606    29063    orders orders_patients_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_patients_id_fk FOREIGN KEY (patient_id) REFERENCES public.patients(id);
 F   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_patients_id_fk;
       public          postgres    false    222    226    3277            �           2606    29068 6   orders_services orders_services_analyzer_results_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.orders_services
    ADD CONSTRAINT orders_services_analyzer_results_id_fk FOREIGN KEY (analyzer_result) REFERENCES public.analyzer_results(id);
 `   ALTER TABLE ONLY public.orders_services DROP CONSTRAINT orders_services_analyzer_results_id_fk;
       public          postgres    false    209    224    3243            �           2606    29073 ,   orders_services orders_services_orders_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.orders_services
    ADD CONSTRAINT orders_services_orders_id_fk FOREIGN KEY (order_id) REFERENCES public.orders(id);
 V   ALTER TABLE ONLY public.orders_services DROP CONSTRAINT orders_services_orders_id_fk;
       public          postgres    false    222    3270    224            �           2606    29078 0   orders_services orders_services_services_code_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.orders_services
    ADD CONSTRAINT orders_services_services_code_fk FOREIGN KEY (service_id) REFERENCES public.medical_services(code);
 Z   ALTER TABLE ONLY public.orders_services DROP CONSTRAINT orders_services_services_code_fk;
       public          postgres    false    219    224    3263            �           2606    29083 2   patients patients_insurance_policy_companies_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_insurance_policy_companies_id_fk FOREIGN KEY (insurance_policy) REFERENCES public.insurance_policy_companies(id);
 \   ALTER TABLE ONLY public.patients DROP CONSTRAINT patients_insurance_policy_companies_id_fk;
       public          postgres    false    226    213    3254            n
   
   x������ � �      p
   
   x������ � �      r
   
   x������ � �      t
   
   x������ � �      v
   
   x������ � �      x
   q  x�e�KN1DםS�f��o 
,���#�IB,@a�vD	�оmgHH#y��TW=Ǌ �;� ��N�`����
?�����5(+����h��x��x�uK�J�*t
iHq�8�:�y�����F8x�w4qH>Ӥaؒ�)�~��$��,�(Ԙ�V���Ȅ{��K��L@��C	f��>�w��p��4��w������S��� �M���x�s�$lZ;nKo�S�/��7���	�
���& Ś��iq*X^��^��6�f��%L��B՚\��G�}���Sŭ�4.�tOF�ᜭ�0�&A����'}�R���Ė�e<K�c��Զ�W��E�L߁$�������}��
�      y
   
   x������ � �      z
   
   x������ � �      {
   
   x������ � �      }
   
   x������ � �      
   
   x������ � �      �
      x�3���W(H-��,.�������� H�      �
      x�3���W(��I����� }      �
      x�3�4����� f     