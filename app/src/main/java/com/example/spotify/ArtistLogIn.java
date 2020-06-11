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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistLogIn extends AppCompatActivity {

    EditText password;
    EditText email;
    boolean appropriatePassword;
    boolean appropriateEmail;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Spotify spotify;

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
            /*Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://c9e439fa-e868-48e8-b329-06131b75737c.mock.pstmn.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            spotify = retrofit.create(Spotify.class);
            Users users = new Users();


            Call<Users> call = spotify.artistLogIn(users);

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
*/

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
