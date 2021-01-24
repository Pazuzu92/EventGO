-- Dislike table

CREATE TABLE dislikes
(
    id BIGINT NOT NULL PRIMARY KEY,
    count_dislikes INTEGER,
    version INTEGER DEFAULT 0
);