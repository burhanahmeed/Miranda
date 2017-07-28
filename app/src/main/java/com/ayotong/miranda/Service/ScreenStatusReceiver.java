package com.ayotong.miranda.Service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by burhan on 26/07/17.
 */

public class ScreenStatusReceiver extends BroadcastReceiver {
    private static final int ALARM_ID = 10;
    @Override
    public void onReceive(Context context, Intent intent){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        AlarmManager service = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, ReminderReceiver.class);
        i.putExtra("jam","00:00");
        i.putExtra("ques","Rest Your Eyes for 20 seconds");
        i.putExtra("xp","100");
        i.putExtra("status","tidak ada");
        i.putExtra("id","001");
        PendingIntent pending = PendingIntent.getBroadcast(context, ALARM_ID, i,PendingIntent.FLAG_CANCEL_CURRENT);
        service.cancel(pending);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 20); //Delay startup by one minute - This is useful to prevent over utilization of resources at boot

        // Start alarm. Set a long repeat time if the screen is off and a short repeat time if the screen is on
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON) || intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            service.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 20000, pending);
            Log.d("Local", "layar nyala");  //alarm bunyi setiap 20 menit pas layar nyala
        }
        else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            service.cancel(pending);
            Log.d("Local", "layar mati");
        }
    }
}
