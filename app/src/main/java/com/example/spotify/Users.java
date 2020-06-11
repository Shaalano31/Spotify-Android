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
    @SerializedName("birthDate")
    @Expose
    private String dateOfBirth;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("body")
    @Expose
    private String text;

    public Users(String userName, String email, String password, String dateOfBirth, String gender) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
}

    public Users() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getBirthDate() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }
}

