package com.millionaire.compound.common.models.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static String datePattern = "yyyy-MM-dd";

    private static  SimpleDateFormat formatter = new SimpleDateFormat(datePattern);

    public static String getTodayDate(){
        return formatter.format(new Date());
    }

    public static Date formateDate(String dateInString) throws ParseException {
        if(dateInString != null) {
            return formatter.parse(dateInString);
        }
        return null;
    }

    public static Date formateDate(String dateInString, String dateFormat) throws ParseException {
        SimpleDateFormat new_formatter = new SimpleDateFormat(dateFormat);
        if(dateInString != null) {
            return new_formatter.parse(dateInString);
        }
        return null;
    }

    public static String formateDate2String(Date date){
        return formatter.format(date);
    }
}
