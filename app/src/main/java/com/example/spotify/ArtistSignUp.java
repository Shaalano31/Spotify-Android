package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtistSignUp extends AppCompatActivity {

    EditText username;
    EditText password;
    Button next2;
    boolean appropriatePassword;
    boolean appropriateUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_sign_up);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passworEditText);
        next2 = findViewById(R.id.nextButton);


        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Validate username
                if(username.getText().toString().length() > 8) {
                    Log.i("INFO","Username valid");
                    appropriateUsername = true;
                }
                else {
                    Log.i("INFO","Username not valid");
                    username.setError("Username must be at least 8 characters");
                    appropriateUsername = false;
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) { //password must be 8 character at least with minimum 1 alphabet and 1 number
                if(password.getText().toString().length()<8 &&!isValidPassword(password.getText().toString())){
                    Log.i("INFO","Not Valid");
                    password.setError("Password must be at least 8 characters with at least 1 number and 1 letter");
                    appropriatePassword = false;
                }else{
                    Log.i("INFO","Valid");
                    //password.setError("Password is valid");
                    appropriatePassword = true;
                }
            }
        });
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public void nextButton(View view) {
        if(appropriatePassword   && appropriateUsername ) {
            Log.i("INFO", "Go to next screen");
            Intent intent = new Intent(getApplicationContext(), ArtistSignUp2.class);
            intent.putExtra("username", username.getText().toString());
            intent.putExtra("password",password.getText().toString());
            startActivity(intent);
        }
        else {
            Log.i("INFO", "Access denied");
        }
    }
}
