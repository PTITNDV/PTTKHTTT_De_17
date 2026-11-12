<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../shared/header.jspf" %>
<div class="breadcrumb"><a href="${pageContext.request.contextPath}/">Trang chủ</a><span>/</span><a href="${pageContext.request.contextPath}/reports/customer-revenue?action=report">Báo cáo</a><span>/</span>Doanh thu khách hàng</div>
<section class="page-heading"><div class="eyebrow">BÁO CÁO KHÁCH HÀNG</div><h1>Thống kê theo doanh thu.</h1><p>Chọn ngày bắt đầu và kết thúc để xem khách hàng có giao dịch trong kỳ.</p></section>
<form class="search-panel" action="${pageContext.request.contextPath}/reports/customer-revenue" method="get"><input type="hidden" name="action" value="statistics"><div class="date-grid"><label for="inngaybatdau">Ngày bắt đầu<input id="inngaybatdau" type="date" name="inngaybatdau" value="<c:out value='${param.inngaybatdau}' />" required></label><label for="inngayketthuc">Ngày kết thúc<input id="inngayketthuc" type="date" name="inngayketthuc" value="<c:out value='${param.inngayketthuc}' />" required></label></div><button id="subxemthongke" class="button primary" type="submit">Xem thống kê →</button></form>
<c:if test="${not empty error}"><div class="alert" role="alert"><c:out value="${error}" /></div></c:if>
<%@ include file="../shared/footer.jspf" %>
