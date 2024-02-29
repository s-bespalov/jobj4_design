create table car_bodies (
    id serial primary key,
    "name" varchar(100)
);

create table car_engines (
    id serial primary key,
    "name" varchar(100)
);

create table car_transmissions (
    id serial primary key,
    "name" varchar(100)
);

create table cars (
    id serial primary key,
    "name" varchar(100),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values
    ('Седан'),
    ('Хэтчбек'),
    ('Универсал'),
    ('Купе'),
    ('Внедорожник'),
    ('Кроссовер'),
    ('Кабриолет'),
    ('Минивэн'),
    ('Пикап'),
    ('Лимузин');

insert into car_engines (name) values
    ('Бензиновый'),
    ('Дизельный'),
    ('Электрический'),
    ('Гибридный'),
    ('Турбированный бензиновый'),
    ('Турбированный дизельный'),
    ('Электрический с гибридной системой'),
    ('Гибридный с турбированным бензиновым двигателем'),
    ('Гибридный с турбированным дизельным двигателем'),
    ('Турбированный бензиновый с электродвигателем');

insert into car_transmissions (name) values
    ('Механическая'),
    ('Автоматическая'),
    ('Роботизированная'),
    ('Вариатор'),
    ('Полуавтоматическая');

insert into cars (name, body_id, engine_id, transmission_id) values
    ('Audi A4', 1, 1, 2),
    ('BMW X5', 3, 1, 3),
    ('Volkswagen Golf', 2, 2, 1),
    ('Ford Focus', 1, 2, 2),
    ('Toyota Prius', 2, 3, 2),
    ('Renault Clio', 1, 3, 1),
    ('Mercedes-Benz E-Class', 3, 2, 3),
    ('Nissan Leaf', 2, 3, 3),
    ('Chevrolet Cruze', 1, 1, 1),
    ('Ford Mustang', 3, 1, 2),
    ('Tesla Model S', null, 3, 1),
    ('Honda Civic', 2, null, 2),
    ('Mazda MX-5', 1, 1, null),
    ('Kia Sportage', 3, null, 1),
    ('Volvo XC90', null, null, 3),
    ('Jeep Wrangler', null, 2, 2),
    ('Subaru Outback', 2, 3, null),
    ('Hyundai Elantra', null, 1, 1),
    ('Porsche 911', 1, null, 3),
    ('Ferrari 488', 3, null, null);