-- Group table

CREATE TABLE group
(
    id BIGINT NOT NULL PRIMARY KEY,
    version INTEGER DEFAULT 0,
    id_user BIGINT NOT NULL REFERENCES users (id),
    id_post BIGINT NOT NULL REFERENCES post (id)
);
