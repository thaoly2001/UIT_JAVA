-- IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'QuanLiSV')
-- BEGIN
--     CREATE DATABASE QuanLiSV;
-- END;
-- GO
-- 
-- USE QuanLiSV;

-- Bảng users
CREATE TABLE users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(100) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    role TINYINT,
    fullname NVARCHAR(255),
    email NVARCHAR(150) NOT NULL UNIQUE,
    is_deleted BIT DEFAULT 0
);

-- Bảng students
CREATE TABLE students (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    address NVARCHAR(255),
    gender NVARCHAR(10),
    img VARBINARY(MAX),
    birthday DATE,
    department NVARCHAR(100),
    is_deleted BIT DEFAULT 0
);

-- Bảng teachers
CREATE TABLE teachers (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100),
    email VARCHAR(100),
    phone VARCHAR(20),
    address NVARCHAR(255),
    gender NVARCHAR(10),
    img VARBINARY(MAX),
    birthday DATE,
    department NVARCHAR(100),
    is_deleted BIT DEFAULT 0
);

-- Bảng subjects
CREATE TABLE subjects (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100),
    credit INT,
    status BIT DEFAULT 0,
    is_deleted BIT DEFAULT 0
);

-- Bảng classes
CREATE TABLE classes (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    subject_id BIGINT NOT NULL,
    teacher_id BIGINT NULL,
    is_deleted BIT DEFAULT 0,
    FOREIGN KEY (teacher_id) REFERENCES teachers(id),
    FOREIGN KEY (subject_id) REFERENCES subjects(id)
);

-- Bảng enrollments
CREATE TABLE enrollments (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    student_id BIGINT NOT NULL,
    class_id BIGINT NOT NULL,            
    score FLOAT,
    enrollment_date DATE DEFAULT CAST(GETDATE() AS DATE),
    is_deleted BIT DEFAULT 0,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (class_id) REFERENCES classes(id)  
);

-- ================= DỮ LIỆU MẪU =================--

