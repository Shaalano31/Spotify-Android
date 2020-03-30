package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    TextView viewProfile;
    TextView userName;
    ImageView profileIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        viewProfile= (TextView) findViewById(R.id.ViewProfiletextView);
        userName= (TextView)  findViewById(R.id.UsernametextView);
        profileIcon= (ImageView) findViewById(R.id.ProfileimageView);

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Settings.this, UserProfile.class);
                startActivity(i);

            }
        });

        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Settings.this, UserProfile.class);
                startActivity(i);

            }
        });


        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Settings.this, UserProfile.class);
                startActivity(i);

            }
        });





    }
}
