USE cineman;

-- Bảng đăng nhập chỉ phục vụ chạy thử hai module.
CREATE TABLE IF NOT EXISTS tblAccount5 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL UNIQUE,
  tendangnhap VARCHAR(80) NOT NULL UNIQUE,
  matkhau_hash CHAR(64) NOT NULL,
  vaitro ENUM('MANAGER','CUSTOMER') NOT NULL,
  FOREIGN KEY (user_id) REFERENCES tblUser5(id)
);
