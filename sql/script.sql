CREATE DATABASE board;

USE board;

CREATE TABLE board.member (
	memberid varchar(50) primary key,
    name varchar(50) not null,
    password varchar(10) not null,
    regdate datetime not null
);

SELECT * FROM member;

CREATE TABLE article (
	article_no int auto_increment primary key,
    writer_id varchar(50) not null,
    writer_name varchar(50) not null,
    title varchar(255) not null,
    regdate datetime not null,
    moddate datetime not null,
    read_cnt int
);

CREATE TABLE article_content (
	article_no int primary key,
    content text
);














