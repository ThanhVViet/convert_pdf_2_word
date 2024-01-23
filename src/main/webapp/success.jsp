<%--
  Created by IntelliJ IDEA.
  User: thanhviet
  Date: 03/12/2023
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Đăng kí tài khoản thành công! Hãy vui lòng đợi một lát !!! Bạn đang quay lại trang đăng nhập.</h1>
<script>
  setTimeout(function () {
    window.location.href = '/login.jsp';
  }, 3000);
</script>
</body>
</html>
