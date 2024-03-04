create
or replace function tax_before()
    returns trigger as
$$
    BEGIN
		new.price = new.price * 1.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger a_tax_before
    before insert
    on products
    for each row
    execute procedure tax_before();