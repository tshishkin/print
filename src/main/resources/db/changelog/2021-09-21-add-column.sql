--liquibase formatted sql
--changeset t_shishkin:2021-09-21-add-column.sql
--comment добавление столбца

alter table users add column passport_attributes varchar(16);

