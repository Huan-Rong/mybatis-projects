drop table if exists department;
drop table if exists employee;

create table department (
  id int(11) primary key auto_increment,
  dept_name varchar(25)
);

create table employee (
  id int(11) primary key auto_increment,
  last_name varchar(25),
  email varchar(25),
  gender varchar(1),
  dept_id int(11)
);
alter table employee add constraint emp_fk foreign key(dept_id) references department(id);

insert into department(dept_name) values("dev");
insert into department(dept_name) values("test");
insert into employee(last_name, email, gender, dept_id) values('nana', 'nana@gg.com', '1', 1);
insert into employee(last_name, email, gender, dept_id) values('na', 'na@gg.com', '1', 2);
insert into employee(last_name, email, gender, dept_id) values('eric', 'eric@gg.com', '2', 2);
insert into employee(last_name, email, gender, dept_id) values('ian', 'ian@gg.com', '2', 1);
