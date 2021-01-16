-- Photo table

CREATE TABLE photo
(
    id BIGINT NOT NULL PRIMARY KEY,
    image BYTEA,
    version INTEGER DEFAULT 0,
    id_post BIGINT NOT NULL REFERENCES post (id)
);
