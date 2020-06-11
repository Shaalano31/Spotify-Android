package com.example.spotify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notifications {

    @SerializedName("pictureUrl")
    @Expose
    private String image;
    @SerializedName("userType")
    @Expose
    private String userType;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("body")
    @Expose
    private String body;

    public String getImage() {
        return image;
    }

    public String getUserType() {
        return userType;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
