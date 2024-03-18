show tables;

create table test (
	idx        int not null auto_increment primary key, /*고유번호*/
	name       varchar(20) not null,     					/*성명*/
	age        int default 20,			     					/*나이*/
	gender     varchar(2) default '남자', 					/*성별*/
	job        varchar(20) default '기타',  				/*직업*/
	address    varchar(50)				       					/*주소*/
);

drop table test;
delete from test;
desc test;

insert into test values (default,'홍길동', default, default, default,'서울');
insert into test values (default,'김말숙', 34, '여자',default,'청주');
insert into test values (default,'이기자', 29, '남자',default,'부산');
insert into test values (default,'김연아', default, '여자',default,'제주');
insert into test values (default,'손흥민',33, default,default,'서울');
insert into test values (default, '소나무',55,default, default,'제주');
insert into test values (default,'대나무',11, '여자','학생', default);
insert into test values (default,'감나무',22, '남자','회사원', default );

select * from test;

delete from test where name ='손흥민';

-- 레코드 수정하기 update 레코드명 set 필드명 ='수정내용' where '조건 (필드명=값)'; 

update test set age=25 where name ='홍길동';

-- 남자들의 나이를 1살씩 모두 더해주시오.
update test set age = age + 1 where gender = '남자';

-- 서울에 사는 사람들만 보여주시오.
select * from test where address = '서울';

-- 서울과 부산에 사는 사람들만 보여주시오.
select * from test where address = '서울' or address = '부산';

-- 나이가 30살 미만인 회원을 보여주시오
select * from test where age < 30;

-- 나이가 30살 미만이면서 여자인 회원을 보여주시오
select * from test where age < 30 and gender = '여자';

--청주에 사는 회원 확인?
select * from test where address = '청주';

-- 청주에 사는 회원 삭제
DELETE from test where address = '청주';

-- '청주/남자/19/강감찬'인 회원을 등록
INSERT into test values('강감찬', 19 , '남자', '청주');
SELECT * from test;

--서울에 사는 여자회원의 나이를 2살씩 뺴주시오.
update test set age = age -2 where gender = '여자' and address = '서울';

-- test 테이블의 구조보기
desc test;

-- 필드 구조 변경...(alter table ~~~~)

--test 테이블에 job필드(직업명은 5글자이내, 기본값: 기타)-컬럼를 추가하시오. => 필드 = 컬럼 
alter table  test add column job varchar(5) default '기타';

-- test테이블에 job필드 삭제하기(deop column)
alter table test drop column job;

--test 테이블의 job필드의 길이를 20자로 수정하시오.(modify column)
alter table test modify column job varchar(20);

-- test 테이블에 고유번호(idx) 필드를 추가하시오. - 기본키를 추가(구분이 될 수 있는 중복을 배제한 필드) 추가
alter table test add column idx int not null auto_increment primary key;

-- primary 키를 2개 줄 수 있음 mysql은 2개를 primary로 주는 대신 1개를 primary(대표키이면서 중복키), 1개는 unique key를 준다.


-- idx==5 삭제하시오?
delete from test where idx = 5;
delete from test where idx = 7;


--고유번호(idx)값을 5번부터 시작하도록 설정하시오.
alter table test auto_increment = 5;


