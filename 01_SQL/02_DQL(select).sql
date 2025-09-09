

/*
    테이블
    -데이터베이스에서 데이터를 저장하는 기본 개념
    -행과 열로 구성된 데이터 집합
    
    컬럼
    -테이블 내의 각 데이터 속성을 정의하는 필드
    -컬럼은 테이블에 저장할 때 속성 = 값으로 저장
    
    => 테이블은 여러 컬럼으로 구성되고, 각 컬럼은 테이블이 표현하는 테이터의 세부적인 속성을 나타냄.
    
    <select>
    select 컬럼명1, 컬럼명2, ...
    from 테이블명
    [where 조건]
    [order by 정렬기준 [ASC | DESC]]
    
*/

--1. JOB 테이블의 모든 정보 조회
select * from job;

--2. JOB 테이블의 직급 이름만 조회
select job_name 
from job;

--3. DEPARTMENT 테이블의 모든 정보 조회
select * from department;

--4. EMPLOYEE 테이블의 직원명, 이메일, 전화번호, 고용일 조회
select emp_name, email, phone, hire_date
from employee;

--5. EMPLOYEE 테이블에서 이름, 연봉, 총수령액(보너스 포함), 실수령액(총수령액-(연봉*세금(3%))) 조회
select emp_name as 이름 , salary*12 as "연봉", (salary + (salary*nvl(bonus, 0)))*12 as "총수령액",
    (salary + (salary*nvl(bonus, 0)))*12-(salary*12*0.03) as "실수령액"
from employee;
--데이터베이스에서 NULL은 빈값을 의미한다.
--모든 연산에 NULL이 포함된 경우, 결과는 NULL이 된다.

--사원명, 입사일, 근무일수를 EMPLOYEE 테이블에서 조회
--데이터베이스에서는 날짜를 계산할 때 덧셈 뺄셈이 가능하다.
--DATE-DATE => 결과를 무조건 일로 표시한다.
--코드 실행시 현재날짜를 표시하는 상수 :sysdate[년/월/일/시/분/초]
select emp_name, hire_date, sysdate-hire_date
from employee;

--DUAL 테이블은 오라클에서 제공하는 가상 테이블이다.
select sysdate from dual;

/*
    <컬럼 별칭>
    컬럼명에 별칭을 부여하면 깔끔하게 표현할 수 있다.
    [표현식]
    컬럼명 별칭 / 컬럼명 as 별칭 / 컬럼명 "별칭" / 컬럼명 as "별칭"
*/

select emp_name 사원명, salary as 급여, bonus "보너스", salary*12 as "연봉"
from employee;

/*
    <리터널>
    직접 값을 나타내는 단위, 임의로 지정한 값 
*/

select emp_id, emp_name, salary, '원'
from employee;

/*
    <연결 연산자: ||>
    여러 컬럼값들을 마치 하나의 컬럼처럼 연결할 수 있음.
*/
select emp_name || '님 급여는'|| salary || '원'
from employee;

/*
    <DISTINCT>
    중복제거 - 컬럼에 표시된 값들을 한번씩만 조회하고자 할 때 사용
*/

--실제로 사용되고 있는 직급목록
select distinct job_code
from employee;

--실제로 사용되고 있는 부서목록
select distinct dept_code
from employee;

--select distinct emp_name, distinct job_code, distinct dept_code
--from employee;
--위처럼 사용시 에러 발생. distinct는 한 명령어에서 한번만 사용가능
--하나의 컬럼만 중복제거 불가능.  모든 컬럼에 대해서 중복을 제거한다

--DISTINCT는 항상 ROW데이터 전체에 대해서 중복을 제거한다.
select distinct  job_code, dept_code
from employee;
--위처럼 사용시 (job_code, dept_code)를 쌍으로 묶어서 중복을 제거한 값을 보여준다.

