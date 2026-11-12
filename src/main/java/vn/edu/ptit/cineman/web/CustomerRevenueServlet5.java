package vn.edu.ptit.cineman.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vn.edu.ptit.cineman.dao.CustomerRevenueStatisticDAO5;
import vn.edu.ptit.cineman.dao.InvoiceDAO5;
import vn.edu.ptit.cineman.model.Customer5;
import vn.edu.ptit.cineman.model.CustomerRevenueStatistic5;
import vn.edu.ptit.cineman.model.Invoice5;

@WebServlet("/reports/customer-revenue")
public class CustomerRevenueServlet5 extends HttpServlet {
    private final CustomerRevenueStatisticDAO5 customerRevenueDAO = new CustomerRevenueStatisticDAO5();
    private final InvoiceDAO5 invoiceDAO = new InvoiceDAO5();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if (!Pages5.manager(req, res)) return;
        String action = req.getParameter("action");
        if ("statistics".equals(action)) xemthongke(req, res);
        else if ("transactions".equals(action)) xemgiaodichkhachhang(req, res);
        else if ("report".equals(action)) Pages5.show(req, res, "revenue/ReportInterface5");
        else Pages5.show(req, res, "revenue/CustomerRevenueSearchInterface5");
    }

    public void xemthongke(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        LocalDate[] dates = dates(req, res);
        if (dates == null) return;
        try {
            CustomerRevenueStatistic5[] dskhachhang = customerRevenueDAO.getdskhachhang(dates[0], dates[1]);
            req.setAttribute("dskhachhang", dskhachhang);
            req.setAttribute("ngaybatdau", dates[0]);
            req.setAttribute("ngayketthuc", dates[1]);
            req.getSession().setAttribute("dskhachhang", dskhachhang);
            req.getSession().setAttribute("ngaybatdau", dates[0]);
            req.getSession().setAttribute("ngayketthuc", dates[1]);
            Pages5.show(req, res, "revenue/CustomerRevenueListInterface5");
        } catch (SQLException ex) {
            throw new ServletException("Không thể tổng hợp thống kê khách hàng.", ex);
        }
    }

    public void xemdanhsachkhachhang(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        xemthongke(req, res);
    }

    public void xemgiaodichkhachhang(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        LocalDate[] dates = dates(req, res);
        if (dates == null) return;
        try {
            int customerId = Integer.parseInt(req.getParameter("idCustomer5"));
            CustomerRevenueStatistic5[] dskhachhang = (CustomerRevenueStatistic5[]) req.getSession()
                    .getAttribute("dskhachhang");
            if (!dates[0].equals(req.getSession().getAttribute("ngaybatdau"))
                    || !dates[1].equals(req.getSession().getAttribute("ngayketthuc"))
                    || dskhachhang == null) {
                res.sendError(400, "Vui lòng xem thống kê trước khi chọn khách hàng.");
                return;
            }
            Customer5 khachhang = null;
            for (CustomerRevenueStatistic5 statistic : dskhachhang) {
                if (statistic.getKhachhang().getId() == customerId) {
                    khachhang = statistic.getKhachhang();
                    break;
                }
            }
            if (khachhang == null) { res.sendError(404, "Không tìm thấy khách hàng trong thống kê."); return; }
            Invoice5[] giaodich = invoiceDAO.getgiaodichkh(customerId, dates[0], dates[1]);
            req.setAttribute("khachhang", khachhang);
            req.setAttribute("giaodich", giaodich);
            req.setAttribute("ngaybatdau", dates[0]);
            req.setAttribute("ngayketthuc", dates[1]);
            Pages5.show(req, res, "revenue/CustomerTransactionDetailInterface5");
        } catch (NumberFormatException ex) {
            res.sendError(400, "Mã khách hàng không hợp lệ.");
        } catch (SQLException ex) {
            throw new ServletException("Không thể xem giao dịch khách hàng.", ex);
        }
    }

    private LocalDate[] dates(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            if (req.getParameter("inngaybatdau") == null || req.getParameter("inngayketthuc") == null)
                throw new IllegalArgumentException();
            LocalDate start = LocalDate.parse(req.getParameter("inngaybatdau"));
            LocalDate end = LocalDate.parse(req.getParameter("inngayketthuc"));
            if (start.isAfter(end)) throw new IllegalArgumentException();
            return new LocalDate[] { start, end };
        } catch (DateTimeParseException | IllegalArgumentException ex) {
            req.setAttribute("error", "Chọn ngày bắt đầu và kết thúc hợp lệ; ngày bắt đầu không được sau ngày kết thúc.");
            Pages5.show(req, res, "revenue/CustomerRevenueSearchInterface5");
            return null;
        }
    }
}
