create database hospital;

CREATE TABLE patients(id BIGINT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(50),age INT,gender VARCHAR(20),mobile BIGINT,email VARCHAR(100),disease VARCHAR(100),password VARCHAR(50));
Query OK, 0 rows affected (0.08 sec)

 CREATE TABLE doctors(id BIGINT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(50),specialization VARCHAR(50),experience INT,mobile BIGINT,password VARCHAR(50));