<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../shared/header.jspf" %>
<div class="breadcrumb"><a href="${pageContext.request.contextPath}/">Trang chủ</a><span>/</span><a href="${pageContext.request.contextPath}/reports/customer-revenue?action=report">Báo cáo</a><span>/</span>Giao dịch khách hàng</div>
<section class="page-heading"><div class="eyebrow">CHI TIẾT GIAO DỊCH</div><h1 data-output="outtenkhachhang"><c:out value="${khachhang.hoten}" /></h1><p>Từ <strong data-output="outngaybatdau"><c:out value="${ngaybatdau}" /></strong> đến <strong data-output="outngayketthuc"><c:out value="${ngayketthuc}" /></strong> · ${fn:length(giaodich)} lần giao dịch.</p></section>
<c:set var="total" value="0" />
<c:choose>
  <c:when test="${empty giaodich}"><div class="empty-state"><h2>Không có giao dịch hợp lệ</h2><p>Khách hàng này chưa có hóa đơn được thanh toán trong kỳ.</p></div></c:when>
  <c:otherwise><div class="table-wrap"><table>
    <thead><tr><th>STT</th><th>Ngày giao dịch</th><th>Số lượng vé</th><th>Điểm thưởng</th><th>Thành tiền</th></tr></thead>
    <tbody><c:forEach var="invoice" items="${giaodich}" varStatus="loop"><c:set var="total" value="${total + invoice.tongtien}" /><tr>
      <td class="muted" data-output="outthutu">${loop.count}</td>
      <td data-output="outngaygiaodich"><c:out value="${invoice.ngaylap}" /></td>
      <td data-output="outsoluongve">${invoice.soluongve}</td>
      <td data-output="outdiemthuong">${invoice.diemthuong}</td>
      <td class="money" data-output="outthanhtien"><fmt:formatNumber value="${invoice.tongtien}" type="number" maxFractionDigits="0" /> ₫</td>
    </tr></c:forEach></tbody>
  </table></div><div class="total-row"><span>Tổng tiền các giao dịch</span><strong data-output="outtongtien"><fmt:formatNumber value="${total}" type="number" maxFractionDigits="0" /> ₫</strong></div></c:otherwise>
</c:choose>
<a class="back-link" href="${pageContext.request.contextPath}/reports/customer-revenue?action=statistics&amp;inngaybatdau=${ngaybatdau}&amp;inngayketthuc=${ngayketthuc}">← Quay lại danh sách thống kê</a>
<%@ include file="../shared/footer.jspf" %>
