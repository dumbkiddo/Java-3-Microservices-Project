#DROP Table movie, genre, director;
CREATE TABLE movie (
    id int(11)  auto_increment,
    title varchar(45),
    small_desc varchar(45),
    long_desc varchar(45),
    director_id int(11),
    price decimal(10,2),
    genre_id int(11),
     PRIMARY KEY (id)
);

CREATE TABLE genre (
    id int(11)  auto_increment,
    name varchar(45),
     PRIMARY KEY (id)
);

CREATE TABLE director (
    id int(11)  auto_increment,
    name varchar(45),
     PRIMARY KEY (id)
);