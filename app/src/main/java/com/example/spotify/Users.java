package com.example.spotify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {

    //"_id": "5e8a799494a3760aef918874",
    //            "userName": "Khaled",
    //            "imagePath": "defaultuser.png"

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
