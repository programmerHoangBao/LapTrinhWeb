CREATE DATABASE demo_login_sign_up;
USE demo_login_sign_up;

CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username CHAR(30) NOT NULL UNIQUE,
    password CHAR(15) NOT NULL
);

INSERT INTO accounts(username, password) VALUES('bao', '123')