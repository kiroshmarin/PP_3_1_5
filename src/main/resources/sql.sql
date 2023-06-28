
insert into user values (1, 25, 'alex@mail.ru', 'Popov', 'Alex', '$2a$12$NKSxLP2xOJM.WHXz4DOgjuiunXLr/D6XAma1zBKeHvU2rliWdbPUq');
insert into user values (2, 17, 'maria@mail.ru', 'Volkova', 'Maria', '$2a$12$NKSxLP2xOJM.WHXz4DOgjuiunXLr/D6XAma1zBKeHvU2rliWdbPUq');
insert into role values (1, 'ROLE_ADMIN');
insert into role values (2, 'ROLE_USER');
insert into users_roles values (1,1);
insert into users_roles values (2,2);
# passwd user/admin 12345