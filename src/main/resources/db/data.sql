insert into user_tb(username, password, email, address, detail_address, tel, real_name, career, resume_title, resume_content, created_at) values('ssar', '1234', 'ssar@nate.com', '부산시 야호구 야동','11길','01023452344','김살',1,'이력서제목1','이력서내용1', now());

insert into company_tb(username, password, email, address, detail_address, tel, company_name, company_scale, company_numb, company_field, created_at) values('cos', '1234','cos@nate.com', '부산시 빡구 동동', '71길', '01012341234', '(주)미친회사', '대기업', 333322221111,' IT업', now());

insert into board_tb(company_id, title, content, career, created_at) values(1, '공고제목1','공고내용1', 1, now());
insert into board_tb(company_id, title, content, career, created_at) values(1, '공고제목2','공고내용2', 3, now());
insert into board_tb(company_id, title, content, career, created_at) values(1, '공고제목3','공고내용3', 6, now());
insert into board_tb(company_id, title, content, career, created_at) values(1, '공고제목4','공고내용4', -1, now());

commit; 