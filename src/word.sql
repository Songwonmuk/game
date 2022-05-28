-- 5월 27일 42기 MyBatis 주말 과제
-- 객체 작성 쿼리를 아래에 작성하시오
DROP SEQUENCE japanword_seq;
DROP TABLE japanword;

CREATE TABLE japanword
(
    seq NUMBER PRIMARY KEY,--일련번호
    word VARCHAR2(200) NOT NULL, --단어
    mean VARCHAR2(300) NOT NULL, --뜻
    memorize CHAR(1) DEFAULT 0 CHECK(memorize in(0,1)) --암기여부 0.암기못함, 1.암기함
);

CREATE SEQUENCE japanword_seq;
