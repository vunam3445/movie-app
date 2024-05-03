package com.example.moviesapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Favourites")
public class movieFavourite {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String slugname;
    private String scrip;
    private String img;

    public movieFavourite(String name, String slugname, String scrip, String img) {
        this.name = name;
        this.slugname = slugname;
        this.scrip = scrip;
        this.img = img;
    }

    public String getSlugname() {
        return slugname;
    }

    public void setSlugname(String slugname) {
        this.slugname = slugname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScrip() {
        return scrip;
    }

    public void setScrip(String scrip) {
        this.scrip = scrip;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
