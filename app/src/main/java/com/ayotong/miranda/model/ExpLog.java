package com.ayotong.miranda.model;

/**
 * Created by Alpha on 16/07/2017.
 */

public class ExpLog {

    public static final String DATABASE_TABLE = "ExpLog";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table if not exists ExpLog (timestamp text primary key, quest_id int, exp_gain integer);";
    public static final String COL_TIMESTAMP = "timestamp";
    public static final String COL_QUESTID= "quest_id";
    public static final String COL_EXPGAIN = "exp_gain";
    public static final int QUEST_ID = -1;

    private String timestamp;
    private int quest_id;
    private int exp_gain;

    public ExpLog(){

    }

    public ExpLog(String timestamp, int quest_id, int exp_gain){
        this.setTimestamp(timestamp);
        this.setQuest_id(quest_id);
        this.setExp_gain(exp_gain);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getQuest_id() {
        return quest_id;
    }

    public void setQuest_id(int quest_id) {
        this.quest_id = quest_id;
    }

    public int getExp_gain() {
        return exp_gain;
    }

    public void setExp_gain(int exp_gain) {
        this.exp_gain = exp_gain;
    }
}
