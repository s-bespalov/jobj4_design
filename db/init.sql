create table roles(
    id serial primary key,
    role_name varchar(255)
);

create table states(
    id serial primary key,
    state_name varchar(255)
);

create table categories(
    id serial primary key,
    category_name varchar(255)
);

create table users(
    id serial primary key,
    user_name varchar(255),
    user_role int references roles(id)
);

create table rules(
    id serial primary key,
    rule_name varchar(255)
);

create table items(
    id serial primary key,
    item_name varchar(255),
    item_owner int references users(id),
    item_category int references categories(id),
    item_state int references states(id)
);

create table comments(
    id serial primary key,
    comment text,
    item_id int references items(id)
);

create table attachs(
    id serial primary key,
    attach_name varchar(255),
    attach_path varchar(255),
    item_id int references items(id)
);