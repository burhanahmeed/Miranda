package com.ayotong.miranda;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.ayotong.miranda.model.UserInfo;

import java.util.Calendar;

public class UpdateProfileActivity extends AppCompatActivity {

    EditText etfullname, etage, etweight, etheight, ettidur, ettidursiang;
    TextInputLayout tilnap;
    RadioGroup rg;
    RadioButton rb;
    CheckBox cbpreg, cbnap;
    UserInfo user;
    UserInfoDB userdb;
    private boolean ispreg, isnap;
    private int gender;

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(UpdateProfileActivity.this, MainActivity.class);
        intent.putExtra("fragnum", 1);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_action_toolbar);
        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.icon_menu_about);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        etfullname = (EditText) findViewById(R.id.your_full_name);
        etage = (EditText) findViewById(R.id.input_age);
        etweight = (EditText) findViewById(R.id.weight);
        etheight = (EditText) findViewById(R.id.height_);
        ettidur = (EditText)findViewById(R.id.tidur);
        ettidursiang = (EditText)findViewById(R.id.tidursiang);
        cbpreg = (CheckBox)findViewById(R.id.checkBox);
        cbnap = (CheckBox) findViewById(R.id.cbnap);
        tilnap = (TextInputLayout) findViewById(R.id.input_tidursiang);

        //Load data dulu
        loadProfile(user);
        Log.i("UpdateProfile: ", "load info to layout");

        rg =(RadioGroup)findViewById(R.id.radiogender);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbM:
                        gender = 0;
                        break;
                    case R.id.rbF:
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


                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateProfileActivity.this,
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


                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateProfileActivity.this,
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
        cbpreg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                ispreg = cbpreg.isChecked();
            }
        });

        //Listener for CheckBox isnap, showing and hiding Input Nap  Time
        cbnap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chknap checked?
                if(cbnap.isChecked()){
                    isnap = true;
                    tilnap.setVisibility(View.VISIBLE);
                }else{
                    isnap = false;
                    tilnap.setVisibility(View.GONE);
                }
            }
        });

        Button save = (Button)findViewById(R.id.savingbtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = etfullname.getText().toString();
                int age = Integer.parseInt(etage.getText().toString());
                int weight = Integer.parseInt(etweight.getText().toString());
                int height = Integer.parseInt(etheight.getText().toString());
                String tidur = ettidur.getText().toString();
                String tidursiang;

                if(isnap)
                    tidursiang = ettidursiang.getText().toString();
                else
                    tidursiang = "none";

                UserInfoDB userdb = new UserInfoDB(getApplicationContext());
                UserInfo user = new UserInfo(0, fullname, age, gender , weight, height, ispreg, tidursiang, "2000");
                userdb.updateInfo(user);
                userdb.close();

                Intent intent = new Intent(UpdateProfileActivity.this, MainActivity.class);
                intent.putExtra("fragnum", 1);
                startActivity(intent);
                finish();
            }
        });

    }
    public void loadProfile(UserInfo user) {
        etfullname = (EditText) findViewById(R.id.your_full_name);
        etage = (EditText) findViewById(R.id.input_age);
        etweight = (EditText) findViewById(R.id.weight);
        etheight = (EditText) findViewById(R.id.height_);
        ettidur = (EditText) findViewById(R.id.tidur);
        ettidursiang = (EditText) findViewById(R.id.tidursiang);
        cbpreg = (CheckBox) findViewById(R.id.checkBox);

        userdb = new UserInfoDB(getApplicationContext());
        user = userdb.loadInfo();

        etfullname.setText(user.getUsername());
        etage.setText(Integer.toString(user.getAge()));
        etweight.setText(Integer.toString(user.getWeight()));
        etheight.setText(Integer.toString(user.getHeight()));
        ettidur.setText(user.getStartsleep());
        ettidursiang.setText(user.getStartnap());
        if (user.ispregnant()) {
            cbpreg.setChecked(true);
        } else {
            cbpreg.setChecked(false);
        }
        if (user.getStartnap().equalsIgnoreCase("none")){
            isnap = false;
            cbnap.setChecked(false);
            tilnap.setVisibility(View.GONE);
        }else {
            tilnap.setVisibility(View.VISIBLE);
            cbnap.setChecked(true);
            isnap = true;
        }

        if (user.getGender()==0){
            RadioButton rmale = (RadioButton)findViewById(R.id.rbM);
            rmale.setChecked(true);
        }else{
            RadioButton rfemale = (RadioButton)findViewById(R.id.rbF);
            rfemale.setChecked(true);
        }
    }
}