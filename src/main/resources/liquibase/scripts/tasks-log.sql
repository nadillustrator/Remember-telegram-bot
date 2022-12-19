-- liquibase formatted sql

-- changeSet nadillustrator:1
-- create table notification_tasks (
--     id SERIAL,
--     chat_id INT8 not null ,
--     task TEXT not null ,
--     dateAndTime TIMESTAMP not null
--
-- );

-- changeSet nadillustrator:2
alter table notification_tasks
    add column isShown boolean;
