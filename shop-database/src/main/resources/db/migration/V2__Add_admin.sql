insert into users (create_date, modify_date, first_name, last_name, user_name, password, email)
    values ('20220408','20220408','Artem', 'Senchenko', 'admin', '$2a$12$ZyGB3eYiTp63LrwegoGuHO5tz5GmBfhP4iJtV5y7k2IybtVrcJUim', 'test@test.test');

insert into roles (create_date, modify_date, title)
    values ('20220408','20220408','ADMIN'), ('20220408','20220408','USER');

insert into users_roles (user_id, role_id)
    values (1, 1);