package com.example.ltm.controller;

import com.example.ltm.Model.BO.FileBO;
import com.example.ltm.Model.BO.UserBO;
import com.example.ltm.Model.Bean.File;
import com.example.ltm.Model.Bean.User;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@WebServlet(name = "ExportServlet", urlPatterns = "/export")
public class ExportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        FileBO fileBO = new FileBO();
        UserBO userBO = new UserBO();
        List<File> list = fileBO.getAllFile();
        List<User> userList = userBO.getAllUser();

        int maximum = 2147483647;
        int minimum = 1;

        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum = rn.nextInt(range) + minimum;

        FileOutputStream file = new FileOutputStream("/Users/thanhviet/ExcelFile/"+ "ConvertHistory" + Integer.toString(randomNum) + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet workSheet = workbook.createSheet("1");
        XSSFRow row;
        XSSFCell cell0;
        XSSFCell cell1;
        XSSFCell cell2;
        XSSFCell cell3;


        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);


        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);


        row = workSheet.createRow(0);
        cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        cell0.setCellStyle(headerStyle);
        cell1 = row.createCell(1);
        cell1.setCellValue("File Name");
        cell1.setCellStyle(headerStyle);
        cell2 = row.createCell(2);
        cell2.setCellValue("Status");
        cell2.setCellStyle(headerStyle);
        cell3 = row.createCell(3);
        cell3.setCellValue("User");
        cell3.setCellStyle(headerStyle);


        for (int i = 0; i < list.size(); i++) {
            row = workSheet.createRow(i + 1);
            cell0 = row.createCell(0);
            cell0.setCellValue(list.get(i).getFileId());
            cell0.setCellStyle(dataStyle);
            cell1 = row.createCell(1);
            cell1.setCellValue(list.get(i).getFileName());
            cell1.setCellStyle(dataStyle);
            cell2 = row.createCell(2);

            if (list.get(i).getFileStatus() == 2) {
                cell2.setCellValue("Success");
            } else {
                cell2.setCellValue("Converting....");
            }
            cell2.setCellStyle(dataStyle);
            cell3 = row.createCell(3);
            cell3.setCellValue(list.get(i).getUserId());
            cell3.setCellStyle(dataStyle);
        }


        workSheet.autoSizeColumn(0);
        workSheet.autoSizeColumn(1);
        workSheet.autoSizeColumn(2);
        workSheet.autoSizeColumn(3);


        workSheet.createFreezePane(0, 1);

        workbook.write(file);
        workbook.close();
        file.close();

        req.setAttribute("mess", "Đã xuất file Excel thành công!");
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
