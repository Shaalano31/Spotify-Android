package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistSignUp2 extends AppCompatActivity {


    EditText email;
    Spinner gender;
    String password;
    String username;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    boolean appropriateEmail;
    Spotify spotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_sign_up2);

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");
        email = findViewById(R.id.emailEditText);
        gender = findViewById(R.id.genderSpinner);


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


        // fill the spinner with female & male
        List<String> spinnerArray = new ArrayList<String>();

        spinnerArray.add("Female");
        spinnerArray.add("Male");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
    }

    public void nextButton (View view) {
        Log.i("Hello", "Hi");
        if(appropriateEmail) {
            //sign up request
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://c9e439fa-e868-48e8-b329-06131b75737c.mock.pstmn.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            spotify = retrofit.create(Spotify.class);
            artistSignUp();

        }
    }

    private void artistSignUp() {
        Users users = new Users(username, email.getText().toString(), password, "11-10-1999", gender.getSelectedItem().toString());

        Call<Users> call = spotify.artistSignUp(users);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Log.i("INFO", "request failed");
                    return;
                }

                Users userResponse = response.body();
                Log.i("INFO", "success");
                Log.i("INFO", userResponse.toString());
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.i("ERROR", t.getMessage());
            }
        });
    }
}
