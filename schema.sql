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

