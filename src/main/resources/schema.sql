CREATE TABLE IF NOT EXISTS guests (id serial PRIMARY KEY,
								  name varchar(100) NOT NULL,
								  email varchar(100) NOT NULL UNIQUE,
								  phone varchar(12) NOT NULL UNIQUE);
								  
CREATE USER manager WITH PASSWORD '1234';
GRANT SELECT, INSERT ON guests TO manager;
GRANT USAGE, SELECT ON SEQUENCE guests_id_seq TO manager;

CREATE USER guard WITH PASSWORD '1234';

CREATE VIEW guests_view AS
(SELECT name FROM guests ORDER BY name);

GRANT SELECT ON guests_view TO guard, manager;

select * from guests_view;