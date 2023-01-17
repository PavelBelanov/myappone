INSERT INTO USERS(FIRST_NAME, LAST_NAME, EMAIL)
VALUES ('Paul','Divider','divider@mail.ru'),
       ('Nastya','Belanova','nbelanova@maol.ru'),
       ('Alla','Belanova','mikhaleva@mail.ru'),
       ('Pavel','Bel','belanov@mail.ru');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('USER', 2),
       ('USER', 3),
       ('ADMIN',4);

INSERT INTO GENDER (GENDER,USER_ID)
VALUES ('MALE',1),
       ('FEMALE',2),
       ('FEMALE',3),
       ('MALE',4);