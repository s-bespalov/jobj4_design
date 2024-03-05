create
or replace function f_remove_zero_count_products()
returns integer
language 'plpgsql'
as
$$
	declare
        result integer;
	begin
	    select into result count(id) 
		from products where count = 0;
		delete from products
		where count = 0;
		return result;
	end;
$$;