/*insert data */
INSERT INTO user(u_pwd,u_name,u_email,u_state) VALUES('123','张飞','fei@126.com','I am 张飞.');
INSERT INTO user(u_pwd,u_name,u_email,u_state) VALUES('123','Tom','www@126.com','I am sad.');
INSERT INTO user(u_pwd,u_name,u_email,u_state) VALUES('123','Jerry','www@126.com','I am busy.');

insert into friend(u_noz,u_noy) VALUES(1,2);
insert into friend(u_noz,u_noy) VALUES(1,3);

INSERT INTO diary(r_title,r_content,u_no) VALUES('工作日志1','今天天气不好，阴沉沉的。',1);
INSERT INTO diary(r_title,r_content,u_no) VALUES('工作日志2','今天好冷。',1);
INSERT INTO diary(r_title,r_content,u_no) VALUES('旅游日志','今天去看大海了。',3);

INSERT INTO album(x_name,u_no) VALUES('正定游',1);
INSERT INTO album(x_name,u_no) VALUES('我的家人',2);

INSERT INTO comment(c_content,u_no,r_id) VALUES('你写的日志太好了，我很稀饭哦',2,1);

INSERT INTO visit(u_no,v_no) VALUES(1,2);

commit;