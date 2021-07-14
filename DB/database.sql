drop database if exists furama_resort;
create database furama_resort;
use furama_resort;

create table position (
	position_id int primary key auto_increment,
    position_name varchar(45) not null
);

create table education_degree (
	education_degree_id int primary key auto_increment,
    education_degree_name varchar(45)not null
);

create table division (
	division_id int primary key auto_increment,
    division_name varchar(45) not null
);

create table `role` (
	role_id int primary key auto_increment,
    role_name varchar(255) not null
);

create table rent_type (
	rent_type_id int primary key auto_increment,
    rent_type_name varchar(45),
    rent_type_cost double not null
);

create table service_type (
	service_type_id int primary key auto_increment,
    service_type_name varchar(45) not null
);

create table service (
	service_id int primary key auto_increment,
    service_code varchar(45) not null unique,
    service_name varchar(45) not null,
    service_area int not null,
    service_cost double not null,
    service_max_people int not null,
    standard_room varchar(45) not null,
	description_other_convenience varchar(45),
    pool_area double not null,
    number_of_floor int not null,
    rent_type_id int,
	service_type_id	int,
    constraint renttype foreign key(rent_type_id) references rent_type(rent_type_id) on delete cascade,
    constraint servicetype foreign key(service_type_id) references service_type(service_type_id) on delete cascade
);

create table customer_type (
	customer_type_id int primary key auto_increment,
    customer_type_name varchar(45) not null
);

create table customer (
	customer_id int primary key auto_increment,
    customer_code varchar(20) not null unique,
    customer_name varchar(45) not null,
    customer_birthday date not null,
    customer_gender bit(1) not null,
    customer_id_card varchar(45) unique not null,
    customer_phone varchar(15) unique not null,
    customer_email varchar(45) unique not null,
    customer_address varchar(45) not null,
    customer_type_id int,
    constraint customer_type foreign key(customer_type_id) references customer_type(customer_type_id) on delete cascade
);

create table `user` (
	username varchar(45) primary key,
    `password` varchar(45) not null
);

create table user_role (
	username varchar(45) ,
    role_id int ,
    primary key (username,role_id),
    constraint torole foreign key(role_id) references `role`(role_id) on delete cascade,
    constraint touser foreign key(username) references `user`(username) on delete cascade
);

create table employee (
	employee_id int primary key auto_increment,
    employee_name varchar(45) not null,
    employee_birthday date not null,
    employee_id_card varchar(15) unique not null,
    employee_salary double not null,
    employee_phone varchar(15) unique not null,
    employee_email varchar(45) unique not null,
    employee_address varchar(45) not null,
    position_id int,
    education_degree_id int,
    division_id int,
    username varchar(45),
    constraint fk_pos_em foreign key(position_id) references `position`(position_id) on delete cascade,
    constraint fk_edu_em foreign key(education_degree_id) references education_degree(education_degree_id) on delete cascade,
    constraint fk_div_em foreign key(division_id) references division(division_id) on delete cascade,
    constraint fk_user_em foreign key(username) references `user`(username) on delete cascade
);

create table contract(
	contract_id int primary key auto_increment,
    contract_start_date date not null,
    contract_end_date date not null,
    contract_deposit double not null,
    employee_id int,
    customer_id int,
    service_id int,
    constraint fk_eml_con foreign key(employee_id) references employee(employee_id) on delete cascade,
    constraint fk_cus_con foreign key(customer_id) references customer(customer_id) on delete cascade,
    constraint fk_ser_con foreign key(service_id) references service(service_id) on delete cascade
);
create table attach_service (
	attach_service_id int primary key auto_increment,
    attach_service_name varchar(45) not null,
    attach_service_cost double not null,
    attach_service_unit int not null,
    attach_service_status varchar(45)
);

create table contract_detail(
	contract_detail_id int not null,
    contract_id int,
    attach_service_id int,
    primary key (contract_id,attach_service_id),
    foreign key(contract_id)  references contract(contract_id) on delete cascade,
    foreign key(attach_service_id)  references attach_service(attach_service_id) on delete cascade
);


