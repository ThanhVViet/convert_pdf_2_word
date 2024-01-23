<%@ page import="com.example.ltm.Model.Bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/style_index.css">
    <style>
        a {
            text-decoration: none;
            color: black;
        }

        .group {
            display: flex;
        }

        body {
            background-color: #f8f9fa;
        }

        header {
            background-color: #343a40;
            padding: 10px 0;
        }

        .nav_link {
            list-style: none;
            display: flex;
            gap: 20px;
        }

        .nav_link li {
            font-size: 18px;
        }

        .nav_link a {
            color: white;
        }

        .nav_link a:hover {
            color: #f8f9fa;
        }

        .container {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
            padding: 30px;
        }

        .header h1 {
            color: #007bff;
        }

        .main {
            margin-top: 30px;
        }

        form {
            max-width: 500px;
            margin: auto;
        }

        .form-label {
            font-weight: bold;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .needs-validation input:invalid {
            border: 1px solid #dc3545;
        }

        .needs-validation .invalid-feedback {
            display: block;
        }
    </style>
</head>
<body>

<%
    User user = new User();
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    if (session.getAttribute("user") == null)
        response.sendRedirect("login.jsp");
    else {
        user = (User) session.getAttribute("user");
    }
%>
<%
    if (session.getAttribute("message") != null) {
%>
<script>alert("<%=session.getAttribute("message")%>")</script>
<%
        session.setAttribute("message", null);
    }
%>
<jsp:include page="/header.jsp"/>
<div>
    <form action="${pageContext.request.contextPath}/export" method="get">
        <button type="submit" class="mb-0 text-center btn btn-primary">Xuất file excel lịch sử convert của <%=user.getUsername()%> </button>
    </form>
</div>

<div class="container mt-5">
    <div class="header">
        <h1 class="text-center">Convert PDF To DOC</h1>
    </div>
    <div class="main">
        <div class="submain">
            <form action="/ConvertServlet?action=upload" method="post" enctype="multipart/form-data"
                  class="needs-validation" novalidate>
                <input type="hidden" name="username" value=<%=user.getUsername()%>>

                <div class="mb-3">
                    <label for="file" class="form-label">Choose PDF File</label>
                    <input type="file" id="file" name="files_upload" class="form-control" accept="application/pdf"
                           multiple required>
                </div>

                <div class="d-flex justify-content-center">
                    <button class="btn btn-primary" type="submit">Upload</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    (function () {
        'use strict';

        var forms = document.querySelectorAll('.needs-validation');

        Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated');
                }, false);
            });
    })();
</script>

</body>
</html>
