--liquibase formatted sql
--changeset t_shishkin:2021-09-23-add-date-column.sql
--comment Добавление столбца даты


alter table users add column creation_date timestamp;
