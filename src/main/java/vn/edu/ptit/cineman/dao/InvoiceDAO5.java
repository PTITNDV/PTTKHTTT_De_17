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
import vn.edu.ptit.cineman.model.Invoice5;

public class InvoiceDAO5 extends DAO5 {
    public InvoiceDAO5() { super(); }
    public InvoiceDAO5(DataSource dataSource) { super(dataSource); }

    public Invoice5[] getgiaodichkh(int id, LocalDate ngaybatdau, LocalDate ngayketthuc)
            throws SQLException {
        String sql = "SELECT i.id, i.booking_id, i.ngaylap, i.tongtien, i.diemthuong, "
            + "i.soluongve, i.trangthai FROM tblInvoice5 i "
            + "JOIN tblBooking5 b ON b.id = i.booking_id "
            + "JOIN tblCustomer5 c ON c.user_id = b.customer_id "
            + "WHERE c.user_id = ? AND i.trangthai = 'PAID' AND b.trangthai = 'CONFIRMED' "
            + "AND i.ngaylap >= ? AND i.ngaylap < ? ORDER BY i.ngaylap DESC, i.id DESC";
        List<Invoice5> invoices = new ArrayList<>();
        try (Connection con = connection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setTimestamp(2, Timestamp.valueOf(ngaybatdau.atStartOfDay()));
            ps.setTimestamp(3, Timestamp.valueOf(ngayketthuc.plusDays(1).atStartOfDay()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Invoice5 invoice = new Invoice5();
                    invoice.setId(rs.getInt("id"));
                    invoice.setBookingId(rs.getInt("booking_id"));
                    invoice.setNgaylap(rs.getTimestamp("ngaylap").toLocalDateTime());
                    invoice.setTongtien(rs.getBigDecimal("tongtien"));
                    invoice.setDiemthuong(rs.getInt("diemthuong"));
                    invoice.setSoluongve(rs.getInt("soluongve"));
                    invoice.setTrangthai(rs.getString("trangthai"));
                    invoices.add(invoice);
                }
            }
        }
        return invoices.toArray(new Invoice5[0]);
    }

}
