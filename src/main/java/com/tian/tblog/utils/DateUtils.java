package com.tian.tblog.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtils {

    final private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static String  getNowFormatDate(){
        return simpleDateFormat.format(new Date());
    }

    public static String format(Date date){
        return simpleDateFormat.format(date);
    }

    public static String format(LocalDateTime localDatetime){
        long l = localDatetime.toInstant(ZoneOffset.UTC).toEpochMilli();
        return simpleDateFormat.format(new Date(l));
    }

    public static Date toDate(String formatDate) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
