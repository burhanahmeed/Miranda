package com.ayotong.miranda.core.databasecontroller;

import android.content.Context;

import com.ayotong.miranda.database.ExpLogDB;
import com.ayotong.miranda.model.ExpLog;

import java.util.ArrayList;

/**
 * Created by Alpha on 29/07/2017.
 */

public class ExpLogCtrl {
    private ExpLog log;
    private ExpLogDB logdb;
    ArrayList<ExpLog> arrlog = new ArrayList<ExpLog>();
    private Context context;

    public ExpLogCtrl(Context context){
        this.context = context;
        logdb = new ExpLogDB(context);
    }

    public void insertLog(){

    }
}