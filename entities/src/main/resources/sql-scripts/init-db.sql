-- Create the passenger table
-- 1. Create Tables
CREATE TABLE AppUser (
                      userId SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      phoneNumber VARCHAR(20) NOT NULL
);

CREATE TABLE Flight (
                        flightId VARCHAR(10) PRIMARY KEY,
                        airline VARCHAR(255) NOT NULL,
                        origin VARCHAR(10) NOT NULL,
                        destination VARCHAR(10) NOT NULL,
                        departureTime TIMESTAMP NOT NULL,
                        arrivalTime TIMESTAMP NOT NULL,
                        price FLOAT NOT NULL,
                        duration VARCHAR(50) NOT NULL,
                        maxSeats INTEGER NOT NULL,
                        travelClass VARCHAR(50) NOT NULL
);

CREATE TABLE Passenger (
                           passengerId SERIAL PRIMARY KEY,
                           firstName VARCHAR(255) NOT NULL,
                           lastName VARCHAR(255) NOT NULL,
                           passportNumber VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Booking (
                         bookingId SERIAL PRIMARY KEY,
                         flightId VARCHAR(10) REFERENCES Flight(flightId),
                         totalPrice FLOAT NOT NULL
);

CREATE TABLE BookingPassengers (
                                   bookingId INTEGER REFERENCES Booking(bookingId),
                                   passengerId INTEGER REFERENCES Passenger(passengerId),
                                   PRIMARY KEY (bookingId, passengerId)
);

CREATE TABLE Payment (
                         paymentId SERIAL PRIMARY KEY,
                         date TIMESTAMP NOT NULL,
                         userId INTEGER REFERENCES AppUser(userId),
                         paymentStatus VARCHAR(50) NOT NULL,
                         amount FLOAT NOT NULL,
                         currency VARCHAR(10) NOT NULL,
                         flightId VARCHAR(10) REFERENCES Flight(flightId),
                         transactionId VARCHAR(50) NOT NULL
);

CREATE TABLE LoyaltyMember (
                               memberId SERIAL PRIMARY KEY,
                               userId INTEGER REFERENCES AppUser(userId),
                               points INTEGER NOT NULL
);

CREATE TABLE Notification (
                              notificationId SERIAL PRIMARY KEY,
                              userId INTEGER REFERENCES AppUser(userId),
                              title VARCHAR(255) NOT NULL,
                              content TEXT NOT NULL,
                              sendingTime TIMESTAMP NOT NULL,
                              notificationType VARCHAR(50) NOT NULL
);

CREATE TABLE SeatAvailability (
                                  flightId VARCHAR(10) REFERENCES Flight(flightId),
                                  availableSeats INTEGER NOT NULL,
                                  totalSeats INTEGER NOT NULL,
                                  PRIMARY KEY (flightId)
);

CREATE TABLE WeatherDelayPrediction (
                                        flightId VARCHAR(10) REFERENCES Flight(flightId),
                                        weatherForecast VARCHAR(255) NOT NULL,
                                        delayProbability FLOAT NOT NULL,
                                        windSpeed FLOAT NOT NULL,
                                        visibility FLOAT NOT NULL,
                                        humidity FLOAT NOT NULL,
                                        temperature FLOAT NOT NULL,
                                        finalProbabilityOfDelay FLOAT NOT NULL,
                                        PRIMARY KEY (flightId)
);

-- 2. Insert Sample Data
-- User Table
INSERT INTO AppUser (name, email, phoneNumber) VALUES
                                                ('John Doe', 'john.doe@example.com', '+1234567890'),
                                                ('Jane Smith', 'jane.smith@example.com', '+0987654321'),
                                                ('Alice Johnson', 'alice.j@example.com', '+1122334455'),
                                                ('Bob Brown', 'bob.brown@example.com', '+2233445566'),
                                                ('Eve White', 'eve.white@example.com', '+3344556677');

-- Flight Table
INSERT INTO Flight (flightId, airline, origin, destination, departureTime, arrivalTime, price, duration, maxSeats, travelClass) VALUES
                                                                                                                                    ('FL123', 'Delta Airlines', 'LAX', 'JFK', '2024-11-20 08:00:00', '2024-11-20 14:30:00', 250.50, 'PT6H30M', 200, 'economy'),
                                                                                                                                    ('FL456', 'United Airlines', 'SFO', 'ORD', '2024-11-21 10:00:00', '2024-11-21 16:00:00', 180.75, 'PT4H', 180, 'economy'),
                                                                                                                                    ('FL789', 'American Airlines', 'ATL', 'MIA', '2024-11-22 12:00:00', '2024-11-22 14:30:00', 150.00, 'PT2H30M', 150, 'economy'),
                                                                                                                                    ('FL101', 'Southwest Airlines', 'LAX', 'LAS', '2024-11-23 08:00:00', '2024-11-23 09:00:00', 90.00, 'PT1H', 150, 'economy'),
                                                                                                                                    ('FL102', 'JetBlue', 'BOS', 'DCA', '2024-11-24 08:00:00', '2024-11-24 09:30:00', 120.50, 'PT1H30M', 140, 'economy');

-- Passenger Table
INSERT INTO Passenger (firstName, lastName, passportNumber) VALUES
                                                                ('John', 'Doe', 'P123456789'),
                                                                ('Jane', 'Smith', 'P987654321'),
                                                                ('Alice', 'Johnson', 'P112233445'),
                                                                ('Bob', 'Brown', 'P223344556'),
                                                                ('Eve', 'White', 'P334455667');

-- Booking Table
INSERT INTO Booking (flightId, totalPrice) VALUES
                                               ('FL123', 501.00),
                                               ('FL456', 361.50),
                                               ('FL789', 300.00),
                                               ('FL101', 180.00),
                                               ('FL102', 241.00);

-- BookingPassengers Table
INSERT INTO BookingPassengers (bookingId, passengerId) VALUES
                                                           (1, 1),
                                                           (1, 2),
                                                           (2, 3),
                                                           (3, 4),
                                                           (4, 5);

-- Payment Table
INSERT INTO Payment (date, userId, paymentStatus, amount, currency, flightId, transactionId) VALUES
                                                                                                 ('2024-11-20 08:00:00', 1, 'ACCEPTED', 250.50, 'USD', 'FL123', 'TX12345'),
                                                                                                 ('2024-11-21 10:00:00', 2, 'PENDING', 180.75, 'USD', 'FL456', 'TX67890'),
                                                                                                 ('2024-11-22 12:00:00', 3, 'DECLINED', 150.00, 'USD', 'FL789', 'TX11223'),
                                                                                                 ('2024-11-23 08:00:00', 4, 'ACCEPTED', 90.00, 'USD', 'FL101', 'TX44556'),
                                                                                                 ('2024-11-24 08:00:00', 5, 'ACCEPTED', 120.50, 'USD', 'FL102', 'TX77889');

-- LoyaltyMember Table
INSERT INTO LoyaltyMember (userId, points) VALUES
                                               (1, 500),
                                               (2, 400),
                                               (3, 300),
                                               (4, 200),
                                               (5, 100);

-- Notification Table
INSERT INTO Notification (userId, title, content, sendingTime, notificationType) VALUES
                                                                                     (1, 'Flight Update', 'Your flight FL123 is on time.', '2024-11-19 08:00:00', 'info'),
                                                                                     (2, 'Weather Alert', 'Your flight FL456 may be delayed due to weather.', '2024-11-20 10:00:00', 'warning'),
                                                                                     (3, 'Seat Update', 'More seats are available for FL789.', '2024-11-21 12:00:00', 'info'),
                                                                                     (4, 'Price Alert', 'Price dropped for FL101.', '2024-11-22 08:00:00', 'promo'),
                                                                                     (5, 'Booking Reminder', 'Don’t forget to check in for FL102.', '2024-11-23 08:00:00', 'reminder');

-- SeatAvailability Table
INSERT INTO SeatAvailability (flightId, availableSeats, totalSeats) VALUES
                                                                        ('FL123', 150, 200),
                                                                        ('FL456', 130, 180),
                                                                        ('FL789', 100, 150),
                                                                        ('FL101', 140, 150),
                                                                        ('FL102', 120, 140);

-- WeatherDelayPrediction Table
INSERT INTO WeatherDelayPrediction (flightId, weatherForecast, delayProbability, windSpeed, visibility, humidity, temperature, finalProbabilityOfDelay) VALUES
                                                                                                                                                            ('FL123', 'Sunny', 10, 15.0, 10.0, 60.0, 22.5, 15.0),
                                                                                                                                                            ('FL456', 'Rainy', 50, 20.0, 5.0, 80.0, 18.0, 40.0),
                                                                                                                                                            ('FL789', 'Cloudy', 20, 10.0, 8.0, 70.0, 20.0, 25.0),
                                                                                                                                                            ('FL101', 'Sunny', 5, 12.0, 10.0, 50.0, 25.0, 10.0),
                                                                                                                                                            ('FL102', 'Clear', 15, 8.0, 12.0, 65.0, 19.0, 20.0);