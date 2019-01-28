insert into authority (id, name) values (1, 'ADMIN');
insert into authority (id, name) values (2, 'USER');
insert into authority (id, name) values (3, 'CONTROLLER');
INSERT INTO users (ID, USERNAME, PASSWORD, CONFIRMED, EMAIL, LAST_NAME, ACTIVATION_ID, FIRST_NAME) VALUES (789, 'aa', '$2a$10$9AG/inYS7fWOHPhxQCtd0Og2gVYnbhwFBUsHaAOek5QAKSQtWItRC', TRUE, 'kwhshocker@gmail.com', 'aa', 'HSAHA','aa');

INSERT INTO account_authority(ID, ACCOUNT_ID, AUTHORITY_ID) VALUES (54, 789, 1);

INSERT INTO users (ID, USERNAME, PASSWORD, CONFIRMED, EMAIL, LAST_NAME, ACTIVATION_ID, FIRST_NAME) VALUES (1, 'user', '$2a$10$9AG/inYS7fWOHPhxQCtd0Og2gVYnbhwFBUsHaAOek5QAKSQtWItRC', TRUE, 'kwhshocker@gmail.com', 'aa', 'HSAHA','aa');
INSERT INTO account_authority(ID, ACCOUNT_ID, AUTHORITY_ID) VALUES (55, 1, 2);

insert into users(ID, USERNAME, PASSWORD, CONFIRMED, EMAIL, LAST_NAME, ACTIVATION_ID, FIRST_NAME) values(799, "jjjjj", "jjjjj", false, "mgr36995@gmail.com", "Jovic", "JOVA2", "Jova");
insert into account_authority(ID, ACCOUNT_ID, AUTHORITY_ID) values(69,799,2);

INSERT INTO stations(ID, ADDRESS, LAT, LNG, NAME, ACTIVE) VALUES (4, 'Bulevar Oslobodjenja', 45.264008, 19.823417, '8', 1);
INSERT INTO stations(ID, ADDRESS, LAT, LNG, NAME, ACTIVE) VALUES (5, 'Bulevar Oslobodjenja', 45.252671, 19.836873, '9', 1);
INSERT INTO stations(ID, ADDRESS, LAT, LNG, NAME, ACTIVE) VALUES (7, 'Bulevar Oslobodjenja', 45.261041, 19.832232, '10', 1);
INSERT INTO stations(ID, ADDRESS, LAT, LNG, NAME, ACTIVE) VALUES (6, 'Bulevar Oslobodjenja', 45.261041, 19.832232, '10', 1);
INSERT INTO stations(ID, ADDRESS, LAT, LNG, NAME, ACTIVE) VALUES (9, 'Bulevar Cara Lazara/Bulevar Evrope', 45.239602, 19.823225, '10', 1);
INSERT INTO stations(ID, ADDRESS, LAT, LNG, NAME, ACTIVE) VALUES (10, 'Merkator(Bulevar Oslobodjenja 100)', 45.243551, 19.841861, '11', 1);
INSERT INTO stations(ID, ADDRESS, LAT, LNG, NAME, ACTIVE) VALUES (11, 'Bulevar Evrope 21', 45.246663, 19.818092, '12', 1);
INSERT INTO stations(ID, ADDRESS, LAT, LNG, NAME, ACTIVE) VALUES (12, 'Hadzi Ruvimova', 45.255998, 19.812475, '13', 1);
Insert into pricelist(startDate, endDate, ticket_Type, price) values('2018-1-1','2018-5-5', "SINGLE", 50);
Insert into pricelist(startDate, endDate, ticket_Type, price) values('2018-1-1','2018-5-5', "MONTHLY", 1500);
Insert into pricelist(startDate, endDate, ticket_Type, price) values('2018-5-5','2019-1-1', "SINGLE", 60);
Insert into pricelist(startDate, endDate, ticket_Type, price) values('2018-5-5','2019-1-1', "MONTHLY", 1800);
Insert into pricelist(startDate, endDate, ticket_Type, price) values('2019-1-1','2019-2-2', "SINGLE", 50);
Insert into line(idline, name, active) values(1,'Line1', true);
Insert into line(idline, name, active) values(2,'Line2', true);
Insert into line(idline, name, active) values(3,'Line3', true);
insert into tickets(user_id, start_time, end_time, ticket_Type, active, price) values(789, '2018-4-4', '2018-4-4', "SINGLE", TRUE, 50);
insert into tickets(user_id, start_time, end_time, ticket_Type, active, price) values(789, '2018-4-6', '2018-4-6', "SINGLE", FALSE, 50);
insert into tickets(user_id, start_time, end_time, ticket_Type, active, price) values(1, '2018-4-6', '2018-4-6', "SINGLE", FALSE, 50);
insert into tickets(user_id, start_time, end_time, ticket_Type, active, price) values(799, '2018-4-6', '2018-4-6', "SINGLE", FALSE, 50);
insert into timeschedule(id, day, time, idline, station_id, transport_id) values (1, null, null, 2, null, 1);
insert into timeschedule_saturday_schedule(timeschedule_id, saturday_schedule) values(1, '12:10');
insert into transport(id, station_order, line_idline, station_id) values (1, 1, 2, 4);


SELECT * FROM authority;