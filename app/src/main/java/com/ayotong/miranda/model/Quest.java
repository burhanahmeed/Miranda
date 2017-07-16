package com.ayotong.miranda.model;

/**
 * Created by burhan on 10/07/17.
 */


public class Quest {

    public static final String DATABASE_TABLE = "Quest";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table if not exists Quest (quest_id integer primary key autoincrement , category text, time text, exp integer, quest_desc text);";
    public static final String COL_ID = "quest_id";
    public static final String COL_CATEGORY = "category";
    public static final String COL_TIME = "time";
    public static final String COL_EXP = "exp";
    public static final String COL_QUESTDESC = "quest_desc";
    public static final int QUEST_ID = -1;

    private String time;
    private int exp;
    private String quest;
    private int id;
    private String category;

    public Quest(){

    }

    public Quest(int id, String category, String jam, int exp, String quest) {
        this.setTime(jam);
        this.setExp(exp);
        this.setQuest(quest);
        this.setCategory(category);
        this.setId(id);
    }

    public String getTime(){
        return time;
    }
    public int getExp(){
        return exp;
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}