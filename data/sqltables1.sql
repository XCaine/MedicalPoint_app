-- https://stackoverflow.com/questions/9789736/how-to-implement-a-many-to-many-relationship-in-postgresql

--tablica zawierajaca liste krajow

CREATE TYPE weekday AS ENUM ('Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun');

CREATE TABLE country (
  id serial,
  name varchar(50) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE medical_unit_type (
  id serial,
  name varchar(100) NOT NULL,
  country_id integer NOT NULL REFERENCES country (id),
  PRIMARY KEY (id)
);

--tablica z podstawowym podzialem administacyjnym tj. wojewodztwo, prowincja
CREATE TABLE province (
  id serial,
  country_id integer REFERENCES country (id),
  name varchar(50) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);

--tablica z miastami wewnatrz prowincji

CREATE TABLE city (
  id serial,
  province_id integer NOT NULL REFERENCES province (id),
  name varchar(50),
  PRIMARY KEY (id)
);

--tablica z adresami wewnatrz miast

CREATE TABLE address (
  id serial,
  city_id integer NOT NULL REFERENCES city (id),
  name varchar(50) NOT NULL,
  street_number varchar(10) NOT NULL,
  postal_code varchar(8) NOT NULL,
  PRIMARY KEY (id)
);

--medical point ma przypisany adres np. przychodnia, szpital

CREATE TABLE medical_point (
  id serial,
  address_id integer REFERENCES address (id),
  name varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

--medical point sklada sie z medical unitow np. oddzialow

CREATE TABLE medical_unit (
  id serial,
  medical_point_id integer NOT NULL REFERENCES medical_point (id),
  name varchar(50) NOT NULL,
  phone integer,
  medical_unit_type_id integer REFERENCES medical_unit_type (id),
  PRIMARY KEY (id)
);

--specjalizacje medyczne

CREATE TABLE specialty (
  id serial,
  name varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

--junction table specialty/medical-unity

CREATE TABLE medical_unit_specialty (
  medical_unit_id integer NOT NULL REFERENCES medical_unit (id),
  specialty_id integer NOT NULL REFERENCES specialty (id),
  CONSTRAINT id PRIMARY KEY (medical_unit_id, specialty_id)
);

--godziny pracy dla placowek pracujacych regularnie

CREATE TABLE business_hours (
  id serial,
  medical_unit_id integer NOT NULL REFERENCES medical_unit (id),
  day_of_week weekday UNIQUE NOT NULL,
  open TIME WITHOUT TIME ZONE NOT NULL,
  close TIME WITHOUT TIME ZONE NOT NULL,
  last_uptade TIMESTAMP WITHOUT TIME ZONE,
  PRIMARY KEY (id)
);

--lista dyzurow dla oddzialow

CREATE TABLE shifts (
  id serial,
  medical_unit_id integer NOT NULL REFERENCES medical_unit (id),
  start TIMESTAMP WITHOUT TIME ZONE UNIQUE NOT NULL,
  length numeric NOT NULL DEFAULT 24, --max 24h
  PRIMARY KEY (id)  
);












