SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS animals (
  id INT PRIMARY KEY auto_increment,
  name VARCHAR,
  gender VARCHAR,
  type VARCHAR,
  breed VARCHAR,
  customerId INTEGER,
  dateAdmitted VARCHAR
);

CREATE TABLE IF NOT EXISTS customer (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    phone INT,
    breedPreference VARCHAR,
    typePreference VARCHAR
);