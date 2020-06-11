package com.example.spotify;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Headers;
import okhttp3.internal.http2.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    private interface OKCallback {
        void onSuccess(String result);

        void onFailure(String result);
    }

    ArrayList<String> listdata = new ArrayList<String>();
    EditText usernameEditText;  //the username entered by user
    EditText passwordEditText;  //the password entered by user
    TextView forgotPasswordTextView;
    InputMethodManager inputMethodManager;
    Spotify spotifyApi;
    String auth1 = "";  //THIS CARRIES USER AUTHENTICATION TOKEN
    SQLiteDatabase userInfoDatabase;
    public void onClick(View view) {
        //To type login function
        String userName = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        //if pressed on the background or the logo and the keyboard is up, hide it
        if (view.getId() == R.id.spotifyImageView || view.getId() == R.id.backgroundLayout) {
            if(inputMethodManager.isAcceptingText()) {
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } else if (view.getId() == R.id.forgotPasswordLoginPage) {
            Intent intent2 = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
            startActivity(intent2);
        }
        //else means log in button is pressed
        else {
            if (userName.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Username is Empty, please enter a Username", Toast.LENGTH_SHORT).show();
            } else if (password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Password is Empty, please enter a password", Toast.LENGTH_SHORT).show();
            }
            // hard-coded verification method
            else
                userLoginApi(new OKCallback() {
                    @Override
                    public void onSuccess(String result) {

                        try {
                            userInfoDatabase.execSQL("INSERT INTO UserTokens(token,value) VALUES('UserToken','" + auth1 + "')");

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getApplicationContext(), "YAY", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                        startActivity(intent);
                    }
                    //end of verification
                    @Override
                    public void onFailure(String result) {
                        Toast.makeText(getApplicationContext(), "An Error ocurred, Try again later", Toast.LENGTH_SHORT).show();
                    }
                });
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
        ConstraintLayout constraintLayout = findViewById(R.id.backgroundLayout);
        constraintLayout.setOnClickListener(this);
        spotifyImageView.setOnClickListener(this);
        //To recognize pressed keys and retrieve and handle them
        passwordEditText.setOnKeyListener(this);

        inputMethodManager =(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://560a8140-b758-4978-bd0a-dc372f8a99b0.mock.pstmn.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        spotifyApi = retrofit.create(Spotify.class);
        try{
            userInfoDatabase = this.openOrCreateDatabase("User",MODE_PRIVATE,null);
            userInfoDatabase.execSQL("CREATE TABLE IF NOT EXISTS UserTokens (token VARCHAR UNIQUE,value VARCHAR UNIQUE)");
            userInfoDatabase.execSQL("INSERT INTO UserTokens(token,value) VALUES('SearchToken','eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s')");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), "YAY", Toast.LENGTH_SHORT).show();
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

    private void userLoginApi(final OKCallback okCallback){
        Users user = new Users();
        user.setEmail(usernameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        Log.d("user email",user.getEmail());
        Log.d("user password",user.getPassword());
        Call<Void> call = spotifyApi.userLogIn(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse (Call < Void > call, Response < Void > response){
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Failed, Code:" + response.code(), Toast.LENGTH_SHORT).show();
                    okCallback.onFailure(auth1);
                    return;
                }
                Headers userResponse = response.headers();
                auth1 = userResponse.get("x-auth");
                Log.d("token", "none" + auth1);
                okCallback.onSuccess(auth1);
            }

            @Override
            public void onFailure (Call < Void > call, Throwable t){
                Toast.makeText(getApplicationContext(), "Failed, Code:", Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
                auth1 = "Failed";
                okCallback.onFailure(auth1);
                return;
            }
        });
    }

}
