DROP DATABASE IF EXISTS lbs_server;

drop user lbs_user@localhost;

CREATE DATABASE IF NOT EXISTS lbs_server default charset utf8 COLLATE utf8_general_ci;

use lbs_server;

grant all privileges on lbs_server.* to lbs_user@localhost identified by 'lbs_user_psd'; 