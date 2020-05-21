/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/5/20 23:00:17                           */
/*==============================================================*/


drop table if exists sys_role;

drop table if exists sys_user;

drop table if exists sys_user_role;


create table sys_role
(
   id                   int not null auto_increment,
   role_name            varchar(30),
   is_deleted           bit,
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

create table sys_user
(
   id                   int not null auto_increment,
   account              varchar(80),
   password             varchar(80),
   is_deleted           bit,
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);


create table sys_user_role
(
   id                   int not null auto_increment,
   user_id              int,
   role_id              int,
   is_deleted           bit,
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

alter table sys_role add constraint FK_Reference_2 foreign key (id)
      references sys_user_role (id) on delete restrict on update restrict;

alter table sys_user add constraint FK_Reference_1 foreign key (id)
      references sys_user_role (id) on delete restrict on update restrict;

