USE basic_app;

create table connections (
    id bigint auto_increment primary key,
    person_a_id bigint not null,
    person_b_id bigint not null,
    constraint fk_connections_person_a foreign key (person_a_id) references people (id) on delete cascade,
    constraint fk_connections_person_b foreign key (person_b_id) references people (id) on delete cascade,
    -- spojení je neorientované: vždy ukládat menší id do person_a_id, pak unique zabrání duplicitám A-B / B-A
    constraint chk_connections_order check (person_a_id < person_b_id),
    constraint uq_connections_pair unique (person_a_id, person_b_id)
);
