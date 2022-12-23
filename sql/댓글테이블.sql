drop table REPLY_TB; 
    
create table REPLY_TB(
    rno int primary key, -- 댓글 번호
    bno int not null, -- 게시물 번호
    reply nvarchar2(1000) not null, -- 댓글 내용
    writer varchar2(50) not null, -- 댓글 작성자
    replyDate date default sysdate, -- 댓글 작성 시간
    modifyDate date default sysdate, -- 댓글 수정 시간
    constraint fk_reply_board foreign key(bno) -- 제약조건
    references board_tb(bno) on delete cascade,
    constraint fk_reply_board foreign key(mno) -- 제약조건
    references reserve_TB(mno) on delete cascade
);

drop sequence seq_reply; 
create sequence seq_reply; 


insert into REPLY_TB (rno, bno, reply, writer)
values(seq_reply.nextval, 1, '댓글내용입니다.01', 'tester');
insert into REPLY_TB (rno, bno, reply, writer)
values(seq_reply.nextval, 1, '댓글내용입니다.02', 'tester');

commit;
select * from REPLY_TB;

