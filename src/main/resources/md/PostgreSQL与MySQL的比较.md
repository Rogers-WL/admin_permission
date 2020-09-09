## PostgreSQL与MySQL的功能对比

### 数据类型

1.Postgre支持用户定义的数据类型，mysql不支持

```sql
create type address as
(
  zip_code varchar(5),
  city     varchar(100),
  street   varchar(100)
);
create table contact
(
  contact_id       integer not null primary key,
  delivery_address address,
  postal_address   address
);
```

2.Postgres支持特殊的用户定义数据类型，通常还基于基本数据类型，还可以包括检查约束，mysql不支持

```sql
create domain positive_number 
    as numeric(12,5) not null 
	check (value > 0);
create table orders
(
  order_id     integer not null primary key,
  customer_id  integer not null ,
  amount       positive_number
);
```

3.Postgres支持数组数据类型，而mysql不支持

```sql
create table testArray (id int[]);
insert into testArray values(ARRAY[1,2,2]);
```

4.Postgres支持枚举数据类型，而mysql不支持

```sql
create type week as enum('Sun','Mon','Tues','Wed','Thur','Fri','Sat');
create table duty(
    person text,
    weekday week
);
insert into duty values('April','Sun');
insert into duty values('Harris','Mon');
insert into duty values('Dave','Wed');
```

5.Postgres提供网络地址类型有inet、cidr、macaddr三种类型用于存储网络地址和MAC地址数据，msql不支持

```sql
create table ip(
id integer not null ,
inet inet,
cidr cidr,
macaddr macaddr,
)
insert into ip values (1,'192.168.213.27','192.168.213.27','0800.2b01.0203');
```

 - cidr和inet函数
   
     |             函数             | 返回类型 |              描述              |                          示例                          |       结果       |
     | :--------------------------: | :------: | :----------------------------: | :----------------------------------------------------: | :--------------: |
     |         abbrev(inet)         |   text   |         显示为文本格式         |           select abbrev(inet '10.1.0.0/16');           |   10.1.0.0/16    |
     |         abbrev(cidr)         |   text   |         显示为文本格式         |           select abbrev(cidr '10.1.0.0/16');           |     10.1/16      |
     |       broadcast(inet)        |   inet   |          网络广播地址          |          select broadcast('192.168.1.5/24');           | 192.168.1.255/24 |
     |         family(inet)         |   int    |  获取地址族，4位IPv4,6为IPv6   |                 select family('::1');                  |        6         |
     |          host(inet)          |   text   |    抽取IP地址，并以text显示    |             select host('192.168.1.5/24');             |   192.168.1.5    |
     |        hostmask(inet)        |   inet   |       为网络构造主机掩码       |          select hostmask('192.168.23.20/30');          |     0.0.0.3      |
     |        masklen(inet)         |   int    |          抽取掩码长度          |           select masklen('192.168.1.5/24');            |        24        |
     |        netmask(inet)         |   inet   |       为网络构造子网掩码       |           select netmask('192.168.1.5/24');            |  255.255.255.0   |
     |        network(inet)         |   cidr   |       抽取地址的网络部分       |           select network('192.168.1.5/24');            |  192.168.1.0/24  |
     |    set_masklen(inet, int)    |   inet   |     为inet设置子网掩码长度     |       select set_masklen('192.168.1.5/24', 16);        |  192.168.1.5/16  |
     |          text(inet)          |   text   |  将IP地址和掩码长度抽取为文本  |            select text(inet '192.168.1.5');            |  192.168.1.5/32  |
     | inet_same_family(inet, inet) | boolean  |      是否属于相同的地址族      |   select inet_same_family('192.168.1.5/24', '::1');    |        f         |
     |    inet_merge(inet, inet)    |   cidr   | 包括两个入参地址的最小网络地址 | select inet_merge('192.168.1.5/24', '192.168.2.5/24'); |  192.168.0.0/22  |

