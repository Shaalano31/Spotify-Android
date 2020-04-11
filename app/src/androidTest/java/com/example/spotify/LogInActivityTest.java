package com.example.spotify;

import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class LogInActivityTest {

    @Rule
    public ActivityTestRule<HomeScreen> mActivityTestRule = new ActivityTestRule<HomeScreen>(HomeScreen.class);

    @Rule
    public ActivityTestRule<LogInActivity> logInRule = new ActivityTestRule<LogInActivity>(LogInActivity.class);

    public LogInActivity logInActivity;
    public HomeScreen homeScreen = null;


    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(HomeScreen.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        logInActivity = logInRule.getActivity();
        homeScreen = mActivityTestRule.getActivity();
    }

    @Test
    public void testNoCredentialsEntered(){

        logInActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                logInActivity.auth1="Failed";
                View view =  logInActivity.findViewById(R.id.loginButton);
                logInActivity.usernameEditText.setText("");
                logInActivity.passwordEditText.setText("");
                logInActivity.onClick(view);
            }
        });
        assertTrue(getInstrumentation().checkMonitorHit(monitor, 0));       //checks if Homescreen was opened 0 times
    }


    @Test
    public void testSuccessfulLogin(){

        logInActivity.auth1="ok";
        logInActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                logInActivity.usernameEditText.setText("ayaelsackaan.1999@gmail.com");
                logInActivity.passwordEditText.setText("222");
            }
        });
        onView(withId(R.id.loginButton)).perform(click());
        assertTrue(getInstrumentation().checkMonitorHit(monitor, 1));            //checks if Homescreen was opened 1 time
        homeScreen.finish();
    }

    @After
    public void tearDown() throws Exception {
        homeScreen=null;
    }
}