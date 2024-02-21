create table roles_rules(
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);