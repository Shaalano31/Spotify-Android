package com.example.spotify;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class userInfo implements Parcelable {
    public String email;
    public String username ;
    public String password ;
    public String gender;
    public String dateOfBirth;  // day-moth- year

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    protected userInfo(Parcel in) {
        email = in.readString();
        username = in.readString();
        password = in.readString();
        gender = in.readString();
        dateOfBirth = in.readString();
    }

    public static final Creator<userInfo> CREATOR = new Creator<userInfo>() {
        @Override
        public userInfo createFromParcel(Parcel in) {
            return new userInfo(in);
        }

        @Override
        public userInfo[] newArray(int size) {
            return new userInfo[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(gender);
        dest.writeString(dateOfBirth);
    }
}
