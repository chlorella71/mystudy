-- DDL(Data Definition Language)

drop table if exists customers restrict;
drop table if exists reservations restrict;
drop table if exists designers restrict;
drop table if exists s_counts restrict;
drop table if exists s_reservations restrict;
drop table if exists s_lists restrict;
drop table if exists s_classes restrict;

create table customers (
  c_no int not null,
  name varchar(50) not null,
  number varchar(30) not null,
  sex char(5) not null,
  f_visit date,
  v_count int not null,
  l_visit date,
  l_service varchar(50),
  l_designer varchar(50),
  memo text
);

alter table customers
  add constraint  primary key (c_no),
  modify column c_no int not null auto_increment,
  add constraint customers_uk unique key (number);

create table reservations (
  r_no int not null,
  c_no int not null,
  d_no int not null,
  date date not null,
  time varchar(50) not null,
  memo text,
  r_date date not null
);

alter table reservations
  add constraint primary key (r_no),
  modify r_no int not null auto_increment,
  add constraint reservations_fk foreign key (c_no) references customers(c_no),
  add constraint reservations_fk2 foreign key (d_no) references designers(d_no);

create table designers (
  d_no int not null,
  name varchar(50) not null,
  number varchar(30) not null,
  position varchar(50) not null
);

alter table designers
  add constraint primary key (d_no),
  modify d_no int not null auto_increment,
  add constraint designers_uk unique key (name);

create table s_counts (
  d_no int not null,
  date date not null,
  service varchar(50) not null,
  count int not null
);

alter table s_counts
  add constraint primary key (d_no, date),
  add constraint s_counts_fk foreign key (d_no) references designers(d_no),
  add constraint s_counts_fk2 foreign key (service) references s_lists(s_list);

create table s_lists (
  s_list varchar(50) not null,
  s_no int not null,
  name varchar(50) not null,
  cost int not null,
  yn boolean not null
);

alter table s_lists
  add constraint primary key (s_list),
  add constraint s_lists_uk unique key (name),
  add constraint s_lists_fk foreign key (s_no) references s_classes(s_no);

create table s_reservations (
  r_no int not null,
  s_list varchar(50) not null
);

alter table s_reservations
  add constraint primary key (r_no, s_list),
  add constraint s_reservations_fk foreign key (r_no) references reservations(r_no),
  add constraint s_reservations_fk2 foreign key (s_list) references s_lists(s_list);

create table s_classes (
  s_no int not null,
  name varchar(50) not null,
  yn boolean not null
);

alter table s_classes
  add constraint primary key (s_no),
  modify s_no int not null auto_increment,
  add constraint s_classes_uk unique key (name);