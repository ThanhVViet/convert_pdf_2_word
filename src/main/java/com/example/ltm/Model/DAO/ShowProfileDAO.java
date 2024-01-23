package com.example.ltm.Model.DAO;



import com.example.ltm.Model.Bean.File;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShowProfileDAO {

    private static final String FIND_USER_BY_ID = "select * from fileupload where uid = ?";
    public static List<File> GetProcessedFile (int userId){
        List<File> files = new ArrayList<>();
        try{
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID);
            statement.setInt(1,userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int fileId = rs.getInt("fid");
                String fileName = rs.getString("fname").split("\\.")[0] + ".docx";
                if(fileName.length() > 20){
                    fileName = fileName.substring(0,20) + "...docx";
                }
                int status = rs.getInt("status");

                files.add(new File(fileId,userId,fileName,status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return files;
    }
}
