package com.ayotong.miranda.model;

import java.util.ArrayList;
/**
 * Created by burhan on 08/07/17.
 */

public class HomeDataModel {
    private String headerTitle;
    private ArrayList<Home_carousel> allItemsInSection;


    public HomeDataModel() {

    }
    public HomeDataModel(String headerTitle, ArrayList<Home_carousel> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }



    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Home_carousel> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<Home_carousel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }

}
