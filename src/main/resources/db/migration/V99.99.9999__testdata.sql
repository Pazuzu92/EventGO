INSERT INTO role (id, role_code) VALUES (1, 'USER');
INSERT INTO role (id, role_code) VALUES (2, 'MODERATOR');
INSERT INTO role (id, role_code) VALUES (3, 'ADMIN');

INSERT INTO users (id, name, email, login, password, id_role) VALUES (
                                                                      100,
                                                                      'user1',
                                                                      'user@user.ru',
                                                                      'user1',
                                                                      '$2y$12$9wwymsFaj5jeiwosF.qNi.QuoqIuS7i8fGAp8A4AKH1tXzer4BEBa',
                                                                      1
                                                                     );
INSERT INTO users (id, name, email, login, password, id_role) VALUES (
                                                                      200,
                                                                      'user2',
                                                                      'user2@user.ru',
                                                                      'user2',
                                                                      '$2y$12$9wwymsFaj5jeiwosF.qNi.QuoqIuS7i8fGAp8A4AKH1tXzer4BEBa',
                                                                      2
                                                                     );
INSERT INTO users (id, name, email, login, password, id_role) VALUES (
                                                                      300,
                                                                      'admin',
                                                                      'admin@user.ru',
                                                                      'admin',
                                                                      '$2y$12$9wwymsFaj5jeiwosF.qNi.QuoqIuS7i8fGAp8A4AKH1tXzer4BEBa',
                                                                      3
                                                                     );

INSERT INTO post_status (id, status, status_name) VALUES (1, 3, 'Активный');
INSERT INTO post_status (id, status, status_name) VALUES (2, 4, 'В архиве');
INSERT INTO post_status (id, status, status_name) VALUES (3, 5, 'Удален');
INSERT INTO post_status (id, status, status_name) VALUES (4, 1, 'На модерации');
INSERT INTO post_status (id, status, status_name) VALUES (5, 2, 'Отклонен');

INSERT INTO category (id, name_category) VALUES (100, 'Спорт');
INSERT INTO category (id, name_category) VALUES (200, 'Театр');
INSERT INTO category (id, name_category) VALUES (300, 'Музыка');
INSERT INTO category (id, name_category) VALUES (400, 'Кино');
INSERT INTO category (id, name_category) VALUES (500, 'Детям');
INSERT INTO category (id, name_category) VALUES (600, 'Кафе');
INSERT INTO category (id, name_category) VALUES (700, 'Обучение');
INSERT INTO category (id, name_category) VALUES (800, 'Выставки');

INSERT INTO city (id, city_name, short_name) VALUES (1, 'Москва', 'msk');
INSERT INTO city (id, city_name, short_name) VALUES (2, 'Санкт-Петербург', 'spb');
INSERT INTO city (id, city_name, short_name) VALUES (3, 'Казань', 'kzn');

