show tables;

create table insarok(
	idx int not null auto_increment primary key,  /* 고유번호 */
	buser varchar(10) not null,										/*	부서명 */
	name varchar(20) not null,										/*	부서원(성명) */
	jikwi varchar(10) not null default '사원',			/*	직위 */
	gender char(2) not null default '남자',				/*	성별 */
	age int default 25,														/*	나이 */
	ipsail datetime not null default now(),				/*	입사일(기본값 : 오늘날짜) */
	address varchar(50)												/* 주소(공백허용) */
);


desc insarok;


insert into insarok values (default,'인사과', '홍길동', '과장', default,38,'1995-1-5','경기도');
insert into insarok values (default,'총무과', '이기자', '부장', default,45,'1990-10-25','청주');
insert into insarok values (default,'영업과', '강감찬', '대리', default,28,'1915-4-15','경기도');
insert into insarok values (default,'자재과', '김말숙', '대리', default,30,'1999-2-18','제주');
insert into insarok values (default,'인사과', '소나무', '사원', default,29,'1998-6-15','서울');
insert into insarok values (default,'영업과', '오하늘', '대리', default,35,'2000-9-5','부산');
insert into insarok values (default,'인사과', '홍민아', '대리', default,34,'2012-1-25','서울');
insert into insarok values (default,'영업과', '탁민아', '사원', default,28,'2013-5-10','제주');
insert into insarok values (default,'자재과', '이재희', '사원', default,21,'2021-1-30','부산');
insert into insarok values (default,'총무과', '김기자', '부장', default,39,'2023-4-15','청주');
insert into insarok values (default,'자재과', '이순신', '과장', default,45,'2020-11-19','서울');
insert into insarok values (default,'인사과', '나도야', '사원', default,31,'2019-8-8','경기도');
insert into insarok values (default,'영업과', '가나다', '사원', default,37,'1998-7-7','서울');
insert into insarok values (default,'영업과', '라마바', '부장', default,29,'1999-10-25','청주');
insert into insarok values (default,'자재과', '대나무', '사원', default,30,'2006-8-10','서울');
insert into insarok values (default,'인사과', '홍길자', '대리', default,25,'2008-6-9','경기도');
insert into insarok values (default,'총무과', '이수연', '대리', default,31,'2007-5-8','서울');
insert into insarok values (default,'인사과', '손흥민', '사원', default,32,'2003-2-3','경기도');
insert into insarok values (default,'자재과', '김연아', '부장', default,25,'2023-10-30','인천');
insert into insarok values (default,'영업과', '나나나', '과장', default,33,'2024-1-25','제주');
insert into insarok values (default,'인사과', '오나라', '사원', default,22,'2023-10-20','경기도');
insert into insarok values (default,'총무과', '강강강', '사원', default,35,'2021-10-2','서울');


select * from insarok;

-- 인사록 테이블의 성명/직위/주소 필드만 모든 자료 표시?
select name, jikwi, address from insarok;

-- 홍길동 레코드만 출력
select * from insarok where name ='홍길동';

--서울에 사는 홍길동 레코드만 출력
select * from insarok where name ='홍길동' and  address = '서울';

-- 홍길동 사원만 출력
select * from insarok where name ='홍길동' and jikwi ='사원';

--서울에 사는 모든 사람?
select * from insarok where address = '서울';

--서울에 살지 않는 모든 사람?
select * from insarok where address != '서울';
select * from insarok where address <> '서울'; /* 똑같음 != , <>  => ~가 아니다*/

-- 입사년도가 2000년 이전에 입사한 직원을 보여주시오?
select * from insarok where ipsail < '2000-1-1';

-- 입사년도가 2000년~2010년에 입사한 직원을 보여주시오?
select * from insarok where ipsail >= '2000-1-1' and ipsail <= '2010-1-1';
-- 앞의 범위 연산자 대신에 between~and 변경가능 
select * from insarok where ipsail between '2000-1-1' and '2010-1-1';

-- 30대 회사원 출력해 주세요
select * from insarok where age >=30 and age <= 39;
select * from insarok where age between 30 and 39;

-- 서울/부산에 사는 직원?
select * from insarok where address = '서울' or address = '부산';
-- 앞의 or 연산자는 in()으로 변경가능
select * from insarok where address in('서울','부산');

--서울/부산에 사는 사원만 출력
select * from insarok where  jikwi = '사원' and address in('서울','부산');

--이름이 '김'씨성인 사람 출력
select * from insarok where name like '홍%'; 

-- 나무로 끝나는 이름을 가진 직원 출력
select * from insarok where name like '%나무';

-- 이수연을 이재혁으로 이름 변경
update insarok set name ='이재혁' where name = '이수연';

select * from insarok;

--가나다를 가재다 로 바꿈
update insarok set name = '가나다' where name = '가재다';
update insarok set name = '재다라' where name = '라마바';

select * from insarok where name like '%재%';

-- 이름중에서 재란 글자를 포함한 직원의 직급을 과장으로 변경하시오.
update insarok set jikwi = '과장' where name like '%재%';
-- 이름중에서 재란 글자를 포함한 직원 중에서 서울에 사는 직원의 이름과 입사일과 주소
select name, ipsail, address from insarok where name like '%재%' and address= '서울';

-- 이름중에서 재란 글자를 포함한 직원 중에서 서울에 사는 직원중 나이가 40이상이면 퇴사시키시오.
delete from insarok where name like '%재%' and address= '서울' and age >= 40;

-- 이름 중 2번쨰 글자가 나인 직원은?
select * from insarok where name like '_나%';

-- 이름 오름차순으로 출력하시오.(순서 order by~~~, 오름차순 : asc(생략가능), 내림차순 desc 
select * from insarok order by name desc;

-- 남자인 자료 중에서 나이 오름차순으로 출력
select * from insarok where gender = '남자' order by age;

-- 남자인 자료 중에서 나이 오름차순으로, 같은나이면 입사일 내림차순 출력
select * from insarok where gender = '남자' order by age, ipsail desc;

-- 이재희의 성별 여자로 변걍
update insarok set gender ='여자' where name ='이재희';

-- 전체자료 중에서 5명만 출력하시오.(입력순서 중 앞에서 5)
select * from insarok limit 5;

-- 전체자료 중에서 5명만(나중에 입력하 회원) 출력하시오.
select * from insarok order by idx desc limit 5;

--남자회원 5명만 출력하시오.
select * from insarok where gender ='남자' order by age desc limit 5;

--남자 회원 중에서 앞에서 2명을 빼고 5명만 출력하시오.
select * from insarok where gender ='남자' order by idx limit 2, 5;

