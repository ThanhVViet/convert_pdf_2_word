<%@ page import="com.example.ltm.Model.Bean.User" %>
<%@ page import="com.example.ltm.Model.Bean.File" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ltm.util.Constaint" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style_profile.css">
    <style>
        a {
            text-decoration: none;
        }

        i {
            color: #007bff; /* Blue color for links */
        }

        body {
            background-color: #f8f9fa; /* Light gray background */
        }

        .container {
            margin-top: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
        }

        .profile-img img {
            max-width: 100%;
            border-radius: 50%;
        }

        .profile-head h4 {
            color: #007bff; /* Blue color for username */
        }

        .table {
            margin-top: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .thead-dark th {
            background-color: #343a40; /* Dark gray for table header */
            color: white;
        }

        .table td, .table th {
            text-align: center;
        }

        .table tbody tr:nth-child(even) {
            background-color: #f2f2f2; /* Light gray for even rows */
        }

        .table tbody tr:hover {
            background-color: #e0e0e0; /* Hover effect for rows */
        }
    </style>
</head>
<body>
<a style="color: white" href="index.jsp" class="back-to-home-link">
    <span>&#8592;</span> Trở về trang chủ
</a>

<%
    if (session.getAttribute("message") != null) {
%>
<script>alert("<%=session.getAttribute("message")%>")</script>
<%
        session.setAttribute("message", null);
    }
%>

<%
    User user = (User) session.getAttribute("user");
    List<File> files = (List<File>) request.getAttribute("Files");
%>

<div class="container mt-4">
    <form method="post">
        <div class="row">
            <div class="col-md-4">
                <div class="profile-img">
                    <img src="" alt="" class="img-fluid"/>
                </div>
            </div>
            <div class="col-md-6">
                <div class="profile-head">
                    <h4><%= user.getFullname() %>
                    </h4>
                </div>

            </div>
        </div>

        <div class="row mt-4">
            <div class="col-md-12">
                <div class="tab-content profile-tab" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <table class="table">
                            <thead class="thead-dark">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">File Name</th>
                                <th scope="col">Download</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (int i = 0; i < files.size(); i++) {
                            %>
                            <tr>
                                <td><%=i + 1%>
                                </td>
                                <td><%=files.get(i).getFileName()%>
                                </td>
                                <td>
                                    <%
                                        int status = files.get(i).getFileStatus();
                                        switch (status) {
                                            case Constaint.PROCESSING:
                                    %>
                                    Đang xử lý...
                                    <%
                                            break;
                                        case Constaint.CONVERT_ERROR:
                                    %>
                                    <a href="/ConvertServlet?action=error?errorCode=<%=Constaint.CONVERT_ERROR%>&userId=<%=user.getId()%>">
                                        <i>Show error</i>
                                    </a>
                                    <%
                                            break;
                                        case Constaint.SUCCESS:
                                    %>
                                    <a href="/ConvertServlet?action=download&fileId=<%=files.get(i).getFileId()%>">
                                        <i>Download</i>
                                    </a>
                                    <%
                                            break;
                                        case Constaint.UPLOAD_ERROR:
                                    %>
                                    <a href="/ConvertPdfServlet?action=error&errorCode=<%=Constaint.UPLOAD_ERROR%>&userId=<%=user.getId()%>">
                                        <i>Show error</i>
                                    </a>
                                    <%
                                            break;
                                        default:
                                    %>
                                    <a href="/ConvertServlet?action=error&errorCode=4&userId=<%=user.getId()%>">
                                        <i>Show error</i>
                                    </a>
                                    <%
                                                break;
                                        }
                                    %>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>
