--liquibase formatted sql

--changeset apawlak:1
CREATE TABLE COMICS_READ_MODEL (
  COMICS_ID varchar(255) not null,
  TITLE varchar(255) not null,
  PUBLISHING_HOUSE varchar(255) not null,
  COMICS_NUMBER BIGINT not null
);

-- GRANT SELECT, INSERT, UPDATE, DELETE ON COMICS_READ_MODEL TO comics_user;
