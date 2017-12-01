-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.0
-- PostgreSQL version: 9.6
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database
-- ;
-- -- ddl-end --
-- 

-- object: public.country | type: TABLE --
-- DROP TABLE IF EXISTS public.country CASCADE;
CREATE TABLE public.country(
	id serial NOT NULL,
	name varchar(40) NOT NULL,
	CONSTRAINT country_pk PRIMARY KEY (id),
	CONSTRAINT country_name_unique UNIQUE (name)

);
-- ddl-end --
ALTER TABLE public.country OWNER TO postgres;
-- ddl-end --

-- object: public.province | type: TABLE --
-- DROP TABLE IF EXISTS public.province CASCADE;
CREATE TABLE public.province(
	id serial NOT NULL,
	name varchar(50) NOT NULL,
	id_country integer NOT NULL,
	CONSTRAINT province_name_unique UNIQUE (name),
	CONSTRAINT province_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.province OWNER TO postgres;
-- ddl-end --

-- object: country_fk | type: CONSTRAINT --
-- ALTER TABLE public.province DROP CONSTRAINT IF EXISTS country_fk CASCADE;
ALTER TABLE public.province ADD CONSTRAINT country_fk FOREIGN KEY (id_country)
REFERENCES public.country (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.city | type: TABLE --
-- DROP TABLE IF EXISTS public.city CASCADE;
CREATE TABLE public.city(
	id serial NOT NULL,
	name varchar(50) NOT NULL,
	id_province integer NOT NULL,
	CONSTRAINT city_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.city OWNER TO postgres;
-- ddl-end --

-- object: province_fk | type: CONSTRAINT --
-- ALTER TABLE public.city DROP CONSTRAINT IF EXISTS province_fk CASCADE;
ALTER TABLE public.city ADD CONSTRAINT province_fk FOREIGN KEY (id_province)
REFERENCES public.province (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.address | type: TABLE --
-- DROP TABLE IF EXISTS public.address CASCADE;
CREATE TABLE public.address(
	id serial NOT NULL,
	street_name varchar(60) NOT NULL,
	street_number varchar(10) NOT NULL,
	postal_code varchar(10),
	id_city integer NOT NULL,
	CONSTRAINT address_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.address OWNER TO postgres;
-- ddl-end --

-- object: city_fk | type: CONSTRAINT --
-- ALTER TABLE public.address DROP CONSTRAINT IF EXISTS city_fk CASCADE;
ALTER TABLE public.address ADD CONSTRAINT city_fk FOREIGN KEY (id_city)
REFERENCES public.city (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.medical_point | type: TABLE --
-- DROP TABLE IF EXISTS public.medical_point CASCADE;
CREATE TABLE public.medical_point(
	id serial NOT NULL,
	phone_number varchar(13),
	name varchar(200) NOT NULL,
	id_address integer,
	longitude decimal,
	latitude decimal,
	CONSTRAINT medical_point_name_unique UNIQUE (name),
	CONSTRAINT medical_point_pk PRIMARY KEY (id),
	CONSTRAINT latitude_longitude_value CHECK (longitude>= -180.0 AND longitude<= 180.0 AND latitude >=-90.0 AND latitude <= 90.0)

);
-- ddl-end --
ALTER TABLE public.medical_point OWNER TO postgres;
-- ddl-end --

-- object: address_fk | type: CONSTRAINT --
-- ALTER TABLE public.medical_point DROP CONSTRAINT IF EXISTS address_fk CASCADE;
ALTER TABLE public.medical_point ADD CONSTRAINT address_fk FOREIGN KEY (id_address)
REFERENCES public.address (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: medical_point_uq | type: CONSTRAINT --
-- ALTER TABLE public.medical_point DROP CONSTRAINT IF EXISTS medical_point_uq CASCADE;
ALTER TABLE public.medical_point ADD CONSTRAINT medical_point_uq UNIQUE (id_address);
-- ddl-end --

-- object: public.medical_unit | type: TABLE --
-- DROP TABLE IF EXISTS public.medical_unit CASCADE;
CREATE TABLE public.medical_unit(
	id serial NOT NULL,
	name varchar(100) NOT NULL,
	phone_number varchar(13),
	id_medical_point integer NOT NULL,
	id_medical_unit_type integer,
	CONSTRAINT medical_unit_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.medical_unit OWNER TO postgres;
-- ddl-end --

-- object: medical_point_fk | type: CONSTRAINT --
-- ALTER TABLE public.medical_unit DROP CONSTRAINT IF EXISTS medical_point_fk CASCADE;
ALTER TABLE public.medical_unit ADD CONSTRAINT medical_point_fk FOREIGN KEY (id_medical_point)
REFERENCES public.medical_point (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.medical_unit_type | type: TABLE --
-- DROP TABLE IF EXISTS public.medical_unit_type CASCADE;
CREATE TABLE public.medical_unit_type(
	id serial NOT NULL,
	name varchar(60) NOT NULL,
	id_country integer NOT NULL,
	CONSTRAINT medical_unit_type_pk PRIMARY KEY (id),
	CONSTRAINT medical_unit_type_unique UNIQUE (name)

);
-- ddl-end --
ALTER TABLE public.medical_unit_type OWNER TO postgres;
-- ddl-end --

-- object: country_fk | type: CONSTRAINT --
-- ALTER TABLE public.medical_unit_type DROP CONSTRAINT IF EXISTS country_fk CASCADE;
ALTER TABLE public.medical_unit_type ADD CONSTRAINT country_fk FOREIGN KEY (id_country)
REFERENCES public.country (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: medical_unit_type_fk | type: CONSTRAINT --
-- ALTER TABLE public.medical_unit DROP CONSTRAINT IF EXISTS medical_unit_type_fk CASCADE;
ALTER TABLE public.medical_unit ADD CONSTRAINT medical_unit_type_fk FOREIGN KEY (id_medical_unit_type)
REFERENCES public.medical_unit_type (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.specialty | type: TABLE --
-- DROP TABLE IF EXISTS public.specialty CASCADE;
CREATE TABLE public.specialty(
	id serial NOT NULL,
	name varchar(50) NOT NULL,
	CONSTRAINT specialty_pk PRIMARY KEY (id),
	CONSTRAINT specialty_uq UNIQUE (name)

);
-- ddl-end --
ALTER TABLE public.specialty OWNER TO postgres;
-- ddl-end --

-- object: public."medical_unit/specialty" | type: TABLE --
-- DROP TABLE IF EXISTS public."medical_unit/specialty" CASCADE;
CREATE TABLE public."medical_unit/specialty"(
	id_medical_unit integer,
	id_specialty integer,
	CONSTRAINT "medical_unit/specialty_pk" PRIMARY KEY (id_medical_unit,id_specialty)

);
-- ddl-end --
ALTER TABLE public."medical_unit/specialty" OWNER TO postgres;
-- ddl-end --

-- object: medical_unit_fk | type: CONSTRAINT --
-- ALTER TABLE public."medical_unit/specialty" DROP CONSTRAINT IF EXISTS medical_unit_fk CASCADE;
ALTER TABLE public."medical_unit/specialty" ADD CONSTRAINT medical_unit_fk FOREIGN KEY (id_medical_unit)
REFERENCES public.medical_unit (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: specialty_fk | type: CONSTRAINT --
-- ALTER TABLE public."medical_unit/specialty" DROP CONSTRAINT IF EXISTS specialty_fk CASCADE;
ALTER TABLE public."medical_unit/specialty" ADD CONSTRAINT specialty_fk FOREIGN KEY (id_specialty)
REFERENCES public.specialty (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: public.illness | type: TABLE --
-- DROP TABLE IF EXISTS public.illness CASCADE;
CREATE TABLE public.illness(
	id serial NOT NULL,
	name varchar(50),
	CONSTRAINT illness_pk PRIMARY KEY (id),
	CONSTRAINT illness_uq UNIQUE (name)

);
-- ddl-end --
ALTER TABLE public.illness OWNER TO postgres;
-- ddl-end --

-- object: public."specialty/illness" | type: TABLE --
-- DROP TABLE IF EXISTS public."specialty/illness" CASCADE;
CREATE TABLE public."specialty/illness"(
	id_illness integer,
	id_specialty integer,
	CONSTRAINT "specialty/illness_pk" PRIMARY KEY (id_illness,id_specialty)

);
-- ddl-end --
ALTER TABLE public."specialty/illness" OWNER TO postgres;
-- ddl-end --

-- object: illness_fk | type: CONSTRAINT --
-- ALTER TABLE public."specialty/illness" DROP CONSTRAINT IF EXISTS illness_fk CASCADE;
ALTER TABLE public."specialty/illness" ADD CONSTRAINT illness_fk FOREIGN KEY (id_illness)
REFERENCES public.illness (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: specialty_fk | type: CONSTRAINT --
-- ALTER TABLE public."specialty/illness" DROP CONSTRAINT IF EXISTS specialty_fk CASCADE;
ALTER TABLE public."specialty/illness" ADD CONSTRAINT specialty_fk FOREIGN KEY (id_specialty)
REFERENCES public.specialty (id) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: public.business_hours | type: TABLE --
-- DROP TABLE IF EXISTS public.business_hours CASCADE;
CREATE TABLE public.business_hours(
	id serial NOT NULL,
	open time NOT NULL,
	close time NOT NULL,
	day_of_week int2 NOT NULL,
	last_update timestamp,
	id_medical_unit integer NOT NULL,
	CONSTRAINT check_day_of_week CHECK (day_of_week>=0 AND day_of_week<=6),
	CONSTRAINT business_hours_pk PRIMARY KEY (id),
	CONSTRAINT day_of_week_uq UNIQUE (day_of_week)

);
-- ddl-end --
ALTER TABLE public.business_hours OWNER TO postgres;
-- ddl-end --

-- object: public.shifts | type: TABLE --
-- DROP TABLE IF EXISTS public.shifts CASCADE;
CREATE TABLE public.shifts(
	start timestamp NOT NULL,
	length numeric NOT NULL,
	id serial NOT NULL,
	id_medical_unit integer NOT NULL,
	CONSTRAINT shifts_pk PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE public.shifts OWNER TO postgres;
-- ddl-end --

-- object: medical_unit_fk | type: CONSTRAINT --
-- ALTER TABLE public.shifts DROP CONSTRAINT IF EXISTS medical_unit_fk CASCADE;
ALTER TABLE public.shifts ADD CONSTRAINT medical_unit_fk FOREIGN KEY (id_medical_unit)
REFERENCES public.medical_unit (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: shifts_uq | type: CONSTRAINT --
-- ALTER TABLE public.shifts DROP CONSTRAINT IF EXISTS shifts_uq CASCADE;
ALTER TABLE public.shifts ADD CONSTRAINT shifts_uq UNIQUE (id_medical_unit);
-- ddl-end --

-- object: medical_unit_fk | type: CONSTRAINT --
-- ALTER TABLE public.business_hours DROP CONSTRAINT IF EXISTS medical_unit_fk CASCADE;
ALTER TABLE public.business_hours ADD CONSTRAINT medical_unit_fk FOREIGN KEY (id_medical_unit)
REFERENCES public.medical_unit (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: business_hours_uq | type: CONSTRAINT --
-- ALTER TABLE public.business_hours DROP CONSTRAINT IF EXISTS business_hours_uq CASCADE;
ALTER TABLE public.business_hours ADD CONSTRAINT business_hours_uq UNIQUE (id_medical_unit);
-- ddl-end --



