create schema test;

create table test.db
(
    id serial primary key,
    body varchar(255) not null,
    date varchar(255),
    time varchar(255)
);
insert into test.db(body, date, time)
values ('test1','2018-05-04','5:38:29'),
       ('test2','2019-05-04','8:38:29'),
       ('test3','2020-05-04','13:38:29'),
       ('test4','2021-05-04','4:38:29'),
       ('test5','2022-05-04','22:38:29')