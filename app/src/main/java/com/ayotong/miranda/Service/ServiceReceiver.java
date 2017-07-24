package com.ayotong.miranda.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by burhan on 22/07/17.
 */

public class ServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        if (intent.getAction()!= null){
            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)|| intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
                context.startService(new Intent(context, BackgroundSvc.class));
            }
        }
//        Intent myIntent = new Intent(context, BackgroundSvc.class);
//        context.startService(myIntent);
    }
}
