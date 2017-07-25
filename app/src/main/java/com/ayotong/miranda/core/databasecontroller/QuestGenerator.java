package com.ayotong.miranda.controller;

import android.content.Context;

import com.ayotong.miranda.DBCtrl.QuestDB;
import com.ayotong.miranda.model.Quest;
import com.ayotong.miranda.model.UserInfo;

import java.util.ArrayList;

/**
 * Created by Alpha on 21/07/2017.
 */

public class QuestGenerator {

    QuestDB questdb;
    Quest quest;
    ArrayList<Quest> arrquest;
    Context context;

    String start_nap, start_sleep, time;
    int gender, age, weight, height;
    boolean ispreg;

    public QuestGenerator(Context context){
        this.context = context;
        questdb = new QuestDB(context);
    }

    public QuestGenerator(Context context, UserInfo user){
        this.context = context;
        questdb = new QuestDB(context);

        this.gender = user.getGender();
        this.age = user.getAge();
        this.weight = user.getWeight();
        this.height = user.getHeight();
        this.start_nap = user.getStartnap();
        this.start_sleep = user.getStartsleep();
    }

    public void generateQuest(UserInfo user){

        //generate quest
        generateDrinkQuest();
        generateNapQuest();
        generateSleepQuest();

        //masukin ke db
        insertToDB();
        questdb.close();
    }

    //method bikin quest minum (itungan e gaero)
    private void generateDrinkQuest(){
        for(int i=0; i<10; i++){
            quest = new Quest(10+i, "minum", 50, "Minum 250 mL Air");
            arrquest.add(quest);
        }
    }

    //method bikin quest boci + bangun e (durasi 1,5 jam)
    private void generateNapQuest(){
        arrquest = new ArrayList<Quest>();
        //none == ga boci
        if(!start_nap.contentEquals("none")){
            quest = new Quest(31, "Tidur Siang", 150, "Boci dulu Tong!");
            arrquest.add(quest);
            quest = new Quest(32, "Tidur Siang", 150, "Udahan Tong boci nya!");
            arrquest.add(quest);
            insertToDB();
        }
    }

    //method bikin quest bobo + bangun e
    private void generateSleepQuest(){
        arrquest = new ArrayList<Quest>();
        if(age<65){
            quest = new Quest(33, "Tidur", 200, "Ndang tidor tong!");
            arrquest.add(quest);
            quest = new Quest(34, "Tidur", 200, "Bangun Tong!");
            arrquest.add(quest);
            insertToDB();
        }else if(age>=65){
            quest = new Quest(33, "Tidur", 200, "Ndang tidor tong!");
            arrquest.add(quest);
            quest = new Quest(34, "Tidur", 200, "Bangun Tong!");
            arrquest.add(quest);
            insertToDB();
        }
    }

    private void insertToDB(){
        for(int i=0; i<arrquest.size(); i++){
            questdb.insert(arrquest.get(i));
        }
    }
}