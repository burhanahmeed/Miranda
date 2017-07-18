package com.ayotong.miranda.controller;


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
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date());
    }

    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date());
    }

    public String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date());
    }

    public String timestampToDate(String timestamp){
        return timestamp.substring(0, 8);
    }

    public String timestampToTime(String timestamp){
        return timestamp.substring(9, 15);
    }
}