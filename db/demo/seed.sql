USE cineman;
-- Dữ liệu mẫu phục vụ đúng hai module. Chạy một lần trên CSDL mới.
INSERT INTO tblUser5 (id,hoten,sodienthoai,email) VALUES
 (1,'Quản lý CineMan','0900000001','manager@cineman.local'),
 (2,'Nguyễn Văn A','0900000002','a@cineman.local'),
 (3,'Trần Thị B','0900000003','b@cineman.local');
INSERT INTO tblCustomer5 (user_id) VALUES (2),(3);
INSERT INTO tblAccount5 (user_id,tendangnhap,matkhau_hash,vaitro) VALUES
 (1,'manager',SHA2('demo123',256),'MANAGER'),
 (2,'customer',SHA2('demo123',256),'CUSTOMER');
INSERT INTO tblMovie5 (tenphim,daodien,dienvien,thoiluong,gioihantuoi,mota,ngaykhoichieu) VALUES
 ('Quỷ Nhập Tràng (2024)','Lưu Thành Luân','Hồng Đào, Nguyễn Thúc Thùy Tiên, Thiên An, Samuel An, Văn Anh',109,18,'Lấy cảm hứng từ truyền thuyết dân gian về hiện tượng quỷ nhập tràng, bộ phim xoay quanh bi kịch của một gia đình làm nghề khảm sành sứ tại Huế.', '2024-11-22'),
 ('Quỷ Môn Quan (2020)','Đang cập nhật','Đang cập nhật',98,16,'Bộ phim kinh dị đầy bí ẩn.', '2020-08-14'),
 ('Quỷ Cẩu (2023)','Lưu Thành Luân','Đang cập nhật',99,18,'Câu chuyện kinh dị lấy cảm hứng từ truyền thuyết dân gian.', '2023-12-22'),
 ('Lật Mặt 7','Lý Hải','Đang cập nhật',138,13,'Một câu chuyện gia đình đầy cảm xúc.', '2024-04-26');
INSERT INTO tblBooking5 (id,customer_id,ngaydat,trangthai,tongtien) VALUES
 (1,2,'2026-09-05 10:00:00','CONFIRMED',400000),
 (2,2,'2026-09-12 18:00:00','CONFIRMED',200000),
 (3,3,'2026-09-08 12:00:00','CONFIRMED',300000),
 (4,2,'2026-09-10 09:00:00','CANCELLED',100000),
 (5,NULL,'2026-09-09 11:00:00','CONFIRMED',150000);
INSERT INTO tblInvoice5 (booking_id,ngaylap,tongtien,diemthuong,soluongve,trangthai) VALUES
 (1,'2026-09-05 10:10:00',400000,40,4,'PAID'),
 (2,'2026-09-12 18:10:00',200000,20,2,'PAID'),
 (3,'2026-09-08 12:10:00',300000,30,2,'PAID'),
 (4,'2026-09-10 09:10:00',100000,0,1,'VOID'),
 (5,'2026-09-09 11:10:00',150000,15,1,'PAID');
