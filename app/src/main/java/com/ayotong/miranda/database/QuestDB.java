package com.ayotong.miranda.database;

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

    public Quest insert(Quest quest) {
        ContentValues cv = new ContentValues();
        cv.put(Quest.COL_ID, quest.getId());
        cv.put(Quest.COL_QUESTDESC, quest.getQuestDescription());
        cv.put(Quest.COL_EXP, quest.getExp());
        return new Quest((int)super.insert(Quest.DATABASE_TABLE, cv), quest.getExp(), quest.getQuestDescription());
    }

    public Quest insert(int questID, Quest quest) {
        ContentValues cv = new ContentValues();
        cv.put(Quest.COL_ID, questID);
        cv.put(Quest.COL_QUESTDESC, quest.getQuestDescription());
        cv.put(Quest.COL_EXP, quest.getExp());
        return new Quest((int)super.insert(Quest.DATABASE_TABLE, cv), quest.getExp(), quest.getQuestDescription());
    }

    public ArrayList<Quest> readAllQuest(){
        String[] columns = new String[]{DatabaseDAO.KEY_ID ,Quest.COL_ID, Quest.COL_EXP, Quest.COL_QUESTDESC};
        Cursor cursor = super.get(Quest.DATABASE_TABLE, columns , Quest.COL_ID + " = * ");

        ArrayList<Quest> arrquest = new ArrayList<Quest>();
        if(cursor != null && cursor.moveToFirst()) {
            while (cursor.isAfterLast()==false) {
                //Calllog is a class with list of fileds
                Quest quest = new Quest();
                quest.setId(cursor.getInt(cursor.getColumnIndex(Quest.COL_ID)));
                quest.setExp(cursor.getInt(cursor.getColumnIndex(Quest.COL_EXP)));
                quest.setQuestDescription(cursor.getString(cursor.getColumnIndex(Quest.COL_QUESTDESC)));
                arrquest.add(quest);
                cursor.moveToNext();
            }
        }else{
            Log.d("Error", "cursor are null");
        }
        cursor.close();
        return arrquest;
    }

    public Quest readQuestByID(int id){
        String[] columns = new String[]{Quest.COL_ID, Quest.COL_EXP, Quest.COL_QUESTDESC};
        Cursor cursor = super.get(Quest.DATABASE_TABLE, columns , Quest.COL_ID + " = " + id);

        Quest quest = new Quest();
        if(cursor != null && cursor.moveToFirst()) {

            //Calllog is a class with list of fileds
            quest.setId(cursor.getInt(cursor.getColumnIndex(Quest.COL_ID)));
            quest.setExp(cursor.getInt(cursor.getColumnIndex(Quest.COL_EXP)));
            quest.setQuestDescription(cursor.getString(cursor.getColumnIndex(Quest.COL_QUESTDESC)));
        }else{
            Log.d("Error", "cursor are null");
        }
        cursor.close();
        return quest;
    }


}