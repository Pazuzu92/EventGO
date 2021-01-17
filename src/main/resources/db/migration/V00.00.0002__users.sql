-- User table

CREATE TABLE users
(
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    login VARCHAR(255),
    password VARCHAR(255),
    version INTEGER DEFAULT 0,
    id_role BIGINT NOT NULL REFERENCES role (id)
);
