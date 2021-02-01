drop database if exists solarpanel;

create database solarpanel;

use solarpanel;

create table solarpanel (
	section varchar(50) not null,
    varRow int not null,
    varCol int not null,
    yearinstall int not null, 
	material varchar(50) not null,
    tracking bit,
    uniqueKey varchar(20) not null,
    panelid int not null primary key auto_increment
          
);

delimiter //
create procedure set_known_good_state()
begin
truncate table solarpanel;
insert into solarpanel (section, varRow, varCol, yearInstall, material, tracking, uniqueKey)

values
	("Upper", 1, 1, 2010, "Multicrystalline silicon", 1, "Upper:1:1"),
    ("Lower", 1, 1, 2020, "Cadmium telluride", 0, "Lower:1:1");
end //
delimiter ;    

select * from solarpanel;    