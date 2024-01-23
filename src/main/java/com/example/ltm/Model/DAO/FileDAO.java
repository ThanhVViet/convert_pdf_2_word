package com.example.ltm.Model.DAO;

import com.example.ltm.Model.Bean.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {
    private static final String SELECT_ALL_FILE = "SELECT * FROM fileupload";

    public List<File> getAllFile() {
        List<File> fileList = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_FILE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fileList.add(new File(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4)));
            }
        } catch (Exception e) {
        }
        return fileList;
    }
}
