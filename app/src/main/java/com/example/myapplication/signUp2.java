package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class signUp2 extends AppCompatActivity {

    EditText email;
    Spinner gender;
    Button next3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up6);

        gender = (Spinner) findViewById(R.id.genderSpinner);
        email =(EditText)findViewById(R.id.emailEditText2);
        next3 =(Button)findViewById(R.id.next3Button);

        // fill the spinner with female & male

        //check email

        //checks



        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent I = new Intent(signUp2.this, signUp3.class);
                startActivity(I);



            }
        });
    }
}
