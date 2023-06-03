package com.caleumtatsu2010.utility.common;

public class StringValidator {
    public static String NulltoBlank(String str) {
        if (str == null) {
            str = "";
        }
        return str;
    }
    
    public static String NulltoZero(String value) {
        if (value == null || value.trim().length() == 0) {
            value = "0";
        }
        return value;
    }
    
    public static int safeParseInt(String value) {
        int num = 0;
        try {
            num = Integer.parseInt(StringValidator.NulltoZero(value));
        } catch (NumberFormatException e) {
            num = 0;
        }
        return num;
    }
    
    public static double safeParseDouble(String value) {
        double num = 0.0;
        try {
            num = Double.parseDouble(StringValidator.NulltoZero(value));
        } catch (NumberFormatException e) {
            num = 0.0;
        }
        return num;
    }
}
