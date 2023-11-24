-- Ejemplo para la tabla 'authors'
INSERT INTO authors (first_name, last_name) VALUES ('John', 'Doe');

-- Ejemplo para la tabla 'publishers'
INSERT INTO publishers(address_line, city, name, state, zip) VALUES ('123 Main St', 'Cityville', 'Publisher1', 'State1', '12345');

-- Ejemplo para la tabla 'books'
INSERT INTO books (isbn, title, publisher_id) VALUES ('123456789', 'Sample Book 1', 1);
INSERT INTO books (isbn, title, publisher_id) VALUES ('987654321', 'Sample Book 2', 1);