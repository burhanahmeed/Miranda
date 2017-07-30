package com.ayotong.miranda;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
import android.widget.Toast;

import com.ayotong.miranda.Service.ReminderReceiver;
import com.ayotong.miranda.core.TimeProcessing;
import com.ayotong.miranda.core.databasecontroller.ExpLogCtrl;
import com.ayotong.miranda.core.databasecontroller.QuestCtrl;
import com.ayotong.miranda.database.ExpLogDB;
import com.ayotong.miranda.database.QuestDB;
import com.ayotong.miranda.model.ExpLog;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by burhan on 21/07/17.
 */

public class DialogActivity extends Activity{
    private Window window;
    String jam, xp, quest, status, id,Qid;
    private MediaPlayer mMediaPlayer;
    ExpLog xpLog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.dialog_popup);
        TextView tjam = (TextView)findViewById(R.id.txtJam);
        TextView txp = (TextView)findViewById(R.id.txtXP);
        TextView tquest = (TextView)findViewById(R.id.txtQuest);

        xpLog = new ExpLog();
        jam = getIntent().getStringExtra("jam");
        quest = getIntent().getStringExtra("ques");
        xp = getIntent().getStringExtra("xp");
        status = getIntent().getStringExtra("status");
        id = getIntent().getStringExtra("id");
        Qid = getIntent().getStringExtra("Qid");

        TimeProcessing timeproc = new TimeProcessing();
        String times = timeproc.getTime();
        final String time = times;
        tjam.setText(time);
        txp.setText(xp);
        tquest.setText(quest);
        Log.d("DialogActivity", "onCreate: Quest: " +quest +"\nStatus minum: " +status);

        ImageButton ac =(ImageButton) findViewById(R.id.id_accomplish);
        ImageButton dis = (ImageButton)findViewById(R.id.id_dissmiss);
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmMinum();
                insertXP();
                QuestCtrl qctrl = new QuestCtrl(getApplicationContext());
                qctrl.deleteQuest(Integer.valueOf(Qid));
                qctrl.closeDB();

                Toast.makeText(DialogActivity.this, "Quest completed! +"+xp+" XP", Toast.LENGTH_LONG).show();
                finish();
//                System.exit(0);
            }
        });

        dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DialogActivity.this, "Quest dismissed as a pending", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        vibrate();
        playSound();
    }

    private void insertXP(){
        TimeProcessing timeproc = new TimeProcessing();
        ExpLogCtrl logCtrl = new ExpLogCtrl(this);
        String ts = timeproc.getTimestamp();
        int month = Integer.parseInt(timeproc.getMonth());
        int expgain = Integer.parseInt(xp);

        xpLog.setExp_gain(expgain);
        xpLog.setMonth(month);
        xpLog.setTimestamp(ts);
        logCtrl.addLog(xpLog);
        logCtrl.closeDB();
        Log.d("DialogActivity: ", "Log: \ntimestamp: " +ts +"\nmonth: " +month +"\nexp: " +expgain);
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
        TimeProcessing timeproc = new TimeProcessing();
        String time = timeproc.getTime();

        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("jam",time);
        intent.putExtra("ques","Minum cucu dulu yaa");
        intent.putExtra("xp","20");
        intent.putExtra("status","tidak ada");
        intent.putExtra("id","006");
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

    public void playSound(){
        mMediaPlayer = new MediaPlayer();

        if (id.equals("001")){
            mMediaPlayer = MediaPlayer.create(this, R.raw.rest);
        }else if (id.equals("002")){
            mMediaPlayer = MediaPlayer.create(this, R.raw.night);
        }else if (id.equals("003")){
            mMediaPlayer = MediaPlayer.create(this, R.raw.morning);
        }else if (id.equals("004")){
            mMediaPlayer = MediaPlayer.create(this, R.raw.nap);
        }else if (id.equals("005")){
            mMediaPlayer = MediaPlayer.create(this, R.raw.napbangun);
        }else if (id.equals("006")){
            mMediaPlayer = MediaPlayer.create(this, R.raw.drink);
        }

        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(false);
        mMediaPlayer.start();
    }
}
