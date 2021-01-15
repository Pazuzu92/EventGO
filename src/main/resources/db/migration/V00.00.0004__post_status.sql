-- Lifecycle table

CREATE TABLE post_status
(
    id BIGINT NOT NULL PRIMARY KEY,
    lifecycle_status VARCHAR(255)
);