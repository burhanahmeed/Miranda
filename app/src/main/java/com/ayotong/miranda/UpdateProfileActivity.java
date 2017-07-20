package com.ayotong.miranda;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class UpdateProfileActivity extends AppCompatActivity {

    EditText fullname, age, weight, height, tidur, tsiang;
    RadioGroup rg;
    RadioButton rb;

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

        fullname = (EditText) findViewById(R.id.your_full_name);
        String fname = fullname.getText().toString();
        age = (EditText) findViewById(R.id.input_age);
        String ages = age.getText().toString();
        weight = (EditText) findViewById(R.id.weight);
        String berat = weight.getText().toString();
        height = (EditText) findViewById(R.id.height_);
        String tinggi = height.getText().toString();
        tidur = (EditText)findViewById(R.id.tidur);
        String tidurs = tidur.getText().toString();
        tsiang = (EditText)findViewById(R.id.tidursiang);
        String tidursiang = tsiang.getText().toString();


        rg =(RadioGroup)findViewById(R.id.radiogender);

        Button btnDisplay=(Button)findViewById(R.id.savingbtn);

        int selectedId=rg.getCheckedRadioButtonId();
        rb=(RadioButton)findViewById(selectedId);

        final CheckBox cbP = (CheckBox)findViewById(R.id.checkBox);
        cbP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                boolean pregnant = cbP.isChecked();

            }
        });

    }

}
