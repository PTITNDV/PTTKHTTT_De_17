package vn.edu.ptit.cineman.demo.web;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vn.edu.ptit.cineman.demo.dao.AccountDAO5;
import vn.edu.ptit.cineman.web.Pages5;

@WebServlet("/login")
public class LoginServlet5 extends HttpServlet {
    private final AccountDAO5 accountDAO = new AccountDAO5();
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Pages5.show(req, res, "demo/LoginInterface5");
    }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            AccountDAO5.LoginResult result = accountDAO.authenticate(username, password);
            if (result == null) {
                req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
                Pages5.show(req, res, "demo/LoginInterface5");
                return;
            }
            HttpSession old = req.getSession(false);
            if (old != null) old.invalidate();
            HttpSession session = req.getSession(true);
            session.setAttribute("user", result.user());
            session.setAttribute("role", result.role());
            res.sendRedirect(req.getContextPath() + "/");
        } catch (SQLException ex) {
            throw new ServletException("Không thể kết nối cơ sở dữ liệu.", ex);
        }
    }
}
