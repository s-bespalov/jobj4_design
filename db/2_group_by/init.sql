create table devices
(
    id    serial primary key,
    "name"  varchar(255),
    price real
);

create table people
(
    id   serial primary key,
    "name" varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices ("name", price) values
    ('Smartphone X', 1200.5),
    ('Laptop Pro', 1850.2),
    ('Fitness Tracker 2000', 1299.8),
    ('Ultra HD TV', 156723.3),
    ('Wireless Earbuds', 345.7),
    ('Gaming Console Deluxe', 1767.2),
    ('Home Security System', 2999.1),
    ('Smart Watch Elite', 3868.6),
    ('Camera Drone X2', 19419.4),
    ('Virtual Reality Headset', 50350.0);

insert into people ("name") values
    ('John Doe'),
    ('Jane Smith'),
    ('Michael Johnson'),
    ('Emily Davis'),
    ('Christopher Brown');

INSERT INTO devices_people (device_id, people_id) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 1),
    (7, 2),
    (8, 3),
    (9, 4),
    (10, 5),
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
    (5, 1),
    (6, 2),
    (7, 3),
    (8, 4),
    (9, 5),
    (10, 1);
