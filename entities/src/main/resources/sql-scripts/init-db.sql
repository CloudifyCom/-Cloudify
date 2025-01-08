-- Create the passenger table
CREATE TABLE IF NOT EXISTS passenger (
                                         first_name VARCHAR(50),
                                         last_name VARCHAR(50),
                                         passport_number VARCHAR(20) UNIQUE PRIMARY KEY
);

-- passenger
INSERT INTO passenger (first_name, last_name, passport_number) VALUES ('John', 'Doe', 'P987654321');
INSERT INTO passenger (first_name, last_name, passport_number) VALUES ('Jane', 'Smith', 'P123456789');
INSERT INTO passenger (first_name, last_name, passport_number) VALUES ('Alice', 'Johnson', 'P112233445');
INSERT INTO passenger (first_name, last_name, passport_number) VALUES ('Bob', 'Williams', 'P998877665');
INSERT INTO passenger (first_name, last_name, passport_number) VALUES ('Charlie', 'Brown', 'P667788990');
