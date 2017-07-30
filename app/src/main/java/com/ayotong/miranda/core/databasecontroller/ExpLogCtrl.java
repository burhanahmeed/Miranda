package com.ayotong.miranda.core.databasecontroller;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

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

    public void addLog(ExpLog log_item){
        logdb.insert(log_item);
    }

    public ArrayList<ExpLog> getAllLog(){
        return logdb.readLog();
    }

    public ArrayList<ExpLog> getLogByMonth(int month){
        return logdb.readLogByMonth(month);
    }

    public int getTotalXP(){
        int total=0;
        Cursor curxp = logdb.SumOfXP();
        if (curxp.moveToNext()) {
            total = curxp.getInt(curxp.getColumnIndex("Total"));
            Log.d("XP", "XP total "+total);
        }
        return total;
    }

    public void clearLog(){
        logdb.deleteLog();
    }

    public void clearLogByMonth(int month){
        logdb.deleteLogByMonth(month);
    }

    public void closeDB(){
        logdb.closeDB();
    }
}