6.Postgres支持布尔数据类型，mysql不支持

7.Postgres支持间隔时间，mysql不支持

```sql
select current_date + interval '2' day;
```

8.Postgres时区支持，mysql不支持

```sql
select now(); --查看数据库的时区与时间
show time zone; --查看时区
select * from pg_timezone_names; --查看数据库可供选择的时区
set time zone "Asia/Shanghai"; --设置时区
```

9.Postgres支持范围类型，mysql不支持

```sql
create table rooms
(
   room_id integer not null primary key,
   room_name varchar
)
create table room_reservation
(
   reservation_id   integer    not null primary key,
   room_id          integer    not null references rooms,
   booked_between   tsrange    not null -- 定义预订此房间的时间“范围” 
);
--插入数据
insert into room_reservation 
  (reservation_id, room_id, booked_between)
values (2, 1, tsrange(timestamp '2014-09-08 10:00:00', timestamp '2014-09-08 18:00:00', '[]'))；
--查询
select *
from room_reservation
where booked_between && tsrange(timestamp '2014-09-08 11:00:00', timestamp '2014-09-08 14:00:00', '[]')
```

10.Postgres没有字符串长度限制
- 一般关系型数据库的字符串有限定长度8k左右，无限长 TEXT 类型的功能受限，只能作为外部大数据访问。而PostgreSQL的 TEXT 类型可以直接访问，SQL语法内置正则表达式，可以索引，还可以全文检索，或使用xml xpath。MySQL 的各种text字段有不同的限制，要手动区分 small text, middle text, large text… PostgreSQL 没有这个限制，text 能支持各种大小。

11.Postgres支持存储数组和json、jsonb，相比使用text存储接送要高效很多。

```sql
-- 创建含有json数据类型的表格
CREATE TABLE orders (
   ID serial NOT NULL PRIMARY KEY,
   info json NOT NULL
);
-- 插入json格式数据
insert into orders(info) values ('{ "customer": "John Doe", "items": {"product": "Beer","qty": 6}}') ;
insert into orders(info) values 
('{ "customer": "Lily Bush", "items": {"product": "Diaper","qty": 24}}'),
('{ "customer": "Josh William", "items": {"product": "Toy Car","qty": 1}}'),
('{ "customer": "Mary Clark", "items": {"product": "Toy Train","qty": 2}}');
-- 查询表格数据
select info from orders;
-- 根据json的键查询
select info -> 'customer' as customer from orders;
-- 下面使用->>操作获取所有顾客作为文本
select info ->> 'customer' as customer from orders;
-- 首先使用info->'item’返回json对象。然后使用info->‘item’->>'product’返回所有产品文本值
select info ->'items' ->> 'product' as product from orders order by product;
-- 在where子句中使用json操作符
select info ->> 'customer' as customer from orders where info -> 'items' ->> 'product' = 'Diaper';
-- 下面查询谁一次买了2个商品
select info ->> 'customer' as customer , info -> 'items' ->> 'product' as product from order 
where CAST(info -> 'items' ->> 'qty' as integer) = 2;
-- 对json数据使用聚集函数
select
MIN(CAST(info -> 'items' ->> 'qty' as integer)),
MAX(CAST(info -> 'items' ->> 'qty' as integer)),
SUM(CAST(info -> 'items' ->> 'qty' as integer)),
AVG(CAST(info -> 'items' ->> 'qty' as integer)) from orders;
-- JSON 函数 json_each()函数的作用是:将最外层的JSON对象展开为一组键值对
select json_each(info) from orders;
-- 如果想得到一组key-value对作为文本，可以使用json_each_text()函数
select json_each_text(info) from orders;
-- json_object_keys()函数可以获得json对象最外层的一组键
select json_object_keys(info -'items') from orders;
-- json_typeof函数返回json最外层key的数据类型作为字符串。可能是number,boolean, null, object,array,string
select json_typeof (info ->'items') from orders;
select json_typeof (info ->'items'->'qty') from orders;
```



