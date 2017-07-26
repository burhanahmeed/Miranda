package com.ayotong.miranda.core.databasecontroller;

import android.content.Context;

import com.ayotong.miranda.core.TimeProcessing;
import com.ayotong.miranda.database.QuestStatusDB;
import com.ayotong.miranda.model.Quest;
import com.ayotong.miranda.model.QuestStatus;
import com.ayotong.miranda.model.UserInfo;

import java.util.ArrayList;

/**
 * Created by Alpha on 25/07/2017.
 */

public class QuestStatusGenerator {
    private Context context;
    private TimeProcessing timeproc;

    private QuestStatusDB qsdb;
    private QuestStatus qs;
    private ArrayList<QuestStatus> arrqs;

    private int age, gender, weight, height;
    private String start_nap, start_sleep, wakenap, wakesleep;
    private boolean ispregnant, isnap;

    public QuestStatusGenerator(Context context){
        this.context = context;
        qsdb = new QuestStatusDB(context);
        timeproc = new TimeProcessing();
    }

    public QuestStatusGenerator(Context context, UserInfo user){
        this.context = context;
        qsdb = new QuestStatusDB(context);
        timeproc = new TimeProcessing();

        this.gender = user.getGender();
        this.age = user.getAge();
        this.weight = user.getWeight();
        this.height = user.getHeight();
        this.start_nap = user.getStartnap();
        this.start_sleep = user.getStartsleep();
    }

    public ArrayList<QuestStatus> generateStatus(){
        arrqs = new ArrayList<QuestStatus>();

        createNapQuest(start_nap);
        createSleepQuest(age, start_sleep);
        createDrinkQuest(age, gender, ispregnant, wakesleep);

        return arrqs;
    }

    public void createDrinkQuest(int age, int gender, boolean ispregnant, String time){
        int repeat;
        if(age > 18 && gender == 1){
            if(ispregnant){
                repeat = 13;
            }else{
                repeat = 11;
            }
        }else if(age > 18 && gender == 0){
            repeat = 15;
        }else{
            if(age>=14 && age<=18){
                repeat = 9;
            }else if(age>=9 && age<14){
                repeat = 8;
            }else{
                repeat = 5;
            }
        }
    }

    public void createNapQuest(String start_nap){
        arrqs = new ArrayList<QuestStatus>();
        if(!start_nap.equalsIgnoreCase("none")){
            qs = new QuestStatus(timeproc.getTimestamp(), 2, start_nap, 150, 1);
            arrqs.add(qs);

            wakenap = timeproc.addTime(start_nap, 8,0);
            qs = new QuestStatus(timeproc.getTimestamp(), 3, wakenap, 150, 1);
            arrqs.add(qs);
            insertToDB();
        }
    }

    public void createSleepQuest(int age, String start_sleep){
        arrqs = new ArrayList<QuestStatus>();
        if(age>=65){
            qs = new QuestStatus(timeproc.getTimestamp(), 4, start_sleep, 150, 1);
            arrqs.add(qs);

            wakesleep = timeproc.addTime(start_sleep, 8,0);
            qs = new QuestStatus(timeproc.getTimestamp(), 5, wakesleep, 150, 1);
            arrqs.add(qs);
        }else{
            qs = new QuestStatus(timeproc.getTimestamp(), 4, start_sleep, 150, 1);
            arrqs.add(qs);

            wakesleep = timeproc.addTime(start_sleep, 9,0);
            qs = new QuestStatus(timeproc.getTimestamp(), 5, wakesleep, 150, 1);
            arrqs.add(qs);
        }
        insertToDB();
    }

    private void insertToDB(){
        for(int i=0; i<arrqs.size(); i++){
            qsdb.insert(arrqs.get(i));
        }
    }

    public void closeDB(){
        qsdb.close();
    }
}