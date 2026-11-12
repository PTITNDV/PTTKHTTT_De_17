<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../shared/header.jspf" %>
<div class="breadcrumb"><a href="${pageContext.request.contextPath}/">Trang chủ</a><span>/</span><a href="${pageContext.request.contextPath}/movies">Tìm kiếm phim</a><span>/</span>Kết quả</div>
<section class="page-heading">
  <div class="eyebrow">KẾT QUẢ TÌM KIẾM</div>
  <h1>Phim phù hợp với “<c:out value="${keyword}" />”</h1>
  <p>Tìm thấy <strong>${fn:length(dsphim)}</strong> bộ phim.</p>
</section>
<c:choose>
  <c:when test="${empty dsphim}">
    <div class="empty-state"><div class="empty-icon">⌕</div><h2>Không tìm thấy bộ phim nào phù hợp</h2><p>Hãy thử một từ khóa khác.</p><a class="button primary" href="${pageContext.request.contextPath}/movies">Tìm lại →</a></div>
  </c:when>
  <c:otherwise>
    <div class="table-wrap"><table>
      <thead><tr><th>STT</th><th>Tên phim</th><th></th></tr></thead>
      <tbody><c:forEach var="phim" items="${dsphim}" varStatus="loop"><tr>
        <td class="muted" data-output="outthutu">${loop.count}</td>
        <td data-output="outtenphim"><strong><c:out value="${phim.tenphim}" /></strong></td>
        <td class="align-right"><form action="${pageContext.request.contextPath}/movies" method="get">
          <input type="hidden" name="action" value="detail"><input type="hidden" name="idMovie5" value="${phim.id}">
          <button class="text-link inline-button" type="submit" name="subchonphim" value="${phim.id}">Xem chi tiết →</button>
        </form></td>
      </tr></c:forEach></tbody>
    </table></div>
    <a class="back-link" href="${pageContext.request.contextPath}/movies">← Tìm phim khác</a>
  </c:otherwise>
</c:choose>
<%@ include file="../shared/footer.jspf" %>
