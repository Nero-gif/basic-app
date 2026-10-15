USE basic_app;
    
create table users (
    id bigint auto_increment primary key,
    username VARCHAR(255) not null,
    password varchar(255) not null,
    type enum('ADMIN', 'USER') not null
);

create table people (
    id bigint auto_increment primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    gender enum('MALE', 'FEMALE') not null
);

