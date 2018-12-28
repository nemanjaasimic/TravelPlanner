INSERT IGNORE INTO role(role_id, name) VALUES (1, "ROLE_USER");
INSERT IGNORE INTO role(role_id, name) VALUES (2, "ROLE_USER_MANAGER");
INSERT IGNORE INTO role(role_id, name) VALUES (3, "ROLE_ADMIN");

-- password is sifra123 for every user
INSERT IGNORE INTO user (user_id, email, email_verified, first_name, gender, last_name, password, phone_number, username, role_id) VALUES (1, "user@user.com", 1, "User", 0, "User", '$2a$10$W1vlRQ2srDY29fhq3HZRkeAPM8HCTdGMGKXbpdvqjHvsbkqx1i4F6',  1234567, "useruser", 1);
INSERT IGNORE INTO user (user_id, email, email_verified, first_name, gender, last_name, password, phone_number, username, role_id) VALUES (2, "admin@admin.com", 1, "Admin", 0, "Admin", '$2a$10$W1vlRQ2srDY29fhq3HZRkeAPM8HCTdGMGKXbpdvqjHvsbkqx1i4F6',  1234567, "adminuser", 3);
INSERT IGNORE INTO user (user_id, email, email_verified, first_name, gender, last_name, password, phone_number, username, role_id) VALUES (3, "manager@manager.com", 1, "Manager", 0, "Manager", '$2a$10$W1vlRQ2srDY29fhq3HZRkeAPM8HCTdGMGKXbpdvqjHvsbkqx1i4F6',  1234567, "manageruser", 2);
INSERT IGNORE INTO user (user_id, email, email_verified, first_name, gender, last_name, password, phone_number, username, role_id) VALUES (4, "test@test.com", 1, "Test", 0, "Test", '$2a$10$W1vlRQ2srDY29fhq3HZRkeAPM8HCTdGMGKXbpdvqjHvsbkqx1i4F6',  1234567, "testuser", 1);
INSERT IGNORE INTO user (user_id, email, email_verified, first_name, gender, last_name, password, phone_number, username, role_id) VALUES (5, "something@something.com", 1, "Something", 0, "something", '$2a$10$W1vlRQ2srDY29fhq3HZRkeAPM8HCTdGMGKXbpdvqjHvsbkqx1i4F6',  1234567, "something", 1);


INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (1, "trip comment 1", '2018-12-30 18:58:14', '2018-12-29 18:58:14', 1, "Beograd");
INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (2, "trip comment 2", '2019-01-10 18:58:14', '2018-12-30 18:58:14', 1, "Novi Sad");
INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (3, "trip comment 3", '2019-01-15 18:58:14', '2019-01-02 18:58:14', 1, "Nis");
INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (4, "trip comment 4", '2019-02-25 18:58:14', '2019-01-03 18:58:14', 1, "Novi Beograd");

INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (5, "trip comment 5", '2018-12-30 18:58:14', '2018-12-29 18:58:14', 4, "Bijeljina");
INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (6, "trip comment 6", '2019-01-10 18:58:14', '2018-12-30 18:58:14', 4, "Banja Luka");
INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (7, "trip comment 7", '2019-01-15 18:58:14', '2019-01-02 18:58:14', 4, "Trebinje");
INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (8, "trip comment 8", '2019-02-27 18:58:14', '2019-01-03 18:58:14', 4, "Doboj");

INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (9, "trip comment 9", '2018-12-30 18:58:14', '2018-12-29 18:58:14', 5, "Podgorica");
INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (10, "trip comment 10", '2019-01-10 18:58:14', '2018-12-30 18:58:14', 5, "Budva");
INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (11, "trip comment 11", '2019-01-15 18:58:14', '2019-01-02 18:58:14', 5, "Herceg Novi");
INSERT IGNORE INTO trip (trip_id, comment, end_date, start_date, user_id, destination) VALUES (12, "trip comment 12", '2019-03-20 18:58:14', '2019-01-03 18:58:14', 5, "Berane");


