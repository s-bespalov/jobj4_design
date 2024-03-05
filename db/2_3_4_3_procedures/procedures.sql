create
or replace procedure set_random_products_count_to_zero(p_count integer)
language 'plpgsql'
as $$
	BEGIN
		update products
		set count = 0
		where id in (select id from products order by random() limit p_count);
	END;
$$;