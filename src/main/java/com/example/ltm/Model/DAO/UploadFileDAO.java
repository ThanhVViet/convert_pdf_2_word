package com.example.ltm.Model.DAO;

import java.sql.*;

public class UploadFileDAO {

    private static final String UPDATE_NEW_FILE = "insert into fileupload(uid,fname,status) values(?,?,?)";
    private static final String UPLOAD_FILE ="update fileupload set status = ? where fname = ?" ;
    private static final String FIND_FILE_BY_NAME = "select * from fileupload where fname = ?";
    public static void Upload(String fileName,int userId,int status){
        try{
            Connection connection = JDBCUtil.getConnection();
            if(!IsFileExist(fileName)){
                PreparedStatement statement = connection.prepareStatement(UPDATE_NEW_FILE);
                statement.setInt(1,userId);
                statement.setString(2,fileName);
                statement.setInt(3,status);
                statement.executeUpdate();
            }
            else {
                PreparedStatement statement = connection.prepareStatement(UPLOAD_FILE);
                statement.setInt(1,status);
                statement.setString(2,fileName);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static boolean IsFileExist(String fileName) {
        try{
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_FILE_BY_NAME);
            statement.setString(1,fileName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