-- 1. TEACHERS (40 records)
INSERT INTO teachers (name, email, phone, address, gender, birthday, department) VALUES
(N'Nguyễn Văn A', 'a@univ.edu', '0912345678', N'123 Lê Lợi, Hà Nội', N'Nam', '1980-05-10', N'Công nghệ thông tin'),
(N'Trần Thị B', 'b@univ.edu', '0987654321', N'456 Trần Hưng Đạo, Đà Nẵng', N'Nữ', '1985-08-22', N'Khoa học máy tính'),
(N'Lê Minh C', 'c@univ.edu', '0912345679', N'12 Nguyễn Trãi, Hà Nội', N'Nam', '1975-03-12', N'Mạng máy tính'),
(N'Phạm Thị D', 'd@univ.edu', '0923456789', N'78 Lý Thường Kiệt, Huế', N'Nữ', '1982-06-30', N'Khoa học dữ liệu'),
(N'Hồ Văn E', 'e@univ.edu', '0934567890', N'45 Nguyễn Huệ, TP.HCM', N'Nam', '1978-12-05', N'An toàn thông tin'),
(N'Ngô Thị F', 'f@univ.edu', '0945678901', N'23 Hai Bà Trưng, Hà Nội', N'Nữ', '1984-11-11', N'Kỹ thuật phần mềm'),
(N'Vũ Văn G', 'g@univ.edu', '0912345690', N'56 Trần Phú, Đà Nẵng', N'Nam', '1979-01-20', N'Khoa học máy tính'),
(N'Tạ Thị H', 'h@univ.edu', '0987654322', N'89 Nguyễn Văn Cừ, Huế', N'Nữ', '1986-07-15', N'Mạng máy tính'),
(N'Phan Văn I', 'i@univ.edu', '0923456790', N'12 Trần Hưng Đạo, TP.HCM', N'Nam', '1981-09-25', N'Công nghệ thông tin'),
(N'Lý Thị J', 'j@univ.edu', '0934567891', N'33 Lê Lợi, Hà Nội', N'Nữ', '1983-04-17', N'Khoa học dữ liệu'),
(N'Nguyễn Văn K', 'k@univ.edu', '0911122233', N'101 Lê Duẩn, Đà Nẵng', N'Nam', '1976-08-02', N'Kỹ thuật phần mềm'),
(N'Trần Thị L', 'l@univ.edu', '0911223344', N'202 Nguyễn Trãi, Hà Nội', N'Nữ', '1980-12-12', N'An toàn thông tin'),
(N'Hồ Văn M', 'm@univ.edu', '0911334455', N'11 Hai Bà Trưng, Huế', N'Nam', '1982-05-18', N'Mạng máy tính'),
(N'Ngô Thị N', 'n@univ.edu', '0911445566', N'77 Trần Phú, TP.HCM', N'Nữ', '1987-09-09', N'Công nghệ phần mềm'),
(N'Vũ Văn O', 'o@univ.edu', '0911556677', N'99 Nguyễn Huệ, Hà Nội', N'Nam', '1977-11-11', N'Công nghệ thông tin'),
(N'Tạ Thị P', 'p@univ.edu', '0911667788', N'33 Lê Lợi, Đà Nẵng', N'Nữ', '1984-02-21', N'Khoa học dữ liệu'),
(N'Phan Văn Q', 'q@univ.edu', '0911778899', N'45 Trần Hưng Đạo, Huế', N'Nam', '1980-06-30', N'Mạng máy tính'),
(N'Lý Thị R', 'r@univ.edu', '0911889900', N'12 Nguyễn Trãi, TP.HCM', N'Nữ', '1985-12-15', N'Kỹ thuật phần mềm'),
(N'Nguyễn Văn S', 's@univ.edu', '0911990011', N'56 Hai Bà Trưng, Hà Nội', N'Nam', '1979-04-10', N'An toàn thông tin'),
(N'Trần Thị T', 't@univ.edu', '0912001122', N'78 Lê Duẩn, Đà Nẵng', N'Nữ', '1983-07-19', N'Công nghệ thông tin'),
(N'Hồ Văn U', 'u@univ.edu', '0912112233', N'101 Nguyễn Huệ, Huế', N'Nam', '1981-09-28', N'Khoa học máy tính'),
(N'Ngô Thị V', 'v@univ.edu', '0912223344', N'23 Trần Phú, TP.HCM', N'Nữ', '1986-03-03', N'Mạng máy tính'),
(N'Vũ Văn W', 'w@univ.edu', '0912334455', N'45 Hai Bà Trưng, Hà Nội', N'Nam', '1978-08-17', N'Kỹ thuật phần mềm'),
(N'Tạ Thị X', 'x@univ.edu', N'0912445566', N'12 Lê Lợi, Đà Nẵng', N'Nữ', '1982-01-25', N'Công nghệ thông tin'),
(N'Phan Văn Y', 'y@univ.edu', '0912556677', N'33 Nguyễn Trãi, Huế', N'Nam', '1976-03-30', N'An toàn thông tin'),
(N'Lý Thị Z', 'z@univ.edu', '0912667788', N'77 Trần Hưng Đạo, TP.HCM', N'Nữ', '1980-05-05', N'Khoa học dữ liệu'),
(N'Nguyễn Văn AA', 'aa@univ.edu', '0912778899', N'11 Lê Duẩn, Hà Nội', N'Nam', '1975-12-12', N'Mạng máy tính'),
(N'Trần Thị BB', 'bb@univ.edu', '0912889900', N'22 Nguyễn Huệ, Đà Nẵng', N'Nữ', '1987-08-08', N'Công nghệ phần mềm'),
(N'Hồ Văn CC', 'cc@univ.edu', '0912990011', N'33 Trần Phú, Huế', N'Nam', '1979-11-11', N'Khoa học dữ liệu'),
(N'Ngô Thị DD', 'dd@univ.edu', '0912001122', N'44 Hai Bà Trưng, TP.HCM', N'Nữ', '1984-09-09', N'Kỹ thuật phần mềm'),
(N'Vũ Văn EE', 'ee@univ.edu', '0912112233', N'55 Lê Lợi, Hà Nội', N'Nam', '1981-06-06', N'Công nghệ thông tin'),
(N'Tạ Thị FF', 'ff@univ.edu', '0912223344', N'66 Nguyễn Trãi, Đà Nẵng', N'Nữ', '1985-03-03', N'An toàn thông tin'),
(N'Phan Văn GG', 'gg@univ.edu', '0912334455', N'77 Trần Hưng Đạo, Huế', N'Nam', '1977-01-01', N'Mạng máy tính'),
(N'Lý Thị HH', 'hh@univ.edu', '0912445566', N'88 Nguyễn Huệ, TP.HCM', N'Nữ', '1983-12-12', N'Khoa học dữ liệu'),
(N'Nguyễn Văn II', 'ii@univ.edu', '0912556677', N'99 Hai Bà Trưng, Hà Nội', N'Nam', '1980-10-10', N'Công nghệ phần mềm'),
(N'Trần Thị JJ', 'jj@univ.edu', '0912667788', N'111 Lê Duẩn, Đà Nẵng', N'Nữ', '1986-07-07', N'Công nghệ thông tin');

