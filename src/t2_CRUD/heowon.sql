show tables;

create table heowon(
	idx int not null auto_increment primary key,
	name varchar(20) not null,
	age int default 20,
	gender char(2) default '여자',
	address varchar(30)
);

desc heowon;

insert into heowon values(default, '홍길동', default , '남자', '서울');
insert into heowon values(default, '김말숙', 29 , default, '청주');
insert into heowon values(default, '이기자', 33 , '남자', '제주');
insert into heowon values(default, '소나무', 41 , '남자', '서울');
insert into heowon values(default, '오하늘', 19 , default, '청주');

select * from heowon;