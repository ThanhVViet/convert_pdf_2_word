package com.example.ltm.Model.DAO;

import com.example.ltm.Model.Bean.File;
import com.example.ltm.Model.Bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final String INSERT = "INSERT INTO user(username, password, fullname)\n"
            + " VALUES(?,?,?)";
    private final String SELECT = "SELECT * FROM user";

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }
        } catch (Exception e) {
        }
        return userList;
    }



    public int save(User t) {
        int ketQua = 0;
        Connection con = JDBCUtil.getConnection();
        try {

            PreparedStatement st = con.prepareStatement(INSERT);

            st.setString(1, t.getUsername());
            st.setString(2, t.getPassword());
            st.setString(3, t.getFullname());

            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ketQua;
    }

    public boolean checkUS(String username) {
        boolean ketQua = false;
        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM user WHERE username=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ketQua = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }
    public User selectByUsernameAndPassWord(User t) {
        User ketQua = null;
        try {

            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM user WHERE username=? and password=?";
            PreparedStatement st = con.prepareStatement(sql);
            System.out.println(t.getUsername()+"/"+t.getPassword());

            st.setString(1, t.getUsername());
            st.setString(2, t.getPassword());
            System.out.println(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int maKhacHang = rs.getInt("uid");
                String tenDangNhap = rs.getString("username");
                String matKhau = rs.getString("password");
                String hoVaTen = rs.getString("fullname");

                ketQua = new User(maKhacHang, tenDangNhap, matKhau, hoVaTen);

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return ketQua;
    }
    public static User getUser(String username){
        User user = new User();
        try{
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                int userId = rs.getInt("uid");
                String password = rs.getString("password");
                String fullName = rs.getString("fullname");
                user = new User(userId,username,password,fullName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}
