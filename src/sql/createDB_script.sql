-- CREATE TABLES --
CREATE TABLE canciones (
	ID INTEGER(5) NOT NULL AUTO_INCREMENT,
	titulo VARCHAR(100) NOT NULL, 
	url VARCHAR(100) NOT NULL, 
	pmax INTEGER(5) NOT NULL DEFAULT 0,
	PRIMARY KEY(ID) 
);
