-- 5�� 27�� 42�� MyBatis �ָ� ����
-- ��ü �ۼ� ������ �Ʒ��� �ۼ��Ͻÿ�
DROP SEQUENCE japanword_seq;
DROP TABLE japanword;

CREATE TABLE japanword
(
    seq NUMBER PRIMARY KEY,--�Ϸù�ȣ
    word VARCHAR2(200) NOT NULL, --�ܾ�
    mean VARCHAR2(300) NOT NULL, --��
    memorize CHAR(1) DEFAULT 0 CHECK(memorize in(0,1)) --�ϱ⿩�� 0.�ϱ����, 1.�ϱ���
);

CREATE SEQUENCE japanword_seq;
