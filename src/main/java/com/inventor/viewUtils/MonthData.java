package com.inventor.viewUtils;

import java.util.ArrayList;
import java.util.List;

public class MonthData {

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
}
