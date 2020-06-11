package com.example.spotify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerService extends Service {

    MediaPlayer mediaPlayer = new MediaPlayer();
    String currentSong;
    String currentImageUrl;
    String currentArtistName;
    int currentPosition;
    //{
    private final IBinder mBinder = new MyBinder();

    public class MyBinder extends Binder{
        PlayerService getService(){                  //this chunk of code only creates a binder that returns the PlayerService class
            return PlayerService.this;
        }
    }
    //}

    public PlayerService() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getStringExtra("url")!=null)
            playStream(intent.getStringExtra("url"));

        if(intent.getStringExtra("songName")!=null)
            currentSong=intent.getStringExtra("songName");

        if(intent.getStringExtra("imageurl")!=null)
            currentImageUrl=intent.getStringExtra("imageurl");

        if(intent.getStringExtra("artistname")!=null)
        {currentArtistName=intent.getStringExtra("artistname");
            currentPosition=intent.getIntExtra("position",0);}


        if(intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)){
            Log.i("info","Start Foreground Service");
            showNotification();
        }
        else if(intent.getAction().equals(Constants.ACTION.PREV_ACTION)){
            previousPlayer();
            Log.i("info","Previous Service Pressed");
        }
        else if(intent.getAction().equals(Constants.ACTION.PLAY_ACTION)){
            togglePlayer();
            Log.i("info","Play Service Pressed");
        }
        else if(intent.getAction().equals(Constants.ACTION.NEXT_ACTION)){
            nextPlayer();
            Log.i("info","Next Service Pressed");
        }
        else if(intent.getAction().equals(Constants.ACTION.STOPFOREGROUND_ACTION)){
            Log.i("info","Stop ForeGround Service");
            stopForeground(true);
            stopSelf();
        }

        return START_NOT_STICKY; //if you want the server to keep on running after the action ends
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i("info","Stop ForeGround Service");
        mediaPlayer.stop();
        stopForeground(true);
        stopSelf();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotification(){
        Intent notificaationIntent = new Intent(this,SongsList.class);
        notificaationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificaationIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificaationIntent,0);

        Intent previousIntent = new Intent(this,PlayerService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this,0,previousIntent,0);

        Intent playIntent = new Intent(this,PlayerService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this,0,playIntent,0);

        Intent nextIntent = new Intent(this,PlayerService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this,0,nextIntent,0);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.spotify);

        String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        int playPauseButtonId = android.R.drawable.ic_media_play;
        if (mediaPlayer !=null && mediaPlayer.isPlaying())
            playPauseButtonId = android.R.drawable.ic_media_pause;

        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle(currentSong)
                .setTicker("Playing Music")
                .setContentText(currentArtistName)
                .setSmallIcon(R.drawable.spot)
                .setLargeIcon(Bitmap.createScaledBitmap(icon,128,128,false))
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.ic_media_previous,"Previous",ppreviousIntent)
                .addAction(playPauseButtonId,"Play",pplayIntent)
                .addAction(android.R.drawable.ic_media_next,"Next",pnextIntent)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle())
                .build();
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,notification);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    Timer timer;
    int mediaPlayerLength;
    public void playStream(String url){
        if(mediaPlayer!=null){
            try{
                mediaPlayer.stop();
            }catch (Exception e){
            }
            mediaPlayer=null;
        }

        mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
        try{
            mediaPlayer.setDataSource(url.replaceAll(" ", "%20"));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onPrepared(MediaPlayer mp) {
                    playPlayer();
                    mediaPlayerLength = mp.getDuration();
                    SongsList.scrubber.setMax(mp.getDuration());
                    Playlist.scrubber.setMax(mp.getDuration());
                    HomeScreen.scrubber.setMax(mp.getDuration());
                    SongsList.textViewSong.setText(currentSong);
                    HomeScreen.textViewSong.setText(currentSong);
                    Playlist.textViewSong.setText(currentSong);
                    sendFirstSignal();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    flipPlayPauseButton(false);
                }
            });
            mediaPlayer.prepareAsync();
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    updatePlayerScreen();

                    SongsList.scrubber.setMax(mediaPlayerLength);
                    Playlist.scrubber.setMax(mediaPlayerLength);
                    HomeScreen.scrubber.setMax(mediaPlayerLength);

                    SongsList.scrubber.setProgress(mediaPlayer.getCurrentPosition());
                    Playlist.scrubber.setProgress(mediaPlayer.getCurrentPosition());
                    HomeScreen.scrubber.setProgress(mediaPlayer.getCurrentPosition());
                    updateName();
                }
            },0,700);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void pausePlayer(){
        try{
            mediaPlayer.pause();
            showNotification();

            flipPlayPauseButton(false);
        }
        catch (Exception e){
            Log.d("EXCEPTION","failed to pause player");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void playPlayer(){
        try{
            mediaPlayer.start();
            showNotification();
            flipPlayPauseButton(true);
        }
        catch (Exception e){
            Log.d("EXCEPTION","failed to play player");
        }
    }

    public void nextPlayer(){
        Intent intent = new Intent("nextsong");
        currentPosition++;
        intent.putExtra("position",currentPosition);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void previousPlayer(){
        if(mediaPlayer.getCurrentPosition()>10000)
        {
            seekToSong(0);
        }
        else
        {
        Intent intent = new Intent("nextsong");
        currentPosition--;
        intent.putExtra("position",currentPosition);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    }

    public void flipPlayPauseButton(boolean isPlaying){
        Intent intent = new Intent("mediaProgress");
        intent.putExtra("isPlaying",isPlaying);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);       //you create ana intent and broadcast it as a message to main activity with the tag
    }                                                                       //changePlayButton to communicate with the main activity (Service to Activity)

    public void updateName(){
        Intent intent = new Intent("name");
        intent.putExtra("name",currentSong);
        intent.putExtra("artistname",currentArtistName);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void updatePlayerScreen(){
        Intent intent = new Intent("playerScreen");
        intent.putExtra("songname",currentSong);
        intent.putExtra("artistname",currentArtistName);
        intent.putExtra("imageurl",currentImageUrl);
        intent.putExtra("songduration",mediaPlayer.getDuration());
        intent.putExtra("songprogress",mediaPlayer.getCurrentPosition());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
    public void sendFirstSignal(){
        Intent intent = new Intent("first");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void togglePlayer() {
        try{
            if(mediaPlayer.isPlaying()){
                pausePlayer();
            }
            else
                playPlayer();

        }catch (Exception e){
            Log.d("EXCPETION","Toggle Error");
        }
    }

    public void seekToSong(int x){
        mediaPlayer.seekTo(x);
    }

}
