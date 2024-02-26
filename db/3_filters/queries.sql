select
    product."id",
    product."name" as "Наименование",
    type."name" as "Тип",
    product.expired_date as "Срок годности",
    product.price as "Цена"
from product
    join "type" on product.type_id = "type"."id"
where "type"."name" like 'Сыр'

select
    product."id",
    product."name" as "Наименование",
    type."name" as "Тип",
    product.expired_date as "Срок годности",
    product.price as "Цена"
from product
    join "type" on product.type_id = "type"."id"
where lower(product."name") like '%мороженое%'

select
    product."id",
    product."name" as "Наименование",
    type."name" as "Тип",
    product.expired_date as "Срок годности",
    product.price as "Цена"
from product
    join "type" on product.type_id = "type"."id"
where product.expired_date < current_date

select
    product."id",
    product."name" as "Наименование",
    type."name" as "Тип",
    product.expired_date as "Срок годности",
    product.price as "Цена"
from product
    join "type" on product.type_id = "type"."id"
where product.price = (
    select max(product.price)
    from product
)

select
    "type".name,
    count(product."id")
from product
    join "type" on product.type_id = "type"."id"
group by "type"."id", "type"."name"

select
    product."id",
    product."name" as "Наименование",
    type."name" as "Тип",
    product.expired_date as "Срок годности",
    product.price as "Цена"
from product
    join "type" on product.type_id = "type"."id"
where type."name" like 'Сыр'
    or type."name" like 'Молочные продукты'

select
    "type".name,
    count(product."id")
from product
    join "type" on product.type_id = "type"."id"
group by "type"."id", "type"."name"
having count(product."id") > 10

select
    product."id",
    product."name" as "Наименование",
    type."name" as "Тип",
    product.expired_date as "Срок годности",
    product.price as "Цена"
from product
    join "type" on product.type_id = "type"."id"