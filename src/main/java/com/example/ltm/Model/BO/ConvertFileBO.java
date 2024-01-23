package com.example.ltm.Model.BO;


import com.example.ltm.Model.Bean.File;
import com.example.ltm.Model.Bean.User;
import com.example.ltm.Model.DAO.ConvertFileDAO;
import com.example.ltm.util.Constaint;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

import java.util.List;

public class ConvertFileBO implements Runnable {
    User user;

    public ConvertFileBO(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        List<File> files = ConvertFileDAO.GetListConvertFile(user.getId());
        for (File file : files) {
            String fileName = file.getFileName().split("\\.")[0];
            try {
                Convert(fileName);
                ConvertFileDAO.ChangeStatus(Constaint.SUCCESS,file.getFileId());
            } catch (Exception e) {
                ConvertFileDAO.ChangeStatus(Constaint.CONVERT_ERROR,file.getFileId());
            }
        }
    }

    private void Convert(String filename) throws Exception {
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile(GetFolderPath("pdfs").getAbsolutePath() + java.io.File.separator + filename + ".pdf");
        pdf.saveToFile(GetFolderPath("docxs").getAbsolutePath() + java.io.File.separator + filename + ".docx", FileFormat.DOCX);
        pdf.close();
    }

    private java.io.File GetFolderPath(String folder) {
        java.io.File folderUpload = new java.io.File(System.getProperty("user.home") + "/" + folder);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
}
