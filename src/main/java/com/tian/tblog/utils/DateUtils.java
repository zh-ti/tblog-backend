package com.tian.tblog.utils;

import java.text.DateFormat;
import java.util.Date;

public class DateUtils {

    public static String  getFormatDate(){
        return DateFormat.getDateTimeInstance().format(new Date());
    }

    public static String formatDate(Date date){
        return DateFormat.getDateTimeInstance().format(date);
    }
}
