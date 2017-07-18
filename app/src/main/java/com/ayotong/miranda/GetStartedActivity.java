package com.ayotong.miranda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * Created by burhan on 18/07/17.
 */

public class GetStartedActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_signup);
        Button startBtn = (Button)findViewById(R.id.get_start_btn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(GetStartedActivity.this, SignUpActivity.class);
                startActivity(in);
            }
        });

    }
}
