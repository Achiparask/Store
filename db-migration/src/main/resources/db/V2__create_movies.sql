DROP TABLE IF EXISTS Movies CASCADE;

CREATE TABLE IF NOT EXISTS Movies (
	Id serial NOT NULL PRIMARY KEY,
	Name VARCHAR(250) NOT NULL,
	Code VARCHAR(250) NOT NULL
);