create table type(
    id serial primary key,
    "name" varchar(255)
);

create table product(
    id serial primary key,
    "name" varchar(255),
    type_id int references type(id),
    expired_date date,
    price real
);

insert into type ("name") values
('Молочные продукты'),
('Мясо и мясные продукты'),
('Овощи и фрукты'),
('Хлебобулочные изделия'),
('Кондитерские изделия'),
('Напитки'),
('Крупы и зернобобовые'),
('Замороженные продукты'),
('Консервированные продукты'),
('Детское питание'),
('Сыр'),

insert into product ("name", type_id, expired_date, price) values
('Мороженое ванильное', 8, '2024-03-15', 150.00),
('Мороженое шоколадное', 8, '2024-03-10', 160.00),
('Мороженое фруктовое', 8, '2024-03-12', 140.00),
('Мороженое клубничное', 8, '2024-03-14', 170.00),
('Мороженое карамельное', 8, '2024-03-16', 180.00),
('Молоко цельное', 1, '2024-03-05', 80.00),
('Творог', 1, '2024-03-08', 90.00),
('Сыр чеддер', 11, '2024-03-20', 200.00),
('Мясо говядины', 2, '2024-03-18', 300.00),
('Куриная грудка', 2, '2024-03-16', 250.00),
('Огурцы', 3, '2024-03-14', 70.00),
('Яблоки', 3, '2024-03-07', 100.00),
('Хлеб пшеничный', 4, '2024-03-09', 50.00),
('Печенье шоколадное', 5, '2024-03-11', 120.00),
('Газировка', 6, '2024-03-06', 90.00),
('Рис', 7, '2024-03-13', 80.00),
('Горошек консервированный', 9, '2024-03-17', 70.00),
('Фруктовое пюре', 10, '2024-03-19', 110.00),
('Морковное пюре', 10, '2024-03-21', 100.00);

insert into product ("name", type_id, expired_date, price) values
('Молоко ультрапастеризованное', 1, '2024-02-25', 70.00),
('Йогурт натуральный', 1, '2024-02-20', 60.00),
('Сыр гауда', 11, '2024-02-15', 180.00);