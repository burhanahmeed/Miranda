package com.ayotong.miranda.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ayotong.miranda.DialogActivity;
import com.ayotong.miranda.core.TimeProcessing;
import com.ayotong.miranda.core.databasecontroller.QuestCtrl;
import com.ayotong.miranda.database.QuestDB;
import com.ayotong.miranda.model.Quest;

/**
 * Created by burhan on 25/07/17.
 */

public class ReminderReceiver extends BroadcastReceiver{
    private Quest quest;
    private QuestCtrl qctrl;
    @Override
    public void onReceive(Context context, Intent intent){
//        if (intent.getAction()!= null){
//            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)|| intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
//                context.startService(new Intent(context, BackgroundSvc.class));
//            }
//        }
        quest = new Quest();
        qctrl = new QuestCtrl(context);

        TimeProcessing tm = new TimeProcessing();
        String time = tm.getTime();

        int id_ = (int)System.currentTimeMillis();

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
        myIntent.putExtra("Qid",String.valueOf(id_));

        //insert database taruh sini bisa
        quest.setId(id_);
        quest.setJam(time);
        quest.setExp(Integer.parseInt(xp));
        quest.setQuestDescription(ques);
        qctrl.addQuest(quest);
        qctrl.closeDB();
        Log.d("Insert DB", "Berhasil, "+(int)System.currentTimeMillis()+", "+ques);

        context.startActivity(myIntent);
    }
}
