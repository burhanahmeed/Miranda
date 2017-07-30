package com.ayotong.miranda.core.databasecontroller;

import android.content.Context;

import com.ayotong.miranda.database.QuestDB;
import com.ayotong.miranda.model.Quest;
import com.ayotong.miranda.model.UserInfo;

import java.util.ArrayList;

/**
 * Created by Alpha on 21/07/2017.
 */

public class QuestCtrl {

    private QuestDB questdb;
    private Quest quest;
    private ArrayList<Quest> arrquest;
    private Context context;

    public QuestCtrl(Context context){
        this.context = context;
        questdb = new QuestDB(context);
    }

    public void addQuest(Quest quest){
        questdb.insert(quest);
    }

    public ArrayList<Quest> getAllQuest(){
        return questdb.readAllQuest();
    }

    public Quest findQuestByID(int id){
        return questdb.readQuestByID(id);
    }

    public void deleteQuest(int id){
        questdb.deleteQuest(questdb.getQuest(id));
    }

    public void closeDB(){
        questdb.close();
    }
}