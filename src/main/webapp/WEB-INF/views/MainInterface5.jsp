<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="shared/header.jspf" %>
<section class="hero"><div class="eyebrow">CHÀO MỪNG ĐẾN CINEMAN</div><h1>Khám phá câu chuyện<br><em>trên màn ảnh lớn.</em></h1><p>Tra cứu thông tin phim và theo dõi hiệu quả kinh doanh của rạp trong một không gian làm việc rõ ràng.</p></section>
<section class="module-grid" aria-label="Chức năng">
  <a id="subtimkiemphim" class="module-card movie-card" href="${pageContext.request.contextPath}/movies">
    <h2>Tìm kiếm thông tin phim</h2>
  </a>
  <c:if test="${sessionScope.role == 'MANAGER'}">
    <a id="subxembaocao" class="module-card report-card" href="${pageContext.request.contextPath}/reports/customer-revenue?action=report">
      <h2>Thống kê khách hàng</h2>
    </a>
  </c:if>
</section>
<%@ include file="shared/footer.jspf" %>
