--1. 춘 기술대학교의 학과 이름과 계열을 표시하시오. 단, 출력 헤더는 "학과 명", "계열"으로 표시하도록 한다.
SELECT DEPARTMENT_NAME AS "학과 명", CATEGORY AS "계열"
FROM TB_DEPARTMENT;

--2. 학과의 학과 정원을 다음과 같은 형태로 화면에 출력하도록 한다.
SELECT SUBSTR(DEPARTMENT_NAME,1 ) || '의 정원은 ' || SUBSTR(CAPACITY,1) ||'입니다'
FROM TB_DEPARTMENT;

--3. "국어국문학과"에 다니는 여학생 중 현재 휴학중인 여학생을 찾아 조회.
--(국문학과의 학과코드는 학과 테이블(TB_DEPARTMENT)을 조회해서 찾는다)
SELECT STUDENT_NAME
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NO = (SELECT DEPARTMENT_NO
                        FROM TB_DEPARTMENT
                        WHERE DEPARTMENT_NAME = '국어국문학과')
AND ABSENCE_YN = 'Y'
AND SUBSTR(STUDENT_SSN,8,1) IN('2','4');

--4. 도서관에서 대출 도서 장기 연체자 들을 찾아 이름을 게시하고자 한다.
--그 대상자들 학번은 A513079, A513090, A513110, A513119 이다
SELECT STUDENT_NAME
FROM TB_STUDENT
WHERE STUDENT_NO IN ('A513079', 'A513090', 'A513110', 'A513119');

--5. 입학정원이 20명 이상 30명 이하인 학과들의 학과 이름과 계열을 출력
SELECT DEPARTMENT_NAME, CATEGORY
FROM TB_DEPARTMENT
WHERE CAPACITY BETWEEN 20 AND 30;

--6. 춘 기술대학교는 총장을 제외하고 모든 교수들이 소속 학과를 가지고있다. 
--춘 기술대학교 총장의 이름을 출력
SELECT PROFESSOR_NAME
FROM TB_PROFESSOR
WHERE DEPARTMENT_NO IS NULL;

--7. 혹시 전산상의 착오로 학과가 지정되어 있지 않은 학생이 있는지 확인하고자 한다. SQL을 작성하시오.
SELECT STUDENT_NAME
FROM TB_STUDENT
WHERE DEPARTMENT_NO IS NULL;

--8. 수강신청을 하려고 한다. 선수과목 여부를 확인해야하는데, 선수과목이 존재하는 과목들은 어떤 과목들인지
--과목번호를 조회해라


--9. 춘 대학에는 어떤 계열들이 있는지 조회하기
SELECT DISTINCT CATEGORY
FROM TB_DEPARTMENT;

--10. 02학번 전주 거주자들의 모임을 만들려고 한다. 휴학한 사람들은 제회한 재학중인 학생들의
--학번, 이름, 주민번호를 출력하시오
SELECT STUDENT_NO, STUDENT_NAME, STUDENT_SSN
FROM TB_STUDENT
WHERE SUBSTR(ENTRANCE_DATE,1,2) = '02'
AND ABSENCE_YN = 'N';

--------------------------------------------------------------------------------------------
/*SELECT-함수*/

--1.영어영문학과(학과코드 002) 학생들의 학번과 이름, 입학 년도를 입학 년도가 빠른 순으로 표시하는 SQL 작성
--헤더는 '학번' '이름' '입학년도'로 표시
SELECT STUDENT_NO, STUDENT_NAME, ENTRANCE_DATE
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NO = '002'
ORDER BY ENTRANCE_DATE;

--2. 춘 기술대학교의 교수 중 이름이 세 글자가 아닌 교수가 한 명 있다고 한다. 
--그 교수의 이름과 주민번호를 화면에 출력하는 SQL문장을 작성하다
SELECT PROFESSOR_NAME, PROFESSOR_SSN
FROM TB_PROFESSOR
WHERE LENGTH(PROFESSOR_NAME) != 3;

--나이가 -로 출력됨.
--3.춘 기술대학교의 남자 교수들의 이름과 나이를 출력하는 SQL문장 작성.
--나이가 적은 사람에서 많은 사람 순으로 출력
--단, 교수 중 2000년 이후 출생자는 없으며 출력 헤더는 '교수이름', '나이'로 한다. 
--나이는 만으로 계산
SELECT PROFESSOR_NAME AS "교수이름", 
    FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(PROFESSOR_SSN, 1, 6),'RRMMDD')) / 12) AS "나이"
FROM TB_PROFESSOR
WHERE SUBSTR(PROFESSOR_SSN,8,1) ='1'
ORDER BY 나이 ;

--4. 교수들의 이름 중 성을 제외한 이름만 출력하는 SQL문장 작성.
--출력 헤어는 "이름"이 찍히도록 한다.(성이2글자인 교수는 없다고 가정)
SELECT SUBSTR(PROFESSOR_NAME,2) AS "이름"
FROM TB_PROFESSOR;

--5. 춘 기술대학교의 재수생 입학자를 구하려고 한다. 어떻게 찾아낼 것인가?
--이때 19살에 입학하면 재수를 하지 않은 것으로 간주한다.
SELECT STUDENT_NO, STUDENT_NAME
FROM TB_STUDENT
WHERE (FLOOR(MONTHS_BETWEEN(SYSDATE, TO_DATE(SUBSTR(STUDENT_SSN, 1, 6),'RRMMDD')) / 12)) >19;

--6. 2020년 크리스마스는 무슨 요일인지 조회
SELECT TO_CHAR(TO_DATE('20201225', 'YYYYMMDD'), 'DAY')
FROM DUAL;

--7. TO_DATE('99/18/11' 'YY/MM/DD'), TO_DATE('49/10/11', '
 