CREATE DATABASE IF NOT EXISTS TECH;

CREATE TABLE Technology (Id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), Company VARCHAR(20), Year INT);

INSERT INTO Technology (Name, Company, Year) VALUES( 'Java', 'Oracle', 1995);
INSERT INTO Technology (Name, Company, Year) VALUES( 'DotNet', 'MicroSoft', 2005);
COMMIT;

