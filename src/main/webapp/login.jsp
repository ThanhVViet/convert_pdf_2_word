<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Log In</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>

    <%
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();
    %>
    <link href="<%=url%>/css/login_style.css" rel="stylesheet">
    <script src="<%=url%>/js/index_js.js"></script>
    <style>
        .red{
            color: red;
        }
    </style>
</head>
<body>

<%
    String error = request.getAttribute("error") + "";
    if (error.equals("null")) {
        error = "";
    }
%>


<div class="container">
    <div class="backbox">
        <div class="loginMsg">
            <div class="textcontent">
                <p class="title">Don't have an account?</p>
                <button id="switch1"><a href="register.jsp"
                                        >SIGN UP</a></button>
            </div>
        </div>


    </div>

    <form action="/ConvertServlet?action=login" method="post">
        <div class="frontbox">
            <div class="login">
                <h2>LOG IN</h2>
                <div class="inputbox">
                    <input type="text" name="username" placeholder="  USERNAME">
                    <input type="password" name="password" placeholder="  PASSWORD">
                </div>
                <div><span class="red"><%=error %></span></div>
                <button type="submit" name="submit">LOG IN</button>
            </div>
        </div>
    </form>

</div>


</body>

</html>