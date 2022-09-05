drop table if exists users2;

create table if not exists users2 (
    identifier  bigserial primary key,
    username    varchar(30) unique not null,
    password    varchar(60) unique not null
);