### 约束条件

1.Postgres支持延期外键约束，mysql不支持

```sql
create table departments
(
  dept_id    integer not null primary key,
  name       varchar(100) not null
);
create table employees
(
   emp_id     integer not null primary key,
   dept_id    integer not null,
   lastname   varchar(100) not null,
   firstname  varchar(100),
   constraint fk_emp_dept 
      foreign key (dept_id) 
      references departments (dept_id)
      deferrable initially deferred
);
BEGIN;
insert into employees (emp_id, dept_id, lastname, firstname)
values (1, 42, 'Ford', 'Prefect');
insert into departments (dept_id, name);
values (42,  'Space Pilots');
COMMIT;
ROLLBACK;
```

2.Postgres支持自定义功能检查约束

```sql
-- 创建自定义约束
create function is_valid(p_to_check integer)
  returns boolean
as
$$
  select p_to_check = 42
$$
language sql;
-- 建表将answer_value 通过自定义约束约束插入的值必须为42
create table answers
(
   question_id      integer not null,
   answer_value     integer not null check (is_valid (answer_value))
);

insert into answers values (1,42)
```

3.Postgres支持排除约束，mysql不支持

```sql
CREATE EXTENSION gist;  -- 使用gist需要创建
create table product_price
(
   price_id      serial        not null primary key,
   product_id    integer       not null ,
   price         numeric(16,4) not null,
   valid_during  daterange not null
);
alter table product_price
  add constraint check_price_range
  exclude using gist(product_id with =, valid_during with &&)

insert into product_price
  (product_id, price, valid_during)
values
  (1, 100.0, '[2010-01-01,2011-01-01)'),
  (1,  90.0, '[2011-01-01,)');
insert into product_price
  (product_id, price, valid_during)
values
  (1,  80.0, '[2012-01-01,2013-01-01)');
```

4.Postgres可以使用MATCH FULL的外键，mysql不支持

```sql
create table parent
(
  id_1   integer not null,
  id_2   integer not null,
  primary key (id_1, id_2)
);

create table child
(
  child_id    integer not null primary key, 
  parent_id_1 integer null,
  parent_id_2 integer null,
  constraint fk_child_parent
      foreign key (parent_id_1, parent_id_2) 
      references parent (id_1, id_2)
);
-- 以下进行插入数据是可以的
insert into parent (id_1, id_2) values (1,1);
insert into child (child_id, parent_id_1, parent_id_2) values (1, null, 42);
-- 如果child的外键加了MATCH FULL 
create table child
(
  child_id    integer not null primary key, 
  parent_id_1 integer null,
  parent_id_2 integer null,
  constraint fk_child_parent
      foreign key (parent_id_1, parent_id_2) 
      references parent (id_1, id_2)
      MATCH FULL
);
-- 通过上面的定义，两个外键列必须都为NULL或都必须引用父表中的有效值。
```

### 查询

1.Postgres有行构造器，mysql没有

```sql
WITH id_list (id) AS (
   VALUES (1),(2),(3)
)
SELECT l.id
FROM id_list l
  LEFT JOIN fk_test t ON t.id = l.id;
```

2.Postgres可以进行筛选的聚合，而mysql不行

```sql
create table customers
(
customer_id integer not null,
amount integer 
)
insert into customers values (1,112312);
insert into customers values (1,112312);
insert into customers values (2,21112312);
insert into customers values (2,111);
-- 两种方法进行查询
-- 第一种
select customer_id, 
       sum(amount) as total_amount,
       sum(amount) filter (where amount > 10000) as large_orders_amount
from customers
group by customer_id;
-- 第二种
select customer_id, 
       sum(amount) as total_amount,
       sum(case when amount > 10000 then amount end) as large_orders_amount
from customers
group by customer_id;
```

3.Postgres支持并行查询，mysql不支持

### 索引编制

1.Postgres支持局部索引(表的子集上定义)，mysql不支持

