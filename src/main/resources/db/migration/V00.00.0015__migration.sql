-- Role table.

CREATE TABLE role
(
    id BIGINT NOT NULL PRIMARY KEY,
    role_code VARCHAR(255),
    version INTEGER DEFAULT 0
);

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

-- Lifecycle table

CREATE TABLE post_status
(
    id BIGINT NOT NULL PRIMARY KEY,
    status INTEGER,
    status_name TEXT,
    version INTEGER DEFAULT 0
);

-- Category table

CREATE TABLE category
(
    id BIGINT NOT NULL PRIMARY KEY,
    name_category VARCHAR(255),
    UNIQUE (name_category)
);

-- City table

CREATE TABLE city
(
    id BIGINT NOT NULL PRIMARY KEY,
    city_name TEXT,
    short_name TEXT,
    version INTEGER DEFAULT 0,
    UNIQUE (city_name, short_name)
);

-- Like table

CREATE TABLE likes
(
    id BIGINT NOT NULL PRIMARY KEY,
    count_likes INTEGER,
    version INTEGER DEFAULT 0
);

-- Post table

CREATE TABLE post
(
    id BIGINT NOT NULL PRIMARY KEY,
    header TEXT,
    description VARCHAR(10485760),
    address TEXT,
    likes INTEGER,
    date_create TIMESTAMP,
    date_from TIMESTAMP,
    date_to TIMESTAMP,
    photo bytea,
    version INTEGER DEFAULT 0,
    id_user BIGINT NOT NULL REFERENCES users (id),
    id_likes INTEGER NOT NULL REFERENCES likes (id),
    id_category BIGINT NOT NULL REFERENCES category (id),
    id_post_status BIGINT NOT NULL REFERENCES post_status (id),
    id_city BIGINT NOT NULL REFERENCES city (id)
);

-- Group table

CREATE TABLE groups
(
    id BIGINT NOT NULL PRIMARY KEY,
    version INTEGER DEFAULT 0,
    id_user BIGINT NOT NULL REFERENCES users (id),
    id_post BIGINT NOT NULL REFERENCES post (id)
);

-- User table

CREATE TABLE comments
(
    id BIGINT NOT NULL PRIMARY KEY,
    comment_text TEXT,
    version INTEGER DEFAULT 0,
    id_user BIGINT NOT NULL REFERENCES users (id),
    id_post BIGINT NOT NULL REFERENCES post (id)
);
