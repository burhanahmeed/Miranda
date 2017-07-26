package com.ayotong.miranda;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ayotong.miranda.Service.ReminderReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by burhan on 21/07/17.
 */

public class DialogActivity extends Activity{
    private Window window;
    String jam, xp, quest, status;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.dialog_popup);

        jam = getIntent().getStringExtra("jam");
        quest = getIntent().getStringExtra("ques");
        xp = getIntent().getStringExtra("xp");
        status = getIntent().getStringExtra("status");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat tm = new SimpleDateFormat("HH:mm");
        String times = tm.format(Calendar.getInstance().getTime());
        String time = times;

        TextView tjam = (TextView)findViewById(R.id.txtJam);
        TextView txp = (TextView)findViewById(R.id.txtXP);
        TextView tquest = (TextView)findViewById(R.id.txtQuest);
        tjam.setText(time);
        txp.setText(xp);
        tquest.setText(quest);
        Log.d("local", "onCreate: "+status);

        ImageButton ac =(ImageButton) findViewById(R.id.id_accomplish);
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmMinum();

                finish();
                System.exit(0);
            }
        });

        vibrate();
    }

    private void vibrate(){
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
    }

    private void alarmMinum(){
        if (status.equals("on")){
            Log.d("alarmMinum: ","ON");
            alarmMinumDo();
        }else if (status.equals("off")){
            Log.d("alarmMinum: ","OFF");
            cancelMinum();
        }
    }
    private void alarmMinumDo(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat tm = new SimpleDateFormat("HH:mm");
        String times = tm.format(Calendar.getInstance().getTime());
        String time = times;

        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("jam",time);
        intent.putExtra("ques","Minum cucu dulu yaa");
        intent.putExtra("xp","100");
        intent.putExtra("status","tidak ada");
        PendingIntent pIntent = PendingIntent.getBroadcast(this,
                102, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), 60*60*1000, pIntent);

    }
    public void cancelMinum(){
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("jam","00:00");
        intent.putExtra("ques","Minum cucu dulu yaa");
        intent.putExtra("xp","100");
        intent.putExtra("status","tidak ada");
        PendingIntent pIntent = PendingIntent.getBroadcast(this,
                102, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        manager.cancel(pIntent);
    }
}
