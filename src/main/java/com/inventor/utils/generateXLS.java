package com.inventor.utils;

import com.inventor.dto.impls.cashersDAOImpls;
import com.inventor.entities.tableClass;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class generateXLS {

    public static boolean saveStats(List<tableClass> data) {

        List<String> columns = new ArrayList<>();
        columns.add("#");
        columns.add("F.I.O");
        columns.add("Fan");
        columns.add("O'qituvchi");
        columns.add("To'lov oyi");
        columns.add("To'lov");
        columns.add("To'lov turi");
        columns.add("To'lov karta egasi");
        columns.add("Izoh");
        columns.add("Chek sanasi");
        columns.add("Admin");


        JFileChooser fr = new JFileChooser();
        FileSystemView fw = fr.getFileSystemView();
        File file = new File(fw.getDefaultDirectory() + "/historyCheck");
        if (!file.exists()) {
            file.mkdirs();
        }

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("sheet1");
            PrintSetup ps = sheet.getPrintSetup();
            sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
            sheet.setFitToPage(true);
            ps.setFitWidth( (short) 1);
            ps.setFitHeight( (short) 0);
            setUtils(sheet);
            Row headerRow = sheet.createRow(0);
            headerRow.setHeight((short) 375);

            DecimalFormat df = new DecimalFormat("#,###");
            df.setMaximumFractionDigits(0);

            for (int i = 0; i < columns.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns.get(i));
                cell.setCellStyle(style(workbook, true, true, BorderStyle.THICK, false, 14));
            }
            int rowNum = 1;
            double summ = 0.0;
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(rowNum++);
                row.setHeight((short) -1);
                row.setRowStyle(rowStyle(workbook));
                row.setHeight((short)-1);
                addCell(workbook, row, String.valueOf(data.get(i).getNo()), 0);
                addCell(workbook, row, data.get(i).getName(), 1);
                addCell(workbook, row, data.get(i).getSub(), 2);
                addCell(workbook, row, data.get(i).getTeacher(), 3);
                addCell(workbook, row, data.get(i).getMonth(), 4);
                addCell(workbook, row, df.format(data.get(i).getAmount()), 5);
                addCell(workbook, row, data.get(i).getObj().isPaymentType() ? "Naqd": "To'lov karta", 6);
                addCell(workbook, row, data.get(i).getObj().getCardHolder(), 7);
                addCell(workbook, row, data.get(i).getObj().getComment(), 8);
                addCell(workbook, row, String.valueOf(data.get(i).getObj().getDateCrated()), 9);
                try {
                    addCell(workbook, row, cashersDAOImpls.getInstance().get(data.get(i).getObj().getCasherId()).getName(), 10);
                } catch (Exception e) {
                    addCell(workbook, row, "user-deleted", 10);
                }
                summ += data.get(i).getAmount();
            }

            rowNum = rowNum + 2;
            Row prRow = sheet.createRow(rowNum);
            Cell prCell = prRow.createCell(10);
            prCell.setCellStyle(style(workbook, true, true,BorderStyle.THICK, false, 15));
            prCell.setCellValue(df.format(summ));

            String xlsName = "Hisobot" + dateUtils.nameDateFormat();
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

    private static void addCell(HSSFWorkbook workbook, Row row, String value, int pos) {
        Cell cell4 = row.createCell(pos);
        cell4.setCellStyle(style(workbook, true, false, BorderStyle.THIN, false, 12));
        cell4.setCellValue(value);
    }

    private static void setUtils(Sheet sheet) {
        sheet.setColumnWidth(0, 1790);
        sheet.setColumnWidth(1, 10930);
        sheet.setColumnWidth(2, 11550);
        sheet.setColumnWidth(3, 11550);
        sheet.setColumnWidth(4, 7895);
        sheet.setColumnWidth(5, 4895);
        sheet.setColumnWidth(6, 5895);
        sheet.setColumnWidth(7, 4895);
        sheet.setColumnWidth(8, 8895);
        sheet.setColumnWidth(9, 4895);
        sheet.setColumnWidth(10, 4895);

    }

    private static CellStyle rowStyle(HSSFWorkbook workbook) {
        CellStyle st = workbook.createCellStyle();
        st.setWrapText(true);
        return st;
    }

    private static CellStyle style(HSSFWorkbook workbook, boolean borders, boolean bold, BorderStyle style,  boolean italic, int size) {
        CellStyle st = workbook.createCellStyle();
        if (borders) {
            st.setBorderBottom(style);
            st.setBorderTop(style);
            st.setBorderRight(style);
            st.setBorderLeft(style);
        }
        st.setAlignment(HorizontalAlignment.CENTER);
        st.setVerticalAlignment(VerticalAlignment.CENTER);
        st.setWrapText(true);
        Font newFont = workbook.createFont();
        newFont.setBold(bold);
        newFont.setItalic(italic);
        newFont.setFontName("Century Gothic");
        newFont.setFontHeightInPoints((short) size);
        st.setFont(newFont);
        return st;
    }

}
