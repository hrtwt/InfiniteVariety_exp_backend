CREATE TABLE if not exists results
(
    id          identity                 not null primary key,
    pair_id     integer                  not null,
    user_name   varchar(32)              not null,
    judge       varchar(32),
    insert_date timestamp with time zone not null,
    update_date timestamp with time zone not null
);
