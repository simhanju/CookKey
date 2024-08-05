use ck;
drop database ck;
create database ck;
insert into user(userid, userpw) values('asd',123);

select * from user;


CREATE TABLE store (
  storenum bigint PRIMARY KEY auto_increment,
  storetitle varchar(300) NOT NULL,
  storecontents text NOT NULL,
  userid varchar(300) NOT NULL
);
insert into store (storetitle, storecontents, userid) values('샘플 데이터 1','샘플 데이터 게시글 내용입니다','asd');

select * from store;
select count(*) from store;

CREATE TABLE s_img (
  s_systemname varchar(700) PRIMARY KEY,
  orgname varchar(700) NOT NULL,
  storenum bigint NOT NULL,
  constraint si_s_fk foreign key(`storenum`) references `store`(`storenum`)
);

CREATE TABLE user (
  userid varchar(300) PRIMARY KEY,
  userpw varchar(300) NOT NULL,
  username varchar(300),
  nickname varchar(300) ,
  phone varchar(300) ,
  email varchar(300) ,
  gender varchar(300) ,
  birth varchar(300) ,
  zipcode varchar(300) ,
  addr varchar(1000) ,
  addrdetail varchar(1000) ,
  addretc varchar(300) 
);