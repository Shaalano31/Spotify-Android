package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchableInfo;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    TextView viewProfile;
    TextView userName;
    ImageView profileIcon;
    userInfo user;
    TextView logOut;
    //SQLiteDatabase userInfoDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        viewProfile= (TextView) findViewById(R.id.ViewProfiletextView);
        userName= (TextView)  findViewById(R.id.UsernametextView);
        profileIcon= (ImageView) findViewById(R.id.ProfileimageView);
        logOut = findViewById(R.id.logOut);

        Intent oldIntent  = getIntent();  // getting the object we created in the last activity
        user= (userInfo)oldIntent.getParcelableExtra("userinfo") ;

        // userName.setText(user.username);

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Settings.this, UserProfile.class);
                i.putExtra("userinfo", user);
                //Log.i("done clicked"," " + user.password + " " + user.username + " " + user.email+ " " + user.gender +  " " +user.dateOfBirth);
                startActivity(i);

            }
        });

        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Settings.this, UserProfile.class);
                i.putExtra("userinfo", user);
                Log.i("done clicked"," " + user.password + " " + user.username + " " + user.email+ " " + user.gender +  " " +user.dateOfBirth);
                startActivity(i);

            }
        });


        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Settings.this, UserProfile.class);
                i.putExtra("userinfo", user);
                Log.i("done clicked"," " + user.password + " " + user.username + " " + user.email+ " " + user.gender +  " " +user.dateOfBirth);
                startActivity(i);

            }
        });





    }

    public void loggingOut (View view) {
        SQLiteDatabase userInfoDatabase;
        try{
            userInfoDatabase = this.openOrCreateDatabase("User",MODE_PRIVATE,null);
            //userInfoDatabase.execSQL("CREATE TABLE IF NOT EXISTS UserTokens (token VARCHAR UNIQUE,value VARCHAR UNIQUE)");
            //userInfoDatabase.execSQL("INSERT INTO UserTokens(token,value) VALUES('SearchToken','eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s')");
            userInfoDatabase.execSQL("DELETE FROM UserTokens WHERE token = 'UserToken'");
            userInfoDatabase.close();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void goToHome (View view) {
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
    }

    public void goToSearch (View view) {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    public void goToPlaylists (View view) {
        Intent intent = new Intent(getApplicationContext(), Playlist.class);
        startActivity(intent);
    }

}
