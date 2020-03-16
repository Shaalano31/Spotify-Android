package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button next3;

    public void SignUp(View view) {

        next3 = findViewById(R.id.signup);

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent I = new Intent(MainActivity.this, signUp.class);
                startActivity(I);



            }
        });

    }

    public void LogIn(View view) {
        // go to log in screen
        Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
        startActivity(intent);
    }

    public void continueUsingFacebook(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        // Now get a handle to any View contained
        // within the main layout you are using
        View someView = findViewById(R.id.login);

        // Find the root view
        View root = someView.getRootView();
        // Set the color
        root.setBackgroundColor(getResources().getColor(android.R.color.black));
    }




}
