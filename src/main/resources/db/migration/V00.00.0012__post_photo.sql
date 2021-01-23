-- Post_photo table

CREATE TABLE post_photo
(
    id BIGINT NOT NULL PRIMARY KEY,
    id_post BIGINT NOT NULL REFERENCES post (id),
    id_photo BIGINT NOT NULL REFERENCES photo (id)
);
