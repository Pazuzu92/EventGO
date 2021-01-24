-- Like table

CREATE TABLE likes
(
    id BIGINT NOT NULL PRIMARY KEY,
    count_likes INTEGER,
    version INTEGER DEFAULT 0
);