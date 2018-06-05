CREATE USER copy_admin WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION;

CREATE SCHEMA copy
  AUTHORIZATION copy_admin;

CREATE DATABASE copy_db
    WITH
    OWNER = copy_admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE TABLE copy.file
(
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    data bytea,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE copy.file
    OWNER to copy_admin;