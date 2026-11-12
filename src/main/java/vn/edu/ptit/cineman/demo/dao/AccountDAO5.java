package vn.edu.ptit.cineman.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import vn.edu.ptit.cineman.dao.DAO5;
import vn.edu.ptit.cineman.model.User5;

public class AccountDAO5 extends DAO5 {
    public AccountDAO5() { super(); }
    public AccountDAO5(DataSource dataSource) { super(dataSource); }

    public LoginResult authenticate(String username, String password) throws SQLException {
        String sql = "SELECT u.id, u.hoten, a.vaitro FROM tblAccount5 a "
            + "JOIN tblUser5 u ON u.id = a.user_id "
            + "WHERE a.tendangnhap = ? AND a.matkhau_hash = SHA2(?, 256)";
        try (Connection con = connection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                User5 user = new User5();
                user.setId(rs.getInt("id"));
                user.setHoten(rs.getString("hoten"));
                return new LoginResult(user, rs.getString("vaitro"));
            }
        }
    }

    public record LoginResult(User5 user, String role) {}
}
