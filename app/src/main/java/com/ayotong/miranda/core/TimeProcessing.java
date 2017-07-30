package com.ayotong.miranda.core;


import android.util.Log;

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
    int interval;

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

    public String getMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
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
    public int minum(int gender, int age, boolean pregnant){

        if (age>=19 && gender==0){
            interval = 5400000; //kurang lbh 90mnt
        }else if (age>=19 && gender==1){
            interval = 6300000; //kurang lbh 105mnt
        }else if(age>=0 && age<=8){
            interval = 12000000; //kurang lbh 200mnt
        }else if (age>=9 && age <=18){
            interval = 6360000; //kurang lbh 106mnt
        }else if (pregnant==true && gender==1 && age>=19){
            interval = 4800000; //kurang lbh 80mnt
        }else{
            interval = 7200000; //kurang lbh 120mnt
        }
        return interval;
    }

    public String timestampToDate(String timestamp){
        return timestamp.substring(0, 10);
    }

    public String timestampToTime(String timestamp){
        return timestamp.substring(11, 19);
    }
}