create table company_tb(
    id int auto_increment primary key,
    username varchar not null unique,
    password varchar not null,
    email varchar not null,
    address varchar not null,
    detail_address varchar,
    tel varchar,
    profile varchar,
    company_name varchar not null,
    company_scale varchar,
    company_numb bigint not null,
    company_field varchar,
    created_at timestamp not null
);

create table user_tb(
    id int auto_increment primary key,
    username varchar not null unique,
    password varchar not null,
    email varchar not null,
    address varchar,
    detail_address varchar,
    tel varchar,
    profile varchar,
    real_name varchar,
    career int,
    resume_title varchar,
    resume_content longtext,
    created_at timestamp not null
);

create table board_tb(
    id int auto_increment primary key,
    company_id int,
    title varchar,
    content longtext,
    career int not null,
    job_type varchar,
    education varchar,
    favor varchar,
    created_at timestamp not null
);

create table apply_tb(
    id int auto_increment primary key,
    user_id int not null,
    board_id int not null,
    created_at timestamp not null
);

create table tech_tb(
    id int auto_increment primary key,
    user_id int,
    board_id int,
    java tinyint,
    c_lang tinyint,
    python tinyint,
    php tinyint,
    jsc tinyint,
    ruby tinyint,
    assembly_lang tinyint,
    sql_lang tinyint,
    created_at timestamp not null
);

create table custom_board_tb(
    id int auto_increment primary key,
    user_id int,
    company_id int,
    title varchar not null,
    content varchar not null,
    created_at timestamp not null
);