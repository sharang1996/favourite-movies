CREATE TABLE movies (
   id VARCHAR(100) NOT NULL,
   title VARCHAR(255) NOT NULL,
   plot VARCHAR(1000),
   poster VARCHAR(1000),
   released VARCHAR(1000),
   rating VARCHAR(100),
   runtime VARCHAR(250),
   genre VARCHAR(250),
   primary key(id)
);

CREATE TABLE users (
   id INTEGER NOT NULL,
   firstName VARCHAR(255) NOT NULL,
   lastName VARCHAR(1000),
   password VARCHAR(1000),
   primary key(id)
);

CREATE TABLE user_movie(
   id INTEGER NOT NULL,
   user_id INTEGER NOT NULL,
   movie_id VARCHAR(100) NOT NULL,
   foreign key (user_id) references users(id),
   foreign key (movie_id) references movies(id),
   primary key(id)
);

--CREATE TABLE movies
--(
--   id varchar(100) not null,
--   title varchar(255) not null,
--   plot varchar(1000),
--   poster varchar(250),
--   released varchar(250),
--   rating varchar(100),
--   runtime varchar(250),
--   genre varchar(250),
--   primary key(id)
--);
--
--CREATE TABLE users
--(
--  id int(11) NOT NULL,
--  firstName varchar(255),
--  lastName varchar(255),
--  password varchar(255),
--  favourite_movies varchar(255)
--);