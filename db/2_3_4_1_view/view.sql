CREATE VIEW readable_flight_schedule AS
SELECT 
	planes.registration_number AS "Номер борта",
    to_char (flight_schedule.departure_time, 'HH24:MI DD Mon YYYY') AS "Время вылета",
    departure_airport.IATA_Code AS "Аэропорт вылета",
    to_char (flight_schedule.arrival_time, 'HH24:MI DD Mon YYYY') AS "Время прибытия",
    arrival_airport.IATA_Code AS "Аэропорт прибытия"
FROM
	flight_schedule
	LEFT JOIN airports AS departure_airport ON departure_airport.id = flight_schedule.departure_airport_id 
	LEFT JOIN airports AS arrival_airport ON arrival_airport.id = flight_schedule.arrival_airport_id 
	LEFT JOIN planes ON planes.id = flight_schedule.plane_id