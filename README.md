# CineMan — 2 module theo sơ đồ thiết kế

Ứng dụng Java Web (JSP + Servlet + JDBC + MySQL + DAO) triển khai **hai chức năng đầu trong tài liệu CineMan**:

1. Khách hàng tìm phim theo tên, xem danh sách và chi tiết phim.
2. Nhân viên quản lý xem báo cáo, thống kê khách hàng theo doanh thu trong khoảng ngày và xem các hóa đơn của từng khách hàng.

Scenario v3.0 được ánh xạ thành `MovieServlet5 → MovieDAO5 → Movie5` và `CustomerRevenueServlet5 → CustomerRevenueStatisticDAO5/InvoiceDAO5 → CustomerRevenueStatistic5/Invoice5`. Mỗi lớp giao diện trong sơ đồ là một JSP cùng tên, viết liền theo quy ước Java. Lớp `DAO5` giữ phần kết nối chung. Đăng nhập là tiền điều kiện của hai scenario; `LoginInterface5.jsp` và các lớp xác thực được thêm để vận hành đúng điều kiện đó.

| Tên lớp giao diện trong tài liệu | File JSP |
| --- | --- |
| Main interface5 | `MainInterface5.jsp` |
| Search interface5 | `movie/SearchInterface5.jsp` |
| Movie list interface5 | `movie/MovieListInterface5.jsp` |
| Movie detail interface5 | `movie/MovieDetailInterface5.jsp` |
| Report interface5 | `revenue/ReportInterface5.jsp` |
| Customer revenue search interface5 | `revenue/CustomerRevenueSearchInterface5.jsp` |
| Customer revenue list interface5 | `revenue/CustomerRevenueListInterface5.jsp` |
| Customer transaction detail interface5 | `revenue/CustomerTransactionDetailInterface5.jsp` |

`shared/header.jspf` và `shared/footer.jspf` chỉ là các mảnh giao diện dùng chung, không đại diện cho lớp giao diện của module. URL được Servlet xử lý rồi `forward` tới JSP trong `WEB-INF`; người dùng không gọi JSP trực tiếp. Tên điều khiển trong form (`innhaptenphim`, `subtimkiem`, `inngaybatdau`, `inngayketthuc`, `subxemthongke`, `subchonkhachhang`) theo sơ đồ.

### Đối chiếu scenario và sơ đồ

- Tìm phim: `MainInterface5.jsp` → `SearchInterface5.jsp` → `MovieServlet5.doGet/timkiemphim` → `MovieDAO5.getphimtheoten` → `Movie5[]` → `MovieListInterface5.jsp` (thuộc tính `dsphim`) → `MovieServlet5.xemchitietphim` → `MovieDAO5.getthongtinphim` → `Movie5` → `MovieDetailInterface5.jsp`.
- Doanh thu: `MainInterface5.jsp` → `ReportInterface5.jsp` → `CustomerRevenueSearchInterface5.jsp` → `CustomerRevenueServlet5.doGet/xemthongke` → `CustomerRevenueStatisticDAO5.getdskhachhang` → `CustomerRevenueStatistic5[]` → `CustomerRevenueListInterface5.jsp` (thuộc tính `dskhachhang`) → `CustomerRevenueServlet5.xemgiaodichkhachhang` → `InvoiceDAO5.getgiaodichkh` → `Invoice5[]` → `CustomerTransactionDetailInterface5.jsp` (thuộc tính `giaodich`, `khachhang`). `Customer5` ở bước cuối lấy từ danh sách thống kê của cùng phiên và khoảng ngày, không thêm lời gọi DAO ngoài biểu đồ tuần tự.
- Các trường giao diện có trong scenario đều có điều khiển hoặc vùng hiển thị `id`/`name`/`data-output` trùng tên trong JSP. Dữ liệu mẫu `db/seed.sql` không cung cấp URL poster hoặc trailer; trang chi tiết luôn hiện ảnh thay thế và dòng “Chưa có trailer” cho hai trường này.
- Sơ đồ dùng tên giao diện có dấu cách và kiểu `DateTime`, không phải định danh/kiểu Java hợp lệ trong dự án này; tên file JSP được viết liền theo Java, ngày từ `<input type="date">` dùng `LocalDate`. Sơ đồ lớp thống kê ghi `getdskhachhang(): Customer5[]` trong khi scenario và biểu đồ tuần tự ghi `CustomerRevenueStatistic5[]`; code dùng kiểu kết quả của scenario. `DAO5` mở `Connection` theo từng truy vấn và đóng bằng try-with-resources để Servlet dùng đồng thời an toàn, thay vì giữ một `Connection` dùng chung ở trường `con`.

