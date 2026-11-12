<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../shared/header.jspf" %>
<div class="breadcrumb"><a href="${pageContext.request.contextPath}/">Trang chủ</a><span>/</span><a href="${pageContext.request.contextPath}/reports/customer-revenue?action=report">Báo cáo</a><span>/</span>Doanh thu khách hàng</div>
<section class="page-heading"><div class="eyebrow">DANH SÁCH THỐNG KÊ</div><h1>Khách hàng theo doanh thu</h1><p>Từ <strong><c:out value="${ngaybatdau}" /></strong> đến <strong><c:out value="${ngayketthuc}" /></strong> · ${fn:length(dskhachhang)} khách hàng có giao dịch.</p></section>
<c:choose>
  <c:when test="${empty dskhachhang}"><div class="empty-state"><div class="empty-icon">▥</div><h2>Không có giao dịch trong khoảng thời gian này</h2><p>Hãy chọn khoảng ngày khác để xem thống kê.</p><a class="button primary" href="${pageContext.request.contextPath}/reports/customer-revenue">Chọn lại ngày →</a></div></c:when>
  <c:otherwise><div class="table-wrap"><table>
    <thead><tr><th>STT</th><th>Tên khách hàng</th><th>Số lần giao dịch</th><th>Tổng doanh thu</th><th></th></tr></thead>
    <tbody><c:forEach var="stat" items="${dskhachhang}" varStatus="loop"><tr>
      <td class="muted" data-output="outthutu">${loop.count}</td>
      <td data-output="outtenkhachhang"><strong><c:out value="${stat.khachhang.hoten}" /></strong></td>
      <td data-output="outsolangiaodich">${stat.solangiaodich}</td>
      <td class="money" data-output="outtongdoanhthu"><fmt:formatNumber value="${stat.tongdoanhthu}" type="number" maxFractionDigits="0" /> ₫</td>
      <td class="align-right"><form action="${pageContext.request.contextPath}/reports/customer-revenue" method="get">
        <input type="hidden" name="action" value="transactions"><input type="hidden" name="idCustomer5" value="${stat.khachhang.id}">
        <input type="hidden" name="inngaybatdau" value="${ngaybatdau}"><input type="hidden" name="inngayketthuc" value="${ngayketthuc}">
        <button class="text-link inline-button" type="submit" name="subchonkhachhang" value="${stat.khachhang.id}">Xem giao dịch →</button>
      </form></td>
    </tr></c:forEach></tbody>
  </table></div></c:otherwise>
</c:choose>
<a class="back-link" href="${pageContext.request.contextPath}/reports/customer-revenue">← Chọn khoảng thời gian khác</a>
<%@ include file="../shared/footer.jspf" %>
