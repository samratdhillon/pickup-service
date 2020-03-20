DROP DATABASE IF EXISTS event_service;


CREATE DATABASE event_service CHARACTER SET utf8mb4;

CREATE USER 'user'@'localhost' identified by 'password';

GRANT ALL  ON event_service.* TO 'user'@'%';
