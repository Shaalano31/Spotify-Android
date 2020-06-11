package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ArtistWelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_welcome_screen);
    }

    public void Testing(View view) {
        Intent intent = new Intent(getApplicationContext(), ArtistUploadMenu.class);
        startActivity(intent);
    }
}
