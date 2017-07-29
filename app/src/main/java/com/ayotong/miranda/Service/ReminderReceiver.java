package com.ayotong.miranda.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;

import com.ayotong.miranda.DialogActivity;
import com.ayotong.miranda.database.QuestDB;
import com.ayotong.miranda.model.Quest;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by burhan on 25/07/17.
 */

public class ReminderReceiver extends BroadcastReceiver{
    private Quest insertQuest;
    private QuestDB qDB;
    @Override
    public void onReceive(Context context, Intent intent){
//        if (intent.getAction()!= null){
//            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)|| intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
//                context.startService(new Intent(context, BackgroundSvc.class));
//            }
//        }
        insertQuest = new Quest();
        qDB = new QuestDB(context);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat tm = new SimpleDateFormat("HH:mm");
        String times = tm.format(Calendar.getInstance().getTime());
        String time = times;

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

//        Timestamp tm = new Timestamp(System.currentTimeMillis());
        myIntent.putExtra("Qid",String.valueOf((int)System.currentTimeMillis()));
        //insert database taruh sini bisa
        insertQuest.setId((int)System.currentTimeMillis());
        insertQuest.setJam(time);
        insertQuest.setExp(Integer.parseInt(xp));
        insertQuest.setQuestDescription(ques);
        qDB.insert(insertQuest);
        Log.d("Insert DB", "BErhasil, "+(int)System.currentTimeMillis()+", "+ques);

        context.startActivity(myIntent);


    }
}
