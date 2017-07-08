package com.ayotong.miranda.model;

/**
 * Created by burhan on 08/07/17.
 */

public class Home_carousel {
    private String name;
    private String url;
    private String description;


    public Home_carousel() {
    }

    public Home_carousel(String name, String url) {
        this.name = name;
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
