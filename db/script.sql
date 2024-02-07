create table buildings (
    id serial primary key, 
    address text,
    built_date date,
    floors numeric
);

insert into buildings(address, built_date, floors) values ('Vosdvizhenka str. 1. Moscow 121019. Russia', '1516-01-01',  5);

update buildings set built_date = '1485-01-01';

delete from buildings;

select * from buildings;
