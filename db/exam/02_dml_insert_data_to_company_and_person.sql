INSERT INTO company (id, name) VALUES
(1, 'Tech Innovations Inc.'),
(2, 'Global Solutions Co.'),
(3, 'Data Dynamics Ltd.'),
(4, 'Smart Technologies Group'),
(5, 'NextGen Enterprises'),
(6, 'Innovative Solutions LLC'),
(7, 'Digital Frontier Corporation'),
(8, 'Optimal Solutions Ltd.'),
(9, 'Visionary Ventures Inc.'),
(10, 'Future Forward Technologies');

INSERT INTO person (id, name, company_id) VALUES
(1, 'John Smith', 1),
(2, 'Emma Johnson', 2),
(3, 'Michael Williams', 3),
(4, 'Emily Brown', 4),
(5, 'Daniel Jones', 5),
(6, 'Olivia Miller', 6),
(7, 'William Davis', 7),
(8, 'Ava Garcia', 8),
(9, 'James Rodriguez', 9),
(10, 'Sophia Martinez', 10),
(11, 'Liam Taylor', 1),
(12, 'Isabella Lopez', 2),
(13, 'Noah Lee', 3),
(14, 'Charlotte Hernandez', 4),
(15, 'Ethan Gonzalez', 5),
(16, 'Amelia Perez', 6),
(17, 'Mason Wilson', 7),
(18, 'Harper Moore', 8),
(19, 'Alexander Miller', 9),
(20, 'Evelyn Sanchez', 10),
(21, 'Benjamin Anderson', 1),
(22, 'Mia Rivera', 2),
(23, 'Elijah Cruz', 3),
(24, 'Abigail King', 4),
(25, 'Logan Scott', 5),
(26, 'Elizabeth Hill', 6),
(27, 'Ryan Green', 7),
(28, 'Grace Adams', 8),
(29, 'Jackson Baker', 9),
(30, 'Natalie Campbell', 10);

INSERT INTO person (id, name, company_id)
VALUES
(31, 'Samantha Baker', (SELECT id FROM company ORDER BY random() LIMIT 1)),
(32, 'Christopher Hall', (SELECT id FROM company ORDER BY random() LIMIT 1)),
(33, 'Jessica Cook', (SELECT id FROM company ORDER BY random() LIMIT 1)),
(34, 'Andrew Ward', (SELECT id FROM company ORDER BY random() LIMIT 1)),
(35, 'Ashley Brooks', (SELECT id FROM company ORDER BY random() LIMIT 1));