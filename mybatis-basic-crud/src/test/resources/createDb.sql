drop table if exists student ;

create table student (
id int primary key auto_increment,
last_name varchar(25),
gender char(1)
);

insert into student(last_name, gender) values('nana', '1');
insert into student(last_name, gender) values('eric', '1');
