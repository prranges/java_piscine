drop database if not exists chat;

drop table if exists message, chatroom, users;

create table if not exists users (
    id          serial primary key,
    login       varchar(30) unique not null,
    password    varchar(30) not null
);

create table if not exists chatroom (
    id          serial primary key,
    name        varchar(30) unique not null,
    owner       int references users(id)
);

create table if not exists message (
    id          serial primary key,
    author      int references users(id),
    room        int references chatroom(id),
    text        text not null ,
    datetime    timestamp
);