#DROP Table booking;
CREATE TABLE booking (
    id int(11) auto_increment,
    user_id int(11),
    order_date DATETIME,
    total_amount decimal(10,2),
	PRIMARY KEY (id)
);
