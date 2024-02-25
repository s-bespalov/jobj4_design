select
    Aircrafts.RegistrationNumber as "Номер борта",
    to_char (FlightSchedule.DepartureTime, 'HH24:MI DD Mon YYYY') as "Время вылета",
    DepartureAirport.IATA_Code as "Аэропорт вылета",
    to_char (FlightSchedule.ArrivalTime, 'HH24:MI DD Mon YYYY') as "Время прибытия",
    ArrivalAirport.IATA_Code as "Аэропорт прибытия"
from FlightSchedule
    join Aircrafts on FlightSchedule.aircraftid = Aircrafts."id"
    join Airports as DepartureAirport on FlightSchedule.DepartureAirportID = DepartureAirport."id"
    join Airports as ArrivalAirport on FlightSchedule.ArrivalAirportID = ArrivalAirport."id"

select
    Aircrafts.RegistrationNumber as "Номер борта",
    DepartureAirport.IATA_Code as "Аэропорт вылета",
    ArrivalAirport.IATA_Code as "Аэропорт прибытия"
from FlightSchedule
    join Aircrafts on FlightSchedule.aircraftid = Aircrafts."id"
    join Airports as DepartureAirport on FlightSchedule.DepartureAirportID = DepartureAirport."id"
    join Airports as ArrivalAirport on FlightSchedule.ArrivalAirportID = ArrivalAirport."id"
where Aircrafts.RegistrationNumber like 'ABC123'

select
    Aircrafts.RegistrationNumber as "Номер борта",
    to_char (FlightSchedule.DepartureTime, 'HH24:MI DD Mon YYYY') as "Время вылета",
    DepartureAirport.IATA_Code as "Аэропорт вылета",
    to_char (FlightSchedule.ArrivalTime, 'HH24:MI DD Mon YYYY') as "Время прибытия",
    ArrivalAirport.IATA_Code as "Аэропорт прибытия"
from FlightSchedule
    join Aircrafts on FlightSchedule.aircraftid = Aircrafts."id"
    join Airports as DepartureAirport on FlightSchedule.DepartureAirportID = DepartureAirport."id"
    join Airports as ArrivalAirport on FlightSchedule.ArrivalAirportID = ArrivalAirport."id"
where FlightSchedule.DepartureTime > '2024-02-25 11:00:00'

select
    ArrivalAirport."Name" as "Название Аэропорта",
    ArrivalAirport.IATA_Code as "IATA код",
    to_char (FlightSchedule.ArrivalTime, 'HH24:MI DD Mon YYYY') as "Время прибытия",
    Aircrafts.RegistrationNumber as "Номер борта"
from FlightSchedule
    join Aircrafts on FlightSchedule.aircraftid = Aircrafts."id"
    join Airports as ArrivalAirport on FlightSchedule.ArrivalAirportID = ArrivalAirport."id"