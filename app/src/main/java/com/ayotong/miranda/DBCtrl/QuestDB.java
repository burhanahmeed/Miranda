package com.ayotong.miranda.DBCtrl;

import android.content.Context;

import com.ayotong.miranda.model.Quest;

/**
 * Created by Alpha on 11/07/2017.
 */

public class QuestDB  extends DatabaseDAO implements QuestDAO{

    private Context context;

    public QuestDB(Context context){
        super(context, DatabaseDAO.dName, Quest.TABLE_CREATE, Quest.DATABASE_TABLE, Quest.DATABASE_VERSION);
        this.context = context;
    }
}
