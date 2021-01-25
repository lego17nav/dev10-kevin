-- drop database if exists tinytheater;
-- create database tinytheater;
-- use tinytheater;

-- select count(*) from tinytheater.rcttc;

drop table if exists customer;
create table customer (
	customerid int primary key auto_increment,
	firstname varchar(25) not null,
    lastname varchar(25) not null,
    email varchar(25) not null,
    customer_phone varchar(15) null,
    address varchar(50) null
);
alter table customer
	modify email varchar(50) not null;

drop table if exists theater;
create table theater (
	theater_id int primary key auto_increment,
	theater_name varchar(25) not null,
    theater_add varchar(50) not null,
    theater_phone varchar(15) not null,
    theater_email varchar(25) not null
);

drop table if exists show_times; 
create table show_times (
	showtime_id int primary key auto_increment,
    show_date date
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


drop table if exists ticket;
create table ticket (
	ticket_id int primary key auto_increment,
    tikcet_price decimal(3,2),
    customerid int not null,
    showtime_id int not null,
    constraint fk_ticket_customerid
      foreign key (customerid)
      references customer(customerid),
    constraint fk_show_times
	  foreign key (showtime_id)
      references show_times(showtime_id)
);


drop table if exists seats;
create table seats (
	seat_number char(5),
    seat_id int primary key auto_increment,
    theater_id int,
    ticket_id int not null,
    constraint fk_seats_theater_id
		foreign key (theater_id)
        references theater(theater_id),
    constraint fk_seats_ticket_id
		foreign key (ticket_id)
        references ticket(ticket_id)
    );
    


