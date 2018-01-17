SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS animals (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  gender VARCHAR,
  dateAdmitted VARCHAR,
  type VARCHAR,
  breed VARCHAR
);

CREATE TABLE IF NOT EXISTS customer (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    phone INT,
    breedPreference VARCHAR,
    typePreference VARCHAR
);