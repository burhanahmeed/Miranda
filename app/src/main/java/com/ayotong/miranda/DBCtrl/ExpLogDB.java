package com.ayotong.miranda.DBCtrl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ayotong.miranda.model.ExpLog;

import java.util.ArrayList;

/**
 * Created by Alpha on 16/07/2017.
 */

public class ExpLogDB extends DatabaseDAO implements ExpLogDAO {

    private Context context;

    public ExpLogDB(Context context){
        super(context, DatabaseDAO.dName, ExpLog.TABLE_CREATE, ExpLog.DATABASE_TABLE, ExpLog.DATABASE_VERSION);
        this.context = context;
    }
    public ExpLog insert(ExpLog expLog){
        ContentValues cv = new ContentValues();
        cv.put(ExpLog.COL_TIMESTAMP, expLog.getTimestamp());
        cv.put(ExpLog.COL_QUESTID, expLog.getQuest_id());
        cv.put(ExpLog.COL_EXPGAIN, expLog.getExp_gain());
        super.insert(ExpLog.DATABASE_TABLE, cv);
        return new ExpLog(expLog.getTimestamp(), expLog.getQuest_id(), expLog.getExp_gain());
    }

    public ExpLog insert(String timestamp, int quest_id, int exp_gain){
        ContentValues cv = new ContentValues();
        cv.put(ExpLog.COL_TIMESTAMP, timestamp);
        cv.put(ExpLog.COL_QUESTID, quest_id);
        cv.put(ExpLog.COL_EXPGAIN, exp_gain);
        super.insert(ExpLog.DATABASE_TABLE, cv);
        return new ExpLog(timestamp, quest_id, exp_gain);
    }

    public ArrayList<ExpLog> readLog(){
        String[] columns = new String[]{ExpLog.COL_TIMESTAMP, ExpLog.COL_QUESTID, ExpLog.COL_EXPGAIN};
        Cursor cursor = super.get(ExpLog.DATABASE_TABLE, columns , ExpLog.COL_TIMESTAMP + " = * ");

        ArrayList<ExpLog> arrlog = null;
        if(cursor != null && cursor.moveToFirst()) {
            arrlog = new ArrayList<ExpLog>();
            while (cursor.isAfterLast()==false) {
                //Calllog is a class with list of fileds
                ExpLog log= new ExpLog();
                log.setTimestamp(cursor.getString(cursor.getColumnIndex(ExpLog.COL_TIMESTAMP)));
                log.setQuest_id(cursor.getInt(cursor.getColumnIndex(ExpLog.COL_QUESTID)));
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

    public ArrayList<ExpLog> readLog(String timestamp){
        String[] columns = new String[]{ExpLog.COL_TIMESTAMP, ExpLog.COL_QUESTID, ExpLog.COL_EXPGAIN};
        Cursor cursor = super.get(ExpLog.DATABASE_TABLE, columns , ExpLog.COL_TIMESTAMP + " = "+ timestamp);

        ArrayList<ExpLog> arrlog = null;
        if(cursor != null && cursor.moveToFirst()) {
            arrlog = new ArrayList<ExpLog>();
            while (cursor.isAfterLast()==false) {
                //Calllog is a class with list of fileds
                ExpLog log= new ExpLog();
                log.setTimestamp(cursor.getString(cursor.getColumnIndex(ExpLog.COL_TIMESTAMP)));
                log.setQuest_id(cursor.getInt(cursor.getColumnIndex(ExpLog.COL_QUESTID)));
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

    public int clearLog(){
        return super.delete(ExpLog.DATABASE_TABLE, ExpLog.COL_TIMESTAMP+"=*", null);
    }

    public int clearLog(String timestamp){
        return super.delete(ExpLog.DATABASE_TABLE, ExpLog.COL_TIMESTAMP+"=" +timestamp, null);
    }
}
