CREATE DATABASE IF NOT EXISTS cineman CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cineman;

CREATE TABLE IF NOT EXISTS tblUser5 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  hoten VARCHAR(120) NOT NULL,
  sodienthoai VARCHAR(20) UNIQUE,
  email VARCHAR(190) UNIQUE,
  ngaysinh DATE
);

CREATE TABLE IF NOT EXISTS tblCustomer5 (
  user_id INT PRIMARY KEY,
  FOREIGN KEY (user_id) REFERENCES tblUser5(id)
);

CREATE TABLE IF NOT EXISTS tblMovie5 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  tenphim VARCHAR(255) NOT NULL,
  anhposter VARCHAR(500),
  daodien VARCHAR(180),
  videotrailer VARCHAR(500),
  dienvien TEXT,
  thoiluong INT NOT NULL,
  gioihantuoi INT NOT NULL,
  mota TEXT,
  ngaykhoichieu DATE,
  INDEX idx_movie_name (tenphim)
);

CREATE TABLE IF NOT EXISTS tblBooking5 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  customer_id INT NULL,
  ngaydat DATETIME NOT NULL,
  trangthai ENUM('PENDING','CONFIRMED','CANCELLED') NOT NULL,
  hanthanhtoan DATETIME,
  tongtien DECIMAL(14,2) NOT NULL,
  ghichu VARCHAR(500),
  FOREIGN KEY (customer_id) REFERENCES tblCustomer5(user_id)
);

CREATE TABLE IF NOT EXISTS tblInvoice5 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  booking_id INT NOT NULL UNIQUE,
  ngaylap DATETIME NOT NULL,
  tongtien DECIMAL(14,2) NOT NULL,
  diemthuong INT NOT NULL DEFAULT 0,
  soluongve INT NOT NULL,
  trangthai ENUM('DRAFT','PAID','VOID') NOT NULL,
  FOREIGN KEY (booking_id) REFERENCES tblBooking5(id),
  INDEX idx_invoice_date (ngaylap)
);
