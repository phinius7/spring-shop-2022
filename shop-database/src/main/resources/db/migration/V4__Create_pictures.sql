create table pictures_data (
    id integer not null auto_increment,
    create_date datetime(6),
    modify_date datetime(6),
    data longblob,
    primary key (id)
);

create table pictures_md (
    id integer not null auto_increment,
    create_date datetime(6),
    modify_date datetime(6),
    content_type varchar(255) not null,
    title varchar(255) not null,
    picture_data_id integer not null,
    product_id integer,
    primary key (id)
);

alter table pictures_md
    add constraint fk_picture_data_id
        foreign key (picture_data_id) references pictures_data (id);

alter table pictures_md
    add constraint fk_product_id
        foreign key (product_id) references products (id);