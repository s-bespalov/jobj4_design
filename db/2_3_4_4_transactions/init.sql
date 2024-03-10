CREATE TABLE employee (
    emp_id SERIAL PRIMARY KEY,
    emp_name VARCHAR(100),
    emp_age INT,
    emp_department VARCHAR(100),
    emp_salary FLOAT,
    emp_start_date DATE
);

INSERT INTO employee (emp_name, emp_age, emp_department, emp_salary, emp_start_date) 
VALUES 
    ('John Doe', 30, 'Marketing', 50000.00, '2020-01-15'),
    ('Jane Smith', 35, 'Finance', 60000.00, '2019-03-20'),
    ('Michael Johnson', 28, 'HR', 45000.00, '2021-07-10'),
    ('Emily Brown', 32, 'Engineering', 70000.00, '2018-09-05'),
    ('David Wilson', 40, 'Operations', 55000.00, '2020-11-12'),
    ('Sarah Lee', 29, 'Sales', 60000.00, '2019-05-28'),
    ('Alexandra Davis', 33, 'Marketing', 55000.00, '2022-02-08'),
    ('Matthew Taylor', 31, 'Engineering', 65000.00, '2017-12-03'),
    ('Jessica Martinez', 36, 'Finance', 62000.00, '2023-04-17'),
    ('Christopher Anderson', 27, 'HR', 48000.00, '2021-01-22');