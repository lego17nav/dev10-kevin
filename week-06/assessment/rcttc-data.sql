use tinytheater;
 insert into customer(firstname, lastname, email, customer_phone, address) 
 select distinct 
 customer_first,
 customer_last,
 customer_email,
 customer_phone,
 customer_address from rcttc;

-- select * from customer;

 insert into theater(theater_name, theater_add, theater_phone, theater_email)
 select distinct theater, theater_address, theater_phone, theater_email from rcttc;
 insert into shows(theater_id, show_name)
 select distinct theater.theater_id, rcttc.`show` from rcttc
 inner join theater on rcttc.theater = theater.theater_name;

 insert into show_times(show_date)
 select distinct `date` from rcttc;

 insert into times_show(showtime_id,showid)
 select distinct show_times.showtime_id,
 shows.showid
 from rcttc
 inner join show_times on rcttc.`date` = show_times.show_date
 inner join shows on rcttc.`show` = shows.show_name;


 insert into seats(seat_number,theater_id)
 select distinct rcttc.seat, theater.theater_id from rcttc
 inner join theater on rcttc.theater = theater.theater_name;

 insert into ticket(ticket_price, customerid, showtime_id, seat_id)
 select distinct rcttc.ticket_price, customer.customerid, show_times.showtime_id, seats.seat_id  from rcttc
 inner join theater on rcttc.theater = theater.theater_name
 inner join seats on rcttc.seat = seats.seat_number and theater.theater_id = seats.theater_id
 inner join customer on rcttc.customer_last = customer.lastname and rcttc.customer_first = customer.firstname
 inner join show_times on rcttc.`date` = show_times.show_date;
set sql_safe_updates = 0;

-- update rcttc
-- set ticket_price = 22.25
-- where `show` = "The Sky Lit Up" and theater = "Little Fitz" and date(`date`) = date('2021-03-01');

select * from rcttc
where `show` = "The Sky Lit Up" and customer_first = "Pooh";

update rcttc
set seat = "B4"
where customer_first = "Pooh" and seat = "A4";

update rcttc
set seat = "C2"
where customer_first = "Cullen" and seat = "B4";

update rcttc
set seat = "A4"
where customer_first = "Chiarra" and seat = "C2";

update rcttc
set customer_phone = "1-801-EAT-CAKE"
where customer_last = "Swindles";

delete from rcttc
where customer_first = "Lucien" or customer_first = "Loralie" and theater = "10 Pin";

delete from rcttc where customer_first = "Liv"