package com.ayotong.miranda;

/**
 * Created by burhan on 13/07/17.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class SplashActivity extends Activity{
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2400;
    private boolean x = true; //false berati baru pertama donlod

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_intro);

        new Handler().postDelayed(new Runnable() {

			/*
             * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (x == false){
                    Intent i = new Intent(SplashActivity.this, GetStartedActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
