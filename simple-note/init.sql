CREATE DATABASE IF NOT EXISTS simple_note DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
CREATE USER 'simple_note'@'localhost' IDENTIFIED BY '123456';
GRANT ALL ON simple_note.* TO 'simple_note'@'localhost';