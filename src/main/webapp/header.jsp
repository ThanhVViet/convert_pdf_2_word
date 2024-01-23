<%@ page import="com.example.ltm.Model.Bean.User" %>
<%@ page import="com.example.ltm.Model.Bean.File" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath();
%>
<%
    User user = new User();


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

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link active"
                                        aria-current="page" href="index.jsp">Trang chủ</a></li>
                <li class="nav-item"><a class="nav-link" href="/ConvertServlet?action=file&userId=<%=user.getId()%>">Trang cá nhân
                </a></li>
            </ul>

            <%
                Object obj = session.getAttribute("check");
                User user1 = null;
                if (obj != null)
                    user1 = (User) obj;
                if (user1 == null) {
            %>
            <a class="btn btn-outline-primary" style="white-space: nowrap"
               href="<%=url%>/login.jsp">
                Đăng nhập
            </a>
            <%} else { %>
            <div class="group" style="display: flex">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor ">
                    <li class="nav-item dropdown dropstart"><a
                            class="nav-link dropdown-toggle" href="#" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false"> Tài khoản: <b><%=user1.getUsername() %>
                    </b></a>

                    </li>
                </ul>
                <button type="button" class="btn btn-outline-danger"><a href="/ConvertServlet?action=logout">Đăng
                    xuất</a>
                </button>
            </div>

            <% } %>

            </form>
        </div>
    </div>
</nav>


