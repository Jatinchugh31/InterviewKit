CREATE TABLE departments (  
dept_id INT PRIMARY KEY,

dept_name VARCHAR(100)
);  
CREATE TABLE employees (  
emp_id INT PRIMARY KEY,

name VARCHAR(100),

salary DECIMAL(10, 2),

join_date DATE,

dept_id INT,

manager_id INT,

FOREIGN KEY (dept_id) REFERENCES departments(dept_id)

);  
-- Departments  
INSERT INTO departments (dept_id, dept_name) VALUES  
(10, 'HR'),  
(20, 'Engineering'),  
(30, 'Sales'),  
(40, 'Marketing');  
-- Employees  
INSERT INTO employees (emp_id, name, salary, join_date, dept_id, manager_id) VALUES  
(1, 'Alice', 90000, '2021-01-15', 20, NULL),  
(2, 'Bob', 75000, '2022-03-10', 20, 1),  
(3, 'Charlie', 50000, '2023-07-01', 10, NULL),  
(4, 'Diana', 60000, '2022-08-25', 30, NULL),  
(5, 'Evan', 45000, '2023-01-10', 30, 4),  
(6, 'Fiona', 85000, '2020-05-20', 20, 1),  
(7, 'Grace', 55000, '2024-01-05', 40, NULL);





======================================
find second highesh salary for each department

SELECT  d.dept_name,
e.emp_id,
e.name        AS employee_name,
e.salary
FROM    employees   e
JOIN    departments d ON d.dept_id = e.dept_id
WHERE   2 =
(
SELECT COUNT(DISTINCT e2.salary)
FROM   employees e2
WHERE  e2.dept_id = e.dept_id
AND    e2.salary >= e.salary
);


====================

find department name  , count avg salary , and employe with manage

SELECT
d.dept_name,
COUNT(e.emp_id) AS total_employees,
ROUND(AVG(e.salary), 2) AS average_salary,
COUNT(CASE WHEN e.manager_id IS NOT NULL THEN 1 END) AS employees_with_managers
FROM
departments d
LEFT JOIN
employees e ON d.dept_id = e.dept_id
GROUP BY
d.dept_name;

=============================================