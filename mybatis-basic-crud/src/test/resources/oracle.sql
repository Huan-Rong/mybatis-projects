drop table if exists student ;
drop sequence student_seq;

create table student (
id int primary key,
last_name varchar(25),
gender char(1)
);
create sequence student_seq;

insert into student(id, last_name, gender) values(student_seq.nextval, 'nana', '1');
insert into student(id, last_name, gender) values(student_seq.nextval, 'eric', '1');
