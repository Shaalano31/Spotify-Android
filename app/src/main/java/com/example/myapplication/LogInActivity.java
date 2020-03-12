package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;

public class LogInActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    EditText usernameEditText;  //the username entered by user
    EditText passwordEditText;  //the password entered by user
    TextView forgotPasswordTextView;
    InputMethodManager inputMethodManager;

    public void onClick(View view){
        //To type login function
        String userName = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        //if pressed on the background or the logo and the keyboard is up, hide it
       if(view.getId()==R.id.spotifyImageView||view.getId()==R.id.spotifyLogo||view.getId()==R.id.backgroundLayout){

            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
       }
       else if(view.getId()==R.id.forgotPasswordLoginPage){
           Intent intent = new Intent(getApplicationContext(),ForgotPasswordActivity.class);
           startActivity(intent);
       }
       //else means log in button is pressed
       else{
           if (userName.isEmpty()) {
               Toast.makeText(getApplicationContext(), "Username is Empty, please enter a Username", Toast.LENGTH_SHORT).show();
           } else if (password.isEmpty()) {
               Toast.makeText(getApplicationContext(), "Password is Empty, please enter a password", Toast.LENGTH_SHORT).show();
           }


           // hard-coded verification method
           if (userName.equals("abdo")) {
               if (password.equals("1234")) {
                   //to type the code that directs to the homepage
                   Toast.makeText(getApplicationContext(), "YAY", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(getApplicationContext(), "Wrong password.", Toast.LENGTH_SHORT).show();
                   Log.i("password",password);
               }
           } else {
               Log.i("username",userName);
               Toast.makeText(getApplicationContext(), "Wrong username or password.", Toast.LENGTH_SHORT).show();
           }
           //end of verification
       }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        usernameEditText = findViewById(R.id.usernameLoginEdittext);
        passwordEditText = findViewById(R.id.passwordLoginEdittext);
        forgotPasswordTextView=findViewById(R.id.forgotPasswordLoginPage);

        ImageView spotifyImageView = findViewById(R.id.spotifyImageView);
        TextView spotifyLogo = findViewById(R.id.spotifyLogo);
        ConstraintLayout constraintLayout = findViewById(R.id.backgroundLayout);
        constraintLayout.setOnClickListener(this);
        spotifyImageView.setOnClickListener(this);
        spotifyLogo.setOnClickListener(this);
        //To recognize pressed keys and retrieve and handle them
        passwordEditText.setOnKeyListener(this);

        inputMethodManager =(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    //This function is to check if enter is pressed, then it logs in
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
            onClick(v);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        return false;
    }
}
