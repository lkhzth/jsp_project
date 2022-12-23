DROP TABLE reserve_TB;

CREATE TABLE reserve_TB(
    MNO INT PRIMARY KEY,
    NAME VARCHAR2(50),
    PHONE VARCHAR2(50),
    CONTENT VARCHAR2(4000),
    JOINDATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE MNO_SEQ;
DROP SEQUENCE MNO_SEQ;

INSERT INTO reserve_TB(MNO,NAME,PHONE,CONTENT)
VALUES(MNO_SEQ.NEXTVAL,'이창우','010-1111-1111','내용');

select * from reserve_TB;

COMMIT;

ALTER TABLE reserve_TB ADD REPLYCOUNT INT;