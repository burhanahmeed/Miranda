package com.ayotong.miranda.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import com.ayotong.miranda.DialogActivity;

/**
 * Created by burhan on 25/07/17.
 */

public class ReminderReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
//        if (intent.getAction()!= null){
//            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)|| intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
//                context.startService(new Intent(context, BackgroundSvc.class));
//            }
//        }
        Intent myIntent = new Intent(context, DialogActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);

        Vibrator vibrator = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
    }
}
