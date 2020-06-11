/*
package com.example.spotify;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signUp3 extends AppCompatActivity {

    userInfo user;
    Button done ;
    Button dateofBirth;
    Calendar cal ;
    DatePickerDialog dp ;
    int day ;
    int month ;
    int year;
    String  valid_date;
    private DatePickerDialog.OnDateSetListener Mydatasetlistener ;
    Calendar dob = Calendar.getInstance();
    Calendar today = Calendar.getInstance();
    int age;
    Spotify spotify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up7);
        done = (Button) findViewById(R.id.doneButton);
        dateofBirth = (Button) findViewById(R.id.dateofbirthButton);
//////////////////////////////////////////////////////////////////////////////////////
        Retrofit retrofit = new Retrofit.Builder()         /////////// for calling any playlists
                .baseUrl("http://52.14.190.202:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        spotify = retrofit.create(Spotify.class);

        //getpost
        // getcomments


        ////////////////////////////////////////////////////////////////////////////////////


        dateofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                dp = new DatePickerDialog(signUp3.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, Mydatasetlistener
                        , year, month, day);
                dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dp.show();
            }
        });


        Mydatasetlistener = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {

                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;

                // Show selected date
                dateofBirth.setText(new StringBuilder().append(month + 1)
                        .append("-").append(day).append("-").append(year)
                        .append(" "));

                valid_date = Integer.toString(day) +
                        "-" + Integer.toString(month + 1) + "-" + Integer.toString(year);
            }
        };


        // calculating age & validations

        dateofBirth.addTextChangedListener(new TextWatcher()  // for every change in the text
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


                age = today.get(Calendar.YEAR) - year;

                if (age <= 0) {
                    Toast.makeText(getApplicationContext(), "Please enter valid bithdate ", Toast.LENGTH_SHORT).show();
                } else if (age < 12) {

                    Toast.makeText(getApplicationContext(), " you are under age sorry cant proceed ", Toast.LENGTH_SHORT).show();
                }

                age = today.get(Calendar.YEAR) - year;
                if (today.get(Calendar.MONTH) < month) {
                    age--;
                    Log.i("age cases", "month still not there ");

                } else if (today.get(Calendar.MONTH) > month) {

                } else if (today.get(Calendar.DAY_OF_MONTH) < day) {
                    age--;
                    Log.i("age cases", "same month but still not yet");
                }

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (age > 12) {


                            Intent i = new Intent(signUp3.this, HomeScreen.class);   //the home screen shouldbe here
                            Intent oldIntent = getIntent();  // getting the object we created in the last activity
                            user = (userInfo) oldIntent.getParcelableExtra("userinfo");
                            user.setDateOfBirth(valid_date);  // passing the userinfo to another activity

                            i.putExtra("userinfo", user);

                            startActivity(i);

                            Log.i("done clicked", "age >12 ");


                            Log.i("done clicked", " " + user.password + " " + user.username + " " + user.email + " " + user.gender + " " + valid_date);

////////////////////////////////////////////////////////creation of user
                            createNewuser(user,spotify);/////////////////////////

                        } else {
                            Toast.makeText(getApplicationContext(), "Please enter valid bithdate ", Toast.LENGTH_SHORT).show();
                            Log.i("done clicked", "age nooooooo ");
                        }

                    }

                });


            }


        });
//////////////creation of user function here

    }
    void  createNewuser(userInfo user,Spotify spotify )
    {

        String dayy = Integer.toString(day);
        String monthh=Integer.toString(month + 1);

        if(9>=day)
        {
            dayy= "0"+Integer.toString(day);
        }
        if(9>=month + 1)
        {
            monthh= "0"+Integer.toString(month + 1);
        }

        Users newUser= new Users (user.username, user.email,user.password,false,false,dayy,monthh,Integer.toString(year),user.gender);

        Call<Users> call = spotify.createUser(newUser);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                if (!response.isSuccessful()) {
                    Log.i("Info", "RIP");
                    return;
                }

                Users use = response.body();


                String content ="" ;
                content+= "userName:" + use.getUserName()+ "\n";
                content+= "email:" + use.getEmail()+ "\n";
                content+= "password:" + use.getPassword()+ "\n";
                content+= "isPremium:" + "false"+ "\n";
                content+= "isActive:" + "false"+ "\n";
                content+=  "day:" + use.getUserName()+ "\n";
                content+= "month:" + use.getUserName()+ "\n";
                content+= "year:" + use.getUserName()+ "\n";
                content+="gender" + use.getGender()+ "\n";

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {


                Intent i = new Intent(signUp3.this, MainActivity.class);
                startActivity(i);

                Toast.makeText(getApplicationContext(), "you didnt create an account ", Toast.LENGTH_SHORT).show();
            }
        });

    }






}

*/
