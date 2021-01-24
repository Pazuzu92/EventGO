-- Category table

CREATE TABLE category
(
    id BIGINT NOT NULL PRIMARY KEY,
    name_category VARCHAR(255),
    UNIQUE (name_category)
);
