package com.ayotong.miranda.model;

/**
 * Created by Alpha on 13/07/2017.
 */

public class UserInfo {

    public static final String DATABASE_TABLE = "UserInfo";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table if not exists UserInfo (id integer primary key, username text, age integer, gender text, weight integer, height integer, is_pregnant integer, is_nap integer,start_nap text, start_sleep text);";
    public static final String COL_ID = "quest_id";
    public static final String COL_CATEGORY = "category";
    public static final String COL_TIME = "time";
    public static final String COL_EXP = "exp";
    public static final String COL_QUESTDESC = "quest_desc";
    public static final int QUEST_ID = -1;
}
