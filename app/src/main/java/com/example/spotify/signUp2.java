package com.example.spotify;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class signUp2 extends AppCompatActivity {
     userInfo user;
    EditText email;
    Spinner gender;
    Button next3;
    String valid_email;
    boolean missing_info ;
    String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up6);

        missing_info=true;
        gender = (Spinner) findViewById(R.id.genderSpinner);
        email = (EditText) findViewById(R.id.emailEditText2);
        next3 = (Button) findViewById(R.id.next3Button);

        // fill the spinner with female & male

        List<String> spinnerArray = new ArrayList<String>();

        spinnerArray.add("F");
        spinnerArray.add("M");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        // GET SELECTED

         selected = gender.getSelectedItem().toString();


        //check email


        email.addTextChangedListener(new TextWatcher()  // for every change in the text
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

                Is_Valid_Email(email); // the text is finally having text from user
            }

            public void Is_Valid_Email(EditText edt)   // function for setting invalid in the edittext

            {
                if (edt.getText().toString() == null)         // nothing is written
                {
                    edt.setError("Enter your email");
                    valid_email = null;
                    missing_info = true;
                } else if (isEmailValid(edt.getText().toString()) == false)  // the email format is not correct
                {
                    edt.setError("Invalid Email Address");
                    valid_email = null;
                    missing_info = true;
                } else  // the email format is correct
                {

                    valid_email = edt.getText().toString();
                    missing_info = false;
                }
            }

            boolean isEmailValid(CharSequence email)   // for checking the right email format

            {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches();
            }

            // end of TextWatcher (email)
        });





        // next button

            next3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (missing_info == false)
                    {
                        Intent I = new Intent(signUp2.this, signUp3.class);

                        Intent oldIntent  = getIntent();  // getting the object we created in the last activity
                        user= (userInfo)oldIntent.getParcelableExtra("userinfo") ;
                        user.setEmail(valid_email);  // passing the userinfo to another activity
                        user.setGender(selected);
                        Log.i("done clicked"," " + user.password + " " + user.username + " " + user.email+ " " + user.gender );
                         I.putExtra("userinfo", user);


                        startActivity(I);


                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please enter the valid email ", Toast.LENGTH_SHORT).show();

                    }

                }


            });




    }
}
