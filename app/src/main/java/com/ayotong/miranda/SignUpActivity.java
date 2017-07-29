package com.ayotong.miranda;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.ayotong.miranda.Service.ReminderReceiver;
import com.ayotong.miranda.core.databasecontroller.QuestStatusGenerator;
import com.ayotong.miranda.database.UserInfoDB;
import com.ayotong.miranda.model.UserInfo;

import java.util.Calendar;

/**
 * Created by burhan on 15/07/17.
 */

public class SignUpActivity extends AppCompatActivity {
    private CheckBox chkispreg, chknap;
    private TextInputLayout tilnap;
    private EditText etfullname, etage, etweight, etheight, ettidur, ettidursiang;
    private RadioGroup rg;
    private boolean ispreg, isnap;
    private int gender;

    QuestStatusGenerator questGenerate;

    public SignUpActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_signup);

        etfullname = (EditText) findViewById(R.id.your_full_name);
        etage = (EditText) findViewById(R.id.input_age);
        etweight = (EditText) findViewById(R.id.weight);
        etheight = (EditText) findViewById(R.id.height_);
        ettidur = (EditText) findViewById(R.id.tidur);
        ettidursiang= (EditText) findViewById(R.id.tidursiang);
        chkispreg = (CheckBox) findViewById(R.id.checkBox);
        chknap = (CheckBox) findViewById(R.id.cbnap);
        tilnap = (TextInputLayout) findViewById(R.id.input_tidursiang);
        rg =(RadioGroup)findViewById(R.id.radiogender);

        //Listener for radiogrup gender
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton1:
                        gender = 0;
                        break;
                    case R.id.radioButton2:
                        gender = 1;
                        break;
                }
            }
        });

        //Listener for EditText tidur and process time input
        ettidur.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(SignUpActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                ettidur.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        //listener for EditText  tidursiang and process time input
        ettidursiang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(SignUpActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                ettidursiang.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        //Listener for CheckBox ispreg
        chkispreg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                ispreg = chkispreg.isChecked();

            }
        });

        //Listener for CheckBox isnap, showing and hiding Input Nap  Time
        chknap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chknap checked?
                if(chknap.isChecked()){
                    isnap = true;
                    tilnap.setVisibility(View.VISIBLE);
                }else{
                    isnap = false;
                    tilnap.setVisibility(View.GONE);
                }
            }
        });

        //Button for continue/save
        Button continuebtn = (Button)findViewById(R.id.signUp);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            saveToDB();

//                questGenerate = new QuestStatusGenerator(getApplicationContext());
//                questGenerate.createSleepQuest(Integer.parseInt(etage.getText().toString()), ettidur.getText().toString());
            alarmTidur();
            alarmBangun();
                if (isnap){
                    alarmNap();
                    alarmBangunNap();
                }else{

                }

            Intent in = new Intent(SignUpActivity.this, MainActivity.class);
            //startActivity(in);
                ComponentName cn = in.getComponent();
                Intent mainIntent = IntentCompat.makeRestartActivityTask(cn);
                startActivity(mainIntent);
            finish();
            }
        });
    }


    private void saveToDB(){
        String fullname = etfullname.getText().toString();
        int age = Integer.parseInt(etage.getText().toString());
        int weight = Integer.parseInt(etweight.getText().toString());
        int height = Integer.parseInt(etheight.getText().toString());

        String tidur = ettidur.getText().toString();
        String tidursiang;
        if(isnap)
            tidursiang= ettidursiang.getText().toString();
        else
            tidursiang = "none";

        UserInfoDB userdb = new UserInfoDB(getApplicationContext());
        UserInfo user = new UserInfo(0, fullname, age, gender , weight, height, ispreg, tidursiang, tidur);
        userdb.insert(0, user);
        userdb.close();
    }

    private void alarmTidur(){
        String jam = ettidur.getText().toString();
        String[] jams = jam.split(":");
        long _alarm = 0;

        Calendar calendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(jams[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(jams[1]));
        calendar.set(Calendar.SECOND, 0);
        if(calendar.getTimeInMillis() <= now.getTimeInMillis())
            _alarm = calendar.getTimeInMillis() + (AlarmManager.INTERVAL_DAY+1);
        else
            _alarm = calendar.getTimeInMillis();

        Intent in = new Intent(this, ReminderReceiver.class);
        in.putExtra("jam",jam);
        in.putExtra("ques","You're getting tired, it's time to sleep baby");
        in.putExtra("xp","50");
        in.putExtra("status","off");
        in.putExtra("id","002");
        PendingIntent peint = PendingIntent.getBroadcast(this, 1, in,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, _alarm, AlarmManager.INTERVAL_DAY, peint);

        Log.i("difire","Berhasil");
    }

    private void alarmBangun(){
//        String jam = ettidur.getText().toString();
        String jam = "06:25";
        String[] jams = jam.split(":");
        long _alarm = 0;

        Calendar now = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(jams[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(jams[1])+6);
        calendar.set(Calendar.SECOND, 0);
        if(calendar.getTimeInMillis() <= now.getTimeInMillis())
            _alarm = calendar.getTimeInMillis() + (AlarmManager.INTERVAL_DAY+1);
        else
            _alarm = calendar.getTimeInMillis();

        Intent in = new Intent(this, ReminderReceiver.class);
        in.putExtra("jam",jam);
        in.putExtra("ques","It's time to wake up and have a nice day");
        in.putExtra("xp","50");
        in.putExtra("status","on");
        in.putExtra("id","003");
        PendingIntent peint = PendingIntent.getBroadcast(this, 2, in,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, _alarm, AlarmManager.INTERVAL_DAY, peint);
    }

    private void alarmNap(){
        String jam = ettidursiang.getText().toString();
        String[] jams = jam.split(":");

        long _alarm = 0;

        Calendar calendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(jams[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(jams[1])+6);
        calendar.set(Calendar.SECOND, 0);
        if(calendar.getTimeInMillis() <= now.getTimeInMillis())
            _alarm = calendar.getTimeInMillis() + (AlarmManager.INTERVAL_DAY+1);
        else
            _alarm = calendar.getTimeInMillis();

        Intent in = new Intent(this, ReminderReceiver.class);
        in.putExtra("jam",jam);
        in.putExtra("ques","Take a short nap will be beneficial");
        in.putExtra("xp","25");
        in.putExtra("status","off");
        in.putExtra("id","004");
        PendingIntent peint = PendingIntent.getBroadcast(this, 3, in,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, _alarm, AlarmManager.INTERVAL_DAY, peint);
    }
    private void alarmBangunNap(){
        String jam = ettidursiang.getText().toString();
        String[] jams = jam.split(":");

        long _alarm = 0;

        Calendar calendar = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(jams[0])+1);
        calendar.set(Calendar.MINUTE, Integer.parseInt(jams[1]));
        calendar.set(Calendar.SECOND, 0);
        if(calendar.getTimeInMillis() <= now.getTimeInMillis())
            _alarm = calendar.getTimeInMillis() + (AlarmManager.INTERVAL_DAY+1);
        else
            _alarm = calendar.getTimeInMillis();

        Intent in = new Intent(this, ReminderReceiver.class);
        in.putExtra("jam",jam);
        in.putExtra("ques","OK, it's time to back to work");
        in.putExtra("xp","50");
        in.putExtra("status","on");
        in.putExtra("id","005");
        PendingIntent peint = PendingIntent.getBroadcast(this, 4, in,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, _alarm, AlarmManager.INTERVAL_DAY, peint);
    }
}