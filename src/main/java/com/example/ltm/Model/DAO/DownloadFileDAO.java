package com.example.ltm.Model.DAO;



import com.example.ltm.Model.Bean.File;

import java.sql.*;

public class DownloadFileDAO {

    private static final String FIND_FILE_BY_ID = "select * from fileupload where fid = ?";
    public static File GetFile(int fileId){
        File file = new File();
        try{
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_FILE_BY_ID);
            statement.setInt(1,fileId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                int userId = rs.getInt("uid");
                String fileName = rs.getString("fname").split("\\.")[0] + ".docx";
                int status = 2;
                 file = new File(fileId,userId,fileName,status);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

}
