package com.ayotong.miranda.DBCtrl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ayotong.miranda.model.Quest;

import java.util.ArrayList;

/**
 * Created by Alpha on 11/07/2017.
 */

public class QuestDB  extends DatabaseDAO implements QuestDAO{

    private Context context;

    public QuestDB(Context context){
        super(context, DatabaseDAO.dName, Quest.TABLE_CREATE, Quest.DATABASE_TABLE, Quest.DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public Quest insert(int questID, Quest quest) {
        ContentValues cv = new ContentValues();
        cv.put(Quest.COL_ID, questID);
        cv.put(Quest.COL_CATEGORY, quest.getCategory());
        cv.put(Quest.COL_QUESTDESC, quest.getQuest());
        cv.put(Quest.COL_TIME, quest.getTime());
        cv.put(Quest.COL_EXP, quest.getExp());
        return new Quest((int)super.insert(Quest.DATABASE_TABLE, cv) , quest.getCategory() ,quest.getTime() , quest.getExp(), quest.getQuest());
    }

    public int updateTime(int questID, Quest quest){
        ContentValues cv = new ContentValues();
        cv.put(Quest.COL_ID, quest.getId());
        cv.put(Quest.COL_TIME, quest.getTime());
        return super.update(Quest.DATABASE_TABLE, Quest.COL_ID + " = " + quest.getId(), cv);
    }

    public int updateQuestDesc(int questID, Quest quest){
        ContentValues cv = new ContentValues();
        cv.put(Quest.COL_ID, quest.getId());
        cv.put(Quest.COL_QUESTDESC, quest.getQuest());
        return super.update(Quest.DATABASE_TABLE, Quest.COL_ID + " = " + quest.getId(), cv);
    }

    public ArrayList<Quest> readQuest(){
        String[] columns = new String[]{DatabaseDAO.KEY_ID ,Quest.COL_ID, Quest.COL_CATEGORY, Quest.COL_TIME, Quest.COL_EXP, Quest.COL_QUESTDESC};
        Cursor cursor = super.get(Quest.DATABASE_TABLE, columns , Quest.COL_ID + " = * ");

        ArrayList<Quest> arrquest = null;
        if(cursor != null && cursor.moveToFirst()) {
            arrquest = new ArrayList<Quest>();
            while (cursor.isAfterLast()==false) {
                //Calllog is a class with list of fileds
                Quest quest = new Quest();
                quest.setId(cursor.getInt(cursor.getColumnIndex(Quest.COL_ID)));
                quest.setCategory(cursor.getString(cursor.getColumnIndex(Quest.COL_CATEGORY)));
                quest.setTime(cursor.getString(cursor.getColumnIndex(Quest.COL_TIME)));
                quest.setExp(cursor.getInt(cursor.getColumnIndex(Quest.COL_EXP)));
                quest.setQuest(cursor.getString(cursor.getColumnIndex(Quest.COL_QUESTDESC)));
                arrquest.add(quest);
                cursor.moveToNext();
            }
        }else{
            Log.d("Error", "cursor are null");
        }
        cursor.close();
        return arrquest;
    }

    public int delete(int quest_id) {

        return super.delete(Quest.DATABASE_TABLE, Quest.COL_ID+"="+ quest_id, null);
    }
}
