insert into users (login, password) values ('user00', '0000');
insert into users (login, password) values ('user01', '1111');
insert into users (login, password) values ('user02', '2222');
insert into users (login, password) values ('user03', '3333');
insert into users (login, password) values ('user04', '4444');

insert into chatroom (name, owner) values ('family', 1);
insert into chatroom (name, owner) values ('work', 2);
insert into chatroom (name, owner) values ('study', 3);
insert into chatroom (name, owner) values ('friends', 4);
insert into chatroom (name, owner) values ('flood', 5);

insert into message (author, room, text, datetime) values (1, 5, 'Hello flooders!',  current_timestamp);
insert into message (author, room, text, datetime) values (2, 4, 'Hello friends!',  current_timestamp);
insert into message (author, room, text, datetime) values (3, 3, 'Hello dudes!',  current_timestamp);
insert into message (author, room, text, datetime) values (4, 2, 'Hello colleagues!',  current_timestamp);
insert into message (author, room, text, datetime) values (5, 1, 'Hello family!',  current_timestamp);