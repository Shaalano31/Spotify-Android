package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeScreen extends AppCompatActivity {


    public void libraryClick(View view){
        Intent intent = new Intent(getApplicationContext(), Playlist.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }


}
