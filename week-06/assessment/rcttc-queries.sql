select * from shows
inner join times_show on shows.showid = times_show.showid
inner join show_times on times_show.showtime_id = show_times.showtime_id
where date(show_date) between "2021-10-01" and "2021-12-31";

select distinct firstname, lastname from customer;

select * from customer
where email not like "%.com";

select distinct shows.show_name, ticket.ticket_price from ticket
inner join show_times on show_times.showtime_id = ticket.showtime_id
inner join times_show on times_show.showtime_id = show_times.showtime_id
inner join shows on times_show.showid = shows.showid
order by ticket_price
limit 3;

select distinct customer.firstname, customer.lastname, shows.show_name from customer
inner join ticket on customer.customerid = ticket.customerid
inner join seats on ticket.seat_id = seats.seat_id
inner join theater on seats.theater_id = theater.theater_id
inner join shows on theater.theater_id = shows.showid
order by firstname, lastname;

select distinct customer.firstname, customer.lastname, theater.theater_name, seats.seat_number from customer
inner join ticket on customer.customerid = ticket.customerid
inner join seats on ticket.seat_id = seats.seat_id
inner join theater on seats.theater_id = theater.theater_id
inner join shows on theater.theater_id = shows.showid
order by firstname, lastname;


select * from customer
where address = "";

select distinct * from customer
inner join ticket on customer.customerid = ticket.customerid
inner join seats on ticket.seat_id = seats.seat_id
inner join theater on theater.theater_id = seats.theater_id
inner join show_times on ticket.showtime_id = show_times.showtime_id
inner join times_show on show_times.showtime_id = times_show.showtime_id
inner join shows on times_show.showid = shows.showid and theater.theater_id = shows.theater_id
order by customer.customerid;

select customer.firstname, customer.lastname, count(*) from customer
inner join ticket on customer.customerid = ticket.customerid
group by customer.firstname, customer.lastname;

select distinct shows.show_name, sum(ticket.ticket_price) from ticket
inner join seats on ticket.seat_id = seats.seat_id
inner join theater on theater.theater_id = seats.theater_id
inner join show_times on ticket.showtime_id = show_times.showtime_id
inner join times_show on show_times.showtime_id = times_show.showtime_id
inner join shows on times_show.showid = shows.showid and theater.theater_id = shows.theater_id
group by shows.show_name;



select theater.theater_name, sum(ticket.ticket_price) from theater
inner join seats on theater.theater_id = seats.theater_id
inner join ticket on seats.seat_id = ticket.seat_id
group by theater.theater_name;



select customer.firstname, customer.lastname , sum(ticket.ticket_price) from customer
inner join ticket on customer.customerid = ticket.customerid
group by customer.firstname, customer.lastname
order by sum(ticket.ticket_price) desc
limit 1;