-- ============================================
-- 2. SUBJECTS (40 records)
INSERT INTO subjects (name, credit) VALUES
(N'Toán rời rạc', 3),
(N'Cấu trúc dữ liệu', 4),
(N'Cơ sở dữ liệu', 3),
(N'Giải tích', 3),
(N'Lập trình Java', 4),
(N'Lập trình Python', 4),
(N'Mạng máy tính', 3),
(N'Hệ điều hành', 3),
(N'An toàn thông tin', 3),
(N'Khoa học dữ liệu', 4),
(N'Trí tuệ nhân tạo', 4),
(N'Máy học', 4),
(N'Lập trình web', 3),
(N'Lập trình di động', 3),
(N'Thuật toán nâng cao', 4),
(N'Công nghệ phần mềm', 3),
(N'Phân tích dữ liệu', 3),
(N'Ngôn ngữ lập trình C++', 3),
(N'Điện tử số', 3),
(N'Điều khiển học', 3),
(N'Đồ họa máy tính', 3),
(N'Thiết kế phần mềm', 4),
(N'Mạng nâng cao', 3),
(N'Lập trình C#', 4),
(N'Trí tuệ nhân tạo 2', 4),
(N'Hệ quản trị cơ sở dữ liệu', 3),
(N'Lập trình nhúng', 3),
(N'Kỹ thuật lập trình', 3),
(N'Lý thuyết đồ thị', 3),
(N'Thị giác máy tính', 4),
(N'Thuật toán đồ họa', 3),
(N'Trực quan hóa dữ liệu', 3),
(N'Lập trình R', 3),
(N'Học máy nâng cao', 4),
(N'Thiết kế web', 3),
(N'Thiết kế UX/UI', 3),
(N'Trí tuệ nhân tạo nâng cao', 4),
(N'Phân tích hệ thống', 3),
(N'Blockchain', 3),
(N'IoT', 3);