```sql
-- 局部索引可以例如用于仅对数据的子集实施唯一约束。
create table projects
(
   project_id integer not null primary key,
   name       varchar(100) not null,
   is_active  boolean
);
create unique index idx_unique_active_name 
   on projects (name)
   where is_active
insert into projects (project_id, name, is_active) values (1,'WebShop', true);
insert into projects (project_id, name, is_active) values (2,'CRM System', true);
insert into projects (project_id, name, is_active) values (3,'WebShop', false);
-- 下面这条语句执行失败
insert into projects (project_id, name, is_active) values (4,'CRM System', true);
-- 作为副作用，如果只有少数行与索引的WHERE子句匹配，则索引的大小会减小。 
```

2.Postgres支持使用自定义函数表达式进行索引

```sql
create table products
(
   id     integer not null primary key,
   code   varchar(100) not null
);
-- 自定义函数
create function get_number(p_value text)
  returns integer
as
$$
begin
  return cast(regexp_replace(p_value, '[^0-9]', '', 'g') as integer);
end;
$$
immutable
language plpgsql;
-- 创建索引
create index ix_code_number on products( get_number(code) );

insert into products values (1,'1');
insert into products values (2,'2');
insert into products values (42,'42');

select *
from products
where get_number(code) = 42;
```

### 联接和运算符

1.Postgres支持全外连接，mysql没有全外连接

```sql
create table employees
(
id integer not null primary key,
name text,
age integer,
address character(50)
)
insert into employees values (1,'Maxsu',25,'海口市人民大道2880号');
insert into employees values (2,'Minsu',25,'广州中山大道');
insert into employees values (3,'李洋',21,'北京市朝阳区');
insert into employees values (4,'Manisha',24,'Munbai');
insert into employees values (5,'Larry',21,'Paris');
insert into employees values (6,'Minsu',24,'Delhi');
insert into employees values (7,'Manisha',19,'boida');

CREATE TABLE department
(
  id integer,
  dept text,
  fac_id integer
)
-- 插入数据
INSERT INTO department VALUES(1,'IT', 1);
INSERT INTO department VALUES(2,'Engineering', 2);
INSERT INTO department VALUES(3,'HR', 7);
INSERT INTO department VALUES(10,'Market', 10);

SELECT EMPLOYEES.ID, EMPLOYEES.NAME, DEPARTMENT.DEPT  
FROM EMPLOYEES 
FULL OUTER JOIN DEPARTMENT  
ON EMPLOYEES.ID = DEPARTMENT.ID;
```

2.Postgres可以使用ORDER BY 字段 NULLS LAST，mysql没有

```sql
SELECT *
FROM employees
ORDER BY age NULLS LAST
```

3.Postgres在使用between的时候可以添加symmetric而忽视排序

```sql
-- 该语句无法查询到结果
select *
from employees
where id between  7 and 3;
-- 加入symmetric后能够查询到结果
select *
from employees
where id between symmetric 7 and 3;
```

4.Postgres可以判断时间是否重叠，mysql不支持

```sql
create table foo 
(
  id            integer not null primary key,
  planned_start date,
  planned_end   date,
  actual_start  date,
  actual_end    date
);
insert into foo (id, planned_start, planned_end, actual_start, actual_end) values 
(1, date '2014-01-01', date '2014-08-01', date '2014-01-02', date '2014-07-30'),
(2, date '2014-01-01', date '2014-08-01', date '2014-09-01', date '2014-09-24');
select *
from foo
where (planned_start, planned_end) overlaps (actual_start, actual_end);
```

### PostgreSQL相对于MySQL的优势

1.不仅仅是关系型数据库，除了存储正常的数据类型外，还支持存储数组和json、jsonb，相比使用text存储接送要高效很多。

2.json类型具有存储快，访问慢的特点；jsonb类型具有存储慢，访问快的特点。此外，jsonb类型还支持索引。

