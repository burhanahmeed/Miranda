package com.ayotong.miranda.model;

/**
 * Created by burhan on 10/07/17.
 */


public class Quest {

    public static final String DATABASE_TABLE = "Quest";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table if not exists Quest (quest_id integer primary key, exp integer, quest_desc text);";
    public static final String COL_ID = "quest_id";
    public static final String COL_EXP = "exp";
    public static final String COL_QUESTDESC = "quest_desc";
    public static final int QUEST_ID = -1;

    private int id, exp;
    private String quest;

    public Quest(){

    }

    public Quest(int id, int exp, String quest_description) {
        this.setExp(exp);
        this.setQuestDescription(quest_description);
        this.setId(id);
    }

    public int getExp(){
        return exp;
    }
    public String getQuestDescription(){
        return quest;
    }
    public int getId(){
        return id;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    public void setQuestDescription(String quest) {
        this.quest = quest;
    }
    public void setId(int id) {
        this.id = id;
    }

//    public String getTime() {
//        return time;
//    }
}