============================================================================
/*
    <WHERE 절>
    조회하고자하는 테이블로부터 특정 조건에 만족하는 데이터만 조회하고자 할 때 사용함.
    조건식에서도 다양한 연산자를 사용할 수 있음.
    
    [표현법]
    select 컬럼, 컬럼, ...
    from 테이블명
    where 조건;
    
    >>비교연산자<<
    >, <, >=, <= : 대소비교
    + : 양쪽이 동일하다.
    !=, ^=, <>: 양쪽이 다르다.
*/
 --EMPLOYEE 테이블에서 부서코드가 D9인 사원들만 조회(모든컬럼)
 select *
 from employee
 where dept_code = 'D9';
 
 --EMPLOYEE 테이블에서 부서코드가 'D1'인 사원들의 사원명, 급여, 부서코드 조회
 select emp_name, salary, dept_code
 from employee
 where dept_code = 'D1';

 --EMPLOYEE 테이블에서 부서코드가 'D1'이 아닌 사원들의 사원명, 급여, 부서코드 조회
 select emp_name, salary, dept_code
 from employee
 where dept_code != 'D1';
 
 --월급이 400만원 이상인 사원들의 사원명, 부서코드, 급여 조회
 select emp_name, dept_code, salary
 from employee
 where salary >= 4000000;
 
 /*
    <AND, OR 연산자>
    조건을 여러개 연결할 때 사용.
    [표현법]
    조건A and 조건B ->조건A와 조건B 모두 만족하는 값만 참으로 한다.
    조건A or 조건B ->조건A와 조건B 중 하나만 만족해도 참으로 한다.
    
    <BETWEEN AND>
    조건식에 사용되는 구문
    몇이상 몇이하인 범위에 대한 조건을 제시할 때 주로 사용하는 연산자(이상, 이하만 가능)
    [표현법]
    비교대상 컬럼 between 하안값 and 상안값;
*/

--급여가 350만원 이상 600만원 이하인 모든 사원의 사원명, 사번, 급여 조회
select emp_name, emp_id, salary
from employee
--where salary >= 3500000 and salary <= 6000000;
where salary between 3500000 and 6000000;

--not: 논리부정 연산자
--컬럼명앞에 또는 between앞에 선언 가능

select emp_name, emp_id, salary
from employee
where not salary between 3500000 and 6000000;

--입사일이 '90/01/01'이상 '01/01/01'이하인 사원들을 전체 조회
select *
from employee
--where hire_date >= '90/01/01' and hire_date <= '01/01/01';
where hire_date between '90/01/01' and '01/01/01';

--NULL을 비교연산자할 때 =을 사용할 수 없다.
--NULL값을 비교할 때는 is null, is not null을 사용한다.
select *
from employee
--where bonus is null;
where bonus is not null;

-------------------------------------------------------------------------------

/*
    <LIKE>
    비교하고자하는 컬럼값이 내가 제시한 특정 패턴에 만족할 경우 조회
    
    [표현법]
    비교한 대상컬럼 LIKE '특정패턴';
    
    특정패턴을 제시할 때 와일드카드라는 특정패턴이 정의되어있다.
    1. '%': 포함문자 검색(0글자 이상 전부 조회)
    EX) 비교할 대상 컬럼 LIKE '문자%' : 비교할 대상컬럼 값 준에서 해당문자로 시작하는 값을 전부 조회
        비교할 대상 컬럼 LIKE '%문자' : 비교할 대상컬럼 값 준에서 해당문자로 끝나는 값을 전부 조회
        비교할 대상 컬럼 LIKE '%문자%' : 비교할 대상컬럼 값 준에서 해당문자가 포함된 값을 전부 조회
        
    2.'_': 1글자를 대체검색할 때 사용
    EX) 비교할 대상 컬럼 LIKE '_문자' : 비교할 대상컬럼 값 문자앞에 아무글자나 딱 한글자가 있는 값을 조회
        비교할 대상 컬럼 LIKE '문자_' : 비교할 대상컬럼 값 문자뒤에 아무글자나 딱 한글자가 있는 값을 조회
        비교할 대상 컬럼 LIKE '_문자_' : 비교할 대상컬럼 값 문자앞뒤에 아무글자나 딱 한글자가 있는 값을 조회
        
        비교할 대상 컬럼 LIKE '_문자__' : 내가 원하는 형태로 _를 통해서 문자수를 조절할 수 있다.
*/

select emp_name, salary
from employee
where emp_name like '전%';

select emp_name, salary
from employee
where emp_name like '%연';

select emp_name, salary
from employee
where emp_name like '%하%';

select emp_name, salary
from employee
where emp_name like '전__';

--사원들 중에서 전화번호 3번째자리가 1인 사원들의 사번, 사원명, 전화번호 조회
select emp_id, emp_name, phone
from employee
where phone like '__1%';

--이메일 중 앞의 글자가 3글자인 사원들의 사번, 이름, 이메일 조회
select emp_id, emp_name, email
from employee
--where email like '___%'; --> 와일드카드로 인식되기 때문에 정상적으로 출력할 수 없다.
--와일드카드를 직접 문자로 사용할 때는 일반문자로 구분을 해줘야한다.
--ESCAPE OPTION을 등록해서 나만의 탈출문자를 사용할 수 있음
where email like '__/_%' escape '/';

