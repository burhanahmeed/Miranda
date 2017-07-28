package com.ayotong.miranda.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;

import com.ayotong.miranda.DialogActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by burhan on 25/07/17.
 */

public class ReminderReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
//        if (intent.getAction()!= null){
//            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)|| intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
//                context.startService(new Intent(context, BackgroundSvc.class));
//            }
//        }
        String status = intent.getExtras().getString("status");
        String jam = intent.getExtras().getString("jam");
        String ques = intent.getExtras().getString("ques");
        String xp = intent.getExtras().getString("xp");
        String id = intent.getExtras().getString("id");
        Log.d("local", "receiver: "+status+", di FIRE");
        Intent myIntent = new Intent(context, DialogActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myIntent.putExtra("jam",jam);
        myIntent.putExtra("ques",ques);
        myIntent.putExtra("xp",xp);
        myIntent.putExtra("status",status);
        myIntent.putExtra("id",id);
        context.startActivity(myIntent);

        //insert database taruh sini bisa


    }
}