-- ============================================
-- STUDENTS (40 records)
INSERT INTO students (name, email, phone, address, gender, birthday, department) VALUES
(N'Nguyen Van A','student1@student.edu','0950223568',N'37 Đường Trần Phú, Huế',N'Nam','2003-08-07',N'Kỹ thuật phần mềm'),
(N'Tran Thi B','student2@student.edu','0957632001',N'76 Đường Trần Phú, Huế',N'Nữ','2002-02-21',N'Kỹ thuật phần mềm'),
(N'Le Van C','student3@student.edu','0940591194',N'169 Đường Hai Bà Trưng, TP.HCM',N'Nam','2002-10-28',N'Công nghệ phần mềm'),
(N'Pham Thi D','student4@student.edu','0984526088',N'66 Đường Điện Biên Phủ, Đà Nẵng',N'Nữ','2002-09-22',N'Mạng máy tính'),
(N'Hoang Van E','student5@student.edu','0992759454',N'79 Đường Hai Bà Trưng, Huế',N'Nam','2002-08-11',N'Công nghệ phần mềm'),
(N'Vu Thi F','student6@student.edu','0972220946',N'14 Đường Hai Bà Trưng, TP.HCM',N'Nữ','2003-12-27',N'Mạng máy tính'),
(N'Ngo Van G','student7@student.edu','0992516173',N'62 Đường Trần Phú, Đà Nẵng',N'Nam','2003-05-02',N'An toàn thông tin'),
(N'Do Thi H','student8@student.edu','0998984662',N'171 Đường Lê Lợi, Huế',N'Nữ','2003-01-08',N'Kỹ thuật phần mềm'),
(N'Bui Van I','student9@student.edu','0963426364',N'184 Đường Điện Biên Phủ, Hà Nội',N'Nam','2003-01-01',N'Kỹ thuật phần mềm'),
(N'Dang Thi J','student10@student.edu','0994014330',N'33 Đường Hai Bà Trưng, Huế',N'Nữ','2004-02-10',N'Kỹ thuật phần mềm'),
(N'Nguyen Van K','student11@student.edu','0945482108',N'105 Đường Hai Bà Trưng, Huế',N'Nam','2004-06-22',N'Mạng máy tính'),
(N'Tran Thi L','student12@student.edu','0972320205',N'89 Đường Nguyễn Trãi, TP.HCM',N'Nữ','2004-09-21',N'Công nghệ thông tin'),
(N'Le Van M','student13@student.edu','0923349295',N'193 Đường Lê Lợi, Đà Nẵng',N'Nam','2003-05-30',N'Mạng máy tính'),
(N'Pham Thi N','student14@student.edu','0991528528',N'142 Đường Hai Bà Trưng, Huế',N'Nữ','2002-10-21',N'Công nghệ phần mềm'),
(N'Hoang Van O','student15@student.edu','0994097348',N'147 Đường Trần Phú, TP.HCM',N'Nam','2002-03-11',N'Hệ thống thông tin'),
(N'Vu Thi P','student16@student.edu','0941371543',N'25 Đường Hai Bà Trưng, TP.HCM',N'Nữ','2003-04-15',N'An toàn thông tin'),
(N'Ngo Van Q','student17@student.edu','0958750253',N'93 Đường Nguyễn Trãi, Huế',N'Nam','2002-05-19',N'Kỹ thuật phần mềm'),
(N'Do Thi R','student18@student.edu','0945661834',N'6 Đường Hai Bà Trưng, Đà Nẵng',N'Nữ','2003-01-16',N'Hệ thống thông tin'),
(N'Bui Van S','student19@student.edu','0928706323',N'200 Đường Nguyễn Trãi, TP.HCM',N'Nam','2003-08-28',N'Công nghệ thông tin'),
(N'Dang Thi T','student20@student.edu','0967195499',N'4 Đường Trần Phú, Huế',N'Nữ','2003-01-27',N'Công nghệ thông tin'),
(N'Nguyen Van U','student21@student.edu','0932916480',N'117 Đường Lê Lợi, TP.HCM',N'Nam','2003-03-21',N'Công nghệ thông tin'),
(N'Tran Thi V','student22@student.edu','0999817861',N'68 Đường Điện Biên Phủ, Huế',N'Nữ','2002-05-09',N'Kỹ thuật phần mềm'),
(N'Le Van W','student23@student.edu','0910088851',N'116 Đường Lê Lợi, TP.HCM',N'Nam','2004-03-24',N'Kỹ thuật phần mềm'),
(N'Pham Thi X','student24@student.edu','0933457586',N'5 Đường Trần Phú, Huế',N'Nữ','2003-03-09',N'Kỹ thuật phần mềm'),
(N'Hoang Van Y','student25@student.edu','0992183436',N'68 Đường Lê Lợi, Đà Nẵng',N'Nam','2002-05-19',N'Kỹ thuật phần mềm'),
(N'Vu Thi Z','student26@student.edu','0977541052',N'2 Đường Hai Bà Trưng, Huế',N'Nữ','2004-08-22',N'Hệ thống thông tin'),
(N'Ngo Van AA','student27@student.edu','0927698500',N'23 Đường Trần Phú, Huế',N'Nam','2002-06-13',N'Kỹ thuật phần mềm'),
(N'Do Thi BB','student28@student.edu','0910894181',N'109 Đường Trần Phú, Huế',N'Nữ','2002-09-14',N'Kỹ thuật phần mềm'),
(N'Bui Van CC','student29@student.edu','0995755958',N'110 Đường Lê Lợi, Hà Nội',N'Nam','2004-01-11',N'An toàn thông tin'),
(N'Dang Thi DD','student30@student.edu','0910502221',N'50 Đường Điện Biên Phủ, TP.HCM',N'Nữ','2003-04-17',N'Công nghệ thông tin'),
(N'Nguyen Van EE','student31@student.edu','0991760894',N'155 Đường Trần Phú, Đà Nẵng',N'Nam','2003-06-04',N'Công nghệ phần mềm'),
(N'Tran Thi FF','student32@student.edu','0945305991',N'130 Đường Hai Bà Trưng, Hà Nội',N'Nữ','2004-06-30',N'Kỹ thuật phần mềm'),
(N'Le Van GG','student33@student.edu','0928478396',N'31 Đường Lê Lợi, Hà Nội',N'Nam','2002-04-04',N'Công nghệ phần mềm'),
(N'Pham Thi HH','student34@student.edu','0934514832',N'174 Đường Nguyễn Trãi, Đà Nẵng',N'Nữ','2003-12-25',N'Công nghệ phần mềm'),
(N'Hoang Van II','student35@student.edu','0989525044',N'120 Đường Trần Phú, TP.HCM',N'Nam','2004-05-14',N'Kỹ thuật phần mềm'),
(N'Vu Thi JJ','student36@student.edu','0927508254',N'88 Đường Trần Phú, Hà Nội',N'Nữ','2003-04-17',N'Mạng máy tính'),
(N'Ngo Van KK','student37@student.edu','0989910112',N'168 Đường Hai Bà Trưng, Đà Nẵng',N'Nam','2004-02-29',N'Hệ thống thông tin'),
(N'Do Thi LL','student38@student.edu','0939805491',N'34 Đường Trần Phú, Hà Nội',N'Nữ','2003-04-02',N'Công nghệ thông tin'),
(N'Bui Van MM','student39@student.edu','0964422912',N'25 Đường Lê Lợi, Đà Nẵng',N'Nam','2004-02-04',N'Mạng máy tính'),
(N'Dang Thi NN','student40@student.edu','0910942704',N'74 Đường Điện Biên Phủ, Đà Nẵng',N'Nữ','2003-08-28',N'Hệ thống thông tin');

