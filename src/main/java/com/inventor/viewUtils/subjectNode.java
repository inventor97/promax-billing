package com.inventor.viewUtils;

import java.util.Random;

public class subjectNode {

    private final String BACKGROUND_1 = "linear-gradient(to bottom right, #11998e, #38ef7d)";
    private final String BACKGROUND_2 = "linear-gradient(to bottom right, #2a4e8b 60%, #ea7a2f 100%)";
    private final String BACKGROUND_3 = "linear-gradient(to bottom right, #23074d, #cc5333)";
    private final String BACKGROUND_4 = "linear-gradient(to bottom right, #FDC830, #F37335)";
    private final String BACKGROUND_5 = "linear-gradient(to bottom right, #FF5F6D, #FFC371)";
    private final String BACKGROUND_6 = "linear-gradient(to bottom right, #16BFFD, #CB3066)";
    private final String BACKGROUND_7 = "linear-gradient(to bottom right, #EECDA3, #EF629F)";
    private final String BACKGROUND_8 = "linear-gradient(to bottom right, #1D4350, #A43931)";
    private final String BACKGROUND_9 = "linear-gradient(to bottom right, #ff4b1f, #1fddff)";
    private final String BACKGROUND_10 = "linear-gradient(to bottom right, #2980b9, #2c3e50)";
    private final String BACKGROUND_11 = "linear-gradient(to bottom right, #00467F, #A5CC82)";
    private final String BACKGROUND_12 = "linear-gradient(to bottom right, #9796f0, #fbc7d4)";
    private final String BACKGROUND_13 = "linear-gradient(to bottom right, #B79891, #94716B)";
    private final String BACKGROUND_14 = "linear-gradient(to bottom right, #BBD2C5 30%, #292E49 100%)";
    private final String BACKGROUND_15 = "linear-gradient(to bottom right, #acb6e5, #86fde8)";
    private final String BACKGROUND_16 = "linear-gradient(to bottom right, #FFE000, #799F0C)";
    private final String BACKGROUND_17 = "linear-gradient(to bottom right, #ffe259, #ffa751)";
    private final String BACKGROUND_18 = "linear-gradient(to bottom right, #799F0C, #ACBB78)";
    private final String BACKGROUND_19 = "linear-gradient(to bottom right, #334d50, #cbcaa5)";
    private final String BACKGROUND_20 = "linear-gradient(to bottom right, #00416A, #E4E5E6)";

    @Override
    public String toString() {
        return BACKGROUND_1 + '\''
                + BACKGROUND_2 + '\''
                + BACKGROUND_3 + '\''
                + BACKGROUND_4 + '\''
                + BACKGROUND_5 + '\''
                + BACKGROUND_6 + '\''
                + BACKGROUND_7 + '\''
                + BACKGROUND_8 + '\''
                + BACKGROUND_9 + '\''
                + BACKGROUND_10 + '\''
                + BACKGROUND_11 + '\''
                + BACKGROUND_12 + '\''
                + BACKGROUND_13 + '\''
                + BACKGROUND_14 + '\''
                + BACKGROUND_15 + '\''
                + BACKGROUND_16 + '\''
                + BACKGROUND_17 + '\''
                + BACKGROUND_18 + '\''
                + BACKGROUND_19 + '\''
                + BACKGROUND_20;
    }

    String getBackground() {
        int indx = new Random().nextInt(19);
        return toString().split("\'")[indx];
    }

}
