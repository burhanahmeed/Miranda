package com.ayotong.miranda.model;

/**
 * Created by burhan on 10/07/17.
 */

import android.support.annotation.NonNull;


public class LocationInformation {

    private String jam;
    private String xp;
    private String quest;

    public LocationInformation(String jam, String xp, String quest) {
        this.jam= jam;
        this.xp = xp;
        this.quest = quest;
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
}