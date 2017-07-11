package com.ayotong.miranda.model;

/**
 * Created by burhan on 10/07/17.
 */

import android.support.annotation.NonNull;


public class Quest {

    private String jam;
    private String xp;
    private String quest;
    private int id;
    private String category;

    public Quest(int id, String category, String jam, String xp, String quest) {
        this.jam= jam;
        this.xp = xp;
        this.quest = quest;
        this.category = category;
        this.id = id;
    }

    public String getJam(){
        return jam;
    }

    public String getXp(){
        return xp;
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
}