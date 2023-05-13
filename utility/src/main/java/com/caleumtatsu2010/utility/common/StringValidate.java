package com.caleumtatsu2010.utility.common;

public class StringValidate {
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
}
