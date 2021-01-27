-- Role table.

CREATE TABLE role
(
    id BIGINT NOT NULL PRIMARY KEY,
    role_code VARCHAR(255),
    version INTEGER DEFAULT 0
);
