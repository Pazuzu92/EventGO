-- User table

CREATE TABLE comments
(
    id BIGINT NOT NULL PRIMARY KEY,
    comment_text TEXT,
    version INTEGER DEFAULT 0,
    id_user BIGINT NOT NULL REFERENCES users (id),
    id_post BIGINT NOT NULL REFERENCES post (id)
);
