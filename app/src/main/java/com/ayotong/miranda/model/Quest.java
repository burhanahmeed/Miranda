package com.ayotong.miranda.model;

/**
 * Created by burhan on 10/07/17.
 */


public class Quest {

    public static final String DATABASE_TABLE = "Quest";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table if not exists Quest (quest_id integer primary key, exp integer, quest_desc text, jam text);";
    public static final String COL_ID = "quest_id";
    public static final String COL_EXP = "exp";
    public static final String COL_QUESTDESC = "quest_desc";
    public static final String COL_JAM = "jam";
    public static final int QUEST_ID = -1;

    private int id, exp;
    private String quest, jam;

    public Quest(){

    }

    public Quest(int id, int exp, String quest_description, String jam) {
        this.exp = exp;
        this.quest = quest_description;
        this.id = id;
        this.jam = jam;
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
    public String getJam() {return jam;}

    public void setExp(int exp) {
        this.exp = exp;
    }
    public void setQuestDescription(String quest) {
        this.quest = quest;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setJam(String jam){this.jam = jam;}

//    public String getTime() {
//        return time;
//    }
}