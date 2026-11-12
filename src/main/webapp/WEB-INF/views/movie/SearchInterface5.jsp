<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../shared/header.jspf" %>
<div class="breadcrumb"><a href="${pageContext.request.contextPath}/">Trang chủ</a><span>/</span>Tìm kiếm phim</div>
<section class="page-heading"><div class="eyebrow">THƯ VIỆN PHIM</div><h1>Tìm bộ phim bạn muốn xem.</h1><p>Nhập một phần hoặc toàn bộ tên phim để bắt đầu tìm kiếm.</p></section>
<form class="search-panel" action="${pageContext.request.contextPath}/movies" method="get"><input type="hidden" name="action" value="search"><label for="innhaptenphim">Tên phim</label><div class="search-row"><input id="innhaptenphim" name="innhaptenphim" placeholder="Ví dụ: Quỷ Nhập Tràng" value="<c:out value='${param.innhaptenphim}' />" required autofocus><button id="subtimkiem" class="button primary" type="submit">Tìm kiếm →</button></div></form>
<c:if test="${not empty error}"><div class="alert" role="alert"><c:out value="${error}" /></div></c:if>
<%@ include file="../shared/footer.jspf" %>
