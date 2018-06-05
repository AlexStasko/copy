-- Create role
CREATE ROLE copy_admin LOGIN ENCRYPTED PASSWORD 'md51f42d2b64b5c0a827ca2217e3de1fab5'
   VALID UNTIL 'infinity';

--Create database
CREATE DATABASE copy_db
    WITH
    OWNER = copy_admin
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

-- Create schema
CREATE SCHEMA copy
  AUTHORIZATION copy_admin;

-- Create table file
CREATE TABLE copy.file
(
    id uuid NOT NULL,
    name character varying(255) NOT NULL,
    data bytea,
    CONSTRAINT pk_file PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE copy.file
    OWNER to copy_admin;

-- Create table account
CREATE TABLE copy.account
(
  id uuid NOT NULL,
  username character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  CONSTRAINT pk_account PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE copy.account
  OWNER TO copy_admin;

-- Create table role
CREATE TABLE copy.role
(
  id uuid NOT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT pk_role PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE copy.role
  OWNER TO copy_admin;

-- Create table role account map
CREATE TABLE copy.role_account_map
(
  account_id uuid NOT NULL,
  role_id uuid NOT NULL,
  CONSTRAINT pk_role_account_map PRIMARY KEY (role_id, account_id),
  CONSTRAINT fk_role_account_map_account FOREIGN KEY (account_id) REFERENCES copy.account (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_role_account_map_role FOREIGN KEY (role_id) REFERENCES copy.role (id) ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE copy.role_account_map
  OWNER TO copy_admin;