## Cấu trúc

```text
db/                     schema.sql, seed.sql
src/main/java/.../
  config/                Kết nối MySQL
  dao/                   DAO5 và các DAO theo sơ đồ
  model/                 Movie5, User5, Customer5, Booking5, Invoice5, CustomerRevenueStatistic5
  web/                   Servlet5, bộ lọc đăng nhập
src/main/webapp/
  WEB-INF/views/         JSP cho từng bước của hai module
  assets/css/            Giao diện
```

## Chạy

Yêu cầu JDK 17+, Maven 3.9+, MySQL 8 và Apache Tomcat 8.5 hoặc 9 (Servlet `javax`). Trên máy hiện tại có MySQL 8.4 và XAMPP Tomcat 8.5.96.

1. Chạy `db/schema.sql`, sau đó `db/seed.sql` bằng MySQL Workbench hoặc `mysql`.
2. Đặt biến môi trường `CINEMAN_DB_URL`, `CINEMAN_DB_USER`, `CINEMAN_DB_PASSWORD` nếu khác mặc định `jdbc:mysql://localhost:3306/cineman`, `root`, mật khẩu rỗng.
3. Chạy `mvn clean package`.
4. Chép `target/cineman.war` vào thư mục `webapps` của Tomcat rồi mở `http://localhost:8080/cineman/`.

Lệnh Maven trên chạy kiểm tra truy vấn DAO bằng H2 ở chế độ MySQL và biên dịch toàn bộ JSP bằng Jasper của Tomcat 8.5 trước khi đóng gói WAR.

### Chạy trên máy Windows hiện tại (XAMPP Tomcat 8.5)

1. Trong MySQL Workbench, đăng nhập MySQL 8.4, mở và chạy toàn bộ `db/schema.sql`, rồi chạy `db/seed.sql` trên CSDL mới.
2. Mở PowerShell tại thư mục dự án và chạy các lệnh sau. Nhập mật khẩu MySQL khi được hỏi; **không** nhập mật khẩu vào source code.

```powershell
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-24'
$env:CATALINA_HOME = 'C:\xampp\tomcat'
$env:CINEMAN_DB_USER = 'root'
$dbSecret = Read-Host 'Mật khẩu MySQL root' -AsSecureString
$env:CINEMAN_DB_PASSWORD = [System.Net.NetworkCredential]::new('', $dbSecret).Password
Copy-Item -LiteralPath '.\target\cineman.war' -Destination 'C:\xampp\tomcat\webapps\cineman.war' -Force
& 'C:\xampp\tomcat\bin\catalina.bat' run
```

3. Khi Tomcat báo đã khởi động, mở `http://localhost:8080/cineman/`. Dùng `manager / demo123` để xem báo cáo; ngày mẫu có giao dịch là 04/09/2026 đến 13/09/2026. Dừng Tomcat bằng `Ctrl+C` trong PowerShell.

Máy này đang chạy dịch vụ `MySQL84`, nhưng tài khoản `root` yêu cầu mật khẩu. Không chạy trực tiếp các file model như `User5.java` vì đây là ứng dụng WAR, không có hàm `main()`.

Tài khoản mẫu: `customer / demo123` và `manager / demo123`. Chỉ quản lý thấy và truy cập được báo cáo. Đổi mật khẩu mẫu trước khi dùng dữ liệu thật.

Thống kê tính **số hóa đơn đã thanh toán** và **tổng tiền hóa đơn** của đơn đã xác nhận, theo `tblCustomer5 → tblBooking5 → tblInvoice5`. Khoảng ngày bao gồm cả ngày kết thúc. Đơn hủy, hóa đơn chưa thanh toán và giao dịch khách vãng lai không gắn mã khách hàng không được tính.
