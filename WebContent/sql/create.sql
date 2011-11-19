/*create table*/
DROP TABLE IF EXISTS visit cascade;
DROP TABLE IF EXISTS p_comment cascade;
DROP TABLE IF EXISTS comment cascade;
DROP TABLE IF EXISTS photo cascade;
DROP TABLE IF EXISTS album cascade;
DROP TABLE IF EXISTS diary cascade;
DROP TABLE IF EXISTS friend cascade;
DROP TABLE IF EXISTS head cascade;
DROP TABLE IF EXISTS user cascade;
DROP TABLE IF EXISTS max cascade;

CREATE TABLE user(
	u_no		INT	NOT NULL auto_increment,		
	u_pwd		VARCHAR(16)	NOT NULL,
	u_name		VARCHAR(8),
	u_email		VARCHAR(18),
	u_state		TINYTEXT,
	PRIMARY		KEY(u_no)
);

CREATE TABLE authority(
	u_no		INT	NOT NULL auto_increment,		
	auth_type	VARCHAR(16)	NOT NULL
	FOREIGN 	KEY(u_no) REFERENCES user(u_no)
);

CREATE TABLE head(
	h_id	 	INT NOT NULL auto_increment,
	h_des	 	VARCHAR(40) NOT NULL,
	h_data 		MEDIUMBLOB NOT NULL,
	u_no		INT NOT NULL,
	PRIMARY	 	KEY(h_id),
	FOREIGN 	KEY(u_no) REFERENCES heuserad(u_no)
);

CREATE TABLE friend
(
	f_id		INT NOT NULL auto_increment,
	u_noz		INT NOT NULL,
	u_noy 		INT NOT NULL,
	f_date 		TIMESTAMP,
	PRIMARY 	KEY(f_id),
	FOREIGN 	KEY(u_noz) REFERENCES user(u_no),
	FOREIGN 	KEY(u_noy) REFERENCES user(u_no)
);

CREATE TABLE diary(
	r_id 		INT NOT NULL auto_increment,
	r_title		VARCHAR(18) NOT NULL,
	r_content 	TEXT NOT NULL,
	r_date 		TIMESTAMP,
	u_no 		INT NOT NULL,
	PRIMARY 	KEY(r_id),
	FOREIGN 	KEY(u_no) REFERENCES user(u_no)
);

CREATE TABLE album(
	x_id 		INT NOT NULL auto_increment,
	x_name 		VARCHAR(18) NOT NULL,
	u_no 		INT NOT NULL,
	x_access 	INT DEFAULT 0,						
	x_date 		TIMESTAMP,
	PRIMARY 	KEY(x_id),
	FOREIGN 	KEY(u_no) REFERENCES user(u_no)
);

CREATE TABLE photo(
	p_id 		INT NOT NULL auto_increment,
	p_name 		VARCHAR(18) NOT NULL,
	p_des		VARCHAR(50) NOT NULL,
	p_data 		MEDIUMBLOB,
	x_id 		INT NOT NULL,
	PRIMARY 	KEY(p_id),
	FOREIGN 	KEY(x_id) REFERENCES album(x_id)
);

CREATE TABLE comment(
	c_id 			INT NOT NULL auto_increment,
	c_content		TEXT NOT NULL,
	u_no 			INT NOT NULL,
	r_id 			INT NOT NULL,
	c_date 			TIMESTAMP,
	PRIMARY 		KEY(c_id),
	FOREIGN 		KEY(u_no) REFERENCES user(u_no),
	FOREIGN 		KEY(r_id) REFERENCES diary(r_id)
);

CREATE TABLE p_comment(
	c_id			INT NOT NULL auto_increment,
	c_content		TEXT NOT NULL,
	u_no			INT NOT NULL,
	p_id			INT NOT NULL,
	c_date			TIMESTAMP,
	PRIMARY 		KEY(c_id),
	FOREIGN			KEY(u_no) REFERENCES user(u_no),
	FOREIGN 		KEY(p_id) REFERENCES photo(p_id)
);

CREATE TABLE visit
(
	v_id 			INT NOT NULL auto_increment,
	u_no 			INT NOT NULL,
	v_no 			INT NOT NULL,
	v_date 			TIMESTAMP,
	PRIMARY 		KEY(v_id),
	FOREIGN 		KEY(u_no) REFERENCES user(u_no),
	FOREIGN 		KEY(v_no) REFERENCES user(u_no)
);

CREATE TABLE max (
  `friend_max` int(11) NOT NULL DEFAULT '0',
  `diary_max` int(11) NOT NULL DEFAULT '0',
  `album_max` int(11) NOT NULL DEFAULT '0',
  `photo_max` int(11) NOT NULL DEFAULT '0',
  `comment_max` int(11) NOT NULL DEFAULT '0',
  `visit_max` int(11) NOT NULL DEFAULT '0',
  `user_max` int(11) NOT NULL DEFAULT '0',
  `head_max` int(11) NOT NULL DEFAULT '0',
  `p_comment_max` int(11) NOT NULL DEFAULT '0'
);






