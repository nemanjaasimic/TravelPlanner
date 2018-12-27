INSERT IGNORE INTO role VALUES (1, "ROLE_USER");
INSERT IGNORE INTO role VALUES (2, "ROLE_USER_MANAGER");
INSERT IGNORE INTO role VALUES (3, "ROLE_ADMIN");

-- password is sifra123 for every user
INSERT IGNORE INTO user VALUES (4, "user@user.com", 1, "User", 0, "User", '$2a$10$W1vlRQ2srDY29fhq3HZRkeAPM8HCTdGMGKXbpdvqjHvsbkqx1i4F6',  1234567, "useruser", 1);
INSERT IGNORE INTO user VALUES (5, "admin@admin.com", 1, "Admin", 0, "Admin", '$2a$10$W1vlRQ2srDY29fhq3HZRkeAPM8HCTdGMGKXbpdvqjHvsbkqx1i4F6',  1234567, "adminuser", 3);
INSERT IGNORE INTO user VALUES (6, "manager@manager.com", 1, "Manager", 0, "Manager", '$2a$10$W1vlRQ2srDY29fhq3HZRkeAPM8HCTdGMGKXbpdvqjHvsbkqx1i4F6',  1234567, "manageruser", 2);
INSERT IGNORE INTO user VALUES (7, "test@test.com", 1, "Test", 0, "Test", '$2a$10$W1vlRQ2srDY29fhq3HZRkeAPM8HCTdGMGKXbpdvqjHvsbkqx1i4F6',  1234567, "testuser", 1);


INSERT IGNORE INTO trip VALUES (6, "trip comment 6", '2018-12-30 18:58:14', '2018-12-29 18:58:14', 3, "Beograd");
INSERT IGNORE INTO trip VALUES (7, "trip comment 7", '2019-01-10 18:58:14', '2018-12-30 18:58:14', 3, "Novi Sad");
INSERT IGNORE INTO trip VALUES (8, "trip comment 8", '2019-01-15 18:58:14', '2019-01-02 18:58:14', 3, "Nis");
INSERT IGNORE INTO trip VALUES (9, "trip comment 9", '2019-02-25 18:58:14', '2019-01-03 18:58:14', 3, "Novi Beograd");


INSERT IGNORE INTO trip VALUES (10, "trip comment 10", '2018-12-30 18:58:14', '2018-12-29 18:58:14', 7, "Bijeljina");
INSERT IGNORE INTO trip VALUES (11, "trip comment 11", '2019-01-10 18:58:14', '2018-12-30 18:58:14', 7, "Banja Luka");
INSERT IGNORE INTO trip VALUES (12, "trip comment 12", '2019-01-15 18:58:14', '2019-01-02 18:58:14', 7, "Trebinje");
INSERT IGNORE INTO trip VALUES (13, "trip comment 13", '2019-02-27 18:58:14', '2019-01-03 18:58:14', 7, "Doboj");

INSERT IGNORE INTO trip VALUES (14, "trip comment 14", '2018-12-30 18:58:14', '2018-12-29 18:58:14', 4, "Podgorica");
INSERT IGNORE INTO trip VALUES (15, "trip comment 15", '2019-01-10 18:58:14', '2018-12-30 18:58:14', 4, "Budva");
INSERT IGNORE INTO trip VALUES (16, "trip comment 16", '2019-01-15 18:58:14', '2019-01-02 18:58:14', 4, "Herceg Novi");
INSERT IGNORE INTO trip VALUES (17, "trip comment 17", '2019-03-20 18:58:14', '2019-01-03 18:58:14', 4, "Berane");


