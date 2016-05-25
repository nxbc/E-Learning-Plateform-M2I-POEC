DROP DATABASE IF EXISTS elearning;

CREATE DATABASE elearning;

USE elearning;

CREATE TABLE users
(
id_user INT NOT NULL AUTO_INCREMENT,
mail_user VARCHAR(100) NOT NULL,
password_user VARCHAR(255) NOT NULL,
confirmed_user BOOLEAN DEFAULT FALSE,
confirmed_key_user VARCHAR(100),
PRIMARY KEY (id_user)
);


CREATE TABLE courses
(
id_course INT NOT NULL AUTO_INCREMENT,
id_user INT NOT NULL,
category_course VARCHAR(50) NOT NULL,
title_course VARCHAR(100) NOT NULL,
descript_course TEXT NOT NULL,
skills_course VARCHAR(100),
tags_course VARCHAR(100),
required_course VARCHAR(100),
PRIMARY KEY (id_course),
FOREIGN KEY (id_user) REFERENCES users(id_user)
);

CREATE TABLE chapters
(
id_chapter INT NOT NULL AUTO_INCREMENT,
id_course INT NOT NULL,
title_chapter VARCHAR(100) NOT NULL,
num_chapter INT NOT NULL,
PRIMARY KEY (id_chapter),
FOREIGN KEY (id_course) REFERENCES courses(id_course)
);

CREATE TABLE lectures
(
id_lecture INT NOT NULL AUTO_INCREMENT,
id_chapter INT NOT NULL,
num_lecture INT NOT NULL,
link_video_lecture VARCHAR(150),
txt_lecture TEXT,
PRIMARY KEY (id_lecture),
FOREIGN KEY (id_chapter) REFERENCES chapters(id_chapter)
);