package com.ayotong.miranda;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.ayotong.miranda.DBCtrl.UserInfoDB;
import com.ayotong.miranda.app.Profile_fragment;
import com.ayotong.miranda.model.UserInfo;

import java.util.Calendar;

/**
 * Created by burhan on 15/07/17.
 */

public class SignUpActivity extends AppCompatActivity {
    private EditText etfullname, etage, etweight, etheight, ettidur, ettidursiang;
    private CheckBox chkispreg;
    private RadioGroup rg;
    private boolean ispreg;
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
        chkispreg = (CheckBox) findViewById(R.id.checkBox);
        ettidur = (EditText) findViewById(R.id.tidur);
        ettidursiang = (EditText) findViewById(R.id.tidursiang);


        rg =(RadioGroup)findViewById(R.id.radiogender);
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

        chkispreg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                ispreg = chkispreg.isChecked();

            }
        });
        Button continuebtn = (Button)findViewById(R.id.signUp);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = etfullname.getText().toString();
                int age = Integer.parseInt(etage.getText().toString());
                int weight = Integer.parseInt(etweight.getText().toString());
                int height = Integer.parseInt(etheight.getText().toString());
                String tidur = ettidur.getText().toString();
                String tidursiang = ettidursiang.getText().toString();

                UserInfoDB userdb = new UserInfoDB(getApplicationContext());
                UserInfo user = new UserInfo(0, fullname, age, gender , weight, height, ispreg, tidursiang, tidur);
                userdb.insert(0, user);
                userdb.close();

                Intent in = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
}