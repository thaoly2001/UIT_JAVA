
create database QuanLiSV;

use QuanLiSV;
go
-- tạo table Grade
create table GRADE(
	ID int not null primary key,
	MaSV nvarchar(10) not null,
	TiengAnh float not null ,
	TinHoc float not null,
	GDTC float not null
);

-- tạo table Students
create table Students(
	MaSV nvarchar(10) not null primary key,
	HoTen nvarchar(30) not null,
	Email nvarchar(30) not null,
	SoDT int not null,
	GioiTinh nvarchar(5) not null,
	DiaChi nvarchar(30) not null,
	Hinh nvarchar (30)
);


--tạo khóa ngoại
alter table GRADE
add constraint FK_Gr_St foreign key (MaSV) references Students(MaSV);

-- Thêm dữ liệu
-- Thêm dữ liệu table Students
insert into Students values 
	('PS01',N'Đỗ Thảo Ly',N'thaoly2001@gmail.com',0947456231,N'Nữ',N'Quận 12, Tp.HCM',null),
	('PS02',N'Trần Thị Hoàn',N'thihoan2001@gmail.com',0947456232,N'Nữ',N'Quận 11, Tp.HCM',null),
	('PS03',N'Nguyễn Thị Linh Nhi',N'linhnhi2001@gmail.com',0947456233,N'Nữ',N'Quận 5, Tp.HCM',null),
	('PS04',N'Bùi Quốc Đạt',N'quocdat2001@gmail.com',0947456234,N'Nam',N'Quận 3, Tp.HCM',null),
	('PS05',N'Trần Xuân Thiện',N'xuanthien2001@gmail.com',0947456235,N'Nam',N'Quận 6, Tp.HCM',null),
	('PS06',N'Phạm Hoàng Khoa',N'hoangkhoa2001@gmail.com',0947456236,N'Nam',N'Quận 3, Tp.HCM',null),
	('PS07',N'Phạm Đoàn Thanh Trúc',N'thanhtruc2001@gmail.com',0947456237,N'Nữ',N'Quận 4, Tp.HCM',null),
	('PS08',N'Lê Văn Cường',N'vancuong2001@gmail.com',0947456239,N'Nam',N'Quận 6, Tp.HCM',null),
	('PS09',N'Lê Thị Yến Linh',N'yenlinh2001@gmail.com',0947456230,N'Nữ',N'Quận 5, Tp.HCM',null),
	('PS10',N'Nguyễn Thị Minh Thư',N'minhthu2001@gmail.com',0947456292,N'Nữ',N'Quận 10, Tp.HCM',null)
	
-- Nhập dữ liệu cho table Grade
insert into GRADE values
(3,'PS01',6,9,7),
(7,'PS01',9,10,10),
(4,'PS02',7,8,5),
(2,'PS03',10,10,9),
(6,'PS03',4,7,5),
(1,'PS01',5,6,6),
(5,'PS01',9,8,7),
(8,'PS04',10,7,9),
(9,'PS04',8,7,6)

-- Tạo bảng người dùng (User)
create table Users(
	UserName nvarchar(30) not null primary key,
	Passwords nvarchar(30) not null,
	roles nvarchar(30) not null
);
-- Nhập dữ liệu cho table User
insert into Users values
(N'Trần Duy Phong',N'phong123',N'Cán bộ đào tạo'),
(N'Lê Văn Phụng',N'phung123',N'Giảng viên'),
(N'Tống Phước Quan',N'quan123',N'Giảng viên'),
(N'Lê Anh Tú',N'tu123',N'Giảng viên'),
(N'Phan Viết Thế',N'the123',N'Cán bộ đào tạo'),






	
