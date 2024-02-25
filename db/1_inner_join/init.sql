create table Aircrafts (
    id serial primary key,
    Manufacturer varchar(100),
    Model varchar(100),
    RegistrationNumber varchar(20),
    MaxSpeed int,
    Range int,
    Capacity int
);

create table Airports (
    id serial primary key,
    "Name" varchar(100),
    City varchar(100),
    Country varchar(100),
    IATA_Code varchar(3),
    ICAO_Code varchar(4)
);

create table FlightSchedule (
    id serial primary key,
    DepartureAirportID int,
    ArrivalAirportID int,
    AircraftID int,
    DepartureTime timestamp,
    ArrivalTime timestamp,
    foreign key (DepartureAirportID) references Airports(id),
    foreign key (ArrivalAirportID) references Airports(id),
    foreign key (AircraftID) references Aircrafts(id)
);

insert into Aircrafts (Manufacturer, Model, RegistrationNumber, MaxSpeed, Range, Capacity)
values
    ('Boeing', '747', 'ABC123', 988, 14200, 524),
    ('Airbus', 'A380', 'DEF456', 1020, 15200, 853),
    ('Boeing', '787', 'GHI789', 954, 15700, 330),
    ('Embraer', 'E195-E2', 'JKL012', 871, 4600, 146);

insert into Airports ("Name", City, Country, IATA_Code, ICAO_Code)
values
    ('Heathrow Airport', 'London', 'United Kingdom', 'LHR', 'EGLL'),
    ('John F. Kennedy International Airport', 'New York City', 'United States', 'JFK', 'KJFK'),
    ('Los Angeles International Airport', 'Los Angeles', 'United States', 'LAX', 'KLAX'),
    ('Tokyo Haneda Airport', 'Tokyo', 'Japan', 'HND', 'RJTT'),
    ('Sydney Kingsford Smith Airport', 'Sydney', 'Australia', 'SYD', 'YSSY');

insert into FlightSchedule (DepartureAirportID, ArrivalAirportID, AircraftID, DepartureTime, ArrivalTime)
values
    (1, 3, 1, '2024-02-25 08:00:00', '2024-02-25 12:00:00'),
    (2, 4, 2, '2024-02-25 10:00:00', '2024-02-25 15:00:00'),
    (3, 5, 3, '2024-02-25 12:00:00', '2024-02-25 18:00:00'),
    (4, 1, 4, '2024-02-25 14:00:00', '2024-02-25 16:00:00'),
    (5, 2, 1, '2024-02-25 16:00:00', '2024-02-25 18:00:00'),
    (1, 4, 2, '2024-02-25 18:00:00', '2024-02-25 21:00:00');