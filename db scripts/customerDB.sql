-- creating a database
CREATE DATABASE customerDB;
-- creating a customer table
create table customerDB.customer(
customer_id int NOT NULL AUTO_INCREMENT primary key,
first_name varchar(50) ,
last_name varchar(50) ,
email varchar(320),
date_of_birth date,
mobile_number varchar(10),
phone_number varchar(10),
password nvarchar(100)
);
-- creating a address table
create table customerDB.address(
address_id int NOT NULL AUTO_INCREMENT primary key,
customer_id int,
email varchar(320),
street_address varchar(320),
street_address2 varchar(320),
city varchar(50),
state varchar(50),
zip_code varchar(50),
FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

