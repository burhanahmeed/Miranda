package com.ayotong.miranda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.ayotong.miranda.DBCtrl.UserInfoDB;
import com.ayotong.miranda.model.UserInfo;

/**
 * Created by burhan on 15/07/17.
 */

public class SignUpActivity extends AppCompatActivity {
    private EditText etfullname;
    private EditText etage;
    private EditText etweight;
    private EditText etheight;
    private CheckBox chkispreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_signup);

        Button continuebtn = (Button)findViewById(R.id.signUp);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etfullname = (EditText) findViewById(R.id.your_full_name);
                etage = (EditText) findViewById(R.id.input_age);
                etweight = (EditText) findViewById(R.id.weight);
                etheight = (EditText) findViewById(R.id.height_);
                chkispreg = (CheckBox) findViewById(R.id.checkBox);
                String fullname = etfullname.getText().toString();
                int age = Integer.parseInt(etage.getText().toString());
                int weight = Integer.parseInt(etweight.getText().toString());
                int height = Integer.parseInt(etheight.getText().toString());
                boolean ispreg = chkispreg.isChecked();

                UserInfoDB userdb = new UserInfoDB(getApplicationContext());
                UserInfo user = new UserInfo(0, fullname, age, "male", weight, height, ispreg, "1000", "2000");
                userdb.insert(0, user);
                Intent in = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(in);
            }
        });
    }
}
