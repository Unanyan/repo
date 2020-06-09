create database chat;

use chat;

create table users
(
    id       int          not null auto_increment primary key,
    name     varchar(65)  not null,
    surname  varchar(65)  not null,
    email    varchar(255) not null,
    password varchar(255) not null
);

create unique index email_unique_index on shippers(email);
create index password_index on shippers(password);