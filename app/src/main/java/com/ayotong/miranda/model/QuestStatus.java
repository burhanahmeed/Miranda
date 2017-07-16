package com.ayotong.miranda.model;

/**
 * Created by Alpha on 16/07/2017.
 */

public class QuestStatus {

    public static final String DATABASE_TABLE = "QuestStatus";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table if not exists QuestStatus (timestamp text primary key, quest_id int, quest_flag int);";
    public static final String COL_TIMESTAMP = "timestamp";
    public static final String COL_QUESTID= "quest_id";
    public static final String COL_QUESTFLAG = "flag";
    public static final String COL_EXP = "exp";
    public static final int QUEST_ID = -1;
    public QuestStatus(){

    }
}
