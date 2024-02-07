create table building_projects(
    id serial primary key,
    number varchar(255),
    building_info text
);

create table buildings(
    id serial primary key,
    address text,
    project_id int references building_projects(id) unique
);

create table apartments(
    id serial primary key,
    number varchar(255),
    building_id int references buildings(id)
)

create table residents(
    id serial primary key,
    resident_name varchar(255)
)

create table apartments_residents(
    apartment_id int references apartments(id),
    resident_id int references residents(id)
)