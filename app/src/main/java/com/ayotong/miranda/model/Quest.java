package com.ayotong.miranda.model;

/**
 * Created by burhan on 10/07/17.
 */

import android.support.annotation.NonNull;


public class Quest {

    public static final String DATABASE_TABLE = "Quest";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table if not exists Quest (id integer primary key autoincrement , category text, time text, Exp integer, questdesc text);";
    public static final String COL_ID = "quest_id";
    public static final String COL_CATEGORY = "category";
    public static final String COL_TIME = "time";
    public static final String COL_EXP = "exp";
    public static final String COL_QUESTDESC = "quest_desc";
    public static final int QUEST_ID = -1;

    private String jam;
    private String xp;
    private String quest;
    private int id;
    private String category;



    public Quest(int id, String category, String jam, String xp, String quest) {
        this.jam= jam;
        this.xp = xp;
        this.quest = quest;
        this.category = category;
        this.id = id;
    }

    public String getJam(){
        return jam;
    }

    public String getXp(){
        return xp;
    }
    public String getQuest(){
        return quest;
    }
    public String getCategory(){
        return category;
    }
    public int getId(){
        return id;
    }
}