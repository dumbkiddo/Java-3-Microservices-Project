#CREATE DATABASE IF NOT EXISTS moviesinfo;
USE moviesinfo;

CREATE TABLE IF NOT EXISTS movies (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS synopsis (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS movie_synopsis (
  movie_id INT(4) UNSIGNED NOT NULL,
  synopsis_id INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (movie_id) REFERENCES movies(id),
  FOREIGN KEY (synopsis_id) REFERENCES synopsis(id),
  UNIQUE (movie_id,synopsis_id)
) engine=InnoDB;
