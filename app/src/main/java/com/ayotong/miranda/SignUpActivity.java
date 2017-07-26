package com.ayotong.miranda;

import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;

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
}