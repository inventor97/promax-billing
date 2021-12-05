package com.inventor.utils;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class dateUtils {

    private static List<String> months = new ArrayList<>();

    public static List<String> getMonths() {
        months.add("Yanvar");
        months.add("Ferval");
        months.add("Mart");
        months.add("Aprel");
        months.add("May");
        months.add("Iyun");
        months.add("Iyul");
        months.add("Avgust");
        months.add("Sentyabr");
        months.add("Oktyabr");
        months.add("Noyabr");
        months.add("Dekabr");
        return months;
    }

    public static List<String> getMonthForSpinner(){
        List<String> ls = new ArrayList<>();
        ls.add("Barchasi");
        ls.addAll(getMonths());
        return ls;
    }

    public static String getCurrentMonth() {
        int currentMonth = new Date().getMonth();
        switch (currentMonth) {
            case 0 : return "Yanvar";
            case 1 : return  "Fevral";
            case 2 : return  "Mart";
            case 3 : return  "Aprel";
            case 4 : return  "May";
            case 5 : return  "Iyun";
            case 6 : return  "Iyul";
            case 7 : return  "Avgust";
            case 8 : return  "Sentyabr";
            case 9 : return  "Oktyabr";
            case 10 : return  "Noyabr";
            case 11 : return  "Dekabr";
        }
        return null;
    }

    public static int getLastDayMonth() {
        YearMonth y = YearMonth.now();
        return y.lengthOfMonth();
    }

    public static int getMonthOrder(String month) {
        switch (month) {
            case "Yanvar" : return 1;
            case "Fevral" : return 2;
            case "Mart" : return 3;
            case "Aprel" : return 4;
            case "May" : return 5;
            case "Iyun" : return 6;
            case "Iyul" : return 7;
            case "Avgust" : return 8;
            case "Sentyabr" : return 9;
            case "Oktyabr" : return 10;
            case "Noyabr" : return 11;
            case "Dekabr" : return 12;
        }
        return -1;
    }

    public static java.sql.Date getFirstDayMonth(java.sql.Date date) {
        int y = 1900 + date.getYear();
        int m = date.getMonth() + 1;
        return java.sql.Date.valueOf(y + "-" + m + "-" + "01");
    }

    public static java.sql.Date getLastDayMonth(java.sql.Date date) {
        int y = 1900 + date.getYear();
        int m = date.getMonth() + 1;
        int lastDay  = YearMonth.of(y, m).lengthOfMonth();
        return java.sql.Date.valueOf(y + "-" + m + "-" + lastDay);
    }

    public static String nameDateFormat() {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("ddMMyy_HHmmss");
        LocalDateTime nowTime = LocalDateTime.now();
        return dt.format(nowTime);
    }
}
