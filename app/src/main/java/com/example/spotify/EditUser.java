package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditUser extends AppCompatActivity {

    userInfo user;
    EditText username;
    EditText oPass;
    EditText nPass;
    TextView save;


    public boolean valid_userIputs() {

        ////////////////username


        if (username.getText().toString().isEmpty())         // nothing is written in username
        {
            username.setError("Enter your username");
            return false;

        }

        else if (username.getText().toString().indexOf(' ') >= 0)  // username is not valid

        {
            username.setError("Enter your username with no spaces ");
            return false;
        }


        ///////////////////////////////////// password

        else if (oPass.getText().toString().equals(user.password))  // the old password is true validation

        {
            Log.i("nhalf way broo"," " + user.password + "  "+oPass.getText().toString() );

            if (nPass.getText().toString().isEmpty())         // nothing is written in new password
            {
                nPass.setError("Enter your password");

                nPass.setText("");

                return false;
            } else   // the password format is correct
            {

                user.password = nPass.getText().toString().trim();
                Toast.makeText(this ," new password is saved ", Toast.LENGTH_SHORT).show();
                return true;

            }
        }


        else if (!(oPass.getText().toString().equals(user.password)) && (! oPass.getText().toString().isEmpty()))  // user entered false old password

            {
            oPass.setError("Enter your old password correctly");

                Log.i("noo we are here clicked"," " + user.password + "  "+oPass.getText().toString() );

            return false;
            }

        Toast.makeText(this, "info saved ",Toast.LENGTH_SHORT).show();
            return true;    // no old password is entered without new one  &&  username has a valid name in it



        }
   ////////////////////////////////////// end of functions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);



        username = (EditText) findViewById(R.id.usernameEditText);
        oPass = (EditText) findViewById(R.id.passworEditText);
        nPass = (EditText) findViewById(R.id.newpassworEditText);
         save= (TextView)  findViewById(R.id.SavetextView);
        Intent oldIntent = getIntent();  // getting the object we created in the last activity
        user = (userInfo) oldIntent.getParcelableExtra("userinfo");


        username.setText(user.username);
        save.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

               if(valid_userIputs())
               {
                   user.username= username.getText().toString();


                   Intent i = new Intent( EditUser.this, HomeScreen.class);
                   i.putExtra("userinfo", user);
                   Log.i("done clicked"," " + user.password + " " + user.username + " " + user.email+ " " + user.gender +  " " +user.dateOfBirth);
                   startActivity(i);
               }


            }
        });







    }

    public void goToHome (View view) {
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
    }

    public void goToSearch (View view) {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    public void goToPlaylists (View view) {
        Intent intent = new Intent(getApplicationContext(), Playlist.class);
        startActivity(intent);
    }

}




