//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.caleumtatsu2010.utility.time;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {



    private DateUtil() {
    }

    public static int compareTo(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

    public static void main(String[] args) {
//        Calendar rightNow = Calendar.getInstance();
//        SimpleDateFormat format = new SimpleDateFormat(format1);
//        System.out.println(format.format(toSqlTimestamp(rightNow)));
        System.out.println(DateUtil.getCurrTimestamp());
        System.out.println(toSimpleDateFormat(getCurrTimestamp()) );
        System.out.println(toSqlTimestamp(""));
    }

    public static int getDaysInMonth(int year, int month) {
        Calendar tempDate = Calendar.getInstance();
        tempDate.set(year, month - 1, 15);
        return getLastDayOfMonth(tempDate);
    }

    public static int getDaysInMonth(Calendar date) {
        Calendar tempDate = (Calendar) date.clone();
        tempDate.add(2, 1);
        tempDate.set(5, 1);
        tempDate.add(5, -1);
        return tempDate.get(5);
    }


    public static int getLastDayOfMonth(int year, int month) {
        Calendar tempDate = Calendar.getInstance();
        tempDate.set(year, month - 1, 15);
        return getLastDayOfMonth(tempDate);
    }


    public static int getLastDayOfMonth(Calendar date) {
        Calendar tempDate = (Calendar) date.clone();
        tempDate.add(2, 1);
        tempDate.set(5, 1);
        tempDate.add(5, -1);
        return tempDate.get(5);
    }

    public static Calendar toCalendar(java.sql.Date date) {
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(date);
        return tempCal;
    }

//    public static java.sql.Date toSqlDate(String textDate) {
//        int year = false;
//        int month = false;
//        int day = false;
//        Integer workInt = null;
//        Calendar getCalendar = null;
//        workInt = new Integer(textDate.substring(0, 4));
//        int year = workInt;
//        workInt = new Integer(textDate.substring(5, 7));
//        int month = workInt - 1;
//        workInt = new Integer(textDate.substring(8, 10));
//        int day = workInt;
//        getCalendar = Calendar.getInstance();
//        getCalendar.set(year, month, day);
//        return toSqlDate(getCalendar);
//    }

    public static java.sql.Date toSqlDate(Calendar date) {
        Date tempDate = date.getTime();
        java.sql.Date sqlDate = new java.sql.Date(tempDate.getTime());
        return sqlDate;
    }

//    public static Time toSqlTime(String textTime) {
//        int year = 2000;
//        int month = 0;
//        int day = 1;
//        int hour = false;
//        int minute = false;
//        int second = false;
//        Integer workInt = null;
//        Calendar getCalendar = null;
//        workInt = new Integer(textTime.substring(0, 2));
//        int hour = workInt;
//        workInt = new Integer(textTime.substring(3, 5));
//        int minute = workInt;
//        workInt = new Integer(textTime.substring(6, 8));
//        int second = workInt;
//        getCalendar = Calendar.getInstance();
//        getCalendar.set(year, month, day, hour, minute, second);
//        return toSqlTime(getCalendar);
//    }

    public static Time toSqlTime(Calendar time) {
        Date tempDate = time.getTime();
        Time sqlTime = new Time(tempDate.getTime());
        return sqlTime;
    }
    
    public static Timestamp toSqlTimestamp(String date) {
        Timestamp timestampl = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if (date != null && date.trim().length() != 0 && !date.contains(":")){
                date = date + " 00:00:00";
            }
            Date parsedDate = dateFormat.parse(date);
            timestampl = new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
        
        }
        return timestampl;
    }

    public static Timestamp toSqlTimestamp(Calendar date) {
        Timestamp sqlTimestamp = null;
        Date utilDate = null;
        utilDate = date.getTime();
        sqlTimestamp = new Timestamp(utilDate.getTime());
        return sqlTimestamp;
    }
    
    public static Timestamp getCurrTimestamp() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
    
    public static String toSimpleDateFormat(Timestamp timestamp) {
        String format1 = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(format1);
        return format.format(timestamp);
    }

    public static String toStringDate(Calendar date) {
        DecimalFormat df1 = new DecimalFormat("0000");
        DecimalFormat df2 = new DecimalFormat("00");
        return df1.format((long) date.get(1)) + "-" + df2.format((long) (date.get(2) + 1)) + "-" + df2.format((long) date.get(5));
    }

    public static String toStringTime(Calendar cal) {
        DecimalFormat df2 = new DecimalFormat("00");
        return df2.format((long) cal.get(11)) + ":" + df2.format((long) cal.get(12)) + ":" + df2.format((long) cal.get(13));
    }

    public static Calendar toCalendar(String date) {
        Calendar tempCal = Calendar.getInstance();
        tempCal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)));
        return tempCal;
    }

    public static Calendar toCalendar(String date, String time) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1, Integer.parseInt(date.substring(8, 10)), Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(3, 5)), Integer.parseInt(time.substring(6, 8)));
        cal.set(14, 0);
        return cal;
    }

    public static final boolean validateDate(String value) {
        if (value != null && value.length() == 10) {
            try {
                Integer.parseInt(value.substring(0, 4));
                Integer.parseInt(value.substring(5, 7));
                Integer.parseInt(value.substring(8, 10));
            } catch (NumberFormatException var2) {
                return false;
            }

            return value.substring(4, 5).equals("-") && value.substring(7, 8).equals("-");
        } else {
            return false;
        }
    }

    public static final boolean validateTime(String value) {
        if (value != null && value.length() == 8) {
            try {
                Integer.parseInt(value.substring(0, 2));
                Integer.parseInt(value.substring(3, 5));
                Integer.parseInt(value.substring(6, 8));
            } catch (NumberFormatException var2) {
                return false;
            }

            return value.substring(2, 3).equals(":") && value.substring(5, 6).equals(":");
        } else {
            return false;
        }
    }

    public static String toStringDate() {
        Calendar tempCal = Calendar.getInstance();
        return toStringDate(tempCal);
    }

    public static String toStringTime() {
        DecimalFormat df2 = new DecimalFormat("00");
        Calendar tempCal = Calendar.getInstance();
        return df2.format((long) tempCal.get(11)) + ":" + df2.format((long) tempCal.get(12)) + ":" + df2.format((long) tempCal.get(13));
    }

    public static int getDayOfWeek(int year, int month, int day) {
        Calendar cl = Calendar.getInstance();
        cl.clear();
        cl.set(year, month - 1, day);
        int week = 0;
        switch (cl.get(7)) {
            case 1:
                week = 0;
                break;
            case 2:
                week = 1;
                break;
            case 3:
                week = 2;
                break;
            case 4:
                week = 3;
                break;
            case 5:
                week = 4;
                break;
            case 6:
                week = 5;
                break;
            case 7:
                week = 6;
        }

        return week;
    }

    public static int getDayOfWeek1st(int year, int month) {
        return getDayOfWeek(year, month, 1);
    }

    public static Calendar getNowRoundedUnitFiveMinute() {
        return getTimeRounded((byte) 5, true);
    }

    public static Calendar getTimeRounded(byte unit, boolean roundingMode) {
        return getTimeRounded(unit, roundingMode, (Calendar) null);
    }

    public static Calendar getTimeRounded(byte unit, boolean roundingMode, Calendar cal) {
        if (cal == null) {
            cal = Calendar.getInstance();
        }

        boolean justFlg = false;
        switch (unit) {
            case 1:
            case 5:
            case 10:
            case 15:
            case 20:
            case 30:
                if (cal.get(12) % unit == 0 && cal.get(13) == 0) {
                    justFlg = true;
                }

                if (roundingMode && !justFlg) {
                    cal.add(12, unit);
                }

                cal.set(12, cal.get(12) / unit * unit);
                cal.set(13, 0);
                return cal;
            default:
                return cal;
        }
    }

    public static String getAddDate(String date, int days) {
        Calendar cDate = toCalendar(date);
        cDate.add(5, days);
        return toStringDate(cDate);
    }

    public static String getAddDate(int days) {
        return getAddDate(toStringDate(), days);
    }

    public static int getDaysDiff(String targetDt) {
        return getDaysDiff(targetDt, toStringDate());
    }

    public static int getDaysDiff(String targetDt, String baseDt) {
        Calendar targetCal = toCalendar(targetDt);
        Calendar baseCal = toCalendar(baseDt);
        long longDiff = (targetCal.getTimeInMillis() - baseCal.getTimeInMillis()) / 86400000L;
        return (int) longDiff;
    }

    public static int getFirstDayOfMonthCalendar(Calendar cal, int firstDayOfWeek) {
        if (firstDayOfWeek < 1 || firstDayOfWeek > 7) {
            firstDayOfWeek = 1;
        }

        int dayOfWeek = cal.get(7);
        int dayOfMonth = cal.get(5);
        int firstDayOfWeekOfMonth = dayOfWeek - (dayOfMonth - 1) % 7;
        if (firstDayOfWeekOfMonth <= 0) {
            firstDayOfWeekOfMonth += 7;
        }

        int firstDayOfMonthCalendar = firstDayOfWeek - firstDayOfWeekOfMonth + 1;
        if (firstDayOfMonthCalendar >= 2) {
            firstDayOfMonthCalendar -= 7;
        }

        return firstDayOfMonthCalendar;
    }

    public static String getDateFromDtTm(String dtTm) {
        String dt = "";
        if (dtTm != null && dtTm.length() > 9) {
            dt = dtTm.substring(0, 10);
        }

        return dt;
    }

    public static String getTimeFromDtTm(String dtTm) {
        String tm = "";
        if (dtTm != null && dtTm.length() > 18) {
            tm = dtTm.substring(11, 19);
        }

        return tm;
    }

    public static String calcCrtTime(String start, String end) {
        if (start != null && start.trim().length() >= 5 && end != null && end.trim().length() >= 5) {
            int hs = Integer.parseInt(start.substring(0, 2));
            int ms = Integer.parseInt(start.substring(3, 5));
            int he = Integer.parseInt(end.substring(0, 2));
            int me = Integer.parseInt(end.substring(3, 5));
            if (me < ms) {
                me += 60;
                --he;
            }

            int h = he - hs;
            int m = me - ms;
            String HH = "" + h;
            String MM = "" + m;
            if (h < 10) {
                HH = "0" + HH;
            }

            if (m < 10) {
                MM = "0" + MM;
            }

            return HH + ":" + MM;
        } else {
            return null;
        }
    }
}
