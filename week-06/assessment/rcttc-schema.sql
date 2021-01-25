-- drop database if exists tinytheater;
-- create database tinytheater;
-- use tinytheater;

-- select count(*) from tinytheater.rcttc;

drop table if exists customer;
create table customer (
	customerid int primary key auto_increment,
	firstname varchar(25) not null,
    lastname varchar(25) not null,
    email varchar(50) not null,
    customer_phone varchar(15) null,
    address varchar(50) null
);

drop table if exists theater;
create table theater (
	theater_id int primary key auto_increment,
	theater_name varchar(25) not null,
    theater_add varchar(50) not null,
    theater_phone varchar(15) not null,
    theater_email varchar(25) not null
);

drop table if exists shows;
create table shows (
	showid int not null primary key auto_increment,
    show_name varchar(25) not null,
    theater_id int not null,
    constraint fk_shows_theater_id
		foreign key (theater_id)
        references theater(theater_id)
);

drop table if exists times_show;
create table times_show (
	showtime_id int not null,
    showid int not null,
    constraint fk_showtime_id
		foreign key (showtime_id)
        references show_times(showtime_id),
    constraint fk_shows_showid
		foreign key (showid)
        references shows(showid),
    constraint pk_times_show
		primary key(showtime_id, showid)
);
  
drop table if exists seats;
create table seats (
	seat_number char(5),
    seat_id int primary key auto_increment,
    theater_id int,
    constraint fk_seats_theater_id
		foreign key (theater_id)
        references theater(theater_id)
    );  

drop table if exists ticket;
create table ticket (
	ticket_id int primary key auto_increment,
    ticket_price decimal(5,2),
    customerid int not null,
    showtime_id int not null,
    seat_id int not null,
    constraint fk_ticket_customerid
      foreign key (customerid)
      references customer(customerid),
    constraint fk_show_times
	  foreign key (showtime_id)
      references show_times(showtime_id),
    constraint fk_ticket_seat_id
      foreign key (seat_id)
      references seats(seat_id)
);

    





