package com.ayotong.miranda;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by burhan on 21/07/17.
 */

public class DialogActivity extends Activity{
    private Window window;
    String jam, xp, quest;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.dialog_popup);

        jam = getIntent().getStringExtra("jam");
        quest = getIntent().getStringExtra("ques");
        xp = getIntent().getStringExtra("xp");

        TextView tjam = (TextView)findViewById(R.id.txtJam);
        TextView txp = (TextView)findViewById(R.id.txtXP);
        TextView tquest = (TextView)findViewById(R.id.txtQuest);
        tjam.setText(jam);
        txp.setText(xp);
        tquest.setText(quest);

        ImageButton ac =(ImageButton) findViewById(R.id.id_accomplish);

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        vibrate();
    }

    private void vibrate(){
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
    }
}
