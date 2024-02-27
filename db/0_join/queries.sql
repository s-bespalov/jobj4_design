-- департаменты без работников
select
    department_name,
    "location"
from departments left join employees on employees.department_id = departments."id"
where employees."id" is null;
-- департаменты без работников right join
select
    department_name,
    "location"
from employees right join departments on employees.department_id = departments."id"
where employees."id" is null;
-- пары
select
    t1.name,
    t1.gender,
    t2.name,
    t2.gender
from teens as t1 cross join  teens as t2
where t1.gender like 'Male'
    and t2.gender like  'Female'