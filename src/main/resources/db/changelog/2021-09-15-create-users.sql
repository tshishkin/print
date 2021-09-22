--liquibase formatted sql
--changeset t_shishkin:2021-09-15-create-users
--comment Создание таблицы


create table users (
	id bigint primary key,
	first_name varchar(32) not null,
	last_name varchar(32) not null,
	middle_name varchar(32) not null
);
create sequence users_seq;
