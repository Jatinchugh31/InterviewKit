In MySQL (and relational databases in general), associations refer to the relationships between different tables. These
associations are typically implemented using foreign keys to enforce referential integrity. The most common associations
include:

One-to-One (1:1) Association
One-to-Many (1:N) Association
Many-to-Many (M:N) Association

1. One-to-One Association
   A one-to-one relationship means that each record in Table A corresponds to exactly one record in Table B, and vice
   versa.
   Example Scenario:
   Imagine you have a users table and a user_profiles table where each user has one unique profile.

-- Create users table
CREATE TABLE users (
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
email VARCHAR(100) NOT NULL
);

-- Create user_profiles table
CREATE TABLE user_profiles (
id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT UNIQUE, -- Ensures one-to-one by allowing only one profile per user
bio TEXT,
profile_picture VARCHAR(255),
FOREIGN KEY (user_id) REFERENCES users(id)
);

In this example, the user_profiles.user_id column is a foreign key referencing users.id and is marked as UNIQUE to
ensure that each user can have only one profile.

2. One-to-Many Association
   A one-to-many relationship means that a single record in Table A can have multiple related records in Table B.
   Example Scenario:
   Consider a users table and an orders table where each user can place multiple orders.
   -- Create users table
   CREATE TABLE users (
   id INT PRIMARY KEY AUTO_INCREMENT,
   username VARCHAR(50) NOT NULL,
   email VARCHAR(100) NOT NULL
   );

-- Create orders table
CREATE TABLE orders (
id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT, -- Foreign key to users table
order_date DATE NOT NULL,
amount DECIMAL(10,2) NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id)
);

Here, each record in orders is linked to a record in users via the user_id column. A user can have many orders, but each
order is associated with only one user.

Query Example (Joining Tables):

SELECT u.username, o.order_date, o.amount
FROM users u
JOIN orders o ON u.id = o.user_id
WHERE u.id = 1;

3. Many-to-Many
   A many-to-many relationship means that multiple records in Table A can be associated with multiple records in Table
   B.
   Example Scenario:
   Imagine a system where you have students and courses. A student can enroll in multiple courses, and each course can
   have multiple students. To implement this, you use a junction table (also called a join table).Association

-- Create students table
CREATE TABLE students (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) NOT NULL
);

-- Create courses table
CREATE TABLE courses (
id INT PRIMARY KEY AUTO_INCREMENT,
course_name VARCHAR(100) NOT NULL
);

-- Create a junction table to establish a many-to-many relationship
CREATE TABLE student_courses (
student_id INT,
course_id INT,
PRIMARY KEY (student_id, course_id),
FOREIGN KEY (student_id) REFERENCES students(id),
FOREIGN KEY (course_id) REFERENCES courses(id)
);

In this example:

The student_courses table holds pairs of student_id and course_id.
The composite primary key (student_id, course_id) ensures that each student can enroll in a course only once.
Foreign keys enforce referential integrity between students and courses.
Query Example (Joining Tables):

-- Retrieve all courses for a given student
SELECT s.name, c.course_name
FROM students s
JOIN student_courses sc ON s.id = sc.student_id
JOIN courses c ON sc.course_id = c.id
WHERE s.id = 1;

Summary
One-to-One: Use a foreign key with a unique constraint.
One-to-Many: Use a foreign key in the “many” table that references the “one” table.
Many-to-Many: Use a junction (join) table with composite primary keys and foreign keys to both tables.