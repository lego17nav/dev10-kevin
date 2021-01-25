use tinytheater;
insert into customer(firstname, lastname, email, customer_phone, address) 
select distinct 
customer_first,
customer_last,
customer_email,
customer_phone,
customer_address from rcttc;

select * from customer;

insert into theater(theater_name, theater_add, theater_phone, theater_email)
select distinct theater,
theater_address,
theater_phone,
theater_email from rcttc;

delete from theater;