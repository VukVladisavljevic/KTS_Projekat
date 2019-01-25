-- insert into authority (id, name) values (1, 'ADMIN')
-- insert into authority (id, name) values (2, 'USER')
-- insert into authority (id, name) values (3, 'CONTROLLER')
-- INSERT INTO users (ID, USERNAME, PASSWORD, CONFIRMED, EMAIL, LAST_NAME, ACTIVATION_ID, FIRST_NAME) VALUES (789, 'aa', '$2a$10$9AG/inYS7fWOHPhxQCtd0Og2gVYnbhwFBUsHaAOek5QAKSQtWItRC', TRUE, 'kwhshocker@gmail.com', 'aa', 'HSAHA','aa')
--
-- INSERT INTO account_authority(ID, ACCOUNT_ID, AUTHORITY_ID) VALUES (54, 789, 1)

-- INSERT INTO users (ID, USERNAME, PASSWORD, CONFIRMED, EMAIL, LAST_NAME, ACTIVATION_ID, FIRST_NAME) VALUES (1, 'user', '$2a$10$9AG/inYS7fWOHPhxQCtd0Og2gVYnbhwFBUsHaAOek5QAKSQtWItRC', TRUE, 'kwhshocker@gmail.com', 'aa', 'HSAHA','aa')
-- INSERT INTO account_authority(ID, ACCOUNT_ID, AUTHORITY_ID) VALUES (55, 1, 2)
-- INSERT INTO station(ID, ADDRESS, LAT, LNG, NAME) VALUES (4, 'Bulevar Oslobodjenja' 45.264008, 19.823417, '8')
-- INSERT INTO station(ID, ADDRESS, LAT, LNG, NAME) VALUES (5, 'Bulevar Oslobodjenja' 45.252671, 19.836873, '9')
-- INSERT INTO station(ID, ADDRESS, LAT, LNG, NAME) VALUES (6, 'Bulevar Oslobodjenja' 45.261041, 19.832232, '10')
-- INSERT INTO station(ID, ADDRESS, LAT, LNG, NAME) VALUES (7, 'Bulevar Oslobodjenja' 45.261041, 19.832232, '10')
-- INSERT INTO station(ID, ADDRESS, LAT, LNG, NAME) VALUES (8, 'Bulevar Cara Lazara/Bulevar Evrope' 45.239602, 19.823225, '10')
-- INSERT INTO station(ID, ADDRESS, LAT, LNG, NAME) VALUES (8, 'Merkator(Bulevar Oslobodjenja 100)' 45.243551, 19.841861, '11')
-- INSERT INTO station(ID, ADDRESS, LAT, LNG, NAME) VALUES (8, 'Bulevar Evrope 21' 45.246663, 19.818092, '12')
-- INSERT INTO station(ID, ADDRESS, LAT, LNG, NAME) VALUES (8, 'Hadzi Ruvimova' 45.255998, 19.812475, '13')
--Insert into pricelist(startDate, endDate, ticket_Type, price) values('2018-1-1','2018-5-5', "SINGLE", 50)
--Insert into pricelist(startDate, endDate, ticket_Type, price) values('2018-1-1','2018-5-5', "MONTHLY", 1500)
--Insert into pricelist(startDate, endDate, ticket_Type, price) values('2018-5-5','2019-1-1', "SINGLE", 60)
--Insert into pricelist(startDate, endDate, ticket_Type, price) values('2018-5-5','2019-1-1', "MONTHLY", 1800)
--insert into tickets(user_id, start_time, end_time, ticket_Type, active) values(789, '2018-4-4', '2018-4-4', "SINGLE", TRUE)
--insert into tickets(user_id, start_time, end_time, ticket_Type, active) values(789, '2018-4-6', '2018-4-6', "SINGLE", TRUE)


SELECT * FROM authority;