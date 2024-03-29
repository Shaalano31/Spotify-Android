package com.example.spotify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;
    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;
    //LoginManager loginManager;

    //Button next3;

    public void SignUp(View view) {

        /*next3 = findViewById(R.id.signup);

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent I = new Intent(MainActivity.this,HomeScreen.class);
                startActivity(I);
            }
        });*/
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //alert.setTitle("Notification");
        alert.setMessage("Choose type of user");
        alert.setPositiveButton("Artist", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //intent to artist sign up screen
                Intent intent = new Intent(getApplicationContext(), ArtistSignUp.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("Regular", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //show alert saying this is unavailable currently
            }
        });
        alert.create().show();
    }

    public void LogIn(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //alert.setTitle("Notification");
        alert.setMessage("Choose type of user");
        alert.setPositiveButton("Artist", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //intent to artist sign up screen
                Intent intent = new Intent(getApplicationContext(), ArtistLogIn.class);
                startActivity(intent);
            }
        });
        alert.setNegativeButton("Regular", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // go to log in screen
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
            }
        });
        alert.create().show();

    }

    private static final String EMAIL = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setPermissions(Arrays.asList(EMAIL));
        //FacebookSdk.fullyInitialize();


        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                // currentAccessToken is null if the user is logged out
                if (currentAccessToken != null) {

                    Log.i("Details", "Yes");
                    // AccessToken is not null implies user is logged in and hence we sen the GraphRequest
                    useLoginInformation(currentAccessToken);
                }else{

                    Log.i("Details", "No");
                    LoginManager.getInstance().logOut();
                    Log.i("Details", "No3");
                }
            }
        };

        /**loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
        Log.i("Details", "Success");
        accessToken = loginResult.getAccessToken();
        useLoginInformation(accessToken);
        }

        @Override
        public void onCancel() {
        Log.i("Info", "Cancel");
        }

        @Override
        public void onError(FacebookException error) {
        Log.i("Info", "Error");
        }
        });**/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void useLoginInformation(AccessToken accessToken) {

        /**
         Creating the GraphRequest to fetch user details
         1st Param - AccessToken
         2nd Param - Callback (which will be invoked once the request is successful)
         **/

        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            //OnCompleted is invoked once the GraphRequest is successful
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    //String name = object.getString("name");
                    String email = object.getString("email");
                    Log.i("Details", "ENTER");
                    Log.i("Details", email);
                    // go to home screen

                    Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("Details", "SAD");
                }
            }
        });

        // We set parameters to the GraphRequest using a Bundle.
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        request.setParameters(parameters);
        // Initiate the GraphRequest
        request.executeAsync();
    }

    public void onStart() {
        super.onStart();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            useLoginInformation(accessToken);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
        // We stop the tracking before destroying the activity
        Log.i("Details", "BYEBYE");
        Log.i("ACCESS", accessTokenTracker.toString());

        Log.i("Details", "BYEBYE2");
        Log.i("ACCESS", accessTokenTracker.toString());
    }

    @Override
    public void onBackPressed() {
        Log.i("Info", "Back pressed");
        moveTaskToBack(true);
    }

}


