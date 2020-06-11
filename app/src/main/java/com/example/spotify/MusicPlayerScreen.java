package com.example.spotify;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

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
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MusicPlayerScreen extends AppCompatActivity {

    static ImageView songImage;
    static TextView textViewArtistName;
    static TextView textViewSongName;
    static SeekBar scrubber;
    static ImageView playPauseButton;
    static ImageView nextButton;
    static ImageView previousButton;
    static ImageView likeButton;
    int songProgress;
    boolean first;
    PlayerService mBoundService;
    boolean mServiceBound = false;

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

    private BroadcastReceiver mMessageReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(!first) {
                first=true;
                Glide.with(getApplicationContext())
                        .asBitmap()                         //load the image
                        .load(intent.getStringExtra("imageurl"))
                        .into(songImage);

                textViewArtistName.setText(intent.getStringExtra("artistname"));
                textViewSongName.setText(intent.getStringExtra("songname"));
                scrubber.setMax(intent.getIntExtra("songduration",0));
                Log.d("yes", "onReceive: yes");
            }
            songProgress=intent.getIntExtra("songprogress",0);
            scrubber.setProgress(songProgress);
            if(!mServiceBound) {
                Intent i = new Intent(getApplicationContext(), PlayerService.class);
                bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
                Log.d("yes", "onReceive: yes");
            }
        }
    };

    private BroadcastReceiver mMessageReciever2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isPlaying = intent.getBooleanExtra("isPlaying",false);
            flipPlayPauseButton(isPlaying); //recieve the message filtered if it came from onResume
        }
    };
    private BroadcastReceiver mMessageReciever3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           first=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player_screen);
        first=false;
        songImage = findViewById(R.id.SongImage);
        textViewSongName = findViewById(R.id.tetViewSongNamePlayer);
        textViewArtistName = findViewById(R.id.tetViewSongNamePlayer2);
        scrubber = findViewById(R.id.musicPlayerSeekbar);
        playPauseButton = findViewById(R.id.playPauseButtonPlayer);
        nextButton = findViewById(R.id.nextButtonPlayer);
        previousButton = findViewById(R.id.previousButtonPlayer);
        likeButton = findViewById(R.id.likeButton);


        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(mServiceBound)
                    mBoundService.togglePlayer();

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mServiceBound)
                    mBoundService.nextPlayer();
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mServiceBound){
                        mBoundService.previousPlayer();
                }
            }
        });

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    if (mServiceBound) {
                        mBoundService.seekToSong(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mServiceBound){
            unbindService(mServiceConnection);
            mServiceBound=false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();       //recieve any message from a broadcast and give it to mMessageReceiver ONLY if it has the tag changePlayButton
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReciever, new IntentFilter("playerScreen"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReciever2, new IntentFilter("mediaProgress"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReciever3, new IntentFilter("first"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReciever);
    }

    public static void flipPlayPauseButton(boolean isPlaying){
        if(isPlaying)
            playPauseButton.setImageResource(R.drawable.pause);
        else
            playPauseButton.setImageResource(R.drawable.play);

    }

}
