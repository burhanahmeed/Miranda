package com.ayotong.miranda.DBCtrl;

import android.content.ContentValues;
import android.content.Context;

import com.ayotong.miranda.model.Quest;

import java.util.List;

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
        cv.put(Quest.COL_TIME, quest.getJam());
        cv.put(Quest.COL_EXP, quest.getXp());
        return new Quest((int)super.insert(Quest.DATABASE_TABLE, cv) , quest.getCategory() ,quest.getJam() , quest.getXp(), quest.getQuest());
    }

    public int updateTime(int questID, Quest quest){
        ContentValues cv = new ContentValues();
        cv.put(Quest.COL_ID, quest.getId());
        cv.put(Quest.COL_TIME, quest.getJam());
        return super.update(Quest.DATABASE_TABLE, Quest.COL_ID + " = " + quest.getId(), cv);
    }

    public int updateQuestDesc(int questID, Quest quest){
        ContentValues cv = new ContentValues();
        cv.put(Quest.COL_ID, quest.getId());
        cv.put(Quest.COL_QUESTDESC, quest.getQuest());
        return super.update(Quest.DATABASE_TABLE, Quest.COL_ID + " = " + quest.getId(), cv);
    }

}
