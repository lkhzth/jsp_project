drop table REPLY_TB; 
    
create table REPLY_TB(
    rno int primary key, -- ��� ��ȣ
    bno int not null, -- �Խù� ��ȣ
    reply nvarchar2(1000) not null, -- ��� ����
    writer varchar2(50) not null, -- ��� �ۼ���
    replyDate date default sysdate, -- ��� �ۼ� �ð�
    modifyDate date default sysdate, -- ��� ���� �ð�
    constraint fk_reply_board foreign key(bno) -- ��������
    references board_tb(bno) on delete cascade,
    constraint fk_reply_board foreign key(mno) -- ��������
    references reserve_TB(mno) on delete cascade
);

drop sequence seq_reply; 
create sequence seq_reply; 


insert into REPLY_TB (rno, bno, reply, writer)
values(seq_reply.nextval, 1, '��۳����Դϴ�.01', 'tester');
insert into REPLY_TB (rno, bno, reply, writer)
values(seq_reply.nextval, 1, '��۳����Դϴ�.02', 'tester');

commit;
select * from REPLY_TB;

