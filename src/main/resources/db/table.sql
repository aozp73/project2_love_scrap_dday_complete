create table company_tb(
    id int auto_increment primary key,
    username varchar not null unique,
    password varchar not null,
    email varchar not null,
    address varchar not null,
    detail_address varchar,
    tel varchar,
    company_name varchar not null,
    company_scale varchar,
    company_numb int not null,
    company_field varchar,
    created_at timestamp not null
);

create table user_tb(
    id int auto_increment primary key,
    username varchar not null unique,
    password varchar not null,
    email varchar not null,
    address varchar not null,
    detail_address varchar,
    tel varchar,
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
    created_at timestamp not null
);
