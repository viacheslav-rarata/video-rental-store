create sequence seq_customer_id start 1 increment 1;
create sequence seq_film_id start 1 increment 1;
create sequence seq_rental_id start 1 increment 1;

create table customers
(
    id           bigint not null,
    first_name   varchar(255),
    last_name    varchar(255),
    bonus_points integer default 0,
    primary key (id)
);

create table films
(
    id        bigint not null,
    title     varchar(255),
    film_type varchar(255),
    primary key (id)
);

create table rental
(
    id          bigint not null,
    rental_date timestamp,
    return_date timestamp,
    rented_days integer,
    extra_days  integer,
    price       integer,
    surcharges  integer,
    customer_id bigint,
    film_id     bigint,
    primary key (id)
);

alter table rental add constraint fk_customers foreign key (customer_id) references customers;
alter table rental add constraint fk_films foreign key (film_id) references films;
