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
  user_id uuid NOT NULL,
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

-- Create table employee
CREATE TABLE copy.employee (
	id uuid NOT NULL,
	first_name varchar(256) NOT NULL,
	last_name varchar(256) NOT NULL,
	tittle varchar(256) NOT NULL,
	CONSTRAINT employee_pk PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
) ;
ALTER TABLE copy.employee
  OWNER TO copy_admin;

-- Create table customer
CREATE TABLE copy.customer (
	id uuid NOT NULL,
	first_name varchar(256) NOT NULL,
	last_name varchar(256) NOT NULL,
	address varchar(256) NULL,
	email varchar(256) NULL,
	phone_number varchar(10) NULL,
	CONSTRAINT customer_pk PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
) ;
ALTER TABLE copy.customer
  OWNER TO copy_admin;

-- Create service table
CREATE TABLE "copy".service (
	id uuid NOT NULL,
	file_id uuid NOT NULL,
	service_type varchar(256) NOT NULL,
	material_id varchar NOT NULL,
	copies_number integer NOT NULL,
	pages_per_sheet integer NOT NULL,
	pages varchar(256) NULL,
	"comment" varchar(512) NULL,
	equipment_id uuid NULL,
	CONSTRAINT service_pk PRIMARY KEY (id),
	CONSTRAINT service_file_fk FOREIGN KEY (file_id) REFERENCES "copy".file(id)
)
WITH (
	OIDS=FALSE
) ;

-- Create order table
CREATE TABLE "copy"."order" (
	id uuid NOT NULL,
	customer_id uuid NULL,
	employee_id uuid NULL,
	status varchar(128) NOT NULL,
	create_date date NULL,
	execution_date date NULL,
	delivery_date date NULL,
	CONSTRAINT order_pk PRIMARY KEY (id),
	CONSTRAINT order_customer_fk FOREIGN KEY (customer_id) REFERENCES "copy".customer(id),
	CONSTRAINT order_employee_fk FOREIGN KEY (employee_id) REFERENCES "copy".employee(id)
)
WITH (
	OIDS=FALSE
) ;

-- Add order service fk
ALTER TABLE "copy".service ADD order_id uuid NOT NULL;
ALTER TABLE "copy".service ADD CONSTRAINT service_order_fk FOREIGN KEY (order_id) REFERENCES "copy"."order"(id) ;

-- Change service table
ALTER TABLE "copy".service RENAME COLUMN service_type TO service_type_id;
ALTER TABLE "copy".service ALTER COLUMN service_type_id TYPE uuid USING service_type_id::uuid;

-- Create service_type table
CREATE TABLE "copy".service_type (
	id uuid NOT NULL,
	"name" varchar(256) NOT NULL,
	CONSTRAINT service_type_pk PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
) ;

-- Change service table
ALTER TABLE "copy".service ADD CONSTRAINT service_service_type_fk FOREIGN KEY (service_type_id) REFERENCES "copy".service_type(id) ;

-- Create materia table
CREATE TABLE "copy".material (
	id uuid NOT NULL,
	"name" varchar(256) NOT NULL,
	CONSTRAINT material_pk PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
) ;

-- Change service table
ALTER TABLE "copy".service ALTER COLUMN material_id TYPE uuid USING material_id::uuid;
ALTER TABLE "copy".service ADD CONSTRAINT service_material_fk FOREIGN KEY (material_id) REFERENCES "copy".material(id) ;

-- Create equipment table
CREATE TABLE "copy".equipment (
	id uuid NOT NULL,
	"name" varchar(256) NOT NULL
)
WITH (
	OIDS=FALSE
) ;
ALTER TABLE "copy".equipment ADD CONSTRAINT equipment_pk PRIMARY KEY (id) ;

-- Change service table
ALTER TABLE "copy".service ADD CONSTRAINT service_equipment_fk FOREIGN KEY (equipment_id) REFERENCES "copy".equipment(id) ;

-- create equipment service type map
CREATE TABLE "copy".equipment_service_type_map (
	equipment_id uuid NOT NULL,
	service_type_id uuid NOT NULL,
	CONSTRAINT equipment_service_type_map_equipment_fk FOREIGN KEY (equipment_id) REFERENCES "copy".equipment(id),
	CONSTRAINT equipment_service_type_map_service_type_fk FOREIGN KEY (service_type_id) REFERENCES "copy".service_type(id)
)
WITH (
	OIDS=FALSE
) ;

--create equipment material map
CREATE TABLE "copy".equipment_material_map (
	equipment_id uuid NOT NULL,
	material_id uuid NOT NULL,
	CONSTRAINT equipment_material_map_equipment_fk FOREIGN KEY (equipment_id) REFERENCES "copy".equipment(id),
	CONSTRAINT equipment_material_map_material_fk FOREIGN KEY (material_id) REFERENCES "copy".material(id)
)
WITH (
	OIDS=FALSE
) ;



