package com.example.ltm.Model.BO;


import com.example.ltm.Model.Bean.File;
import com.example.ltm.Model.DAO.DownloadFileDAO;

public class DownloadFileBO {
    public static File GetFile(int fileId) {
        return DownloadFileDAO.GetFile(fileId);
    }
}
