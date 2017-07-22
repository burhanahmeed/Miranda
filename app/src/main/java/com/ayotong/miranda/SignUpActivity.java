package com.ayotong.miranda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ayotong.miranda.DBCtrl.UserInfoDB;
import com.ayotong.miranda.app.Profile_fragment;
import com.ayotong.miranda.model.UserInfo;

/**
 * Created by burhan on 15/07/17.
 */

public class SignUpActivity extends AppCompatActivity {
    private EditText etfullname, etage, etweight, etheight;
    private CheckBox chkispreg;
    private RadioGroup rg;
    private boolean ispreg;
    private int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_signup);

        etfullname = (EditText) findViewById(R.id.your_full_name);
        etage = (EditText) findViewById(R.id.input_age);
        etweight = (EditText) findViewById(R.id.weight);
        etheight = (EditText) findViewById(R.id.height_);
        chkispreg = (CheckBox) findViewById(R.id.checkBox);

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
                int height = Integer.parseInt(etheight.getText().toString())
                        ;
                UserInfoDB userdb = new UserInfoDB(getApplicationContext());
                UserInfo user = new UserInfo(0, fullname, age, gender , weight, height, ispreg, "1000", "2000");
                userdb.insert(0, user);
                userdb.close();

                Intent in = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
}