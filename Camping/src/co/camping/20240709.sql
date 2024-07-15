--학생정보(학생번호, 이름, 연락처, 주소, 생년월일)
--tbl_student 테이블명.
create table student(
    std_no varchar2(10) primary key,
    std_name varchar2(100) not null,
    std_phone varchar2(20), -- 010-1111-2222
    address varchar2(100),
    birth_date date,
    creation_date date default sysdate
);

-- sample data.
insert into student (std_no, std_name, std_phone)
values('S2024-01', '홍길동', '010-1234-5678');
insert into student (std_no, std_name, std_phone, address)
values('S2024-02', '김길동', '010-3333-4444', '서울 100번지');

select *
from student;

update student
set address = '대전 100'
where std_no = 's2024-01';

delete from student
where std_no = 'S2024-01';


--생일, 주소 변경
update student
set birth_date = to_date('19940621', 'yyyy-mm-dd')
    ,std_no = 'S2024-03',
    -- address = address
where address = '대전 100';

--update student
--set std_name = '변경값'
--    ,std_phone = '변경값'
--where std_no = 'S2024-02';


rollback;


insert into student (std_no, std_name, std_phone)
values('S2024-04', '김민규', '010-2222-5678');

commit;

-- std_no,