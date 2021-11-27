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
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class generateXlSXprinter {

    private static Image logo = new Image("header-check.png", 420,180,false,false);

    public static boolean saveSoldCheck(ChecksDataEntity data) {
        StringBuilder val = new StringBuilder();

        val.append("FIO: ").append(data.getName())
                .append("\nTo'lov: ").append(data.getAmountBill())
                .append("\nOy: ").append(data.getPayedMonth())
                .append("\nFan: ").append(data.getSubjects())
                .append("\ndate: ").append(data.getDateCrated());

        Image qrCode = generateQRCode.generateCode(val.toString(), 420, 420).getImage();

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
            Row headerRow = sheet.createRow(0);
            headerRow.setHeight((short) 2375);
            setUtils(sheet);

            setHeaderImages(workbook, sheet,logo, 0, 0);

            setHeaderImages(workbook, sheet,logo, 0, 0);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy.MM.dd\nHH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String dataDate = dtf.format(now);

            Row datRow1 = sheet.createRow(5);
            datRow1.setRowStyle(titleStyle(workbook, 15, false));
            Cell main = datRow1.createCell(0);
            main.setCellStyle(titleStyle(workbook, 15, false));
            main.setCellValue(dataDate);

            Cell no = datRow1.createCell(1);
            no.setCellStyle(style(workbook, HorizontalAlignment.CENTER, false, 14));
            no.setCellValue("#" + checksDataDAOimpls.getInstance().getMaxId());

            Row datRow3 = sheet.createRow(7);
            datRow3.setHeight((short) 1860);
            Cell date = datRow3.createCell(0);
            date.setCellStyle(titleStyle(workbook,14, false));
            date.setCellValue("Promax education o'quv markazi \n" +
                    "Manzil: Toshkent sh. Chilonzor t.\nIntegro 7 -qavat\n" +
                    "Tel nomer: 99895 5137775");

            Row datRow4 = sheet.createRow(11);
            datRow4.setHeight((short) 1060);
            Cell date4 = datRow4.createCell(0);
            date4.setCellStyle(style(workbook, HorizontalAlignment.CENTER, true, 15));
            date4.setCellValue("Hisob to'lov varag'i");
            Cell dat5 = datRow4.createCell(1);
            dat5.setCellStyle(style(workbook, HorizontalAlignment.CENTER, true, 15));

            DecimalFormat df = new DecimalFormat("#,###");
            df.setMaximumFractionDigits(0);

            addRow(sheet, workbook, "O'quvchi: ", data.getName(), 12, 1,false, 15);
            addRow(sheet, workbook, "O'quv fani: ", data.getSubjects(), 13, 1, false, 15);
            addRow(sheet, workbook, "O'qituvchilar:", data.getTeachers(), 14, 1, false, 15);
            addRow(sheet, workbook,"", "", 15, 1, true, 14);
            addRow(sheet, workbook, "To'lov oyi: ", data.getPayedMonth(), 16, 1, false, 15);
            addRow(sheet, workbook, "To'lov turi: ", data.isPaymentType() ? "Naqd": "To'lov karta", 17, 1, false, 15);
            addRow(sheet, workbook, "Summa: ", df.format(data.getAmountBill()), 18, 1, false, 20);
            addRow(sheet, workbook,"", "", 19, 1, true, 14);
            addRow(sheet, workbook, "Izoh: ", data.getComment(), 20, 1, true, 15);

            Row bottom = sheet.createRow(48);
            bottom.setHeight((short) 650);
            Cell btm = bottom.createCell(0);
            btm.setCellStyle(titleStyle(workbook, 12, true));
            btm.setCellValue("Eng muhim maqsadlaringizni biling!");

            setHeaderImages(workbook, sheet, qrCode, 0, 24);

            String xlsName = data.getName() + new Date().getTime();
            FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath() + "/" + xlsName + ".xls");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
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
        sheet.addMergedRegion(new CellRangeAddress(48, 48, 0, 1));

        sheet.setColumnWidth(0, 5730);
        sheet.setColumnWidth(1, 9980);
    }

    private static void addRow(HSSFSheet sheet, HSSFWorkbook workbook, String firstClm,
                               String secondClm, int rowOrder, int alignment, boolean border, int size) {
        Row row = sheet.createRow(rowOrder);
        row.setHeight((short) -1);
        row.setRowStyle(rowStyle(workbook));
        row.setHeight((short)-1);

        Cell cell0 = row.createCell(0);
        cell0.setCellStyle(style(workbook, HorizontalAlignment.LEFT, border, 15));
        cell0.setCellValue(firstClm);

        if (alignment == 1) {
            Cell cell1 = row.createCell(1);
            cell1.setCellStyle(style(workbook, HorizontalAlignment.RIGHT, border, size));
            cell1.setCellValue(secondClm);
        } else {
            Cell cell1 = row.createCell(1);
            cell1.setCellStyle(style(workbook, HorizontalAlignment.LEFT, border, size));
            cell1.setCellValue(secondClm);
        }
    }

    private static CellStyle style(HSSFWorkbook workbook, HorizontalAlignment align, boolean border, int size) {
        CellStyle st = workbook.createCellStyle();
        if (border){
            st.setBorderBottom(BorderStyle.DASH_DOT);
        }
        st.setWrapText(true);
        Font newFont = workbook.createFont();
        newFont.setBold(true);
        st.setWrapText(true);
        newFont.setFontName("Century Gothic");
        newFont.setFontHeightInPoints((short) size);
        st.setFont(newFont);
        st.setAlignment(align);
        st.setVerticalAlignment(VerticalAlignment.CENTER);
        return st;
    }

    private static CellStyle rowStyle(HSSFWorkbook workbook) {
        CellStyle st = workbook.createCellStyle();
        st.setWrapText(true);
        return st;
    }

    private static CellStyle titleStyle(HSSFWorkbook workbook, int size, boolean italic) {
        CellStyle st = workbook.createCellStyle();
        st.setWrapText(true);
        Font newFont = workbook.createFont();
        newFont.setBold(true);
        st.setWrapText(true);
        newFont.setItalic(italic);
        newFont.setFontName("Century Gothic");
        newFont.setFontHeightInPoints((short) size);
        st.setFont(newFont);
        st.setAlignment(HorizontalAlignment.CENTER);
        st.setVerticalAlignment(VerticalAlignment.CENTER);
        return st;
    }

}
