<%--
  Created by IntelliJ IDEA.
  User: thanhviet
  Date: 02/12/2023
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style_register.css">
    <style>
        .red {
            color: red;
        }
    </style>
</head>
<body>

<%
    String baoLoi = request.getAttribute("baoLoi") + "";
    baoLoi = (baoLoi.equals("null")) ? "" : baoLoi;

    String tenDangNhap = request.getAttribute("username") + "";
    tenDangNhap = (tenDangNhap.equals("null")) ? "" : tenDangNhap;

    String hoVaTen = request.getAttribute("fullname") + "";
    hoVaTen = (hoVaTen.equals("null")) ? "" : hoVaTen;
%>
<div class="background">
    <div class="shape"></div>
    <div class="shape"></div>
</div>

<form action="/ConvertServlet?action=register" method="post">

    <h2>Register Here</h2>
    <div class="inputbox">
        <input type="text" name="rfname" required value="<%=hoVaTen%>" placeholder="  FULLNAME">
        <input type="text" name="r_name" required value="<%=tenDangNhap%>" placeholder="  USERNAME">
        <input type="password" required name="rpassword" placeholder="  PASSWORD">
    </div>
    <br/>
    <div class="red" id="baoLoi">
        <%=baoLoi %>
    </div>
    <button>SIGN UP</button>

</form>
</body>
</html>
