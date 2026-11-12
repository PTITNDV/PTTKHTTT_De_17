package vn.edu.ptit.cineman.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class Pages5 {
    private Pages5() {}
    public static void show(HttpServletRequest req, HttpServletResponse res, String page)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/" + page + ".jsp").forward(req, res);
    }
    static boolean manager(HttpServletRequest req, HttpServletResponse res) throws IOException {
        if (!"MANAGER".equals(req.getSession(false).getAttribute("role"))) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Chỉ nhân viên quản lý được xem báo cáo.");
            return false;
        }
        return true;
    }
}
