package com.inventor.utils;

import com.inventor.dao.impls.checksDataDAOimpls;
import com.inventor.dao.impls.subjectDAOimpls;
import com.inventor.entities.ChecksDataEntity;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class generateXlSXprinter {

    private static Image logo = new Image("header-check.png", 350,150,false,false);

    public static boolean saveSoldCheck(ChecksDataEntity data) {
        int k = 1;
        StringBuilder val = new StringBuilder();

        List<String> columns = new ArrayList<>();
        columns.add("");
        columns.add("");


        val.append("FIO: ").append(data.getName())
                .append("\nTo'lov: ").append(data.getAmountBill())
                .append("\nOy: ").append(data.getPayedMonth())
                .append("\nFan: ").append(data.getSubjectId())
                .append("\ndate: ").append(data.getDateCrated());

        Image qrCode = generateQRCode.generateCode(val.toString(), 300, 300).getImage();

        JFileChooser fr = new JFileChooser();
        FileSystemView fw = fr.getFileSystemView();
        File file = new File(fw.getDefaultDirectory() + "/historyCheck");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setFitToPage(true);
            sheet.setAutobreaks(true);
            sheet.setMargin(Sheet.BottomMargin, 0.0);
            sheet.setMargin(Sheet.TopMargin, 0.0);
            sheet.setMargin(Sheet.LeftMargin, 0.0);
            sheet.setMargin(Sheet.RightMargin, 0.0);
            Row headerRow = sheet.createRow(6);
            headerRow.setHeight((short) 375);
            setUtils(sheet);

            setHeaderImages(workbook, sheet,logo, 0, 0);

            Row datRow1 = sheet.createRow(5);
//            datRow1.setHeight((short)1000);
            datRow1.setRowStyle(titleStyle(workbook, 15, false));
            Cell main = datRow1.createCell(0);
            main.setCellStyle(titleStyle(workbook, 15, false));
            main.setCellValue(data.getDateCrated().toString() + new Date().getTime());

            Cell no = datRow1.createCell(1);
            no.setCellStyle(titleStyle(workbook, 14, false));
            no.setCellValue("№ " + checksDataDAOimpls.getInstance().getMaxId());

            Row datRow3 = sheet.createRow(7);
//            datRow3.setHeight((short) 1360);
            Cell date = datRow3.createCell(0);
            date.setCellStyle(titleStyle(workbook, 14, false));
            date.setCellValue("Promax education o'quv markazi \n" +
                    "Manzil: Toshkent sh. Chilonzor t. Integro 7 -qavat\n" +
                    "Tel nomer: 99895 5137775\n");

            Row datRow4 = sheet.createRow(11);
//            datRow3.setHeight((short) 1360);
            Cell date4 = datRow4.createCell(0);
            date4.setCellStyle(titleStyle(workbook, 14, false));
            date4.setCellValue("Hisob to'lov varag'i");

            sheet.autoSizeColumn(1, true);

            for (int i = 0; i < columns.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns.get(i));
                cell.setCellStyle(style(workbook));
            }

            addRow(sheet, workbook, "O'quvchi: ", data.getName(), 12);
            addRow(sheet, workbook, "O'quv fani: ", subjectDAOimpls.getInstance().get(data.getSubjectId()).getName(), 13);
            addRow(sheet, workbook, "O'qituvchilar:", data.getTeachers(), 14);
            addRow(sheet, workbook, "To'lov oyi: ", data.getPayedMonth(), 15);
            addRow(sheet, workbook, "To'lov turi: ", data.isPaymentType() ? "Naqd": "To'lov karta", 16);
            addRow(sheet, workbook, "Summa: ", String.valueOf(data.getAmountBill()), 17);
            addRow(sheet, workbook, "Summa: ", String.valueOf(data.getAmountBill()), 18);


            Row bottom = sheet.createRow(28);
            Cell btm = bottom.createCell(0);
            btm.setCellStyle(titleStyle(workbook, 13, true));
            btm.setCellValue("Eng muhim maqqsadlaringizni bilig!");

            setHeaderImages(workbook, sheet, qrCode, 0, 19);

            String xlsName = data.getName() + data.getDateCrated() + new Date().getTime();
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + "/" + xlsName + ".xls");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static void addRow(HSSFSheet sheet, HSSFWorkbook workbook, String firstClm, String secondClm, int rowOrder) {
        Row row = sheet.createRow(rowOrder);
        row.setHeight((short) -1);
        row.setRowStyle(rowStyle(workbook));
        row.setHeight((short)-1);

        Cell cell0 = row.createCell(0);
        cell0.setCellStyle(mainStyle(workbook));
        cell0.setCellValue(firstClm);

        Cell cell1 = row.createCell(1);
        cell1.setCellStyle(mainStyle(workbook));
        cell1.setCellValue(secondClm);
    }

    private static void setHeaderImages(HSSFWorkbook workbook, Sheet sheet, Image image, int col, int row){
        try {
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", s);
            byte[] res  = s.toByteArray();
            s.close();
            int pictureIdx = workbook.addPicture(res, Workbook.PICTURE_TYPE_PNG);
            CreationHelper helper = workbook.getCreationHelper();
            Drawing drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(col);
            anchor.setRow1(row);
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            pict.resize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setUtils(Sheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(0, 4, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(5, 6, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(5, 6, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 5));
        sheet.addMergedRegion(new CellRangeAddress(7, 10, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(17, 18, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(28, 28, 0, 1));

        sheet.setColumnWidth(0, 9950);
        sheet.setColumnWidth(1, 10000);
    }

    private static CellStyle style(HSSFWorkbook workbook) {
        CellStyle st = workbook.createCellStyle();
        st.setBorderBottom(BorderStyle.THIN);
        st.setBorderTop(BorderStyle.THIN);
        st.setBorderRight(BorderStyle.THIN);
        st.setBorderLeft(BorderStyle.THIN);
        st.setWrapText(true);
        Font newFont = workbook.createFont();
        newFont.setBold(true);
        newFont.setItalic(false);
        newFont.setFontName("Poppins");
        newFont.setFontHeightInPoints((short) 15);
        st.setFont(newFont);
        return st;
    }

    private static CellStyle rowStyle(HSSFWorkbook workbook) {
        CellStyle st = workbook.createCellStyle();
        st.setWrapText(true);
        return st;
    }

    private static CellStyle mainStyle(HSSFWorkbook workbook) {
        CellStyle st = workbook.createCellStyle();
        st.setBorderBottom(BorderStyle.THIN);
        st.setBorderTop(BorderStyle.THIN);
        st.setBorderRight(BorderStyle.THIN);
        st.setBorderLeft(BorderStyle.THIN);
        Font newFont = workbook.createFont();
        newFont.setBold(true);
        st.setWrapText(true);
        newFont.setItalic(false);
        newFont.setFontName("Poppins");
        newFont.setFontHeightInPoints((short) 15);
        st.setFont(newFont);

        return st;
    }

    private static CellStyle titleStyle(HSSFWorkbook workbook, int size, boolean italic) {
        CellStyle st = workbook.createCellStyle();
        st.setWrapText(true);
        Font newFont = workbook.createFont();
        newFont.setBold(true);
        st.setWrapText(true);
        newFont.setItalic(italic);
        newFont.setFontName("Poppins");
        newFont.setFontHeightInPoints((short) size);
        st.setFont(newFont);
        st.setAlignment(HorizontalAlignment.CENTER);
        st.setVerticalAlignment(VerticalAlignment.CENTER);
        return st;
    }

}
