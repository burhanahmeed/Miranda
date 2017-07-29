package com.ayotong.miranda.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ayotong.miranda.DialogActivity;
import com.ayotong.miranda.R;
import com.ayotong.miranda.Service.ReminderReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class About_fragment extends Fragment {

    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
      View v = inflater.inflate(R.layout.fragment_about, container, false);



        return v;
    }

    public void questMunim(String time){
        AlarmManager manager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity().getApplicationContext(), ReminderReceiver.class);
        intent.putExtra("jam",time);
        intent.putExtra("ques","Minum cucu dulu yaa");
        intent.putExtra("xp","100");
        PendingIntent pIntent = PendingIntent.getBroadcast(getActivity(),
                102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), 2*60*1000, pIntent);
    }

    public void cancelMinum(){
        AlarmManager manager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("REPEATING_ALARM_TRIGGERED");
        PendingIntent pIntent = PendingIntent.getBroadcast(getActivity(),
                102, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        manager.cancel(pIntent);
    }

}
