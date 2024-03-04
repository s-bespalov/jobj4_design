create
or replace function track_history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
		select 
			products.name, 
			products.price, 
			current_timestamp
		from products
		where id = new.id;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger z_track_history_trigger
    after insert
    on products
    for each row
    execute procedure track_history();