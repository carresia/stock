DROP TABLE IF EXISTS stock_info;

CREATE TABLE stock_info (
                              transaction_id INT AUTO_INCREMENT PRIMARY KEY,
                              trade_id INT NOT NULL,
                              version INT NOT NULL,
                              security_code VARCHAR(10) NOT NULL,
                              quantity INT NOT NULL,
                              db_operator ENUM('INSERT', 'UPDATE', 'CANCEL') NOT NULL,
                              user_operator ENUM('BUY', 'SELL') NOT NULL
);

INSERT INTO stock_info (trade_id, version, security_code, quantity, db_operator, user_operator) VALUES
(1,1,'REL',50,'INSERT','BUY'),
(2,1,'ITC',40,'INSERT','SELL'),
(3,1,'INF',70,'INSERT','BUY'),
(1,2,'REL',60,'UPDATE','BUY'),
(2,2,'ITC',30,'CANCEL','BUY'),
(4,1,'INF',20,'INSERT','SELL');