
#										TESTS 



# Add user with mail and password
INSERT into users (mail_user, password_user)
VALUES ('test@test.com', 'JO');

# Add user with mail and crypted password
INSERT into users (mail_user, password_user)
VALUES ('test@test.com', MD5('JO'));

# Add user with and custom id
INSERT into users (id_user, mail_user, password_user)
VALUES (3, 'test@test.com', MD5('JO'));

# Add user with "null" mail : FAIL
INSERT into users (mail_user, password_user)
VALUES (null, MD5('JO'));

# Add user with "null" mail and "null" password : FAIL
INSERT into users (mail_user, password_user)
VALUES (null, null);

SELECT *
FROM users;



# Add course with a user_id and by specifying the id of the course
INSERT into courses (id_course, id_user, category_course, title_course, descript_course)
VALUES (1, 1, 'Learn', 'First course', 'Course details');

# Add course with a user_id
INSERT into courses (id_user, category_course, title_course, descript_course)
VALUES (3, 'Learn', 'Another course', 'Another course details');

# Add course without specifying the user id : FAIL
INSERT into courses (category_course, title_course, descript_course)
VALUES ('Learn', 'First course', 'Course details');

# Select courses from users that has his id_user = 1
SELECT category_course, title_course, descript_course
FROM courses, users
WHERE users.id_user = courses.id_user
AND courses.id_user = 3;



# Add chapter with the id of the course
INSERT into chapters (id_course, title_chapter, num_chapter)
VALUES (1, 'Chapter title', 10);

# Add chapter in the table without specifying the course : FAIL
INSERT into chapters (title_chapter, num_chapter)
VALUES ('Chapter title', 10);

# Add chapter with the id of the course but with a 'null' chapter title : FAIL
INSERT into chapters (id_course, title_chapter, num_chapter)
VALUES (1, 'Chapter title', null);

# Select chapters from the course with id = 2
SELECT title_chapter, num_chapter
FROM chapters, courses
WHERE chapters.id_course = courses.id_course
AND chapters.id_course = 2;



# Add lecture with long text with the id of the chapter
INSERT into lectures (id_chapter, num_lecture, txt_lecture)
VALUES (1, 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum a ligula nec lectus bibendum egestas non at risus.');

# Add lecture without text and video link
INSERT into lectures (id_chapter, num_lecture)
VALUES (1, 7);

# Add lecture without specifyng the id of the chapter : FAIL
INSERT into lectures (num_lecture, txt_lecture)
VALUES (5, 'text');

# SELECT all lectures from a course of user with user id = 1 and from the chapter where chapter id = 1
SELECT lectures.id_chapter, num_lecture, txt_lecture
FROM lectures, chapters, courses, users
WHERE lectures.id_chapter = chapters.id_chapter
AND chapters.id_course = courses.id_course
AND courses.id_user = users.id_user
AND users.id_user = 1
AND chapters.id_chapter = 1;