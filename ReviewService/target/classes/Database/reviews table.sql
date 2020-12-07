#CREATE DATABASE IF NOT EXISTS reviewsinfo;

USE usersinfo;

CREATE TABLE IF NOT EXISTS reviews (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  booking_id INT(4) UNSIGNED NOT NULL,
  review_date DATE,
  description VARCHAR(8192),
  FOREIGN KEY (booking_id) REFERENCES bookings(id)
) engine=InnoDB;
