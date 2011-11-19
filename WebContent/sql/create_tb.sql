/*create table*/
DROP TABLE IF EXISTS userBaseInfo cascade;

CREATE TABLE userBaseInfo(
	u_id		INT	NOT NULL auto_increment,		
	u_name		VARCHAR(255),
	u_email		VARCHAR(255),
	u_mood		VARCHAR(255),
	u_pwd		VARCHAR(255),
	u_state		VARCHAR(255),
	u_hometown	VARCHAR(255),
	u_job		VARCHAR(255),
	u_mobile	INT(11),
	u_avatar	MEDIUMBLOB,
	PRIMARY		KEY(u_id)
);






