package com.millionaire.compound.common.models.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static String datePattern = "yyyy-M-dd";

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
}