package com.ayotong.miranda.core.databasecontroller;

import android.content.Context;

import com.ayotong.miranda.database.QuestDB;
import com.ayotong.miranda.model.Quest;
import com.ayotong.miranda.model.UserInfo;

import java.util.ArrayList;

/**
 * Created by Alpha on 21/07/2017.
 */

public class QuestGenerator {

    private QuestDB questdb;
    private Quest quest;
    private ArrayList<Quest> arrquest;
    private Context context;

    public QuestGenerator(Context context){
        this.context = context;
        questdb = new QuestDB(context);
    }

    public void generateQuest(UserInfo user){
        arrquest = new ArrayList<Quest>();

        //generate quest
        generateDrinkQuest();
        generateNapQuest();
        generateSleepQuest();

        //masukin ke db
        insertToDB();
        closeDB();
    }

    public ArrayList<Quest> getAllQuest(){
        return questdb.readAllQuest();
    }

    public Quest findQuestByID(int id){
        return questdb.readQuestByID(id);
    }

    //method bikin quest minum (itungan e gaero)
    private void generateDrinkQuest(){
        quest = new Quest(1, 50, "Minum 250 mL Air");
        arrquest.add(quest);
    }

    //method bikin quest boci + bangun e (durasi 1,5 jam)
    private void generateNapQuest(){
        quest = new Quest(2, 150, "Boci dulu Tong!");
        arrquest.add(quest);
        quest = new Quest(3, 150, "Udahan Tong boci nya!");
        arrquest.add(quest);
    }

    //method bikin quest bobo + bangun e
    private void generateSleepQuest(){
        quest = new Quest(4, 200, "Ndang tidor tong!");
        arrquest.add(quest);
        quest = new Quest(5, 200, "Bangun Tong!");
        arrquest.add(quest);
    }

    private void insertToDB(){
        for(int i=0; i<arrquest.size(); i++){
            questdb.insert(arrquest.get(i));
        }
    }

    public void closeDB(){
        questdb.close();
    }
}