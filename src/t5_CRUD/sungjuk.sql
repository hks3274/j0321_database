show tables; 

create table sungjuk(
	idx  int not null auto_increment primary key,
	name varchar(20) not null,  /* 성명 */
	kor int default 0,					/* 국어점수 */
	eng int default 0,					/* 영어점수 */
	mat int default 0						/* 수학점수 */
);

drop table sungjuk;

desc sungjuk;

insert into sungjuk values (default, '홍길동', 100, 90, 80);

select * from sungjuk;

