-- Lifecycle table

CREATE TABLE post_status
(
    id BIGINT NOT NULL PRIMARY KEY,
    post_status INTEGER,
    version INTEGER DEFAULT 0
);