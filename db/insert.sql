insert into roles (role_name) values
('Admin'),
('Support'),
('User');

insert into states (state_name) values
('Open'),
('In Progress'),
('Resolved'),
('Closed');

insert into categories (category_name) values
('Technical Support'),
('Bug Report'),
('Feature Request');

insert into users (user_name, user_role) values
('John Doe', 1),
('Jane Smith', 2),
('Alice Johnson', 3);

insert into rules (rule_name) values
('Create Ticket'),
('View Ticket'),
('Edit Ticket'),
('Delete Ticket'),
('Comment on Ticket');

insert into items (item_name, item_owner, item_category, item_state) values
('Internet Connection Issue', 1, 1, 1),
('Application Error', 2, 2, 1),
('New Feature Request', 3, 3, 1);

insert into comments (comment, item_id) values
('Please check your router settings.', 1),
('I will investigate the issue further.', 1),
('Could you provide more details about the error?', 2);

insert into attachs (attach_name, attach_path, item_id) values
('screenshot.png', '/path/to/screenshot.png', 2),
('log_file.txt', '/path/to/log_file.txt', 2);