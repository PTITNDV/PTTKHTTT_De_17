package vn.edu.ptit.cineman.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import vn.edu.ptit.cineman.model.Customer5;
import vn.edu.ptit.cineman.model.CustomerRevenueStatistic5;

public class CustomerRevenueStatisticDAO5 extends DAO5 {
    public CustomerRevenueStatisticDAO5() { super(); }
    public CustomerRevenueStatisticDAO5(DataSource dataSource) { super(dataSource); }

    public CustomerRevenueStatistic5[] getdskhachhang(LocalDate ngaybatdau, LocalDate ngayketthuc)
            throws SQLException {
        String sql = "SELECT c.user_id AS customer_id, u.hoten, COUNT(i.id) AS solangiaodich, "
            + "SUM(i.tongtien) AS tongdoanhthu FROM tblCustomer5 c "
            + "JOIN tblUser5 u ON u.id = c.user_id "
            + "JOIN tblBooking5 b ON b.customer_id = c.user_id "
            + "JOIN tblInvoice5 i ON i.booking_id = b.id "
            + "WHERE i.trangthai = 'PAID' AND b.trangthai = 'CONFIRMED' "
            + "AND i.ngaylap >= ? AND i.ngaylap < ? "
            + "GROUP BY c.user_id, u.hoten ORDER BY tongdoanhthu DESC, c.user_id";
        List<CustomerRevenueStatistic5> statistics = new ArrayList<>();
        try (Connection con = connection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(ngaybatdau.atStartOfDay()));
            ps.setTimestamp(2, Timestamp.valueOf(ngayketthuc.plusDays(1).atStartOfDay()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Customer5 customer = new Customer5();
                    customer.setId(rs.getInt("customer_id"));
                    customer.setHoten(rs.getString("hoten"));
                    CustomerRevenueStatistic5 stat = new CustomerRevenueStatistic5();
                    stat.setKhachhang(customer);
                    stat.setNgaybatdau(ngaybatdau);
                    stat.setNgayketthuc(ngayketthuc);
                    stat.setSolangiaodich(rs.getInt("solangiaodich"));
                    stat.setTongdoanhthu(rs.getBigDecimal("tongdoanhthu"));
                    statistics.add(stat);
                }
            }
        }
        return statistics.toArray(new CustomerRevenueStatistic5[0]);
    }
}