-- CLASSES (40 records)
INSERT INTO classes (name, subject_id, teacher_id) VALUES
(N'Lớp CSDL 01',8,16),
(N'Lớp Mạng máy tính 02',16,5),
(N'Lớp AI 03',9,18),
(N'Lớp ML 04',4,16),
(N'Lớp Python 05',11,10),
(N'Lớp ML 06',15,15),
(N'Lớp Toán rời rạc 07',10,13),
(N'Lớp CSDL 08',2,19),
(N'Lớp CSDL 09',19,2),
(N'Lớp ML 10',16,12),
(N'Lớp AI 11',10,2),
(N'Lớp ML 12',5,4),
(N'Lớp Toán rời rạc 13',9,9),
(N'Lớp ML 14',2,11),
(N'Lớp AI 15',9,17),
(N'Lớp Mạng máy tính 16',13,4),
(N'Lớp CTDL 17',7,12),
(N'Lớp AI 18',8,17),
(N'Lớp CSDL 19',15,11),
(N'Lớp CSDL 20',2,2),
(N'Lớp ML 21',7,16),
(N'Lớp CTDL 22',7,10),
(N'Lớp Python 23',5,15),
(N'Lớp Java 24',4,18),
(N'Lớp CSDL 25',4,8),
(N'Lớp Mạng máy tính 26',16,2),
(N'Lớp AI 27',1,3),
(N'Lớp CSDL 28',15,4),
(N'Lớp Java 29',15,16),
(N'Lớp CSDL 30',18,3),
(N'Lớp Toán rời rạc 31',18,6),
(N'Lớp Python 32',7,7),
(N'Lớp CTDL 33',2,3),
(N'Lớp ML 34',19,3),
(N'Lớp CTDL 35',3,6),
(N'Lớp Toán rời rạc 36',7,15),
(N'Lớp CTDL 37',18,12),
(N'Lớp CTDL 38',1,16),
(N'Lớp Mạng máy tính 39',14,8),
(N'Lớp ML 40',15,8);

