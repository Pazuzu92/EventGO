-- User table

CREATE TABLE comments
(
    id BIGINT NOT NULL PRIMARY KEY,
    comment_text TEXT,
    id_user BIGINT NOT NULL REFERENCES users
--     ,id_post BIGINT NOT NULL REFERENCES post
);
