-- boards 테이블 데이터
--insert into boards(title,content,writer, category) values('제목1', '내용1', '홍길동', 1);
--insert into boards(title,content,writer, category) values('제목2', '내용2', '임꺽정', 1);
--insert into boards(title,content,writer, category) values('제목3', '내용3', '유관순', 2);
--insert into boards(title,content,writer, category) values('제목4', '내용4', '안중근', 2);
--insert into boards(title,content,writer, category) values('제목5', '내용5', '윤봉길', 2);

insert into boards(
  board_no,
  title,
  content,
  writer,
  category)
  values(1, '제목1', '내용1', '홍길동', 1);
insert into boards(board_no,title,content,writer, category)
  values(2, '제목2', '내용2', '임꺽정', 1);
insert into boards(board_no,title,content,writer, category)
  values(3, '제목3', '내용3', '유관순', 2);
insert into boards(board_no,title,content,writer, category)
  values(4, '제목4', '내용4', '안중근', 2);
insert into boards(board_no,title,content,writer, category)
  values(5, '제목5', '내용5', '윤봉길', 2);

--  board_files 테이블 데이터
insert into board_files(file_no,file_path,board_no) values
  (1, 'a1.gif', 1), (2, 'a2.gif', 1), (3, 'a3.gif', 1),
  (4, 'b1.gif', 2), (5, 'b2.gif', 2),
  (6, 'c1.gif', 3), (7, 'c2.gif', 3), (8, 'c3.gif', 4), (9, 'c4.gif', 4),
  (10, 'd1.gif', 5);


-- assignments 테이블 데이터
insert into assignments(
  assignment_no,
  title,
  content,
  deadline)
  values(1, '제목1', '내용1', '2024-05-22');
insert into assignments(assignment_no,title,content,deadline)
  values(2, '제목2', '내용2', '2024-05-22');
insert into assignments(assignment_no,title,content,deadline)
  values(3, '제목3', '내용3', '2024-05-22');
insert into assignments(assignment_no,title,content,deadline)
  values(4, '제목4', '내용4', '2024-05-22');
insert into assignments(assignment_no,title,content,deadline)
  values(5, '제목5', '내용5', '2024-05-22');

-- members 테이블 데이터

insert into members(member_no,email,name,password)
  values(1,'aa@bit.com','홍일동',sha2('aa',256));
insert into members(member_no,email,name,password)
  values(2,'bb@bit.com','홍이동',sha2('bb',256));
insert into members(member_no,email,name,password)
  values(3,'cc@bit.com','홍삼동',sha2('cc',256));
insert into members(member_no,email,name,password)
  values(4,'dd@bit.com','홍사동',sha2('dd',256));
insert into members(member_no,email,name,password)
  values(5,'ee@bit.com','홍오동',sha2('ee',256));