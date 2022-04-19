create table categories (
    id integer not null auto_increment,
    create_date datetime(6),
    modify_date datetime(6),
    article varchar(255),
    title varchar(255),
    primary key (id)
);


create table products (
    id integer not null auto_increment,
    create_date datetime(6),
    modify_date datetime(6),
    description varchar(512),
    price decimal(19,2),
    title varchar(255),
    category_id integer,
    primary key (id)
);

alter table products
    add constraint fk_categories_id
    foreign key (category_id) references categories (id);
