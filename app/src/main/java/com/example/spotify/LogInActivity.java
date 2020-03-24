package com.example.spotify;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class LogInActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                JSONObject jsonObject = new JSONObject(result);

                String accounts = jsonObject.getString("accounts");


                JSONArray arr = new JSONArray(accounts);

                for (int i = 0; i < arr.length(); i++) {

                    listdata.add(arr.getString(i));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    ArrayList<String> listdata = new ArrayList<String>();
    EditText usernameEditText;  //the username entered by user
    EditText passwordEditText;  //the password entered by user
    TextView forgotPasswordTextView;
    InputMethodManager inputMethodManager;

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
            Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
            startActivity(intent);
        }
        //else means log in button is pressed
        else {
            if (userName.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Username is Empty, please enter a Username", Toast.LENGTH_SHORT).show();
            } else if (password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Password is Empty, please enter a password", Toast.LENGTH_SHORT).show();
            }

            // hard-coded verification method
            String requestDetails = "{\""+userName+"\":\""+password+"\"}";
                if (listdata.contains(requestDetails)) {
                        //to type the code that directs to the homepage
                        Toast.makeText(getApplicationContext(), "YAY", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(getApplicationContext(), "Wrong username or password.", Toast.LENGTH_SHORT).show();

                }
                //end of verification

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        DownloadTask task = new DownloadTask();
        task.execute("https://b9bf2891-740a-4087-b3eb-24551a0a4ae2.mock.pstmn.io/login/");

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
