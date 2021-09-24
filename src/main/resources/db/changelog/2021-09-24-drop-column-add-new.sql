--liquibase formatted sql
--changeset t_shishkin:2021-09-24-drop-column-add-new.sql
--comment удаление столбца, добавление нового


alter table users drop column passport_attributes;

alter table users add column passport_serial varchar(16);
alter table users add column passport_number varchar(16);
