package com.ayotong.miranda.model;

/**
 * Created by Alpha on 13/07/2017.
 *
 * Gender:
 * 0. Male
 * 1. Female
 */

public class UserInfo {

    public static final String DATABASE_TABLE = "UserInfo";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE =
            "create table if not exists UserInfo (userinfo_id integer primary key, username text, age integer, gender integer, weight integer, height integer, is_pregnant integer,start_nap text, start_sleep text);";
    public static final String COL_ID = "userinfo_id";
    public static final String COL_UNAME = "username";
    public static final String COL_AGE = "age";
    public static final String COL_GENDER = "gender";
    public static final String COL_WEIGHT = "weight";
    public static final String COL_HEIGHT = "height";
    public static final String COL_ISPREGNANT = "is_pregnant";
    public static final String COL_STARTNAP = "start_nap";
    public static final String COL_STARTSLEEP = "start_sleep";
    public static final int USER_ID = -1;

    private int id, age, gender, weight, height;
    private String username, startnap, startsleep;
    private boolean ispregnant;

    public UserInfo(){

    }

    public UserInfo(int id, String username, int age, int gender, int weight, int height, boolean ispregnant, String startnap, String startsleep){
        this.id = id;
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.ispregnant = ispregnant;
        this.startnap = startnap;
        this.startsleep = startsleep;
    }

    public String genderToText(int gender){
        if(gender==0)
            return "male";
        return "female";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean ispregnant() {
        return ispregnant;
    }

    public void setIspregnant(boolean ispregnant) {
        this.ispregnant = ispregnant;
    }

    public String getStartnap() {
        return startnap;
    }

    public void setStartnap(String startnap) {
        this.startnap = startnap;
    }

    public String getStartsleep() {
        return startsleep;
    }

    public void setStartsleep(String startsleep) {
        this.startsleep = startsleep;
    }
}