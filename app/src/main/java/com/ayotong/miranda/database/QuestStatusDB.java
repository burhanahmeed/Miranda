package com.ayotong.miranda.database;

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
        cv.put(QuestStatus.COL_QUEST_TIME, quest_status.getQuesttime());
        cv.put(QuestStatus.COL_QUEST_EXP, quest_status.getQuestexp());
        cv.put(QuestStatus.COL_QUESTFLAG, quest_status.getQuestflag());

        super.insert(QuestStatus.DATABASE_TABLE, cv);
        return new QuestStatus(quest_status.getTimestamp(), quest_status.getQuestid(), quest_status.getQuesttime(),
                quest_status.getQuestexp(), quest_status.getQuestflag());
    }

    public QuestStatus insert(String timestamp, int questID, String quest_time, int quest_exp, int quest_flag){
        ContentValues cv = new ContentValues();
        cv.put(QuestStatus.COL_TIMESTAMP, timestamp);
        cv.put(QuestStatus.COL_QUESTID, questID);
        cv.put(QuestStatus.COL_QUEST_TIME, quest_time);
        cv.put(QuestStatus.COL_QUEST_EXP, quest_exp);
        cv.put(QuestStatus.COL_QUESTFLAG, quest_flag);
        super.insert(QuestStatus.DATABASE_TABLE, cv);
        return new QuestStatus(timestamp, questID, quest_time, quest_exp, quest_flag);
    }

    public int updateQuestStatus(QuestStatus queststatus){
        ContentValues cv = new ContentValues();
        cv.put(QuestStatus.COL_TIMESTAMP, queststatus.getTimestamp());
        cv.put(QuestStatus.COL_QUESTID, queststatus.getQuestid());
        cv.put(QuestStatus.COL_QUEST_TIME, queststatus.getQuesttime());
        cv.put(QuestStatus.COL_QUEST_EXP, queststatus.getQuestexp());
        cv.put(QuestStatus.COL_QUESTFLAG, queststatus.getQuestflag());
        return super.update(QuestStatus.DATABASE_TABLE, QuestStatus.COL_TIMESTAMP + " = " + queststatus.getTimestamp(), cv);
    }

    public int updateQuestStatus(String timestamp, int quest_id, String quest_time, int quest_exp, int quest_flag){
        ContentValues cv = new ContentValues();
        cv.put(QuestStatus.COL_TIMESTAMP, timestamp);
        cv.put(QuestStatus.COL_QUESTID, quest_id);
        cv.put(QuestStatus.COL_QUEST_TIME, quest_time);
        cv.put(QuestStatus.COL_QUEST_EXP, quest_exp);
        cv.put(QuestStatus.COL_QUESTFLAG, quest_flag);
        return super.update(QuestStatus.DATABASE_TABLE, QuestStatus.COL_TIMESTAMP + " = " +timestamp, cv);
    }

    public ArrayList<QuestStatus> readAllStatus(){
        String[] columns = new String[]{QuestStatus.COL_TIMESTAMP, QuestStatus.COL_QUESTID, QuestStatus.COL_QUEST_TIME,
                QuestStatus.COL_QUEST_EXP, QuestStatus.COL_QUESTFLAG};
        Cursor cursor = super.get(QuestStatus.DATABASE_TABLE, columns , QuestStatus.COL_QUESTFLAG + " = * ");

        ArrayList<QuestStatus> arrstatus = new ArrayList<QuestStatus>();
        if(cursor != null && cursor.moveToFirst()) {
            while (cursor.isAfterLast()==false) {
                //Calllog is a class with list of fileds
                QuestStatus status = new QuestStatus();
                status.setTimestamp(cursor.getString(cursor.getColumnIndex(QuestStatus.COL_TIMESTAMP)));
                status.setQuestid(cursor.getInt(cursor.getColumnIndex(QuestStatus.COL_QUESTID)));
                status.setQuesttime(cursor.getString(cursor.getColumnIndex(QuestStatus.COL_QUEST_TIME)));
                status.setQuestexp(cursor.getInt(cursor.getColumnIndex(QuestStatus.COL_QUEST_EXP)));
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

    public ArrayList<QuestStatus> readAllQuestByStatus(int status){
        String[] columns = new String[]{QuestStatus.COL_TIMESTAMP, QuestStatus.COL_QUESTID, QuestStatus.COL_QUESTFLAG};
        Cursor cursor = super.get(QuestStatus.DATABASE_TABLE, columns , QuestStatus.COL_QUESTFLAG + " = " + status);

        ArrayList<QuestStatus> arrstatus = new ArrayList<QuestStatus>();
        if(cursor != null && cursor.moveToFirst()) {
            //Calllog is a class with list of fileds
            QuestStatus queststatus = new QuestStatus();
            queststatus.setTimestamp(cursor.getString(cursor.getColumnIndex(QuestStatus.COL_TIMESTAMP)));
            queststatus.setQuestid(cursor.getInt(cursor.getColumnIndex(QuestStatus.COL_QUESTID)));
            queststatus.setQuesttime(cursor.getString(cursor.getColumnIndex(QuestStatus.COL_QUEST_TIME)));
            queststatus.setQuestexp(cursor.getInt(cursor.getColumnIndex(QuestStatus.COL_QUEST_EXP)));
            queststatus.setQuestflag(cursor.getInt(cursor.getColumnIndex(QuestStatus.COL_QUESTFLAG)));
            arrstatus.add(queststatus);
        }else{
            Log.d("Error", "cursor are null");
        }
        cursor.close();
        return arrstatus;
    }

    public int deleteQuest(int quest_id){
        return super.delete(QuestStatus.DATABASE_TABLE, QuestStatus.COL_QUESTID+"="+ quest_id, null);
    }

    public void refreshTable(){
        super.delete(QuestStatus.DATABASE_TABLE);
        new QuestStatusDB(context);
    }
}
