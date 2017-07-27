package com.ayotong.miranda.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.ayotong.miranda.DialogActivity;
import com.ayotong.miranda.R;

import java.util.Calendar;
import java.util.zip.Inflater;

//for calling ==> startService(new Intent(this, BackgroundService.class));

public class BackgroundSvc extends Service {
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;
    Inflater mInflater;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("background", "onCreate start ");

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
//                Toast.makeText(context, "Service is still running", Toast.LENGTH_LONG).show();
                Log.i("LocalService", "still run ");
                handler.postDelayed(runnable, 10000);
            }
        };

        handler.postDelayed(runnable, 10000);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "start again ");
        // REGISTER RECEIVER THAT HANDLES SCREEN ON AND SCREEN OFF LOGIC
        Toast.makeText(context, "Miranda", Toast.LENGTH_LONG).show();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenStatusReceiver();
        registerReceiver(mReceiver, filter);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        /* IF YOU WANT THIS SERVICE KILLED WITH THE APP THEN UNCOMMENT THE FOLLOWING LINE */
        //handler.removeCallbacks(runnable);
//        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
        super.onDestroy();
        Log.i("LocalService", "service destroy");
        PendingIntent service = PendingIntent.getService(
                getApplicationContext(),
                1001,
                new Intent(getApplicationContext(), ServiceReceiver.class),
                PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, service);
//        sendBroadcast(new Intent("YouWillNeverKillMe"));
    }

    @Override
    public void onStart(Intent intent, int startid) {
//        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
        Log.d("myTag", "Service start");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        Log.d("Local", "TASK REMOVED");

        PendingIntent service = PendingIntent.getBroadcast(
                getApplicationContext(),
                1001,
                new Intent(getApplicationContext(), ServiceReceiver.class),
                PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 100, service);
    }

    private Thread.UncaughtExceptionHandler defaultUEH;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.d("local", "Uncaught exception start!");
        ex.printStackTrace();

        //Same as done in onTaskRemoved()
        PendingIntent service = PendingIntent.getBroadcast(
                getApplicationContext(),
                1001,
                new Intent(getApplicationContext(), ServiceReceiver.class),
                PendingIntent.FLAG_ONE_SHOT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 100, service);
        System.exit(2);
        }
    };
}