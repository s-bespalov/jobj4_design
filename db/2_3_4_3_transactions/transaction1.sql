set session transaction isolation level serializable;

select sum(count) from products;

update products set count = 26 where name = 'product_1';