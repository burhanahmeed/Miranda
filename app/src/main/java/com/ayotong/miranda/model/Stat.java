package com.ayotong.miranda.model;

/**
 * Created by burhan on 17/07/17.
 */

public class Stat {

    private String bulan;
    private int xp;
    private int xp_overall;

    //constructors tong
    public Stat(String bulan, int xp, int xp_overall){
        this.bulan = bulan;
        this.xp = xp;
        this.xp_overall = xp_overall;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getXp_overall() {
        return xp_overall;
    }

    public void setXp_overall(int xp_overall) {
        this.xp_overall = xp_overall;
    }
}