--------------------------------------------------------------------------------

/*
    <IN>
    where절에 비교대상 컬럼값을 가지고 내가 제시한 목록중에 일치하는지 값이 있는지 검사하는 문법
    
    [표현법]
    비교대상 컬럼 in(값, 값, 값, ...)
*/

--부서코드가 D6이거나 D8이거나 D5인 부서원들의 이름, 부서코드, 급여 조회
select emp_name, dept_code, salary
from employee
--where dept_code = 'D6' or dept_code = 'D8' or dept_code = 'D5' ;
where dept_code in ('D6', 'D8', 'D5');

/*
    <연산자 우선순위>
    1. 산술연산자
    2. 연결연산자
    3. 비교연산자
    4. IS NULL/ LIKE/ IN
    5. BETWEEN A AND B
    6. NOT
    7. AND
    8. OR
*/

-----------------------------실습--------------------------------------------
--1. 이름이 '연'으로 끝나는 사원들의 사원명, 입사일 조회
select emp_name, hire_date
from employee
where emp_name like '%연';

--2. 전화번호 처음 3자리가 010이 아닌 사원들의 사원명, 전화번호 조회
select emp_name, phone
from employee
where phone not like'010%';

--3. 이름에 '하'가 포함되어있고 급여가 240만원 이상인 사원들의 사원명, 급여 조회
select emp_name, salary
from employee
where emp_name like '%하%' and salary >= 2400000;

--4. 부서(department)테이블에서 해외영업부서인 부서들의 부서코드, 부서명 조회
select dept_id, dept_title
from department
where dept_title like '%해외영업%';

--5. 사수(manager)가 없고, 부서배치도 받지 않은 사원들의 사원명, 사번, 부서코드 조회
select emp_name, emp_id, dept_code
from employee
where manager_id is Null and dept_code is NULL;

--6. 연봉(급여*12)이 3천만원 이상이고 보너스를 받지 않은 사원들의 사번, 사원명, 급여, 보너스 조회
select emp_id, emp_name, salary, bonus
from employee
where salary*12 >= 30000000 and bonus is NULL;

--7. 입사일이 '95/01/01'이상이고 부서배치를 받지 않은 사원들의 사번, 사원명, 입사일, 부서코드 조회
select emp_id, emp_name, hire_date, dept_code
from employee
where hire_date >= '95/01/01' and dept_code is NULL;

--8. 급여가 200만원 이상이고 500만원 이하인 사원 중에서 입사일이 '01/01/01'이상이고 보너스를 받지 않는
--   사원들의 사번, 사원명, 입사일, 보너스 조회
select emp_id, emp_name, hire_date, bonus
from employee
where (salary between 2000000 and 5000000) and (hire_date >= '01/01/01') and bonus is NULL;

--9. 보너스를 포함 연봉((salary + (salary * Bonus)) *12)가 NULL이 아니고 이름에 '하'가 포함된 사원들의
--   사번, 사원명, 급여, 보너스포함 연봉 조회
select emp_id, emp_name, salary, (salary + (salary * Bonus)) *12
from employee
where ((salary + (salary * Bonus)) *12 is not Null) and emp_name like '%하%';

/*
    <ORDER BY절>
    데이터를 정렬해서 조회하기위한 구문
    select문 가장 마지막 줄에 작성, 실행순서 또한 가장 마지막에 실행이 된다.
    
    [표현법]
    select 조회할컬럼...
    from 테이블
    where 조건식
    order by 절렬기분이 될 컬럼 | 별칭 | 컬럼순번 [ASC | DESC] [NULLS FIRST | NULLS LAST]
    
    -ASC : 오름차순(작은값으로 시작해서 점점 커지는 것) -> 기본값
    -DESC : 내림차순(큰 값으로 시작해서 값이 점점 줄어드는 것)
    
    -NULL은 기본적으로 가장 큰 값으로 분류해서 정렬한다.
*/

select *
from employee
--order by bonus;
--order by bonus nulls first;
order by bonus desc, salary;
--정렬기준에 컬럼값이 동일할 경우 그 다음차순을 위해서 여러개의 기준 컬럼을 제시할 수 있다.

--전 사원의 사원명, 연봉 조회(이 때 연봉을 기준으로 내림차순 정렬)
select emp_name, salary*12 as "연봉"
from employee
--order by salary *12 desc;
--order by 연봉 desc;
order by 2 desc;