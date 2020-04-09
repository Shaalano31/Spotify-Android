package com.example.spotify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("isPremium")
    @Expose
    private boolean isPremium;
    @SerializedName("isActive")
    @Expose
    private boolean isActive;


    @SerializedName("day")
    @Expose
    private String day;

    @SerializedName("month")
    @Expose
    private String month;

    @SerializedName("year")
    @Expose
    private String year;

    /*@SerializedName("birthDate")
    @Expose
    private String birthDate;*/



    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("body")
    @Expose
    private String text;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //"_id": "5e8a799494a3760aef918874",
    //            "userName": "Khaled",
    //            "imagePath": "defaultuser.png"

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;


    public Users(String username, String email, String password, boolean b, boolean b1, String day, String month, String year, String gender) {
    }
    public Users() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public boolean isActive() {
        return isActive;
    }

    /*public String getBirthDate() {
        return birthDate;
    }*/

    public String getGender() {
        return gender;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
