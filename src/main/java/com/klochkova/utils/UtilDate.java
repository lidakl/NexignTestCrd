package com.klochkova.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilDate {
    public static Date convertToDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
    }

    public static String convertToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    }

    public static String getDuration(Date startDate, Date endDate){
        LocalDateTime startTimeDateLocal = LocalDateTime.ofInstant(
                startDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime endTimeDateLocal = LocalDateTime.ofInstant(
                endDate.toInstant(), ZoneId.systemDefault());

        Duration durationTimeDate = Duration.between(startTimeDateLocal, endTimeDateLocal);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime durationDateLocal = LocalDateTime.of(1,1,1,0,0,0)
                .plus(durationTimeDate);
        return formatter.format(durationDateLocal);
    }
}
