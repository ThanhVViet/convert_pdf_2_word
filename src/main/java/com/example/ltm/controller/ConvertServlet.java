package com.example.ltm.controller;


import com.example.ltm.Model.BO.*;
import com.example.ltm.Model.Bean.File;
import com.example.ltm.Model.Bean.User;
import com.example.ltm.Model.DAO.UserDAO;
import com.example.ltm.util.Constaint;
import com.example.ltm.util.MaHoa;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 500)
@WebServlet(name = "ConvertServlet", value = "/ConvertServlet")
public class ConvertServlet extends HttpServlet {

    private UserBO userBO = new UserBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "logout":
                logout(request, response);
                break;
            case "download":
                download(request, response);
                break;
            case "error":
                showError(request, response);
                break;
            case "file":
                file(request, response);
                break;
            default:
                showLoginForm(request, response);
                break;
        }
    }


    private void file(HttpServletRequest request, HttpServletResponse response) {
        int userId = 0;

        try {
            userId = Integer.parseInt(request.getParameter("userId"));
        } catch (Exception e) {

        }

        if (userId != 0) {
            List<File> files = ShowProfileBO.GetProcessFile(userId);
            request.setAttribute("Files", files);
            String src = "/profile.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(src);
            try {
                rd.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showError(HttpServletRequest request, HttpServletResponse response) {
        int errorCode = 0;
        int userId = 0;
        try {
            errorCode = Integer.parseInt(request.getParameter("errorCode"));
            userId = Integer.parseInt(request.getParameter("userId"));
        } catch (Exception e) {

        }

        switch (errorCode) {
            case Constaint.PROCESSING: {
                break;
            }
            case Constaint.CONVERT_ERROR: {
                request.getSession().setAttribute("message",
                        "Có lỗi xảy ra trong quá trình chuyển đổi, vui lòng kiểm tra lại tên file và thử lại sau!");
                break;
            }
            case Constaint.UPLOAD_ERROR: {
                request.getSession().setAttribute("message",
                        "Có lỗi xảy ra trong quá trình upload file, vui lòng thử lại sau!");
                break;
            }
            default:
                request.getSession().setAttribute("message", "Có lỗi xảy ra, vui lòng thử lại sau!");
                break;
        }

        try {
            response.sendRedirect("UserProfileServlet?userId=" + userId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private java.io.File GetFolderPath(String folder) {
        java.io.File folderUpload = new java.io.File(System.getProperty("user.home") + "/" + folder);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    private void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int fileId = 0;

        try {
            fileId = Integer.parseInt(request.getParameter("fileId"));
        } catch (Exception e) {
        }

        if (fileId != 0) {
            File file = DownloadFileBO.GetFile(fileId);

            String scrpath = GetFolderPath("docxs").getAbsolutePath() + java.io.File.separator + file.getFileName();
            Path path = Paths.get(scrpath);
            byte[] data = new byte[0];
            try {
                data = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; fileName=" + file.getFileName());
            response.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));

            OutputStream outStream = null;
            try {
                outStream = response.getOutputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while (true) {
                try {
                    if (!((bytesRead = inputStream.read(buffer)) != -1)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                outStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outStream.close();

        } else {
            request.getSession().setAttribute("message", "File không tồn tại hoặc đã bị xóa!");
        }
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "upload":
                upload(request, response);
                break;
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tenDangNhap = request.getParameter("r_name");
        String matKhau = request.getParameter("rpassword");
        String fullname = request.getParameter("rfname");


        request.setAttribute("username", tenDangNhap);
        request.setAttribute("fullname", fullname);


        String url = "";

        String baoLoi = "";
        UserDAO userDAO = new UserDAO();

        if (userDAO.checkUS(tenDangNhap)) {
            baoLoi += "Tên đăng nhập đã tồn tại, vui lòng chọn tên đăng nhập khác.<br/>";
        }
        matKhau = MaHoa.toSHA1(matKhau);
        request.setAttribute("baoLoi", baoLoi);

        if (!baoLoi.isEmpty()) {
            url = "/register.jsp";
        } else {
            User kh = new User(tenDangNhap, matKhau, fullname);
            userDAO.save(kh);
            url = "/success.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }


    private void upload(HttpServletRequest request, HttpServletResponse response) {
        try {
            Part filesPart = request.getPart("files_upload");
            if (filesPart != null && filesPart.getSize() != 0) {
                String username = request.getParameter("username");
                User user = userBO.findUserByName(username);
                Thread t = new Thread(new UploadFileBO(request, user));
                t.start();
                request.getSession().setAttribute("message", "Uploading...");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        try {
            response.sendRedirect("index.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        session.invalidate();

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();

        response.sendRedirect(url + "/login.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("username");
        String password = request.getParameter("password");

        password = MaHoa.toSHA1(password);

        User user = new User();

        user.setUsername(user_name);
        user.setPassword(password);

        UserDAO userDAO = new UserDAO();
        User check = userDAO.selectByUsernameAndPassWord(user);
        User user1 = userBO.findUserByName(user_name);
        String url = "";
        if (check != null) {
            HttpSession session = request.getSession();
            session.setAttribute("check", check);
            request.getSession().setAttribute("user", user1);
            url = "/index.jsp";
        } else {
            request.setAttribute("error", "Mật khẩu hoặc tên đăng nhập không đúng !!!");
            url = "/login.jsp";
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
        requestDispatcher.forward(request, response);

    }
}
