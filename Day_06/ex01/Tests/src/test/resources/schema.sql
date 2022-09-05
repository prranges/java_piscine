CREATE TABLE if NOT EXISTS product (
    identifier  INT PRIMARY KEY IDENTITY,
    name        VARCHAR(30) UNIQUE NOT NULL,
    price       INT NOT NULL
);