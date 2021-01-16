-- Place table

CREATE TABLE place
(
    id BIGINT NOT NULL PRIMARY KEY,
    street TEXT,
    house TEXT,
    number TEXT,
    version INTEGER DEFAULT 0,
    id_city BIGINT NOT NULL REFERENCES city (id)
);