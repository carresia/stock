CREATE DATABASE IF NOT EXISTS stock;

CREATE TABLE IF NOT EXISTS `stock_info`(
   `transaction_id` INT UNSIGNED AUTO_INCREMENT,
   `trade_id` INT NOT NULL,
   `version` INT NOT NULL,
   `security_code` VARCHAR(10) NOT NULL,
   `quantity` INT NOT NULL,
   `db_operator` ENUM('INSERT', 'UPDATE', 'CANCEL') NOT NULL,
   `user_operator` ENUM('BUY', 'SELL') NOT NULL,
   PRIMARY KEY ( `transaction_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into stock_info (trade_id,version,security_code,quantity,db_operator,user_operator) values (1,1,'REL',50,'INSERT','BUY');
insert into stock_info (trade_id,version,security_code,quantity,db_operator,user_operator) values (2,1,'ITC',40,'INSERT','SELL');
insert into stock_info (trade_id,version,security_code,quantity,db_operator,user_operator) values (3,1,'INF',70,'INSERT','BUY');
insert into stock_info (trade_id,version,security_code,quantity,db_operator,user_operator) values (1,2,'REL',60,'UPDATE','BUY');
insert into stock_info (trade_id,version,security_code,quantity,db_operator,user_operator) values (2,2,'ITC',30,'CANCEL','BUY');
insert into stock_info (trade_id,version,security_code,quantity,db_operator,user_operator) values (4,1,'INF',20,'INSERT','SELL');