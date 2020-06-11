package com.example.spotify;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signUp extends AppCompatActivity {

    //userInfo user;
    EditText username;
    EditText password;
    Button next2;
    boolean missing_info;
    boolean missing_info1;
    String valid_username;;
    String valid_Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up5);

        username = (EditText) findViewById(R.id.usernameEditText);
        password = (EditText) findViewById(R.id.passworEditText);
        next2 = (Button) findViewById(R.id.nextButton);
        missing_info = true;
        missing_info1 = true;



        // check for username

        username.addTextChangedListener(new TextWatcher()  // for every change in the text
        {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,    // must be there or the func of text watcher will giver error
                                      int count)                                // not used
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, // must be there or the func of text watcher will giver error
                                          int after)                            // not used
            {
            }

            @Override
            public void afterTextChanged(Editable s) {

                Is_Valid_username(username); // the text is finally having text from user
            }

            public void Is_Valid_username(EditText edt)   // function for setting invalid in the edittext

            {
                if (edt.getText().toString() == null)         // nothing is written
                {
                    edt.setError("Enter your username");
                    valid_username = null;
                    missing_info = true;

                } else  // the email format is correct
                {

                    valid_username = edt.getText().toString().trim();
                    missing_info = false;
                }

                if (valid_username.indexOf(' ') >= 0)
                {
                    edt.setError("Enter your username with no spaces ");
                    valid_username = null;
                    missing_info = true;
                }
            }
        });
        // check for password

        password.addTextChangedListener(new TextWatcher()  // for every change in the text
        {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,    // must be there or the func of text watcher will giver error
                                      int count)                                // not used
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, // must be there or the func of text watcher will giver error
                                          int after)                            // not used
            {
            }

            @Override
            public void afterTextChanged(Editable s) {

                Is_Valid_Password(password); // the text is finally having text from user
            }

            public void Is_Valid_Password(EditText edt)   // function for setting invalid in the edittext

            {
                if (edt.getText().toString() .equals(" "))         // nothing is written
                {
                    edt.setError("Enter your password");
                    valid_Password = null;
                    missing_info1= true;

                }


                else   // the email format is correct
                {

                    valid_Password = edt.getText().toString().trim();
                    missing_info1 = false;
                }


            }
        });





        next2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //if ()
            //{
              // password.setError("Enter a password with more than 6 characters  ");
              //  valid_Password = null;
               // missing_info = true;
                //valid_Password.length();  // this line crashes the program
            //}

                if (missing_info == false && missing_info1 == false && ! username.getText().toString().trim().isEmpty() && ! password.getText().toString().trim().isEmpty() )
                {


                    //Intent i = new Intent(signUp.this, signUp2.class);


                   //user = new userInfo();
                    //user.password =valid_Password ;   // passing the userinfo to another activity
                    //user.username =valid_username;
                    Log.i("done clicked"," " + valid_Password + " " + valid_username  );
                   //i.putExtra("userinfo", user);

                    //startActivity(i);


                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please enter valid username or password ", Toast.LENGTH_SHORT).show();

                }

            }

        });

    }
}
