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

-- changeSet nadillustrator:3
create table photo
(
    id      SERIAL,
    file_id TEXT not null,
    notification_task_id INT8 not null
);

-- changeSet nadillustrator:4
ALTER TABLE photo
    DROP COLUMN notification_task_id;

-- changeSet nadillustrator:5
alter table notification_tasks
    add column photo_id int8;

-- changeSet nadillustrator:6
create index notification_tasks_index on notification_tasks (id);

