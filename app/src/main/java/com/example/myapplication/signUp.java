package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signUp extends AppCompatActivity {


    EditText username;
    EditText password;
    Button next2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up5);

        username = (EditText) findViewById(R.id.usernameEditText2);
        password = (EditText) findViewById(R.id.passworEditText);
        next2 = (Button) findViewById(R.id.next1Button);


        // checks

        next2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(signUp.this, signUp2.class);
                startActivity(i);

            }

        });

    }
}
