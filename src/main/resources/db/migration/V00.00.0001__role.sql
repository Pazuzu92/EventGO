-- Role table.

CREATE TABLE role
(
    id BIGINT NOT NULL PRIMARY KEY,
    role_code INTEGER,
    role_name VARCHAR(255),
    version INTEGER
);
