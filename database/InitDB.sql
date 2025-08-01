IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'QuanLiSV')
BEGIN
    CREATE DATABASE QuanLiSV;
END;
GO

USE QuanLiSV;

CREATE TABLE users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(100) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    role TINYINT,
    fullname NVARCHAR(255),
    email NVARCHAR(150) NOT NULL UNIQUE,
    is_deleted BIT DEFAULT 0
);
CREATE TABLE students (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    address NVARCHAR(255),
    gender NVARCHAR(10),
    birthday DATE,
    department NVARCHAR(100),
    is_deleted BIT DEFAULT 0
);

CREATE TABLE teachers (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    address NVARCHAR(255),
    gender NVARCHAR(10),
    birthday DATE,
    department NVARCHAR(100),
    is_deleted BIT DEFAULT 0
);

CREATE TABLE subjects (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100),
    credit INT,
    is_deleted BIT DEFAULT 0,
);

CREATE TABLE classes (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    subject_id BIGINT NOT NULL,
    is_deleted BIT DEFAULT 0,
	teacher_id BIGINT,
		
    FOREIGN KEY (teacher_id) REFERENCES teachers(id),
    FOREIGN KEY (subject_id) REFERENCES subjects(id)
);

CREATE TABLE enrollments (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    student_id BIGINT,
    subject_id BIGINT,
    score FLOAT,
    enrollment_date DATE,
    is_deleted BIT DEFAULT 0,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (subject_id) REFERENCES subjects(id)
);

-- Dữ liệu mẫu
INSERT INTO users (username, password, role, fullname, email)
VALUES 
('admin', '0', 0, 'Quản trị viên', 'admin@example.com'),
('teach', '1', 1, 'Giáo viên', 'teach@example.com'),
('student', '2', 2, 'Học sinh', 'student@example.com');

INSERT INTO teachers (name, email, phone, address, gender, birthday, department)
VALUES
(N'Nguyễn Văn A', 'a@univ.edu', '0912345678', N'123 Lê Lợi, Hà Nội', N'Nam', '1980-05-10', N'Công nghệ thông tin'),
(N'Trần Thị B', 'b@univ.edu', '0987654321', N'456 Trần Hưng Đạo, Đà Nẵng', N'Nữ', '1985-08-22', N'Khoa học máy tính');


INSERT INTO subjects (name, credit, teacher_id) VALUES
(N'Toán rời rạc', 3, 1),
(N'Cấu trúc dữ liệu', 4, 2),
(N'Cơ sở dữ liệu', 3, 1);

INSERT INTO students (name, email, phone, address, gender, birthday, department)
VALUES
(N'Lê Hoàng', 'hoang@student.edu', '0901234567', N'12 Nguyễn Trãi, Hà Nội', N'Nam', '2003-02-15', N'Công nghệ thông tin'),
(N'Ngô Mai', 'mai@student.edu', '0912345679', N'78 Lý Thường Kiệt, Huế', N'Nữ', '2002-11-05', N'Hệ thống thông tin'),
(N'Phạm Tuấn', 'tuan@student.edu', '0923456781', N'45 Nguyễn Huệ, TP.HCM', N'Nam', '2004-06-30', N'Kỹ thuật phần mềm');


INSERT INTO enrollments (student_id, subject_id, score, enrollment_date) VALUES
(1, 1, 8.5, '2025-07-01'),
(1, 2, 7.0, '2025-07-01'),
(2, 1, 9.0, '2025-07-02'),
(2, 3, 6.5, '2025-07-02'),
(3, 2, 8.0, '2025-07-03');

INSERT INTO classes (name, subject_id, teacher_id)
VALUES 
(N'Lớp Toán rời rạc 01', 1, 1),
(N'Lớp CTDL 01', 2, 2),
(N'Lớp CSDL 01', 3, 1),
(N'Lớp CTDL 02', 2, NULL),
(N'Lớp CSDL 02', 3, 2);  