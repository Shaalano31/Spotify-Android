package com.example.spotify;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    EditText email;
    Spinner gender;
    Button next3;
    String valid_email;
    boolean missing_info ;
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

        spinnerArray.add("Female");
        spinnerArray.add("Male");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        // GET SELECTED

        String selected = gender.getSelectedItem().toString();


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