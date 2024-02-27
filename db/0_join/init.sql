create table departments (
    id serial primary key,
    department_name varchar(100),
    location varchar(100)
);

create table employees (
    id serial primary key,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(100),
    phone_number varchar(20),
    hire_date date,
    department_id int references departments(id)
);

create table teens (
    teen_id serial primary key,
    "name" varchar(100),
    gender varchar(50)
);

insert into departments (department_name, location) values
('Sales', 'New York'),
('Marketing', 'Los Angeles'),
('Human Resources', 'Chicago'),
('Finance', 'Houston');

insert into employees (first_name, last_name, email, phone_number, hire_date, department_id) values
('John', 'Doe', 'john@example.com', '123-456-7890', '2022-01-01', 1),
('Jane', 'Smith', 'jane@example.com', '987-654-3210', '2022-02-15', 2),
('Michael', 'Johnson', 'michael@example.com', '555-555-5555', '2022-03-10', 1),
('Emily', 'Williams', 'emily@example.com', '111-222-3333', '2022-04-20', 3),
('David', 'Brown', 'david@example.com', '444-444-4444', '2022-05-05', 2),
('Sarah', 'Jones', 'sarah@example.com', '666-777-8888', '2022-06-30', 1),
('Christopher', 'Martinez', 'christopher@example.com', '999-999-9999', '2022-07-15', 3),
('Jessica', 'Garcia', 'jessica@example.com', '000-111-2222', '2022-08-25', 2),
('Ryan', 'Hernandez', 'ryan@example.com', '333-333-3333', '2022-09-10', 1),
('Kimberly', 'Lopez', 'kimberly@example.com', '777-777-7777', '2022-10-20', 3);

insert into teens ("name", gender) values
('Emma', 'Female'),
('Liam', 'Male'),
('Olivia', 'Female'),
('Noah', 'Male'),
('Ava', 'Female'),
('William', 'Male'),
('Sophia', 'Female'),
('James', 'Male'),
('Isabella', 'Female'),
('Benjamin', 'Male');
