package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    TextView editProfile;
    TextView username;
    userInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        editProfile =  (TextView) findViewById(R.id.EditProfiletextView);
        username = (TextView) findViewById(R.id.UsernametextView);

        Intent oldIntent  = getIntent();  // getting the object we created in the last activity
        user= (userInfo)oldIntent.getParcelableExtra("userinfo") ;

        username.setText(user.username);


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserProfile.this, EditUser.class);

                i.putExtra("userinfo", user);
              Log.i("done clicked"," " + user.password + " " + user.username + " " + user.email+ " " + user.gender +  " " +user.dateOfBirth);
                startActivity(i);
            }
        });
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
