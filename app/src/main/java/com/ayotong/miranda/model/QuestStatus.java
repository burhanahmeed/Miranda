package com.ayotong.miranda.model;

/**
 * Created by Alpha on 16/07/2017.
 *
 * Quest Flag:
 * 0. pending
 * 1. Done
 */

public class QuestStatus {

    public static final String DATABASE_TABLE = "QuestStatus";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table if not exists QuestStatus (timestamp text primary key, quest_id integer, time text, exp integer, quest_flag integer);";
    public static final String COL_TIMESTAMP = "timestamp";
    public static final String COL_QUESTID= "quest_id";
    public static final String COL_QUEST_TIME = "time";
    public static final String COL_QUEST_EXP = "exp";
    public static final String COL_QUESTFLAG = "quest_flag";
    public static final int QUEST_ID = -1;

    private String timestamp, questtime;
    private int questid, questexp, questflag;

    public QuestStatus(){

    }

    public QuestStatus(String timestamp, int quest_id, String quest_time, int quest_exp, int quest_flag){
        this.timestamp = timestamp;
        this.questid = quest_id;
        this.questtime = quest_time;
        this.questexp = quest_exp;
        this.questflag = quest_flag;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getQuestid() {
        return questid;
    }

    public void setQuestid(int questid) {
        this.questid = questid;
    }

    public int getQuestflag() {
        return questflag;
    }

    public void setQuestflag(int questflag) {
        this.questflag = questflag;
    }

    public String getQuesttime() {
        return questtime;
    }

    public void setQuesttime(String questtime) {
        this.questtime = questtime;
    }

    public int getQuestexp() {
        return questexp;
    }

    public void setQuestexp(int questexp) {
        this.questexp = questexp;
    }
}