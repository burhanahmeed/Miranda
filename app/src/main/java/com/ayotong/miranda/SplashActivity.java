package com.ayotong.miranda;

/**
 * Created by burhan on 13/07/17.
 */
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.ayotong.miranda.Service.BackgroundSvc;

import java.util.Calendar;

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
                final String PREFS_NAME = "MyPrefsFile";

                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

                if (settings.getBoolean("my_first_time", true)) {
                    //the app is being launched for first time, do something
                    Log.d("Comments", "First time");
                    // first time task
                    Intent i = new Intent(SplashActivity.this, GetStartedActivity.class);
                    startActivity(i);
                    // record the fact that the app has been started at least once
                    settings.edit().putBoolean("my_first_time", false).commit();

                }else{
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

        startService(new Intent(this, BackgroundSvc.class));
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(this, BackgroundSvc.class);
        PendingIntent pintent = PendingIntent
                .getService(this, 0, intent, 0);

        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // Start service every 20 seconds
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                20* 1000, pintent);
    }
}
