INSERT INTO user_tb (username, password, email, created_at) VALUES ('ssar', '1234', 'ssar@nate.com', NOW());
INSERT INTO user_tb (username, password, email, created_at) VALUES ('cos', '1234', 'cos@nate.com', NOW());
INSERT INTO user_tb (username, password, email, created_at) VALUES ('love', '1234', 'love@nate.com', NOW());


INSERT INTO board_tb (title, content, user_id, created_at) VALUES ('제목1', '내용1', 1, NOW());
INSERT INTO board_tb (title, content, user_id, created_at) VALUES ('제목2', '내용2', 1, NOW());
INSERT INTO board_tb (title, content, user_id, created_at) VALUES ('제목3', '내용3', 2, NOW());
INSERT INTO board_tb (title, content, user_id, created_at) VALUES ('제목4', '내용4', 3, NOW());

insert into reply_tb(comment, board_id, user_id, created_at) values('댓글1', 4, 1, now());
insert into reply_tb(comment, board_id, user_id, created_at) values('댓글2', 4, 1, now());
insert into reply_tb(comment, board_id, user_id, created_at) values('댓글3', 4, 2, now());
insert into reply_tb(comment, board_id, user_id, created_at) values('댓글4', 3, 2, now());