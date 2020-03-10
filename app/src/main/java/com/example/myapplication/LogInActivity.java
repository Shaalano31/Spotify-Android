package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {


    protected void UsernameLogInClick(View view){
        //To type login function
    }

    protected void ForgotPasswordClickLogin(View view){
        //To type forget password code
    }

    protected void GoBackLogin(View view){
        //To handle going back from login page to welcome page
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }
}
