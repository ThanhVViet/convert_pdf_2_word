package com.example.ltm.Model.BO;

import com.example.ltm.Model.Bean.File;
import com.example.ltm.Model.DAO.FileDAO;

import java.util.List;

public class FileBO {
    private FileDAO fileDAO = new FileDAO();
    public List<File> getAllFile(){
        return fileDAO.getAllFile();
    }
}
