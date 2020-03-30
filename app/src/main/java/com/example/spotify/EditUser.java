package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class EditUser extends AppCompatActivity {

    userInfo user;
    EditText username;
    EditText oPass;
    EditText nPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        username = (EditText) findViewById(R.id.usernameEditText2);
        oPass = (EditText) findViewById(R.id.passworEditText);
        nPass = (EditText) findViewById(R.id.newpassworEditText);

        Intent oldIntent = getIntent();  // getting the object we created in the last activity
        user = (userInfo) oldIntent.getParcelableExtra("userinfo");

        username.setText(user.username);



        /////////// when save is clicked


        if (oPass.toString() == user.password)


        {
                if (nPass.getText().toString().equals(" "))         // nothing is written
                {
                    nPass.setError("Enter your password");

                    Toast.makeText(this, " the new password is not valid", Toast.LENGTH_SHORT).show();
                    nPass.setText(" ");
                }

                else   // the password format is correct
                {

                    user.password = nPass.getText().toString().trim();

                }


        }



        //Intent i = new Intent( EditUser.this, UserProfile.class);

       // i.putExtra("userinfo", user);
        Log.i("done clicked"," " + user.password + " " + user.username + " " + user.email+ " " + user.gender +  " " +user.dateOfBirth);
        //startActivity(i);
      ///////////////////////////////////////////////////////////////

    }

}