-- ENROLLMENTS (50 records)
INSERT INTO enrollments (student_id, class_id, score, enrollment_date) VALUES
(23,31,6.9,'2025-07-08'),
(29,9,7.8,'2025-07-11'),
(26,27,7.8,'2025-07-05'),
(29,22,5.5,'2025-07-03'),
(38,22,8.2,'2025-07-19'),
(22,35,9.9,'2025-07-03'),
(4,38,9.0,'2025-07-02'),
(35,40,5.4,'2025-07-07'),
(38,31,8.2,'2025-07-16'),
(26,38,6.5,'2025-07-05'),
(10,12,7.2,'2025-07-11'),
(31,3,5.5,'2025-07-12'),
(29,15,7.6,'2025-07-04'),
(21,16,8.7,'2025-07-17'),
(2,1,5.9,'2025-07-14'),
(14,14,9.7,'2025-07-09'),
(33,7,9.0,'2025-07-16'),
(4,35,7.9,'2025-07-02'),
(29,13,5.6,'2025-07-06'),
(40,39,9.3,'2025-07-11'),
(37,27,6.3,'2025-07-09'),
(25,28,9.1,'2025-07-16'),
(15,30,7.4,'2025-07-02'),
(28,40,7.6,'2025-07-07'),
(35,10,5.5,'2025-07-13'),
(18,20,9.1,'2025-07-03'),
(36,13,6.9,'2025-07-05'),
(14,24,6.3,'2025-07-11'),
(9,23,6.6,'2025-07-06'),
(3,32,9.4,'2025-07-19'),
(18,16,9.1,'2025-07-13'),
(35,31,6.7,'2025-07-13'),
(10,36,7.9,'2025-07-05'),
(10,12,9.0,'2025-07-17'),
(4,4,6.9,'2025-07-18'),
(34,2,8.4,'2025-07-10'),
(36,19,6.5,'2025-07-14'),
(38,39,7.5,'2025-07-02'),
(24,10,9.2,'2025-07-04'),
(22,31,9.8,'2025-07-08'),
(23,20,6.0,'2025-07-07'),
(17,32,9.0,'2025-07-09'),
(12,37,6.9,'2025-07-04'),
(40,10,6.6,'2025-07-09'),
(37,14,6.0,'2025-07-04'),
(14,16,8.5,'2025-07-21'),
(27,25,9.5,'2025-07-13'),
(11,11,6.0,'2025-07-03'),
(29,28,6.8,'2025-07-02'),
(28,18,9.4,'2025-07-10');

INSERT INTO users (username, [password], [role], email, is_deleted) VALUES
('admin', 'pass123', 0, 'admin@univ.edu', 0),
('teacher1', 'pass123', 1, 'ff@univ.edu', 0),
('teacher2', 'pass123', 1, 't2@univ.edu', 0),
('teacher3', 'pass123', 1, 't3@univ.edu', 0),
('student1', 'pass123', 2, 'student1@student.edu', 0),
('student2', 'pass123', 2, 's2@student.edu', 0),
('student3', 'pass123', 2, 's3@student.edu', 0),
('student4', 'pass123', 2, 's4@student.edu', 0),
('student5', 'pass123', 2, 's5@student.edu', 0),
('student6', 'pass123', 2, 's6@student.edu', 0);

