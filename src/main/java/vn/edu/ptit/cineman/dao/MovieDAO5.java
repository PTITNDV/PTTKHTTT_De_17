package vn.edu.ptit.cineman.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import vn.edu.ptit.cineman.model.Movie5;

public class MovieDAO5 extends DAO5 {
    public MovieDAO5() { super(); }
    public MovieDAO5(DataSource dataSource) { super(dataSource); }

    public Movie5[] getphimtheoten(String tenphim) throws SQLException {
        String sql = "SELECT id, tenphim, anhposter, daodien, videotrailer, dienvien, "
            + "thoiluong, gioihantuoi, mota, ngaykhoichieu FROM tblMovie5 "
            + "WHERE tenphim LIKE ? ESCAPE '!' ORDER BY tenphim, id";
        String keyword = tenphim == null ? "" : tenphim.trim();
        String pattern = "%" + keyword.replace("!", "!!").replace("%", "!%")
            .replace("_", "!_") + "%";
        List<Movie5> movies = new ArrayList<>();
        try (Connection con = connection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, pattern);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) movies.add(map(rs));
            }
        }
        return movies.toArray(new Movie5[0]);
    }

    public Movie5 getthongtinphim(int idMovie5) throws SQLException {
        String sql = "SELECT id, tenphim, anhposter, daodien, videotrailer, dienvien, "
            + "thoiluong, gioihantuoi, mota, ngaykhoichieu FROM tblMovie5 WHERE id = ?";
        try (Connection con = connection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMovie5);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    private Movie5 map(ResultSet rs) throws SQLException {
        Movie5 movie = new Movie5();
        movie.setId(rs.getInt("id"));
        movie.setTenphim(rs.getString("tenphim"));
        movie.setAnhposter(rs.getString("anhposter"));
        movie.setDaodien(rs.getString("daodien"));
        movie.setVideotrailer(rs.getString("videotrailer"));
        movie.setDienvien(rs.getString("dienvien"));
        movie.setThoiluong(rs.getInt("thoiluong"));
        movie.setGioihantuoi(rs.getInt("gioihantuoi"));
        movie.setMota(rs.getString("mota"));
        Date date = rs.getDate("ngaykhoichieu");
        movie.setNgaykhoichieu(date == null ? null : date.toLocalDate());
        return movie;
    }
}
