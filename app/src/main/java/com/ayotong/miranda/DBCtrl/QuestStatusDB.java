package com.ayotong.miranda.DBCtrl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ayotong.miranda.model.QuestStatus;

import java.util.ArrayList;

/**
 * Created by Alpha on 16/07/2017.
 */

public class QuestStatusDB extends DatabaseDAO implements QuestStatusDAO {
    private Context context;

    public QuestStatusDB(Context context){
        super(context, DatabaseDAO.dName, QuestStatus.TABLE_CREATE, QuestStatus.DATABASE_TABLE, QuestStatus.DATABASE_VERSION);
        this.context = context;
    }

    public QuestStatus insert(QuestStatus quest_status){
        ContentValues cv = new ContentValues();
        cv.put(QuestStatus.COL_TIMESTAMP, quest_status.getTimestamp());
        cv.put(QuestStatus.COL_QUESTID, quest_status.getQuestid());
        cv.put(QuestStatus.COL_QUESTFLAG, quest_status.getQuestflag());
        super.insert(QuestStatus.DATABASE_TABLE, cv);
        return new QuestStatus(quest_status.getTimestamp(), quest_status.getQuestid(), quest_status.getQuestflag());
    }

    public QuestStatus insert(String timestamp, int questID, int quest_flag){
        ContentValues cv = new ContentValues();
        cv.put(QuestStatus.COL_TIMESTAMP, timestamp);
        cv.put(QuestStatus.COL_QUESTID, questID);
        cv.put(QuestStatus.COL_QUESTFLAG, quest_flag);
        super.insert(QuestStatus.DATABASE_TABLE, cv);
        return new QuestStatus(timestamp, questID, quest_flag);
    }

    public int updateQuestStatus(QuestStatus queststatus){
        ContentValues cv = new ContentValues();
        cv.put(QuestStatus.COL_TIMESTAMP, queststatus.getTimestamp());
        cv.put(QuestStatus.COL_QUESTID, queststatus.getQuestid());
        cv.put(QuestStatus.COL_QUESTFLAG, queststatus.getQuestflag());
        return super.update(QuestStatus.DATABASE_TABLE, QuestStatus.COL_QUESTID + " = " + queststatus.getQuestid(), cv);
    }

    public int updateQuestStatus(String timestamp, int questID, int quest_flag){
        ContentValues cv = new ContentValues();
        cv.put(QuestStatus.COL_TIMESTAMP, timestamp);
        cv.put(QuestStatus.COL_QUESTID, questID);
        cv.put(QuestStatus.COL_QUESTFLAG, quest_flag);
        return super.update(QuestStatus.DATABASE_TABLE, QuestStatus.COL_QUESTID + " = " +questID, cv);
    }

    public ArrayList<QuestStatus> readQuestStatus(){
        String[] columns = new String[]{QuestStatus.COL_TIMESTAMP, QuestStatus.COL_QUESTID, QuestStatus.COL_QUESTFLAG};
        Cursor cursor = super.get(QuestStatus.DATABASE_TABLE, columns , QuestStatus.COL_QUESTFLAG + " = * ");

        ArrayList<QuestStatus> arrstatus = null;
        if(cursor != null && cursor.moveToFirst()) {
            arrstatus = new ArrayList<QuestStatus>();
            while (cursor.isAfterLast()==false) {
                //Calllog is a class with list of fileds
                QuestStatus status = new QuestStatus();
                status.setTimestamp(cursor.getString(cursor.getColumnIndex(QuestStatus.COL_TIMESTAMP)));
                status.setQuestid(cursor.getInt(cursor.getColumnIndex(QuestStatus.COL_QUESTID)));
                status.setQuestflag(cursor.getInt(cursor.getColumnIndex(QuestStatus.COL_QUESTFLAG)));
                arrstatus.add(status);
                cursor.moveToNext();
            }
        }else{
            Log.d("Error", "cursor are null");
        }
        cursor.close();
        return arrstatus;
    }

    public ArrayList<QuestStatus> readQuestStatus(int status){
        String[] columns = new String[]{QuestStatus.COL_TIMESTAMP, QuestStatus.COL_QUESTID, QuestStatus.COL_QUESTFLAG};
        Cursor cursor = super.get(QuestStatus.DATABASE_TABLE, columns , QuestStatus.COL_QUESTFLAG + " = " + status);

        ArrayList<QuestStatus> arrstatus = null;
        if(cursor != null && cursor.moveToFirst()) {
            arrstatus = new ArrayList<QuestStatus>();
            while (cursor.isAfterLast()==false) {
                //Calllog is a class with list of fileds
                QuestStatus queststatus = new QuestStatus();
                queststatus.setTimestamp(cursor.getString(cursor.getColumnIndex(QuestStatus.COL_TIMESTAMP)));
                queststatus.setQuestid(cursor.getInt(cursor.getColumnIndex(QuestStatus.COL_QUESTID)));
                queststatus.setQuestflag(cursor.getInt(cursor.getColumnIndex(QuestStatus.COL_QUESTFLAG)));
                arrstatus.add(queststatus);
                cursor.moveToNext();
            }
        }else{
            Log.d("Error", "cursor are null");
        }
        cursor.close();
        return arrstatus;
    }

    public int delete(int quest_id){
        return super.delete(QuestStatus.DATABASE_TABLE, QuestStatus.COL_QUESTID+"="+ quest_id, null);
    }
}
