<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../shared/header.jspf" %>
<div class="breadcrumb"><a href="${pageContext.request.contextPath}/">Trang chủ</a><span>/</span><a href="${pageContext.request.contextPath}/movies">Tìm kiếm phim</a><span>/</span>Chi tiết phim</div>
<section class="detail-layout">
  <div class="poster" data-output="outanhphim">
    <c:choose>
      <c:when test="${not empty phim.anhposter}"><img src="<c:out value='${phim.anhposter}' />" alt="Poster phim <c:out value='${phim.tenphim}' />"></c:when>
      <c:otherwise><img src="${pageContext.request.contextPath}/assets/images/poster-placeholder.svg" alt="Chưa có poster phim <c:out value='${phim.tenphim}' />"></c:otherwise>
    </c:choose>
  </div>
  <div>
    <div class="eyebrow">THÔNG TIN PHIM</div>
    <h1 class="detail-title" data-output="outtenphim"><c:out value="${phim.tenphim}" /></h1>
    <p class="description" data-output="outmota"><c:out value="${phim.mota}" default="Chưa có mô tả." /></p>
    <dl class="facts">
      <div><dt>Đạo diễn</dt><dd data-output="outdaodien"><c:out value="${phim.daodien}" default="Đang cập nhật" /></dd></div>
      <div><dt>Diễn viên</dt><dd data-output="outdienvien"><c:out value="${phim.dienvien}" default="Đang cập nhật" /></dd></div>
      <div><dt>Ngày khởi chiếu</dt><dd data-output="outngaykhoichieu"><c:out value="${phim.ngaykhoichieu}" default="Đang cập nhật" /></dd></div>
      <div><dt>Thời lượng</dt><dd data-output="outthoiluong">${phim.thoiluong} phút</dd></div>
      <div><dt>Giới hạn tuổi</dt><dd data-output="outgioihantuoi">T${phim.gioihantuoi}</dd></div>
      <div><dt>Trailer</dt><dd data-output="outvideotrailler"><c:choose><c:when test="${not empty phim.videotrailer}"><a class="text-link" href="<c:out value='${phim.videotrailer}' />" target="_blank" rel="noopener noreferrer">Xem trailer ↗</a></c:when><c:otherwise>Chưa có trailer</c:otherwise></c:choose></dd></div>
    </dl>
    <a class="back-link" href="${pageContext.request.contextPath}/movies">← Quay lại tìm kiếm</a>
  </div>
</section>
<%@ include file="../shared/footer.jspf" %>
