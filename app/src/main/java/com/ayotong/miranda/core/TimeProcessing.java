package com.ayotong.miranda.controller;


import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Alpha on 17/07/2017.
 */

public class TimeProcessing {

    public TimeProcessing(){

    }

    public String getTimestamp(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy_HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
        sdf.setTimeZone(TimeZone.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    public String addTime(String time, int hour, int minute){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");
        LocalTime ltime = formatter.parseLocalTime(time);
        ltime = ltime.plusHours(hour);
        ltime = ltime.plusMinutes(minute);
        String result = ltime.toString().substring(0, 8);
        return result;
    }

    public String timestampToDate(String timestamp){
        return timestamp.substring(0, 10);
    }

    public String timestampToTime(String timestamp){
        return timestamp.substring(11, 19);
    }
}