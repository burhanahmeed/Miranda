package com.ayotong.miranda.core;


import com.ayotong.miranda.model.UserInfo;

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
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
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
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        LocalTime ltime = formatter.parseLocalTime(time);
        ltime = ltime.plusHours(hour);
        ltime = ltime.plusMinutes(minute);
        String result = ltime.toString().substring(0, 8);
        return result;
    }

    public String getWakeNapTime(String start_nap){
        return addTime(start_nap, 1,30);
    }

    public String getWakeSleepTime(int age, String start_sleep){
        if(age>=65){
            return addTime(start_sleep, 8,0);
        }
        return addTime(start_sleep, 9,0);
    }

    public String timestampToDate(String timestamp){
        return timestamp.substring(0, 10);
    }

    public String timestampToTime(String timestamp){
        return timestamp.substring(11, 19);
    }
}