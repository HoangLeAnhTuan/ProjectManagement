CREATE DATABASE ProjectManagement;
USE ProjectManagement;	

-- Tạo bảng Department
CREATE TABLE Department(
	DEPT_ID INT PRIMARY KEY,
    DEPT_NAME VARCHAR(255) NOT NULL
);

-- Tạo bảng Project
CREATE TABLE Project(
	PROJECT_ID INT PRIMARY KEY,
    PROJECT_NAME VARCHAR(255) NOT NULL,
    PROJECT_DEPT INT,
    START_DAY DATETIME NOT NULL,
    END_DAY DATETIME NOT NULL,
    DIFFICULTY CHAR(1) CHECK (DIFFICULTY IN ('E','M','H')),
    VERSION INT,
    FOREIGN KEY (PROJECT_DEPT) REFERENCES Department(DEPT_ID) ON DELETE SET NULL
);

-- Tạo bảng User
CREATE TABLE User(
	USER_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_NAME VARCHAR(20) UNIQUE,
    PASSWORD VARCHAR(20)
);
-- Thêm dữ liệu mẫu vào bảng Department
INSERT INTO Department (DEPT_ID, DEPT_NAME) VALUES 
(1, 'Software Development'),
(2, 'Data Science'),
(3, 'Marketing'),
(4, 'Finance'),
(5, 'Human Resources');

-- Thêm dữ liệu mẫu vào bảng Project
INSERT INTO Project (PROJECT_ID, PROJECT_NAME, PROJECT_DEPT, START_DAY, END_DAY, DIFFICULTY, VERSION) VALUES
(1, 'AI Chatbot Development', 1, '2024-03-01 09:00:00', '2025-06-01 18:00:00', 'E', 2),
(2, 'Big Data Analysis', 2, '2024-04-01 10:00:00', '2025-07-01 17:00:00', 'H', 3),
(3, 'Social Media Campaign', 3, '2024-05-01 08:30:00', '2025-08-01 16:30:00', 'M', 1),
(4, 'Financial Forecasting Model', 4, '2024-06-01 09:15:00', '2025-09-01 17:45:00', 'M', 2),
(5, 'Employee Training Program', 5, '2024-07-01 10:30:00', '2025-10-01 15:30:00', 'E', 1);


-- Xem tất cả dữ liệu của bảng Department
SELECT * FROM Department;

-- Xem tất cả dữ liệu của bảng Project
SELECT * FROM Project;

-- Xem tất cả dữ liệu của bảng User
SELECT * FROM User;

DROP TABLE Project;

-- Add 95 more sample projects
INSERT INTO Project (PROJECT_ID, PROJECT_NAME, PROJECT_DEPT, START_DAY, END_DAY, DIFFICULTY, VERSION) VALUES
(6, 'Mobile App Development', 1, '2024-03-15 09:00:00', '2024-12-15 18:00:00', 'M', 1),
(7, 'Cloud Migration', 1, '2024-04-01 09:00:00', '2025-01-01 18:00:00', 'H', 1),
(8, 'Machine Learning Model', 2, '2024-03-20 09:00:00', '2024-09-20 18:00:00', 'H', 2),
(9, 'Email Marketing Campaign', 3, '2024-05-01 09:00:00', '2024-08-01 18:00:00', 'E', 1),
(10, 'Budget Analysis 2024', 4, '2024-06-01 09:00:00', '2024-12-01 18:00:00', 'M', 1),
(11, 'Employee Portal', 1, '2024-07-01 09:00:00', '2025-01-01 18:00:00', 'M', 1),
(12, 'Data Warehouse Setup', 2, '2024-08-01 09:00:00', '2025-02-01 18:00:00', 'H', 1),
(13, 'Brand Redesign', 3, '2024-09-01 09:00:00', '2024-12-01 18:00:00', 'M', 1),
(14, 'Risk Assessment', 4, '2024-10-01 09:00:00', '2025-03-01 18:00:00', 'H', 1),
(15, 'Recruitment Platform', 5, '2024-11-01 09:00:00', '2025-04-01 18:00:00', 'M', 1);

-- Batch 2 (16-30)
INSERT INTO Project (PROJECT_ID, PROJECT_NAME, PROJECT_DEPT, START_DAY, END_DAY, DIFFICULTY, VERSION) VALUES
(16, 'API Gateway Implementation', 1, '2024-03-01 09:00:00', '2024-09-01 18:00:00', 'H', 1),
(17, 'Data Analytics Dashboard', 2, '2024-04-01 09:00:00', '2024-10-01 18:00:00', 'M', 1),
(18, 'Digital Marketing Strategy', 3, '2024-05-01 09:00:00', '2024-11-01 18:00:00', 'M', 1),
(19, 'Financial Reporting System', 4, '2024-06-01 09:00:00', '2024-12-01 18:00:00', 'H', 1),
(20, 'HR Management System', 5, '2024-07-01 09:00:00', '2025-01-01 18:00:00', 'M', 1);

-- Continue with similar patterns for PROJECT_ID 21-100
INSERT INTO Project (PROJECT_ID, PROJECT_NAME, PROJECT_DEPT, START_DAY, END_DAY, DIFFICULTY, VERSION)
SELECT 
    t1.PROJECT_ID,
    CONCAT('Project ', t1.PROJECT_ID),
    1 + (t1.PROJECT_ID % 5), -- Cycles through departments 1-5
    DATE_ADD('2024-01-01 09:00:00', INTERVAL t1.PROJECT_ID DAY),
    DATE_ADD('2024-06-01 18:00:00', INTERVAL t1.PROJECT_ID DAY),
    CASE (t1.PROJECT_ID % 3)
        WHEN 0 THEN 'E'
        WHEN 1 THEN 'M'
        ELSE 'H'
    END,
    1 + (t1.PROJECT_ID % 3)
FROM 
    (SELECT 21 + id.n AS PROJECT_ID
     FROM 
        (SELECT a.N + b.N * 10 + c.N * 100 AS n
         FROM 
            (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) a,
            (SELECT 0 AS N UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) b,
            (SELECT 0 AS N) c
         WHERE (a.N + b.N * 10 + c.N * 100) < 80) id) t1;

DROP TABLE Project;

