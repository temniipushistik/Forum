create sequence hibernate_sequence start 1 increment 1;

create table message (
                         id int8 not null,
                         text varchar(2048) not null,
                         user_id int8 not null,
                         primary key (id)
);

create table user_role (
                           user_id int8 not null,
                           roles varchar(255)
);

create table usr (
                     id int8 not null,
                     active boolean not null,
                     email varchar(255),
                     phone_number varchar(255),
                     password varchar(255) not null,
                     username varchar(255) not null,
                     primary key (id)
);

alter table message
    add constraint message_user_fk
    foreign key (user_id) references usr (id);

alter table user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr (id);





