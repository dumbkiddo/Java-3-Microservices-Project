#DROP Table user;
CREATE TABLE user (
    id int(11) auto_increment,
    email varchar(45),
    mobile varchar(10),
    password varchar(500),
    username varchar(45),
    PRIMARY KEY (id)
);


