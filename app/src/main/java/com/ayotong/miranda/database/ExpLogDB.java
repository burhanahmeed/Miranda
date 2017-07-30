package com.ayotong.miranda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ayotong.miranda.model.ExpLog;

import java.util.ArrayList;

/**
 * Created by Alpha on 16/07/2017.
 */

public class ExpLogDB extends DatabaseDAO implements ExpLogDAO {

    private Context context;
    private SQLiteDatabase sqDB;

    public ExpLogDB(Context context){
        super(context, DatabaseDAO.dName, ExpLog.TABLE_CREATE, ExpLog.DATABASE_TABLE, ExpLog.DATABASE_VERSION);
        this.context = context;
    }

    public ExpLog insert(ExpLog expLog){
        ContentValues cv = new ContentValues();
        cv.put(ExpLog.COL_TIMESTAMP, expLog.getTimestamp());
        cv.put(ExpLog.COL_MONTH, expLog.getMonth());
        cv.put(ExpLog.COL_EXPGAIN, expLog.getExp_gain());
        super.insert(ExpLog.DATABASE_TABLE, cv);
        return new ExpLog( expLog.getTimestamp(), expLog.getMonth(), expLog.getExp_gain());
    }

    public ExpLog insert(String timestamp, int month, int exp_gain){
        ContentValues cv = new ContentValues();
        cv.put(ExpLog.COL_TIMESTAMP, timestamp);
        cv.put(ExpLog.COL_MONTH, month);
        cv.put(ExpLog.COL_EXPGAIN, exp_gain);
        super.insert(ExpLog.DATABASE_TABLE, cv);
        return new ExpLog(timestamp, month, exp_gain);
    }

    public ArrayList<ExpLog> readLog(){
        String[] columns = new String[]{ExpLog.COL_TIMESTAMP, ExpLog.COL_MONTH, ExpLog.COL_EXPGAIN};
        Cursor cursor = super.get(ExpLog.DATABASE_TABLE, columns);

        ArrayList<ExpLog> arrlog = new ArrayList<ExpLog>();
        if(cursor != null && cursor.moveToFirst()) {
            while (cursor.isAfterLast()==false) {
                //Calllog is a class with list of fileds
                ExpLog log= new ExpLog();
                log.setTimestamp(cursor.getString(cursor.getColumnIndex(ExpLog.COL_TIMESTAMP)));
                log.setMonth(cursor.getInt(cursor.getColumnIndex(ExpLog.COL_MONTH)));
                log.setExp_gain(cursor.getInt(cursor.getColumnIndex(ExpLog.COL_EXPGAIN)));
                arrlog.add(log);
                cursor.moveToNext();
            }
        }else{
            Log.d("Error", "cursor are null");
        }
        cursor.close();
        return arrlog;
    }

    public ArrayList<ExpLog> readLogByMonth(int month){
        String[] columns = new String[]{ExpLog.COL_TIMESTAMP, ExpLog.COL_MONTH, ExpLog.COL_EXPGAIN};
        Cursor cursor = super.get(ExpLog.DATABASE_TABLE, columns, ExpLog.COL_MONTH + " = " + month);

        ArrayList<ExpLog> arrlog = new ArrayList<ExpLog>();
        if(cursor != null && cursor.moveToFirst()) {
            while (cursor.isAfterLast()==false) {
                //Calllog is a class with list of fileds
                ExpLog log= new ExpLog();
                log.setTimestamp(cursor.getString(cursor.getColumnIndex(ExpLog.COL_TIMESTAMP)));
                log.setMonth(cursor.getInt(cursor.getColumnIndex(ExpLog.COL_MONTH)));
                log.setExp_gain(cursor.getInt(cursor.getColumnIndex(ExpLog.COL_EXPGAIN)));
                arrlog.add(log);
                cursor.moveToNext();
            }
        }else{
            Log.d("Error", "cursor are null");
        }
        cursor.close();
        return arrlog;
    }

    public int deleteLog(){
        return super.delete(ExpLog.DATABASE_TABLE, ExpLog.COL_TIMESTAMP+"=*", null);
    }

    public int deleteLogByMonth(int month){
        return super.delete(ExpLog.DATABASE_TABLE, ExpLog.COL_MONTH+"=" +month, null);
    }

    public Cursor SumOfXP(){
        sqDB = this.getWritableDatabase();
        Cursor c = sqDB.rawQuery("SELECT SUM("+ExpLog.COL_EXPGAIN+") as Total FROM "+ExpLog.DATABASE_TABLE, null);
        Log.d("SUM", "SumOfXP: "+c.getColumnIndex("Total"));
        return c;
    }

    public void closeDB(){
        sqDB.close();
    }
}