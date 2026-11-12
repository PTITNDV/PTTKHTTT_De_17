package vn.edu.ptit.cineman.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModuleDaoTest {
    private JdbcDataSource source;

    @BeforeEach void setup() throws Exception {
        source = new JdbcDataSource();
        source.setURL("jdbc:h2:mem:modules;MODE=MySQL;DB_CLOSE_DELAY=-1");
        try (Connection con = source.getConnection(); Statement s = con.createStatement()) {
            s.execute("DROP ALL OBJECTS");
            s.execute("CREATE TABLE tblMovie5 (id INT PRIMARY KEY, tenphim VARCHAR(255), anhposter VARCHAR(500), daodien VARCHAR(180), videotrailer VARCHAR(500), dienvien VARCHAR(500), thoiluong INT, gioihantuoi INT, mota VARCHAR(500), ngaykhoichieu DATE)");
            s.execute("CREATE TABLE tblUser5 (id INT PRIMARY KEY, hoten VARCHAR(120), sodienthoai VARCHAR(20), email VARCHAR(190), ngaysinh DATE)");
            s.execute("CREATE TABLE tblCustomer5 (user_id INT PRIMARY KEY)");
            s.execute("CREATE TABLE tblBooking5 (id INT PRIMARY KEY, customer_id INT, trangthai VARCHAR(20))");
            s.execute("CREATE TABLE tblInvoice5 (id INT PRIMARY KEY, booking_id INT, ngaylap TIMESTAMP, tongtien DECIMAL(14,2), diemthuong INT, soluongve INT, trangthai VARCHAR(20))");
            s.execute("INSERT INTO tblMovie5 (id,tenphim,thoiluong,gioihantuoi) VALUES (1,'Quỷ Nhập Tràng',109,18),(2,'Quỷ Cẩu',99,18),(3,'100% Yêu',90,13)");
            s.execute("INSERT INTO tblUser5 (id,hoten) VALUES (1,'Nguyễn Văn A'),(2,'Trần Thị B')");
            s.execute("INSERT INTO tblCustomer5 VALUES (1),(2)");
            s.execute("INSERT INTO tblBooking5 VALUES (1,1,'CONFIRMED'),(2,1,'CONFIRMED'),(3,2,'CONFIRMED'),(4,1,'CANCELLED'),(5,NULL,'CONFIRMED')");
            s.execute("INSERT INTO tblInvoice5 VALUES (1,1,'2026-09-04 00:00:00',100000,10,1,'PAID'),(2,2,'2026-09-13 23:59:59',200000,20,2,'PAID'),(3,3,'2026-09-09 12:00:00',50000,5,1,'PAID'),(4,4,'2026-09-10 12:00:00',999000,0,1,'PAID'),(5,5,'2026-09-10 12:00:00',999000,0,1,'PAID')");
        }
    }

    @Test void movieSearchMatchesNameAndTreatsWildcardsLiterally() throws Exception {
        MovieDAO5 dao = new MovieDAO5(source);
        assertEquals(2, dao.getphimtheoten("Quỷ").length);
        assertEquals("100% Yêu", dao.getphimtheoten("%")[0].getTenphim());
        assertEquals(1, dao.getphimtheoten("%").length);
        assertEquals("Quỷ Nhập Tràng", dao.getthongtinphim(1).getTenphim());
        assertNull(dao.getthongtinphim(999));
    }

    @Test void revenueAndTransactionsUseSameInclusiveDateAndPaidRules() throws Exception {
        LocalDate start = LocalDate.of(2026, 9, 4), end = LocalDate.of(2026, 9, 13);
        var stats = new CustomerRevenueStatisticDAO5(source).getdskhachhang(start, end);
        assertEquals(2, stats.length);
        assertEquals(1, stats[0].getKhachhang().getId());
        assertEquals(2, stats[0].getSolangiaodich());
        assertEquals(new BigDecimal("300000.00"), stats[0].getTongdoanhthu());
        var invoices = new InvoiceDAO5(source).getgiaodichkh(1, start, end);
        assertEquals(2, invoices.length);
        assertEquals(2, invoices[0].getId());
        assertEquals(new BigDecimal("300000.00"), java.util.Arrays.stream(invoices).map(i -> i.getTongtien()).reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
