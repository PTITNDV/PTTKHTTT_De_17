package vn.edu.ptit.cineman.web;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vn.edu.ptit.cineman.dao.MovieDAO5;
import vn.edu.ptit.cineman.model.Movie5;

@WebServlet("/movies")
public class MovieServlet5 extends HttpServlet {
    private final MovieDAO5 movieDAO = new MovieDAO5();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("search".equals(action)) timkiemphim(req, res);
        else if ("detail".equals(action)) xemchitietphim(req, res);
        else Pages5.show(req, res, "movie/SearchInterface5");
    }

    public void timkiemphim(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String keyword = req.getParameter("innhaptenphim");
        if (keyword == null || keyword.isBlank()) {
            req.setAttribute("error", "Vui lòng nhập tên phim cần tìm.");
            Pages5.show(req, res, "movie/SearchInterface5");
            return;
        }
        try {
            req.setAttribute("keyword", keyword.trim());
            req.setAttribute("dsphim", movieDAO.getphimtheoten(keyword));
            Pages5.show(req, res, "movie/MovieListInterface5");
        } catch (SQLException ex) {
            throw new ServletException("Không thể tìm kiếm phim.", ex);
        }
    }

    public void xemchitietphim(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("idMovie5"));
            Movie5 movie = movieDAO.getthongtinphim(id);
            if (movie == null) { res.sendError(404, "Không tìm thấy phim."); return; }
            req.setAttribute("phim", movie);
            Pages5.show(req, res, "movie/MovieDetailInterface5");
        } catch (NumberFormatException ex) {
            res.sendError(400, "Mã phim không hợp lệ.");
        } catch (SQLException ex) {
            throw new ServletException("Không thể xem thông tin phim.", ex);
        }
    }
}
