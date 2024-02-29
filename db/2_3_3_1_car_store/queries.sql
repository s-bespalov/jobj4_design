-- список всех машин и все привязанные к ним детали
select
    cars.name as "Автомобиль",
    car_bodies.name as "Кузов",
    car_engines.name as "Двигатель",
    car_transmissions.name as "Трансмиссия"
from cars
 left join car_bodies on cars.body_id = car_bodies.id
 left join car_engines on car_engines.id = cars.engine_id
 left join car_transmissions on car_transmissions.id = cars.transmission_id;
-- кузова, которые не используются НИ в одной машине
select
 car_bodies.name as "Кузов"
from car_bodies left join cars on cars.body_id = car_bodies."id"
where cars.body_id is null;
-- двигатели, которые не используются НИ в одной машине
select
 car_engines.name as "Двигатель"
from car_engines left join cars on cars.engine_id = car_engines."id"
where cars.engine_id is null;
-- коробки передач, которые не используются НИ в одной машине
select
 car_transmissions.name as "Трансмиссия"
from car_transmissions left join cars on cars.transmission_id = car_transmissions."id"
where cars.transmission_id is null;