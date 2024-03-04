create
or replace function tax_after()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id in (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger b_tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax_after();