/*
    <시퀀스 SEAUENCE>
    자동으로 숫자를 발생시켜주는 역할을 하는 객체.
    주로 키본키로 사용되는 회원번호, 사원번호, 게시글번호, 택배운송장번호...등에 활용됨.
    시퀀스를 사용하면 중복 없는 숫자값을 편리하게 관리할 수 있음.
*/

/*
    1. 시퀀스 객체 생성
    
    [표현식]
    CREATE SEQUENCE 시퀀스명
    [START WITH 시작숫자] -> 처음 발생시킬 시작값을 지정(기본값1)
    [INCREMENT BY 증가값] -> 증가값을 지정할 수 있음(기본값1)
    [MAXVALUE 최대값] -> 최대값 지정(기본값 매우큼)
    [MINVALUE 최소값] -> 최소값 지정(기본값1)
    [CYCLE | NOCYCLE] -> 순환여부(NOCYCLE)
    [CACHE | NOCACHE] -> 캐시메모리 사용 여부
    
    *캐시메모리: 미리 일정량의 값들
    예: CACHE 20 -> 시퀀스를 미리 20개 만들어두고 필요할 때 꺼내 씀
*/

CREATE SEQUENCE SEQ_TEST;

SELECT * FROM user_sequences;
