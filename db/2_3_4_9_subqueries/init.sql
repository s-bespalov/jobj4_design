CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES
    ('Иван', 'Иванов', 30, 'Россия'),
    ('Анна', 'Петрова', 25, 'США'),
    ('Мария', 'Сидорова', 40, 'Франция'),
    ('Петр', 'Смирнов', 35, 'Германия'),
    ('Елена', 'Козлова', 28, 'Италия');
	
INSERT INTO orders (amount, customer_id)
VALUES
    (100, 3),
    (150, 4),
    (200, 5),
    (120, 3),
    (180, 4),
    (90, 5),
    (220, 3),
    (130, 4),
    (170, 5),
    (110, 3);