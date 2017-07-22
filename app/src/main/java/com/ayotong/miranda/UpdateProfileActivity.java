package com.ayotong.miranda;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ayotong.miranda.DBCtrl.UserInfoDB;
import com.ayotong.miranda.app.Profile_fragment;
import com.ayotong.miranda.model.UserInfo;

public class UpdateProfileActivity extends AppCompatActivity {

    EditText etfullname, etage, etweight, etheight, ettidur, ettsiang;
    RadioGroup rg;
    RadioButton rb;
    CheckBox cbP;
    UserInfo user;
    UserInfoDB userdb;
    private boolean ispreg;
    private int gender;

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
        ettsiang = (EditText)findViewById(R.id.tidursiang);
        cbP = (CheckBox)findViewById(R.id.checkBox);

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

        cbP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                ispreg = cbP.isChecked();
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

                UserInfoDB userdb = new UserInfoDB(getApplicationContext());
                UserInfo user = new UserInfo(0, fullname, age, gender , weight, height, ispreg, "1000", "2000");
                userdb.updateInfo(user);
                userdb.close();
                Intent intent = new Intent(UpdateProfileActivity.this, MainActivity.class);
                intent.putExtra("fragnum", 1);
                startActivity(intent);
                finish();
            }
        });

    }
    public void loadProfile(UserInfo user){
        etfullname = (EditText) findViewById(R.id.your_full_name);
        etage = (EditText) findViewById(R.id.input_age);
        etweight = (EditText) findViewById(R.id.weight);
        etheight = (EditText) findViewById(R.id.height_);
        ettidur = (EditText)findViewById(R.id.tidur);
        ettsiang = (EditText)findViewById(R.id.tidursiang);
        cbP = (CheckBox)findViewById(R.id.checkBox);

        userdb = new UserInfoDB(getApplicationContext());
        user = userdb.loadInfo();
        etfullname.setText(user.getUsername());
        etage.setText(Integer.toString(user.getAge()));
        etweight.setText(Integer.toString(user.getWeight()));
        etheight.setText(Integer.toString(user.getHeight()));
        ettidur.setText(user.getStartsleep());
        ettsiang.setText(user.getStartnap());
        if (user.ispregnant()){
            cbP.setChecked(true);
        }else{
            cbP.setChecked(false);
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