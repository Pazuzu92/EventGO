-- User table

CREATE TABLE users
(
    id BIGINT NOT NULL PRIMARY KEY,
    email VARCHAR(255),
    login VARCHAR(255),
    name VARCHAR(255),
    password VARCHAR(255),
    version INTEGER,
    id_role BIGINT NOT NULL REFERENCES role
);
