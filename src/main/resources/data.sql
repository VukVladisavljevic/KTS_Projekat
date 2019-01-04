insert into authority (id, name) values (1, 'ADMIN')
insert into authority (id, name) values (2, 'USER')
insert into authority (id, name) values (3, 'CONTROLLER')

INSERT INTO USERS (ID, USERNAME, PASSWORD, CONFIRMED, EMAIL, LAST_NAME, ACTIVATION_ID, FIRST_NAME) VALUES (789, 'aa', '$2a$10$9AG/inYS7fWOHPhxQCtd0Og2gVYnbhwFBUsHaAOek5QAKSQtWItRC', TRUE, 'kwhshocker@gmail.com', 'aa', 'HSAHA','aa')

INSERT INTO ACCOUNT_AUTHORITY(ID, ACCOUNT_ID, AUTHORITY_ID) VALUES (54, 789, 1)