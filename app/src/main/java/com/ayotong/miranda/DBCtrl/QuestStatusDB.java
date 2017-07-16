package com.ayotong.miranda.DBCtrl;

import android.content.Context;

import com.ayotong.miranda.model.QuestStatus;

/**
 * Created by Alpha on 16/07/2017.
 */

public class QuestStatusDB extends DatabaseDAO implements QuestStatusDAO {
    private Context context;

    public QuestStatusDB(Context context){
        super(context, DatabaseDAO.dName, QuestStatus.TABLE_CREATE, QuestStatus.DATABASE_TABLE, QuestStatus.DATABASE_VERSION);
        this.context = context;
    }
}
