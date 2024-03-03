BEGIN;

INSERT INTO airports (name, city, country, iata_code, icao_code) 
VALUES
    ('John F. Kennedy International Airport', 'New York', 'USA', 'JFK', 'KJFK'),
    ('Los Angeles International Airport', 'Los Angeles', 'USA', 'LAX', 'KLAX'),
    ('Heathrow Airport', 'London', 'UK', 'LHR', 'EGLL'),
    ('Charles de Gaulle Airport', 'Paris', 'France', 'CDG', 'LFPG'),
    ('Narita International Airport', 'Tokyo', 'Japan', 'NRT', 'RJAA'),
    ('Sydney Airport', 'Sydney', 'Australia', 'SYD', 'YSSY'),
    ('Dubai International Airport', 'Dubai', 'UAE', 'DXB', 'OMDB'),
    ('Beijing Capital International Airport', 'Beijing', 'China', 'PEK', 'ZBAA'),
    ('São Paulo–Guarulhos International Airport', 'São Paulo', 'Brazil', 'GRU', 'SBGR'),
    ('Hong Kong International Airport', 'Hong Kong', 'China', 'HKG', 'VHHH');

INSERT INTO planes (registration_number, model, manufacturer, max_speed, range, capacity) 
VALUES
    ('ABC123', 'Boeing 737', 'Boeing', 850, 5000, 150),
    ('DEF456', 'Airbus A320', 'Airbus', 880, 4500, 180),
    ('GHI789', 'Boeing 747', 'Boeing', 920, 8000, 400),
    ('JKL012', 'Airbus A380', 'Airbus', 900, 8000, 550),
    ('MNO345', 'Embraer E190', 'Embraer', 890, 3500, 110);

INSERT INTO flight_schedule (plane_id, departure_airport_id, departure_time, arrival_airport_id, arrival_time) 
VALUES
    (1, 1, '2024-03-04 08:00:00', 2, '2024-03-04 12:00:00'),
    (2, 3, '2024-03-04 09:30:00', 4, '2024-03-04 15:00:00'),
    (3, 5, '2024-03-04 11:00:00', 6, '2024-03-04 15:30:00'),
    (4, 7, '2024-03-04 12:30:00', 8, '2024-03-04 16:30:00'),
    (5, 9, '2024-03-04 14:00:00', 10, '2024-03-04 18:00:00'),
    (1, 2, '2024-03-05 08:30:00', 3, '2024-03-05 13:00:00'),
    (2, 4, '2024-03-05 10:00:00', 5, '2024-03-05 15:30:00'),
    (3, 6, '2024-03-05 11:30:00', 7, '2024-03-05 16:30:00'),
    (4, 8, '2024-03-05 13:00:00', 9, '2024-03-05 17:30:00'),
    (5, 10, '2024-03-05 14:30:00', 1, '2024-03-05 19:00:00'),
    (1, 3, '2024-03-06 08:30:00', 4, '2024-03-06 13:00:00'),
    (2, 5, '2024-03-06 10:00:00', 6, '2024-03-06 15:30:00'),
    (3, 7, '2024-03-06 11:30:00', 8, '2024-03-06 16:30:00'),
    (4, 9, '2024-03-06 13:00:00', 10, '2024-03-06 17:30:00'),
    (5, 1, '2024-03-06 14:30:00', 2, '2024-03-06 19:00:00'),
    (1, 4, '2024-03-07 08:30:00', 5, '2024-03-07 13:00:00'),
    (2, 6, '2024-03-07 10:00:00', 7, '2024-03-07 15:30:00'),
    (3, 8, '2024-03-07 11:30:00', 9, '2024-03-07 16:30:00'),
    (4, 10, '2024-03-07 13:00:00', 1, '2024-03-07 17:30:00'),
    (5, 2, '2024-03-07 14:30:00', 3, '2024-03-07 19:00:00');

END;
