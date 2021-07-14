select * from customer
where customer_name regexp '[D][a-z]*$'

delimiter //
create procedure get_list_customer()
begin
select c.customer_id, c.customer_code, c.customer_name, c.customer_birthday, c.customer_gender, c.customer_id_card, c.customer_phone,
		c.customer_email, c.customer_address, ct.customer_type_name
from customer c 
inner join customer_type ct on c.customer_type_id = ct.customer_type_id; 
end;
// delimiter ;


delimiter //
create procedure get_list_customer_type()
begin
select *
from customer_type ;
end;
// delimiter ;


delimiter //
create procedure insert_customer (new_code varchar(20), new_name varchar(45) , new_birthday date ,
				new_gender bit(1), new_id_card varchar(45), new_phone varchar(15) ,
                new_email varchar(45) ,new_address varchar(45),new_type_id int)
begin
insert into customer  (customer_code, customer_name ,customer_birthday,customer_gender, customer_id_card, customer_phone, customer_email,
					customer_address, customer_type_id) 
values (new_code,
		new_name,
		new_birthday, 
		new_gender, 
		new_id_card, 
		new_phone, 
		new_email, 
		new_address, 
		new_type_id
			);
end ;
// delimiter ;

delimiter //
create procedure get_customer_by_id(id int)
begin
select c.customer_id, c.customer_code, c.customer_name, c.customer_birthday, c.customer_gender, c.customer_id_card, c.customer_phone,
		c.customer_email, c.customer_address,ct.customer_type_id, ct.customer_type_name
from customer c 
inner join customer_type ct on c.customer_type_id = ct.customer_type_id
where c.customer_id = id;
end;
// delimiter ;

delimiter //
create procedure update_customer(id int, `code` varchar(10), `name` varchar(45), birth_day date, gender bit, id_card varchar(15)
								, phone varchar(12), email varchar(45), address varchar(45), type_id int)
begin
update customer
set 
	customer.customer_code = `code`,
    customer.customer_name = `name`,
    customer.customer_birthday = birth_day,
    customer.customer_gender = gender,
    customer.customer_id_card = id_card,
    customer.customer_phone = phone,
    customer.customer_email = email,
    customer.customer_address = address,
    customer.customer_type_id = type_id
where customer.customer_id = id;
end;
// delimiter ;


delimiter //
create procedure delete_by_id(id int)
begin
delete from customer
where customer_id = id;
end;
// delimiter ;

call delete_by_id(5);
call get_list_customer();
call get_list_customer_type();

delimiter //
create procedure search(`code` varchar(45),`name` varchar(45),address varchar(45))
begin
select c.customer_id, c.customer_code, c.customer_name, c.customer_birthday, c.customer_gender, c.customer_id_card, c.customer_phone,
		c.customer_email, c.customer_address, ct.customer_type_name
from customer c
inner join customer_type ct on c.customer_type_id = ct.customer_type_id
where c.customer_code like concat('%',`code`,'%') and substring_index(c.customer_name,' ',-1) like concat('%',`name`,'%') 
	and c.customer_address like concat('%',address,'%');
end;
// delimiter ;

call search('KH-1','Don','Viet Nam');
call search('KH-1111','','')	