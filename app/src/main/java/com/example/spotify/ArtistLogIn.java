package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtistLogIn extends AppCompatActivity {

    EditText password;
    EditText email;
    boolean appropriatePassword;
    boolean appropriateEmail;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_log_in);

        email = findViewById(R.id.emailLoginEdittext);
        password = findViewById(R.id.passwordLoginEdittext);

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

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("INFO", email.getText().toString());
                if (email.getText().toString().matches(emailPattern) && s.length() > 0)
                {
                    Log.i("INFO","valid email");
                    appropriateEmail = true;
                }
                else
                {
                    appropriateEmail = false;
                    email.setError("Wrong email format");
                }
            }
        });
    }


    public void LogInClicked (View view) {
        if(appropriateEmail && appropriatePassword) {
            //login request


            Intent intent = new Intent(getApplicationContext(), ArtistWelcomeScreen.class);
            startActivity(intent);

        }
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}
