package com.example.spotify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artists {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("artistName")
    @Expose
    private String name;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Artists{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
