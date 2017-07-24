package com.ayotong.miranda.controller;

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
    TimeProcessing timeproc;

    String start_nap, start_sleep, time;
    int gender, age, weight, height;
    boolean ispreg;


    public QuestGenerator(){

    }

    public void generateQuest(UserInfo user){
        gender = user.getGender();
        age = user.getAge();
        weight = user.getWeight();
        height = user.getHeight();
        start_nap = user.getStartnap();
        start_sleep = user.getStartsleep();

        time = timeproc.addTime(start_sleep, 9, 0); //wake-up time (anggep ae tidur 9 jam)

        //generate quest
        generateDrinkQuest();
        generateEatQuest();
        generateNapQuest();
        generateSleepQuest();

        //masukin ke db
        for(int i=0; i<arrquest.size(); i++){
            questdb.insert(arrquest.get(i));
        }
    }

    //method bikin quest minum (itungan e gaero)
    private void generateDrinkQuest(){
        for(int i=0; i<10; i++){
            quest = new Quest(10+i, "minum", timeproc.addTime(time, i, 0), 50, "Minum 200 mL Air");
            arrquest.add(quest);
        }
    }

    //method bikin quest makan bang (itungan e gaero)
    private void generateEatQuest(){
        quest = new Quest(20, "makanbang", "09:00:00", 100, "Sarapan dulu bang!");
        arrquest.add(quest);
        quest = new Quest(21, "makanbang", "12:00:00", 100, "Makan siang dulu bang!");
        arrquest.add(quest);
        quest = new Quest(22, "makanbang", "19:00:00", 100, "Dinner dulu bang!");
        arrquest.add(quest);
    }

    //method bikin quest boci + bangun e (durasi 1,5 jam)
    private void generateNapQuest(){
        //0 == ga boci
        if(!start_nap.contentEquals("0")){
            quest = new Quest(31, "Tidur Siang", start_nap, 150, "Boci dulu Tong!");
            arrquest.add(quest);
            quest = new Quest(32, "Tidur Siang", timeproc.addTime(start_nap, 1, 30), 150, "Udahan Tong boci nya!");
        }
    }

    //method bikin quest bobo + bangun e
    private void generateSleepQuest(){
        quest = new Quest(33, "Tidur", start_sleep, 200, "Ndang tidor tong!");
        arrquest.add(quest);
        quest = new Quest(32, "Tidur", timeproc.addTime(start_sleep, 9, 00), 200, "Bangun Tong!");
        arrquest.add(quest);
    }
}