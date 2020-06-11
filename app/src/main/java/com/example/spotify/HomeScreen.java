package com.example.spotify;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
  
    RecyclerView  recyclerView;
    RecyclerAdapterHome homeAdapter;
    static SeekBar scrubber;
    static TextView textViewArtist;
    static TextView textViewSong;
    FloatingActionButton flipPlayPauseButton;

    private ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>() ;
    private ArrayList<ArrayList<String>> picUrls= new ArrayList<ArrayList<String>>() ;
    private ArrayList<String> Titles = new ArrayList<String>() ;

    ImageView settings ;
    userInfo user;
    Button mPlayer;

    public void libraryClick(View view){
        Intent intent = new Intent(getApplicationContext(), Playlist.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        scrubber=findViewById(R.id.musicScrubberHome);
        textViewArtist=findViewById(R.id.textViewArtistHome);
        textViewSong=findViewById(R.id.textViewSongHome);
        flipPlayPauseButton=findViewById(R.id.imageViewHome);

        Titles.add("Made for you ");
        Titles.add("Popular tracks ");
        Titles.add("Popular songs");
        Titles.add("Popular Artist ");
        Titles.add("Top geners ");

        int i = 0;

       while (i<5)
       {
           initImageBitmaps(i);
           i++;
       }
        flipPlayPauseButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(mServiceBound)
                    mBoundService.togglePlayer();
            }
        });
        mPlayer = findViewById(R.id.Player);

        mPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MusicPlayerScreen.class);
                startActivity(i);
            }
        });

        initRecyclerView();
    }

    public void goToSearch (View view) {

        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    public void goToSettings (View view) {
        Intent intent = new Intent(getApplicationContext(), Settings.class);

///////////////////////////////to be passed to settings
        userInfo   user;

        Intent oldIntent  = getIntent();  // getting the object we created in the last activity


        user= (userInfo)oldIntent.getParcelableExtra("userinfo") ;

       // Log.i("done clicked"," " + user.password + " " + user.username + " " + user.email+ " " + user.gender +  " " +user.dateOfBirth);  //// samah added

        intent.putExtra("userinfo", user);



        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Log.i("Info", "Back pressed");
        moveTaskToBack(true);
    }

    private void initImageBitmaps(int i)
    {

       ArrayList<String> tempName = new ArrayList<String>() ;
        ArrayList<String> tempUrl = new ArrayList<String>() ;


        tempUrl.add("https://static.billboard.com/files/media/Solastalgia-missy-higgins-album-art-2018-billboard-1240-1024x1024.jpg");
        tempName.add(" Amazing Pop");

        tempUrl.add("https://static.billboard.com/files/media/ariana-grande-sweetner-album-art-2018-billboard-1240-1024x1024.jpg");
        tempName.add("chill");

        tempUrl.add("https://static.billboard.com/files/media/kacey-musgraves-golden-hour-album-2018-billboard-embed-1024x1024.jpg");
        tempName.add("Have fun ");

        tempUrl.add("https://static.billboard.com/files/media/janelle-monae-dirty-computer-album-2018-billboard-embed-1024x1024.jpg");
        tempName.add("Our best selection");

        tempUrl.add("https://static.billboard.com/files/media/Bad-Religion-Age-of-Unreason-album-art-2019-billboard-1240-1024x1024.jpg");
        tempName.add("Swag mood");

        tempUrl.add("https://static.billboard.com/files/media/Taylor-Swift-Lover-album-art-2019-billboard-1240-1024x1024.jpg");
        tempName.add("Fav pop");

        picUrls.add(tempUrl);
         names.add(tempName);


    }

    public  void initRecyclerView()
    {

        LinearLayoutManager LayoutManager =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.theMainRV);
        homeAdapter= new RecyclerAdapterHome(Titles,names, picUrls,HomeScreen.this);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(LayoutManager);
    }

    private BroadcastReceiver mMessageReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("name");
            textViewSong.setText(name);
            textViewArtist.setText(intent.getStringExtra("artistname"));
            Intent i = new Intent(getApplicationContext(),PlayerService.class);
            bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
        }
    };
    private BroadcastReceiver mMessageReciever2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isPlaying = intent.getBooleanExtra("isPlaying",false);
            flipPlayPause(isPlaying);

        }
    };
    private void flipPlayPause(boolean isPlaying)
    {
        if(isPlaying)
            flipPlayPauseButton.setImageResource(R.drawable.pausee);
        else
            flipPlayPauseButton.setImageResource(R.drawable.playy);

    }

    private ServiceConnection mServiceConnection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayerService.MyBinder myBinder = (PlayerService.MyBinder) service;
            mBoundService = myBinder.getService();
            mServiceBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name){
            mServiceBound = false;
        }
    };

    PlayerService mBoundService;
    boolean mServiceBound = false;
    @Override
    protected void onResume() {
        super.onResume();       //recieve any message from a broadcast and give it to mMessageReceiver ONLY if it has the tag changePlayButton
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReciever, new IntentFilter("name"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReciever2, new IntentFilter("mediaProgress"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReciever);
    }
}

