-- Role table.

CREATE TABLE role
(
    id BIGINT NOT NULL PRIMARY KEY,
    role_code INTEGER,
    version INTEGER DEFAULT 0
);
