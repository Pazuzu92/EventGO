-- City table

CREATE TABLE city
(
    id BIGINT NOT NULL PRIMARY KEY,
    city_name TEXT,
    version INTEGER DEFAULT 0
);