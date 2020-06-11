package com.example.spotify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Songs {

    private String url;


    private String imageurl;


    private String artist;


    private String song;

    public String getUrl() {
        return url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }
}
