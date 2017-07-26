package com.ayotong.miranda.Service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by burhan on 26/07/17.
 */

public class InterpreterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String status = getIntent().getStringExtra("status");
        String jam = getIntent().getStringExtra("jam");
//        String ques = getIntent().getStringExtra("ques");
//        String xp = getIntent().getStringExtra("xp");

        if (status == "on"){
//buat bangun
            Log.i("Mana if else","yang on");
            String[] jams = jam.split(":");

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(jams[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(jams[1])+5);
            calendar.set(Calendar.SECOND, 0);
            Intent in = new Intent(this, ReminderReceiver.class);
            in.putExtra("jam",jam);
            in.putExtra("ques","It's time to wake up and have a nice day");
            in.putExtra("xp","23");
            PendingIntent peint = PendingIntent.getBroadcast(this, 3, in,PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, peint);

//            buat minum
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(this, ReminderReceiver.class);
            intent.putExtra("jam","00:00");
            intent.putExtra("ques","Minum cucu dulu yaa");
            intent.putExtra("xp","100");
            PendingIntent pIntent = PendingIntent.getBroadcast(this,
                    102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis(), 2*60*1000, pIntent);
        }else if (status == "off"){
            Log.i("Mana if else","yang off");
            String[] jams = jam.split(":");

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(jams[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(jams[1]));
            calendar.set(Calendar.SECOND, 0);
            Intent in = new Intent(this, ReminderReceiver.class);
            in.putExtra("jam",jam);
            in.putExtra("ques","You're getting tired, it's time to sleep baby");
            in.putExtra("xp","23");
            PendingIntent peint = PendingIntent.getBroadcast(this, 4, in,PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, peint);

            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent("REPEATING_ALARM_TRIGGERED");
            PendingIntent pIntent = PendingIntent.getBroadcast(this,
                    102, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            manager.cancel(pIntent);
        }
    }
}
