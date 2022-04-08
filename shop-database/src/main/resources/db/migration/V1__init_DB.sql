create table roles (
    id integer not null auto_increment,
    create_date datetime(6),
    modify_date datetime(6),
    title varchar(32),
    primary key (id)
);

create table users (
    id integer not null auto_increment,
    create_date datetime(6),
    modify_date datetime(6),
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    user_name varchar(255),
    primary key (id)
);

create table users_roles (
    user_id integer not null,
    role_id integer not null,
    primary key (user_id, role_id)
);

alter table users_roles
    add constraint fk_role_id
    foreign key (role_id) references roles (id);

alter table users_roles
    add constraint fk_user_id
    foreign key (user_id) references users (id);