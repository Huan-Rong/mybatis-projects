drop table if exists department;

create table department (
 id int(11) primary key auto_increment,
 dept_name varchar(25)
);
insert into department(dept_name) values("dev");
insert into department(dept_name) values("test");
