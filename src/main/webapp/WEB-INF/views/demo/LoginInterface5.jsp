<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html><html lang="vi"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Đăng nhập · CineMan</title><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"></head>
<body class="login-page"><main class="login-card"><div class="eyebrow">HỆ THỐNG RẠP CHIẾU PHIM</div><h1>CINE<span>MAN</span></h1><p>Đăng nhập để tìm phim hoặc xem báo cáo khách hàng.</p>
<c:if test="${not empty error}"><div class="alert" role="alert"><c:out value="${error}" /></div></c:if>
<form action="${pageContext.request.contextPath}/login" method="post" class="form-stack"><label>Tên đăng nhập<input name="username" required autocomplete="username" value="<c:out value='${param.username}' />"></label><label>Mật khẩu<input type="password" name="password" required autocomplete="current-password"></label><button class="button primary" type="submit">Đăng nhập <span>→</span></button></form>
<div class="demo-note">Dữ liệu mẫu: <strong>manager / demo123</strong> hoặc <strong>customer / demo123</strong></div></main></body></html>
