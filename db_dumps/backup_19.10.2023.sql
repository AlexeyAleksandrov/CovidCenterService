PGDMP                     	    {            CovidCenterService    15.2    15.2 {    �
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
           1262    33740    CovidCenterService    DATABASE     �   CREATE DATABASE "CovidCenterService" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
 $   DROP DATABASE "CovidCenterService";
                postgres    false            �            1259    33741    analyzer_results    TABLE     ,  CREATE TABLE public.analyzer_results (
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
       public         heap    postgres    false            �            1259    33747    analyzer_results_id_seq    SEQUENCE     �   CREATE SEQUENCE public.analyzer_results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.analyzer_results_id_seq;
       public          postgres    false    214            �
           0    0    analyzer_results_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.analyzer_results_id_seq OWNED BY public.analyzer_results.id;
          public          postgres    false    215            �            1259    33748 	   analyzers    TABLE     d   CREATE TABLE public.analyzers (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);
    DROP TABLE public.analyzers;
       public         heap    postgres    false            �            1259    33751    analyzers_id_seq    SEQUENCE     y   CREATE SEQUENCE public.analyzers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.analyzers_id_seq;
       public          postgres    false    216            �
           0    0    analyzers_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.analyzers_id_seq OWNED BY public.analyzers.id;
          public          postgres    false    217            �            1259    33752    insurance_policy_companies    TABLE     9  CREATE TABLE public.insurance_policy_companies (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    individual_tax_number bigint NOT NULL,
    checking_account bigint NOT NULL,
    bank_identification_code bigint NOT NULL,
    policy_code bigint
);
 .   DROP TABLE public.insurance_policy_companies;
       public         heap    postgres    false            �            1259    33757    insurance_policy_company_id_seq    SEQUENCE     �   CREATE SEQUENCE public.insurance_policy_company_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.insurance_policy_company_id_seq;
       public          postgres    false    218            �
           0    0    insurance_policy_company_id_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.insurance_policy_company_id_seq OWNED BY public.insurance_policy_companies.id;
          public          postgres    false    219            �            1259    33758    issued_invoices    TABLE     �   CREATE TABLE public.issued_invoices (
    id bigint NOT NULL,
    company bigint NOT NULL,
    value bigint NOT NULL,
    status character varying(255),
    user bigint NOT NULL,
    order_service_id bigint
);
 #   DROP TABLE public.issued_invoices;
       public         heap    postgres    false            �            1259    33761    issued_invoices_id_seq    SEQUENCE        CREATE SEQUENCE public.issued_invoices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.issued_invoices_id_seq;
       public          postgres    false    220            �
           0    0    issued_invoices_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.issued_invoices_id_seq OWNED BY public.issued_invoices.id;
          public          postgres    false    221            �            1259    33762 
   login_history    TABLE     �   CREATE TABLE public.login_history (
    successful boolean,
    id bigint NOT NULL,
    enter_date bigint NOT NULL,
    user_login character varying(255)
);
 !   DROP TABLE public.login_history;
       public         heap    postgres    false            �            1259    33765    login_history_id_seq    SEQUENCE     }   CREATE SEQUENCE public.login_history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.login_history_id_seq;
       public          postgres    false    222            �
           0    0    login_history_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.login_history_id_seq OWNED BY public.login_history.id;
          public          postgres    false    223            �            1259    33766    medical_services    TABLE     �   CREATE TABLE public.medical_services (
    code bigint NOT NULL,
    service_name character varying(255) NOT NULL,
    price double precision NOT NULL,
    deadline integer,
    avg_deviation double precision
);
 $   DROP TABLE public.medical_services;
       public         heap    postgres    false            �            1259    33769    members    TABLE       CREATE TABLE public.users (
    id bigint NOT NULL,
    full_name character varying(255) NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    ip character varying(15),
    role bigint DEFAULT 0 NOT NULL,
    last_enter_time bigint
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    33775    members_allow_services    TABLE     w   CREATE TABLE public.members_allow_services (
    member_id bigint NOT NULL,
    allow_services_code bigint NOT NULL
);
 *   DROP TABLE public.members_allow_services;
       public         heap    postgres    false            �            1259    33778    orders    TABLE     �   CREATE TABLE public.orders (
    id bigint NOT NULL,
    create_date date NOT NULL,
    status character varying(255) NOT NULL,
    completion_date date,
    patient_id bigint NOT NULL
);
    DROP TABLE public.orders;
       public         heap    postgres    false            �            1259    33781 
   orders_id_seq    SEQUENCE     v   CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.orders_id_seq;
       public          postgres    false    227            �
           0    0 
   orders_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;
          public          postgres    false    228            �            1259    33782    orders_services    TABLE     �   CREATE TABLE public.orders_services (
    id bigint NOT NULL,
    order_id bigint NOT NULL,
    service_id bigint NOT NULL,
    analyzer_result bigint
);
 #   DROP TABLE public.orders_services;
       public         heap    postgres    false            �            1259    33785    orders_services_id_seq    SEQUENCE        CREATE SEQUENCE public.orders_services_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.orders_services_id_seq;
       public          postgres    false    229            �
           0    0    orders_services_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.orders_services_id_seq OWNED BY public.orders_services.id;
          public          postgres    false    230            �            1259    33786    patients    TABLE     �  CREATE TABLE public.patients (
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
       public         heap    postgres    false            �            1259    33791    patients_id_seq    SEQUENCE     x   CREATE SEQUENCE public.patients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.patients_id_seq;
       public          postgres    false    231            �
           0    0    patients_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.patients_id_seq OWNED BY public.patients.id;
          public          postgres    false    232            �            1259    33792    permissions    TABLE     ]   CREATE TABLE public.permissions (
    id bigint NOT NULL,
    name character varying(255)
);
    DROP TABLE public.permissions;
       public         heap    postgres    false            �            1259    33795    permissions_id_seq    SEQUENCE     {   CREATE SEQUENCE public.permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.permissions_id_seq;
       public          postgres    false    233            �
           0    0    permissions_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.permissions_id_seq OWNED BY public.permissions.id;
          public          postgres    false    234            �            1259    33796    roles    TABLE     W   CREATE TABLE public.roles (
    id bigint NOT NULL,
    name character varying(255)
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    33799    roles_id_seq    SEQUENCE     u   CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    235            �
           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    236            �            1259    33800    roles_permissions    TABLE     k   CREATE TABLE public.roles_permissions (
    role_id bigint NOT NULL,
    permissions_id bigint NOT NULL
);
 %   DROP TABLE public.roles_permissions;
       public         heap    postgres    false            �            1259    33803    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    225            �
           0    0    users_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    238            �           2604    33804    analyzer_results id    DEFAULT     z   ALTER TABLE ONLY public.analyzer_results ALTER COLUMN id SET DEFAULT nextval('public.analyzer_results_id_seq'::regclass);
 B   ALTER TABLE public.analyzer_results ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214            �           2604    33805    analyzers id    DEFAULT     l   ALTER TABLE ONLY public.analyzers ALTER COLUMN id SET DEFAULT nextval('public.analyzers_id_seq'::regclass);
 ;   ALTER TABLE public.analyzers ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216            �           2604    33806    insurance_policy_companies id    DEFAULT     �   ALTER TABLE ONLY public.insurance_policy_companies ALTER COLUMN id SET DEFAULT nextval('public.insurance_policy_company_id_seq'::regclass);
 L   ALTER TABLE public.insurance_policy_companies ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218            �           2604    33807    issued_invoices id    DEFAULT     x   ALTER TABLE ONLY public.issued_invoices ALTER COLUMN id SET DEFAULT nextval('public.issued_invoices_id_seq'::regclass);
 A   ALTER TABLE public.issued_invoices ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220            �           2604    33808    login_history id    DEFAULT     t   ALTER TABLE ONLY public.login_history ALTER COLUMN id SET DEFAULT nextval('public.login_history_id_seq'::regclass);
 ?   ALTER TABLE public.login_history ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222            �           2604    33809 
   members id    DEFAULT     f   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 9   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    238    225            �           2604    33810 	   orders id    DEFAULT     f   ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
 8   ALTER TABLE public.orders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227            �           2604    33811    orders_services id    DEFAULT     x   ALTER TABLE ONLY public.orders_services ALTER COLUMN id SET DEFAULT nextval('public.orders_services_id_seq'::regclass);
 A   ALTER TABLE public.orders_services ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    230    229            �           2604    33812    patients id    DEFAULT     j   ALTER TABLE ONLY public.patients ALTER COLUMN id SET DEFAULT nextval('public.patients_id_seq'::regclass);
 :   ALTER TABLE public.patients ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    232    231            �           2604    33813    permissions id    DEFAULT     p   ALTER TABLE ONLY public.permissions ALTER COLUMN id SET DEFAULT nextval('public.permissions_id_seq'::regclass);
 =   ALTER TABLE public.permissions ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    234    233            �           2604    33814    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    236    235            {
          0    33741    analyzer_results 
   TABLE DATA           �   COPY public.analyzer_results (id, analyzer_id, result, execution_time, competition_time, status, accepted, member_id) FROM stdin;
    public          postgres    false    214   w�       }
          0    33748 	   analyzers 
   TABLE DATA           -   COPY public.analyzers (id, name) FROM stdin;
    public          postgres    false    216   ��       
          0    33752    insurance_policy_companies 
   TABLE DATA           �   COPY public.insurance_policy_companies (id, name, address, individual_tax_number, checking_account, bank_identification_code, policy_code) FROM stdin;
    public          postgres    false    218   ��       �
          0    33758    issued_invoices 
   TABLE DATA           _   COPY public.issued_invoices (id, company, value, status, user, order_service_id) FROM stdin;
    public          postgres    false    220   Γ       �
          0    33762 
   login_history 
   TABLE DATA           O   COPY public.login_history (successful, id, enter_date, user_login) FROM stdin;
    public          postgres    false    222   �       �
          0    33766    medical_services 
   TABLE DATA           ^   COPY public.medical_services (code, service_name, price, deadline, avg_deviation) FROM stdin;
    public          postgres    false    224   �       �
          0    33769    members 
   TABLE DATA           \   COPY public.users (id, full_name, login, password, ip, role, last_enter_time) FROM stdin;
    public          postgres    false    225   ��       �
          0    33775    members_allow_services 
   TABLE DATA           P   COPY public.members_allow_services (member_id, allow_services_code) FROM stdin;
    public          postgres    false    226   q�       �
          0    33778    orders 
   TABLE DATA           V   COPY public.orders (id, create_date, status, completion_date, patient_id) FROM stdin;
    public          postgres    false    227   $�       �
          0    33782    orders_services 
   TABLE DATA           T   COPY public.orders_services (id, order_id, service_id, analyzer_result) FROM stdin;
    public          postgres    false    229   A�       �
          0    33786    patients 
   TABLE DATA           �   COPY public.patients (id, full_name, patronymic, passport, phone_number, email, insurance_policy, login, password, birthday) FROM stdin;
    public          postgres    false    231   ^�       �
          0    33792    permissions 
   TABLE DATA           /   COPY public.permissions (id, name) FROM stdin;
    public          postgres    false    233   {�       �
          0    33796    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          postgres    false    235   ��       �
          0    33800    roles_permissions 
   TABLE DATA           D   COPY public.roles_permissions (role_id, permissions_id) FROM stdin;
    public          postgres    false    237   ��       �
           0    0    analyzer_results_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.analyzer_results_id_seq', 1, false);
          public          postgres    false    215            �
           0    0    analyzers_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.analyzers_id_seq', 1, false);
          public          postgres    false    217            �
           0    0    insurance_policy_company_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.insurance_policy_company_id_seq', 1, false);
          public          postgres    false    219            �
           0    0    issued_invoices_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.issued_invoices_id_seq', 1, false);
          public          postgres    false    221            �
           0    0    login_history_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.login_history_id_seq', 1, false);
          public          postgres    false    223            �
           0    0 
   orders_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.orders_id_seq', 1, false);
          public          postgres    false    228            �
           0    0    orders_services_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.orders_services_id_seq', 1, false);
          public          postgres    false    230            �
           0    0    patients_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.patients_id_seq', 1, false);
          public          postgres    false    232            �
           0    0    permissions_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.permissions_id_seq', 1, true);
          public          postgres    false    234            �
           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 6, true);
          public          postgres    false    236            �
           0    0    users_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.users_id_seq', 502, true);
          public          postgres    false    238            �           2606    33816 $   analyzer_results analyzer_results_pk 
   CONSTRAINT     b   ALTER TABLE ONLY public.analyzer_results
    ADD CONSTRAINT analyzer_results_pk PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.analyzer_results DROP CONSTRAINT analyzer_results_pk;
       public            postgres    false    214            �           2606    33818    analyzers analyzers_pk 
   CONSTRAINT     T   ALTER TABLE ONLY public.analyzers
    ADD CONSTRAINT analyzers_pk PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.analyzers DROP CONSTRAINT analyzers_pk;
       public            postgres    false    216            �           2606    33820 6   insurance_policy_companies insurance_policy_company_pk 
   CONSTRAINT     t   ALTER TABLE ONLY public.insurance_policy_companies
    ADD CONSTRAINT insurance_policy_company_pk PRIMARY KEY (id);
 `   ALTER TABLE ONLY public.insurance_policy_companies DROP CONSTRAINT insurance_policy_company_pk;
       public            postgres    false    218            �           2606    33822 "   issued_invoices issued_invoices_pk 
   CONSTRAINT     `   ALTER TABLE ONLY public.issued_invoices
    ADD CONSTRAINT issued_invoices_pk PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.issued_invoices DROP CONSTRAINT issued_invoices_pk;
       public            postgres    false    220            �           2606    33824    login_history login_history_pk 
   CONSTRAINT     \   ALTER TABLE ONLY public.login_history
    ADD CONSTRAINT login_history_pk PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.login_history DROP CONSTRAINT login_history_pk;
       public            postgres    false    222            �           2606    33826    orders orders_pk 
   CONSTRAINT     N   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pk;
       public            postgres    false    227            �           2606    33828 "   orders_services orders_services_pk 
   CONSTRAINT     `   ALTER TABLE ONLY public.orders_services
    ADD CONSTRAINT orders_services_pk PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.orders_services DROP CONSTRAINT orders_services_pk;
       public            postgres    false    229            �           2606    33830    patients patients_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_pk PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.patients DROP CONSTRAINT patients_pk;
       public            postgres    false    231            �           2606    33832    permissions permissions_pk 
   CONSTRAINT     X   ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_pk PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.permissions DROP CONSTRAINT permissions_pk;
       public            postgres    false    233            �           2606    33834    roles roles_pk 
   CONSTRAINT     L   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pk PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pk;
       public            postgres    false    235            �           2606    33836    medical_services services_pk 
   CONSTRAINT     \   ALTER TABLE ONLY public.medical_services
    ADD CONSTRAINT services_pk PRIMARY KEY (code);
 F   ALTER TABLE ONLY public.medical_services DROP CONSTRAINT services_pk;
       public            postgres    false    224            �           2606    33838    members users_pk 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pk;
       public            postgres    false    225            �           1259    33839    analyzer_results_id_uindex    INDEX     \   CREATE UNIQUE INDEX analyzer_results_id_uindex ON public.analyzer_results USING btree (id);
 .   DROP INDEX public.analyzer_results_id_uindex;
       public            postgres    false    214            �           1259    33840    analyzers_id_uindex    INDEX     N   CREATE UNIQUE INDEX analyzers_id_uindex ON public.analyzers USING btree (id);
 '   DROP INDEX public.analyzers_id_uindex;
       public            postgres    false    216            �           1259    33841    analyzers_name_uindex    INDEX     R   CREATE UNIQUE INDEX analyzers_name_uindex ON public.analyzers USING btree (name);
 )   DROP INDEX public.analyzers_name_uindex;
       public            postgres    false    216            �           1259    33842 8   insurance_policy_company_bank_identification_code_uindex    INDEX     �   CREATE UNIQUE INDEX insurance_policy_company_bank_identification_code_uindex ON public.insurance_policy_companies USING btree (bank_identification_code);
 L   DROP INDEX public.insurance_policy_company_bank_identification_code_uindex;
       public            postgres    false    218            �           1259    33843 0   insurance_policy_company_checking_account_uindex    INDEX     �   CREATE UNIQUE INDEX insurance_policy_company_checking_account_uindex ON public.insurance_policy_companies USING btree (checking_account);
 D   DROP INDEX public.insurance_policy_company_checking_account_uindex;
       public            postgres    false    218            �           1259    33844 "   insurance_policy_company_id_uindex    INDEX     n   CREATE UNIQUE INDEX insurance_policy_company_id_uindex ON public.insurance_policy_companies USING btree (id);
 6   DROP INDEX public.insurance_policy_company_id_uindex;
       public            postgres    false    218            �           1259    33845 5   insurance_policy_company_individual_tax_number_uindex    INDEX     �   CREATE UNIQUE INDEX insurance_policy_company_individual_tax_number_uindex ON public.insurance_policy_companies USING btree (individual_tax_number);
 I   DROP INDEX public.insurance_policy_company_individual_tax_number_uindex;
       public            postgres    false    218            �           1259    33846 $   insurance_policy_company_name_uindex    INDEX     r   CREATE UNIQUE INDEX insurance_policy_company_name_uindex ON public.insurance_policy_companies USING btree (name);
 8   DROP INDEX public.insurance_policy_company_name_uindex;
       public            postgres    false    218            �           1259    33847    issued_invoices_id_uindex    INDEX     Z   CREATE UNIQUE INDEX issued_invoices_id_uindex ON public.issued_invoices USING btree (id);
 -   DROP INDEX public.issued_invoices_id_uindex;
       public            postgres    false    220            �           1259    33848    login_history_id_uindex    INDEX     V   CREATE UNIQUE INDEX login_history_id_uindex ON public.login_history USING btree (id);
 +   DROP INDEX public.login_history_id_uindex;
       public            postgres    false    222            �           1259    33960    members_login_uindex    INDEX     P   CREATE UNIQUE INDEX members_login_uindex ON public.users USING btree (login);
 (   DROP INDEX public.members_login_uindex;
       public            postgres    false    225            �           1259    33849    orders_id_uindex    INDEX     H   CREATE UNIQUE INDEX orders_id_uindex ON public.orders USING btree (id);
 $   DROP INDEX public.orders_id_uindex;
       public            postgres    false    227            �           1259    33850    orders_services_id_uindex    INDEX     Z   CREATE UNIQUE INDEX orders_services_id_uindex ON public.orders_services USING btree (id);
 -   DROP INDEX public.orders_services_id_uindex;
       public            postgres    false    229            �           1259    33851    patients_email_uindex    INDEX     R   CREATE UNIQUE INDEX patients_email_uindex ON public.patients USING btree (email);
 )   DROP INDEX public.patients_email_uindex;
       public            postgres    false    231            �           1259    33852    patients_id_uindex    INDEX     L   CREATE UNIQUE INDEX patients_id_uindex ON public.patients USING btree (id);
 &   DROP INDEX public.patients_id_uindex;
       public            postgres    false    231            �           1259    33853    permissions_id_uindex    INDEX     R   CREATE UNIQUE INDEX permissions_id_uindex ON public.permissions USING btree (id);
 )   DROP INDEX public.permissions_id_uindex;
       public            postgres    false    233            �           1259    33854    roles_id_uindex    INDEX     F   CREATE UNIQUE INDEX roles_id_uindex ON public.roles USING btree (id);
 #   DROP INDEX public.roles_id_uindex;
       public            postgres    false    235            �           1259    33855    services_code_uindex    INDEX     X   CREATE UNIQUE INDEX services_code_uindex ON public.medical_services USING btree (code);
 (   DROP INDEX public.services_code_uindex;
       public            postgres    false    224            �           1259    33856    services_service_uindex    INDEX     c   CREATE UNIQUE INDEX services_service_uindex ON public.medical_services USING btree (service_name);
 +   DROP INDEX public.services_service_uindex;
       public            postgres    false    224            �           1259    33857    users_id_uindex    INDEX     H   CREATE UNIQUE INDEX users_id_uindex ON public.users USING btree (id);
 #   DROP INDEX public.users_id_uindex;
       public            postgres    false    225            �           2606    33858 1   analyzer_results analyzer_results_analyzers_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.analyzer_results
    ADD CONSTRAINT analyzer_results_analyzers_id_fk FOREIGN KEY (analyzer_id) REFERENCES public.analyzers(id);
 [   ALTER TABLE ONLY public.analyzer_results DROP CONSTRAINT analyzer_results_analyzers_id_fk;
       public          postgres    false    214    3256    216            �           2606    33863 /   analyzer_results analyzer_results_members_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.analyzer_results
    ADD CONSTRAINT analyzer_results_members_id_fk FOREIGN KEY (member_id) REFERENCES public.users(id);
 Y   ALTER TABLE ONLY public.analyzer_results DROP CONSTRAINT analyzer_results_members_id_fk;
       public          postgres    false    3277    225    214            �           2606    33868 -   roles_permissions fk570wuy6sacdnrw8wdqjfh7j0q 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.roles_permissions
    ADD CONSTRAINT fk570wuy6sacdnrw8wdqjfh7j0q FOREIGN KEY (permissions_id) REFERENCES public.permissions(id);
 W   ALTER TABLE ONLY public.roles_permissions DROP CONSTRAINT fk570wuy6sacdnrw8wdqjfh7j0q;
       public          postgres    false    3290    233    237            �           2606    33873 2   members_allow_services fkjok1f6bll2buubeblijqvyou7 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.members_allow_services
    ADD CONSTRAINT fkjok1f6bll2buubeblijqvyou7 FOREIGN KEY (allow_services_code) REFERENCES public.medical_services(code);
 \   ALTER TABLE ONLY public.members_allow_services DROP CONSTRAINT fkjok1f6bll2buubeblijqvyou7;
       public          postgres    false    3272    224    226            �           2606    33945 2   members_allow_services fkkb46bmpdak9nrr2sbhw1g2udn 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.members_allow_services
    ADD CONSTRAINT fkkb46bmpdak9nrr2sbhw1g2udn FOREIGN KEY (member_id) REFERENCES public.users(id) ON DELETE CASCADE;
 \   ALTER TABLE ONLY public.members_allow_services DROP CONSTRAINT fkkb46bmpdak9nrr2sbhw1g2udn;
       public          postgres    false    225    3277    226            �           2606    33883 -   roles_permissions fkqi9odri6c1o81vjox54eedwyh 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.roles_permissions
    ADD CONSTRAINT fkqi9odri6c1o81vjox54eedwyh FOREIGN KEY (role_id) REFERENCES public.roles(id);
 W   ALTER TABLE ONLY public.roles_permissions DROP CONSTRAINT fkqi9odri6c1o81vjox54eedwyh;
       public          postgres    false    235    237    3293            �           2606    33888 @   issued_invoices issued_invoices_insurance_policy_companies_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.issued_invoices
    ADD CONSTRAINT issued_invoices_insurance_policy_companies_id_fk FOREIGN KEY (company) REFERENCES public.insurance_policy_companies(id);
 j   ALTER TABLE ONLY public.issued_invoices DROP CONSTRAINT issued_invoices_insurance_policy_companies_id_fk;
       public          postgres    false    218    220    3263            �           2606    33893 -   issued_invoices issued_invoices_members_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.issued_invoices
    ADD CONSTRAINT issued_invoices_members_id_fk FOREIGN KEY (user) REFERENCES public.users(id);
 W   ALTER TABLE ONLY public.issued_invoices DROP CONSTRAINT issued_invoices_members_id_fk;
       public          postgres    false    3277    225    220            �           2606    33898 5   issued_invoices issued_invoices_orders_services_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.issued_invoices
    ADD CONSTRAINT issued_invoices_orders_services_id_fk FOREIGN KEY (order_service_id) REFERENCES public.orders_services(id);
 _   ALTER TABLE ONLY public.issued_invoices DROP CONSTRAINT issued_invoices_orders_services_id_fk;
       public          postgres    false    3283    229    220            �           2606    33903    members members_roles_id_fk 
   FK CONSTRAINT     w   ALTER TABLE ONLY public.users
    ADD CONSTRAINT members_roles_id_fk FOREIGN KEY (role) REFERENCES public.roles(id);
 E   ALTER TABLE ONLY public.users DROP CONSTRAINT members_roles_id_fk;
       public          postgres    false    235    225    3293            �           2606    33908    orders orders_patients_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_patients_id_fk FOREIGN KEY (patient_id) REFERENCES public.patients(id);
 F   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_patients_id_fk;
       public          postgres    false    231    3287    227            �           2606    33913 6   orders_services orders_services_analyzer_results_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.orders_services
    ADD CONSTRAINT orders_services_analyzer_results_id_fk FOREIGN KEY (analyzer_result) REFERENCES public.analyzer_results(id);
 `   ALTER TABLE ONLY public.orders_services DROP CONSTRAINT orders_services_analyzer_results_id_fk;
       public          postgres    false    214    229    3252            �           2606    33918 ,   orders_services orders_services_orders_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.orders_services
    ADD CONSTRAINT orders_services_orders_id_fk FOREIGN KEY (order_id) REFERENCES public.orders(id);
 V   ALTER TABLE ONLY public.orders_services DROP CONSTRAINT orders_services_orders_id_fk;
       public          postgres    false    227    3280    229            �           2606    33923 0   orders_services orders_services_services_code_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.orders_services
    ADD CONSTRAINT orders_services_services_code_fk FOREIGN KEY (service_id) REFERENCES public.medical_services(code);
 Z   ALTER TABLE ONLY public.orders_services DROP CONSTRAINT orders_services_services_code_fk;
       public          postgres    false    3272    229    224            �           2606    33928 2   patients patients_insurance_policy_companies_id_fk 
   FK CONSTRAINT     �   ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_insurance_policy_companies_id_fk FOREIGN KEY (insurance_policy) REFERENCES public.insurance_policy_companies(id);
 \   ALTER TABLE ONLY public.patients DROP CONSTRAINT patients_insurance_policy_companies_id_fk;
       public          postgres    false    231    3263    218            {
   
   x������ � �      }
   
   x������ � �      
   
   x������ � �      �
   
   x������ � �      �
   
   x������ � �      �
   q  x�e�KN1DםS�f��o 
,���#�IB,@a�vD	�оmgHH#y��TW=Ǌ �;� ��N�`����
?�����5(+����h��x��x�uK�J�*t
iHq�8�:�y�����F8x�w4qH>Ӥaؒ�)�~��$��,�(Ԙ�V���Ȅ{��K��L@��C	f��>�w��p��4��w������S��� �M���x�s�$lZ;nKo�S�/��7���	�
���& Ś��iq*X^��^��6�f��%L��B՚\��G�}���Sŭ�4.�tOF�ᜭ�0�&A����'}�R���Ė�e<K�c��Զ�W��E�L߁$�������}��
�      �
      x�mzɶ�ʲ���1��J�I 	��&4A#щ��5�|�*���g�;"��͌`)Dȡ���-M��a���"������ם������c8�l��<�^���y����z%ׅ6�NG���^,�A����(XX�!hN��������ChIV�Y�����D��K[n��Ej2�P╶U�F-;��K�)I�	4L�w����wO�I��d� "D��h�`!��x�bIYbnZ��I�n?�/�ё�������l���G=/�NO%�o3�UU���^N-�`��2A�)AF�H�lS��e䈍Yf���c"������R���\ʳ�5�(׌=�65�`i����)�3�M��"3��$�D,��I�SJH�K�S3���<	�$',������&��`��lj�,;&��;6$�Eǜ��j9�əb���
;)��B��u"RH�K�D�R �A�G���Y��n8[Əʉ�-���K:�-�7�v�<���(��j;�޸N3���9�F4�(��?�Ɉ45T�I�v�|⌈����rr��Y�^w�񐊄�)���11C]2u���;;��������xΗ����%���Dl�8�+�
l?��(�F�/���L������>�����~��Q��t�X�W�x�i�u�t�
)w@�0P�p��@�P7�Ԣ�����af�%}�'S��W�Z���hs�>��/�6]��)O+��ݵ�e0�]�v��:�q�/,)@v�'�>+EI��n���Y-s�Ȅr*ޫ4��������Pv�wݣ�)M-?�Ty���ۖg�Emg<�o��$�~�ch��F�䤙���9�b3�qQ�Ѽ�� �����;�a}�O��]��������%W�y7�[�b/��DƋ������7��3$NΘ�3Ʃ���$.�ش���_h��O��Kw$s:_$�u7#5�L��:����<���t�v����H�ŗ��Cq��ET���→�YBl;	��4)�"p]"����˽g��x�z��F�r*��ȓ%&����<'YW#C;կ-��-^`�������J�xv(l�}��;w4���¶��:˙=�l;��tq�:��Ǫ�Dga_��6Kxv�8NzYC��n���I"��������Y���	=����+&���_Rkz��Y�X�NL-��!�$�"\���Ш�q�o�gv����T/��,Z�H��;�(�ϴ@@>�mA?�r�ο��K�J�%�}~µ�E��W7�(�{�����k&�KQ�f�.�K�I�,�P�����ݵH(f�O�~�&�x��t��x2��rR$��ds)��m��C�R��Y�0�+���<�k����K�2�5�O�I�jV�F[[6���#��w�W|�.�j��S�B���,c�K�I�����;���ٽ����d���An � �aH��Y��cD���115c�p,���X�m�eWuu
o&�:������ʉ-�d� �S��}��^گ��ۥ�*��D��Ԕ��2��$��p4-I��0�O����p�p����-���S�@�Uw��緍p�T�wZӼv���ྶ5��~�s"	@͈?�!�?�B�X��9Z�ib��|��_��pu~��;m	3xy>���W����ZfFo�sZ���L��J��J�����g��C�"@.��O��0������8Nφ��7]��ɔ�����8��λ��x��������j���L}���{?�*RC	��$ls�7���Ś�'��1Yr,�aJ#xnuX�(�H���U(�v�f�mj`�I~��z�AZe,�=P��w*�vۣ�Y:)�Λ���(�
-�Hj��<G�-�)��i��\^�R~�//�g��E���50bA�
�9�>3B`���Ö�,�G�
KnA�$�����pV��*O*�9�������)W[��sݺ��i���H�˻��v��.�!\~`����#Dq���w�B*<:�%@$�5�%�-����~>
�Cّ��\�(3�댔o�+��ο��
�Ŏc&�`A�n��I4ýˋD8�hl�XYRz>ag���ˬWK�Z:t��K,��宝���S;�N�Y�cקYX�/��|�,w >=ϣA'P4�|�� ��nH�{=F`%}�) �;�~�Me"�'� �V�IM�gCt2'�Y�v�N�Ҿ���r�.|�/����}������Y��2c(b�ypԲ��a��	�@���X{k��?_�[�Ƹ~&�=%=��9�j?��&�u$�����{6
7����?����2C{�̒������u歚��X�Fg���~\NR%�Es!oy�����`�A���ȷ�@M�"��>X�s��m�&M�0� ��	ʁ�&Tt	��"m����U�Yyx�Wv��+�&�'u�5��F�������(R`I��hy	
��0�,t�7��X�Y�=���/��$aR��J�U�������J�{&镯
9ϖ���)�ӕs����e�$���>��A5��d������bߌ����/�1ane�T#���*��L�ק�m#gVEm�7iW��BL�O���B� �Ei��OV
�z@s :�Aa������/9��̛����	��\�L75H&j�G3'f��Mʫ�V�r,��|�3?��#a ��^�o���`��	�@�yɾ]��s;/��1�I*���&f�5�:����W>��(��[���o^��Gs����I\�@*a{~�<�~a7���:a���{��KJ�Tj��Ћ�Ư��+:��Ƌ1/��J^#�$�^���%�=h���d�����t����Wzp6�zz�]|�Y�^�ʟ�K������R��딄ꄔ��4&�.f!������P�gÒ����H���1��! t�3�I�F����x.�{g~�gm\J�hҧ�y1	zx%(��1�;>����`L��Iz�;�;�0���Y�	��\n�Wz�6.���ט4Fin�Y �7��l�Y��i�nH��-=���1N�i4tO_I=e�k|S�������
8\L'�V�I��s����p���f��ɬ؞��vغ��or�PX6-++��T��8����g��� ��j�E�[Z?�4�`=� ʓEPX�{���+uY:F0�L�b�i�t^�:�W�WK��[��Ю�!���d�2��ą�����ֿ��K3��q�v�Ѓ۱y<c� %Y�ǂU8�"�p��^�+9�Y}"5���é:8S�^:����H �����4(�%��TF�2{�Sd���[������N��J�Y[ޑ��V�e�􉂽�\�c2�Qo�
*K��Y��hq�iG
P���L��`z�:v����>څ�k��tPI�is@	��D�筣>��p���h�r��)�#�������	~����Fv��`w��D�H��6�-��g,�,׊�=��;��j�u�:qD'���z����O�J�0yY$�3	q�l�=3�*��=��"I8��^Nۜ��3�S��R�B,	G�a
�:�Le��dZ�n�)2�(<�x�m����n+
k�A��Wzp=����{�s^�HاO�"}�Bg
�S{:�$.��>f���3�{����M�o��[Z�@$�h��c9�*�]�w@���Τv6S�ޡ��I2�I~Ɨ0�Vk�N65��k���U�yZ�0�v���#r���43M{�|3�"��8���`y�ZԡW�7�V�D�oeR-���eO�'OQ��pƮ��B\ۑ�%�]&����(B������Os4�a��O@6kf�8�^׆�z<痱`�"sf���b�+꼴��������b
4�7�{�$��G]�!b����ᆓ:x�p�҃�a&u).}��}X4�9
��T��ė��&�7+Cn�
�&A`ב�@4�>��IR�ʚc��	|r�d��V�{���?���(���X���'�*�ۍ�d���A�<1H�3%��6u]+�	���Գ�Oa	�8�g�?F�F��t��`�Wz08V^JX/ͧ�T�L������L컑��j���P���VH�@0	���)J��,��*�
 �
  Xc ������S�3��n�ߏ��F��9�����v�,�Hv{�����p6x�+��Q��D���f��Y��bH�Dlz�:���y_^�7Ý���t�L��#������}9��l7�I�^N@�	��������l����$���ta����"s�����Y��҈Ƈu��S�w�3L%�� W(��Ԙ^�\}�%}�+�aH�k�#�f�a�V�� S��[�X��̎�Y?Y�1��k�|u.%� ��SL����(�G݅�*,��Oqrx�ý*|�	�cVJPZ�,����x@�����e����f3^�����3��
q��v.����yY�+=3����, />��'���<؜&���q�[g��у�!Q��ۂ[�Ւkco�4��9i�����8o����u���,�j�}@����o��9�+��;<
��0̬0���+=�OK�j���	����Ă:��:T��j��.�b�d@fuu4O�H=w��Qo/���xAD��s`	�{t���A�������u0�r:���[�g~����Y��tq^u~�R��<����N*T�t�8h(�#�}R:����\xЈ^�%�~sx:����&c�|�O_��lr$�K���J�95���)�&�&���
+�`{���	{���t`顰y����Ch�Y�r��_�������X�\Vi�-�E)�<cf��H�
��bZ��|4N�K�y��o��G���eE4 4H���7p�
��?=8����cII�oȉ"��&җލ�z����s~lM��ݞ��IsZB����E�K�)�Џo�<!'1tO\q2�}�%��у��I$��]V׹;Gz����SO�X잜��s�M2?�[�*���m� ����i}�����(	CO	�!�]����
"����h5e�U�c�<%�+^۞P�W-c�'�=���ë�&��)�f�&>�:	���$0�0�aFN��1GS�zm?��+=�y+Ѽ����>IO��7�6$CKZM��#���n܍7��,�Us9�0��^�p���<�ؿo, e�^�������!���xj���S�_Usu¾r�&Cwv�,�F�ڢ\��݌{+��"��#�>�a��<`����K�CFS�b¶����P����ɹq���<y��)Y�/��a�������)��.�^�v��m,:������@�A��e�@39p�&���鰙[���o�� 8�L�XG��2��S��wfA�7f>��ۥ�G�*	�B�g���+��i( bm��gn<��J^�XN{���,j>�j]��s��_�l{q�&�1��3��T>ՖI�����O��,%��w���H�����&�'��������N�,����@����d��xv�k��o3��$��m�LOz����ۭ4��%.�� m�2v�:�4{s�'�\������R�x/A�u��.�����.�Maj^���o>��8�����RٶŶ��)PՉ������h�<�W;O��m�u���'�C���|�#�q. A�y�c5��Ҏ�&!H�H����C���TxBIb3m{�0N'�h�<N��2��G�t9�ئ��f5���rZ�
��R{��\c<Ak�]m����z��z	}9�P=(4A Ԥţ
6���G8�h�<f�I���i;M�D��&r]�N�RX�U��{禅k|=g~�d����g�P꧓��7�gy"�� 6GǬ�R�+�
��۰S2���z���$q7^A�=��ݹ�ջ�^}�dI��Ґ�~��~l�Yj�;��`���Y�Bd!�}��C���쾩���x�f5^Ota���3ڊ��ս�ݞz��.�"w^���
=�x;���)�A���x9����'�'@��11�^9�:�U�)��X�lɇ��}�����f�eo#���T��ޢ��U$���7-�&��3R���	��4k\�θ������˷���d�����,�\�Y�&��ڜ�/s�S�5!�ϓ��_����zJ v�#=ρQ���|4�����rus�m�3Z��r�����	��B'�hJ��y�M��G��{���7R`��b��Ѫ�̂��~A�ɱN�d��{���Xġ�GxUd���/X�z���[Gc�ϼ�Z��S��������� F���q��SD�{�
6GEJ���ʹ�Y����gy��f>�ܕ��'��(�`·BZK�=�	�s�W������WJ�?��j��< u���^��p(�y=�h����ԝ�x�%��4峁�����$�8h�lL>_���'ṅE�^u���N]`�@�&eJ�E���ۘ�����\��:�֭u�E�Qe�ƫ�z
p4sì���8F����$�~Ya(f�7�M�I촣�	��OϬ�������"�t�ƚ��s99�l=��T���$��Kf�43�R���2�"��wY<)Q��g0��b�ѽ�a������i4xM�k��sS��%����_��^K@���m3^���?عs4{5� ")�-C�kF��:�M��-�ћ��7B�ˑ�
S��
��%zV�<k|*Pm�68�>7��;\U�E|l��~�����ˠa�pF�� �V�I	P�� 
>���1�X?����.e�$N���8�&�o���N��fl��uz0&�gy���#̳o{Q_�7�"�&83a����P@x�7B��Q�tUmgӫ���x}���T�o�v<h�t|?Q3J}�SqR"Tv��B��PI�i�W�0<��a`3�K6��*����O��C�t��wQ�[S�9ww+�~����V��N�6?���Q`��TT����H��I%�~{I��#1�t@x�����l�Z��>�s�f�}��_�EW�n��Tj���\٬�Ľ�	���OWež�g�
 �ER��_�"� �~%&�0-�|����C&�;@��q���tm6���G䮴;{���f�'�Y����D��[���A��
Hu&}������8`�eV��e؄��4���)��a�7�l����3���7�J7%c�
�ڒ�J�u�i�V8������jo�n��P�a�|5)"�j�`tT;�*��u#�3[2���qF��t�e�{��^�0�2��o�0�����	hMS��C58R��ЁS�ۿ���ye���`x�^�����_�&R#�
�\�^���u������۬�P���O�n۽�E�R��]�4b�AKb�1-�+��$��O��c��v�-��:��xQD^���K�K�=���x#޶z�
��ڤ����RH
�	�}9ֿ�;%��j״2st���8�	+��h�<�v�
�����[r�i���U����K֏�[����o�fVk�c|\��ȓ|�5����I���9�"����������|�㽢���49,ESf�ڜ���sH��8I�f1�^��L�ȇ�Q�jTϒL�+�L��-@���(�8Zb��I���/h�=�yM>��O�#bԤ/_�	�w�����*	x�sG�)�ٳa<� 2����Gٟ7�����?�����6i�      �
   �  x�E��%!D���5*�\6�8���W��@C���G�R�w��yle�J��]�j����;��l��97����_{d�sg������<�#�܋�ȵ���8~�x�,���>g�A�u�Y��w5v��f���g�,�zv>K�#��䵔<��2�~m�.걨�
�Ṓ�9v����ߩoa�ۋ���fc�W��7�.l��c���S�M�}.��/v|şN7�7�6��ι�7�S�M�6}<��/l����~�=���±�أ�Cߎ`�S�����c�7�8����9走�w����'�/�/�/���/�5~}��A��s.�뇝�Po!/����$��P��楼E7J��G��Zы���щ��:6OU®O��=�U���|�w�Խp�<b������Oѓ���Qo�=��x�F��?������1���e��{޻���п����bߋ����x'F�,�u�5惿S_g�9����rt�{?�3�9��v���=zse�;wt�?��O�N���<px;�}6����y0���*��[��RП@��@�o��_�+�îc�+��=� ���>F|���H�ȹ���d^䚼��%�T2�s��o��dn'}M�_��S��o�/yw�ĥO�n&s.��
��?\8q�g�>�}����7������?�����e      �
   
   x������ � �      �
   
   x������ � �      �
   
   x������ � �      �
      x�3���W(H-��,.�������� H�      �
   G   x�3���W(��I�2�t,.�,.I�+�2A��R�S��3R��L9���3Nǔ��<���Ē�"�=... ��      �
      x�3�4�2b 6b3 ����� '�     