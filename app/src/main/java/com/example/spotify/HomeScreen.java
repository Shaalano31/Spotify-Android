package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class HomeScreen extends AppCompatActivity {

    ImageView settings ;
    userInfo user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        settings= findViewById(R.id.settingsImageview);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, Settings.class);
                Intent oldIntent  = getIntent();  // getting the object we created in the last activity
                user= (userInfo)oldIntent.getParcelableExtra("userinfo") ;
                i.putExtra("userinfo", user);
                Log.i("done clicked"," " + user.password + " " + user.username + " " + user.email+ " " + user.gender +  " " +user.dateOfBirth);
                startActivity(i);
            }
        });
    }



}
