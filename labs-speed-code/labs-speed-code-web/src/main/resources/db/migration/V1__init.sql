drop table if exists speed_database;
drop table if exists speed_table;

create table speed_database (
  id bigint auto_increment,
  url varchar(100),
  username varchar(100),
  password varchar(100),
  constraint pk_sys_user primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_user_username on sys_user(username);
create index idx_sys_user_organization_id on sys_user(organization_id);