3.支持地理信息处理扩展，PostGIS 为PostgreSQL提供了存储空间地理数据的支持，使PostgreSQL成为了一个空间数据库，能够进行空间数据管理、数量测量与几何拓扑分析。在功能上，和MYSQL对比，PostGIS具有下列优势：O2O业务场景中的LBS业务使用PostgreSQL + PostGIS有无法比拟的优势。

4.可以快速构建REST API，PostgREST 可以方便的为任何 PostgreSQL 数据库提供完全的 RESTful API 服务。

5.支持树状结构,支持R-trees这样可扩展的索引类型，可以更方便地处理一些特殊数据。MySQL 处理树状的设计会很复杂, 而且需要写很多代码, 而 PostgreSQL 可以高效处理树结构。

6.有极其强悍的 SQL 编程能力,支持递归，有非常丰富的统计函数和统计语法支持。
- MySQL：支持 CREATE PROCEDURE 和 CREATE FUNCTION 语句。存储过程可以用 SQL 和 C++ 编写。用户定义函数可以用 SQL、C 和 C++ 编写。
- PostgreSQL：没有单独的存储过程，都是通过函数实现的。用户定义函数可以用 PL/pgSQL(专用的过程语言)、PL/Tcl、PL/Perl、PL/Python 、SQL 和 C 编写。

7.外部数据源支持

- 可以把 70 种外部数据源 (包括 Mysql, Oracle, CSV, hadoop …) 当成自己数据库中的表来查询。Postgres有一个针对这一难题的解决方案：一个名为“外部数据封装器(Foreign Data Wrapper，FDW)”的特性。该特性最初由PostgreSQL社区领袖Dave Page四年前根据SQL标准SQL/MED(SQL Management of External Data)开发。FDW提供了一个SQL接口，用于访问远程数据存储中的远程大数据对象，使DBA可以整合来自不相关数据源的数据，将它们存入Postgres数据库中的一个公共模型。这样，DBA就可以访问和操作其它系统管理的数据，就像在本地Postgres表中一样。例如，使用FDW for MongoDB，数据库管理员可以查询来自文档数据库的数据，并使用SQL将它与来自本地Postgres表的数据相关联。借助这种方法，用户可以将数据作为行、列或JSON文档进行查看、排序和分组。他们甚至可以直接从Postgres向源文档数据库写入(插入、更细或删除)数据，就像一个一体的无缝部署。也可以对Hadoop集群或MySQL部署做同样的事。FDW使Postgres可以充当企业的中央联合数据库或“Hub”。

8.对索引的支持更强，PostgreSQL 的可以使用函数和条件索引，这使得PostgreSQL数据库的调优非常灵活，mysql就没有这个功能，条件索引在web应用中很重要。对于索引类型：

- MySQL：取决于存储引擎。MyISAM：BTREE，InnoDB：BTREE。
- PostgreSQL：支持 B-树、哈希、R-树和 Gist 索引。

9.集群支持更好

- Mysql Cluster可能与你的想象有较大差异。开源的cluster软件较少。复制(Replication)功能是异步的并且有很大的局限性。例如，它是单线程的(single-threaded)，因此一个处理能力更强的Slave的恢复速度也很难跟上处理能力相对较慢的Master。
- PostgreSQL有丰富的开源cluster软件支持。plproxy 可以支持语句级的镜像或分片，slony 可以进行字段级的同步设置，standby 可以构建WAL文件级或流式的读写分离集群，同步频率和集群策略调整方便，操作非常简单。另外，PostgreSQL的主备复制属于物理复制，相对于MySQL基于binlog的逻辑复制，数据的一致性更加可靠，复制性能更高，对主机性能的影响也更小。对于WEB应用来说，复制的特性很重要，mysql到现在也是异步复制，pgsql可以做到同步，异步，半同步复制。还有mysql的同步是基于binlog复制，类似oracle golden gate,是基于stream的复制，做到同步很困难，这种方式更加适合异地复制，pgsql的复制基于wal，可以做到同步复制。同时，pgsql还提供stream复制。

