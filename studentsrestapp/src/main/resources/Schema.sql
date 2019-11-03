DROP TABLE IF EXISTS student;

create table student (
    student_id BIGINT auto_increment NOT null primary key,
    first_name varchar (30),
    last_name varchar (30),
    birthdate date,
    gpa double
);