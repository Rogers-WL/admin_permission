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
   username              varchar(80),
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

create table sys_action
(
    id                   int not null auto_increment,
    action_name            varchar(30),
    action_url           varchar(100),
    create_time          datetime,
    update_time          datetime,
    primary key (id)
);
create table sys_role_action
(
    id                   int not null auto_increment,
    role_id              int,
    action_id            int,
    create_time          datetime,
    update_time          datetime,
    primary key (id)
);
ALTER TABLE USERS ADD alias varchar(20) COMMENT '别名';
-- 3.1 不修改名称 使用modify
--  ALTER TABLE USERS MODIFY name varchar(20) NOT NULL COMMENT '用户名';
-- 3.2 修改名称 使用change  格式 是 change 要修改的名称 新名称 ...
--  ALTER TABLE USERS CHANGE name username varchar(20) NOT NULL COMMENT '用户名';
-- 4、删除字段
--  ALTER TABLE USERS DROP COLUMN alias;
-- 删除多个字段
-- ALTER TABLE USERS DROP COLUMN alias,DROP COLUMN age;

-- https://blog.csdn.net/slj821/article/details/104961010
-- https://blog.csdn.net/andy_zhang2007/article/details/90632411

