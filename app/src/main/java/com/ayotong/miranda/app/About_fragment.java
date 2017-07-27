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

        Button bt = (Button)v.findViewById(R.id.button2);
        Button repeat = (Button)v.findViewById(R.id.button1);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String jam = "21:21";
                String[] jams = jam.split(":");

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(jams[0]));
                calendar.set(Calendar.MINUTE, Integer.parseInt(jams[1]));
                calendar.set(Calendar.SECOND, 0);
                Intent in = new Intent(getActivity().getApplicationContext(), ReminderReceiver.class);
                in.putExtra("jam",jam);
                in.putExtra("ques","Ayo bangun tong, masak kalah sama otong");
                in.putExtra("xp","23");
                final int _id = (int) System.currentTimeMillis();
                PendingIntent peint = PendingIntent.getBroadcast(getActivity(), _id, in,PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager am = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, peint);
            }
        });
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat tm = new SimpleDateFormat("HH:mm");
                String times = tm.format(Calendar.getInstance().getTime());
                String time = times;
                questMunim(time);
            }
        });

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
