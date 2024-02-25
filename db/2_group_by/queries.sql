select to_char (avg(price), 'FM9999999999D99') as "Средняя цена"
from devices;

select
    people."name" as "Имя",
    to_char (avg(devices.price), 'FM9999999999D00') as "Средняя цена устройств"
from devices_people
    join devices on devices_people.device_id = devices."id"
    join people on devices_people.people_id = people."id"
group by people."name", people."id"

select
    people."name" as "Имя",
    to_char (avg(devices.price), 'FM9999999999D00') as "Средняя цена устройств"
from devices_people
    join devices on devices_people.device_id = devices."id"
    join people on devices_people.people_id = people."id"
group by people."name", people."id"
having avg(devices.price) > 5000