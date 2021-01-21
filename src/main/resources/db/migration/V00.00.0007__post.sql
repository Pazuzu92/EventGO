-- Post table

CREATE TABLE post
(
    id BIGINT NOT NULL PRIMARY KEY,
    header TEXT,
    description TEXT,
    likes INTEGER,
    dislikes INTEGER,
    date_create TIMESTAMP,
    date_from TIMESTAMP,
    date_to TIMESTAMP,
    version INTEGER DEFAULT 0,
    id_user BIGINT NOT NULL REFERENCES users (id),
    id_category BIGINT NOT NULL REFERENCES category (id),
    id_post_status BIGINT NOT NULL REFERENCES post_status (id),
    id_place BIGINT NOT NULL REFERENCES place (id)
);