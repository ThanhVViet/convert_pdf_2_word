package com.example.ltm.Model.DAO;



import com.example.ltm.Model.Bean.File;
import com.example.ltm.util.Constaint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertFileDAO {


    private static final String GET_CONVERT_FILE = "select * from fileupload where uid = ? and status = " + Constaint.PROCESSING;
    public static List<File> GetListConvertFile(Integer id){
        List<File> files = new ArrayList<>();
        try{Connection connection = JDBCUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_CONVERT_FILE);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int fileId = rs.getInt("fid");
                int userId = rs.getInt("uid");
                String fileName = rs.getString("fname");
                int fileStatus = rs.getInt("status");
                files.add(new File(fileId,userId,fileName,fileStatus));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return files;
    }

    private static final String CHANGE_STATUS = "UPDATE fileupload set status = ? where fid = ?";
    public static void ChangeStatus(Integer fileStatus, Integer fileId){
        try{
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS);
            statement.setInt(1,fileStatus);
            statement.setInt(2,fileId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