10.事务隔离做的更好

- MySQL 的事务隔离级别 repeatable read 并不能阻止常见的并发更新, 得加锁才可以, 但悲观锁会影响性能, 手动实现乐观锁又复杂， 而 PostgreSQL 的列里有隐藏的乐观锁 version 字段, 默认的 repeatable read 级别就能保证并发更新的正确性, 并且又有乐观锁的性能。

11.对于字符支持更好一些

- MySQL 里需要 utf8mb4 才能显示 emoji 的坑, PostgreSQL 没这个坑。

12.对表连接支持较完整

- 对表连接支持较完整，MySQL只有一种表连接类型:嵌套循环连接(nested-loop),不支持排序-合并连接(sort-merge join)与散列连接(hash join)。PostgreSQL都支持。

13.存储方式支持更大的数据量

- PostgreSQL主表采用堆表存放，MySQL采用索引组织表，能够支持比MySQL更大的数据量。

14.序列支持更好

- MySQL 不支持多个表从同一个序列中取 id, 而 PostgreSQL 可以。

15.对子查询支持更好

- 对子查询的支持。虽然在很多情况下在SQL语句中使用子查询效率低下，而且绝大多数情况下可以使用带条件的多表连接来替代子查询，但是子查询的存在在很多时候仍然不可避免。而且使用子查询的SQL语句与使用带条件的多表连接相比具有更高的程序可读性。几乎任何数据库的子查询 (subquery) 性能都比 MySQL 好。

### MySQL相对于PostgreSQL的优势

1.MySQL比PostgreSQL更流行

- 流行对于一个商业软件来说，也是一个很重要的指标，流行意味着更多的用户，意味着经受了更多的考验，意味着更好的商业支持、意味着更多、更完善的文档资料。易用，很容易安装。第三方工具，包括可视化工具，让用户能够很容易入门。

2.回滚实现更优

- nnodb的基于回滚段实现的MVCC机制，相对PG新老数据一起存放的基于XID的MVCC机制，是占优的。新老数据一起存放，需要定时触发VACUUM，会带来多余的IO和数据库对象加锁开销，引起数据库整体的并发能力下降。而且VACUUM清理不及时，还可能会引发数据膨胀。

3.在Windows上运行更可靠

- 与PostgreSQL相比，MySQL更适宜在Windows环境下运行。MySQL作为一个本地的Windows应用程序运行(在 NT/Win2000/WinXP下，是一个服务)，而PostgreSQL是运行在Cygwin模拟环境下。PostgreSQL在Windows下运行没有MySQL稳定，应该是可以想象的。

4.线程模式相比进程模式的优势

- MySQL使用了线程，而PostgreSQL使用的是进程。在不同线程之间的环境转换和访问公用的存储区域显然要比在不同的进程之间要快得多。
- 进程模式对多CPU利用率比较高。进程模式共享数据需要用到共享内存，而线程模式数据本身就是在进程空间内都是共享的，不同线程访问只需要控制好线程之间的同步。线程模式对资源消耗比较少。所以MySQL能支持远比PostgreSQL多的更多的连接。但PostgreSQL中有优秀的连接池软件软件，如pgbouncer和pgpool，所以通过连接池也可以支持很多的连接。

### 如何确定在MySQL和PostgreSQL中进行选择，以下规则总是有效的：

- 如果你的操作系统是Windows，你应该使用MySQL。
- 当绝对需要可靠性和数据完整性的时候，PostgreSQL是更好的选择。
- 如果需要数据库执行定制程序，那么可扩展的PostgreSQL是更好的选择。
- 你的应用处理的是地理数据，由于R-TREES的存在，你应该使用PostgreSQL。
- 如果你对数据库并不了十分了解，甚至不知道事务、存储过程等究竟是什么，你应该使用MySQL。