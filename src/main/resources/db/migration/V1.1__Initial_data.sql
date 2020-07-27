insert into users(id, username, password) values ('658d1a8e-d02a-11ea-87d0-0242ac130003', 'admin', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW');
insert into users(id, username, password)  values ('78f7cc04-d02a-11ea-87d0-0242ac130003', 'bob', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW');
insert into users(id, username, password)  values ('80aa18da-d02a-11ea-87d0-0242ac130003', 'steve', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW');
insert into users(id, username, password)  values ('85bb4b14-d02a-11ea-87d0-0242ac130003', 'nate', '{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW');

insert into authorities(username, authority) values('admin', 'ADMIN');
insert into authorities(username, authority) values('bob', 'USER');
insert into authorities(username, authority) values('steve', 'USER');
insert into authorities(username, authority) values('nate', 'USER');

insert into tasks(id, user_id, title, description) values ('aa0e9504-22fd-41d4-b829-c6d47c837961','78f7cc04-d02a-11ea-87d0-0242ac130003', 'Test 1', 'I should complete this task 1');
insert into tasks(id, user_id, title, description) values ('31e37df2-ebf4-4e7b-bb8f-4c6dd6ca7f39','80aa18da-d02a-11ea-87d0-0242ac130003', 'Test 2', 'I should complete this task 2');
insert into tasks(id, user_id, title, description) values ('e2f82fce-8e0b-4333-bd97-2c7f136128b5','85bb4b14-d02a-11ea-87d0-0242ac130003', 'Test 3', 'I should complete this task 3');


