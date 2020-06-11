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
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SongsList extends AppCompatActivity {

    private static final String TAG = "Song List";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mUrls = new ArrayList<>();
    private ArrayList<String> mArtistNames = new ArrayList<>();
    static int currentPosition;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    static SeekBar scrubber;
    static TextView textViewArtist;
    static TextView textViewSong;
    FloatingActionButton flipPlayPauseButton;
    Button mPlayer;
    int type; //1 for rock, 2 for hiphop

    Spotify spotifyApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_songs_list);
        String playlistName = intent.getStringExtra("image_name");
        String playlistImage = intent.getStringExtra("image_url");
        if(playlistName.equals("Rock")){
            type=1;
        }
        else if(playlistName.equals("Hip-Hop"))
        {
            type = 2;
        }
        setHeadlines(playlistName,playlistImage);
        initImageBitmaps();

        mPlayer = findViewById(R.id.Player);
        scrubber = findViewById(R.id.musicScrubber);
        textViewArtist = findViewById(R.id.textViewArtist);
        textViewSong = findViewById(R.id.textViewSong);
        flipPlayPauseButton=findViewById(R.id.imageView8);

        flipPlayPauseButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if(mServiceBound)
                    mBoundService.togglePlayer();
            }
        });

        mPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MusicPlayerScreen.class);
                startActivity(i);
            }
        });


        adapter.setOnItemClickListener(new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position, String url) {
                currentPosition=position;
                startStreamingService(url);
                textViewArtist.setText("Billie Eilish");
                SongsList.textViewSong.setText(mNames.get(position));
                HomeScreen.textViewArtist.setText("Billie Eilish");
                Playlist.textViewArtist.setText("Billie Eilish");
            }
        });

 /*       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://560a8140-b758-4978-bd0a-dc372f8a99b0.mock.pstmn.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        spotifyApi = retrofit.create(Spotify.class);*/

    }

/*    public void getTracks(){

        Call<List<Songs>> call = spotifyApi.getSongs();
        call.enqueue(new Callback<List<Songs>>() {
            @Override
            public void onResponse(Call<List<Songs>> call, Response<List<Songs>> response) {
                List<Songs> songs = response.body();
                for (Songs song : songs){
                    mUrls.add(song.getUrl());
                    mNames.add(song.getSong());
                    mArtistNames.add(song.getArtist());
                    mImageUrls.add(song.getImageurl());

                }
            }

            @Override
            public void onFailure(Call<List<Songs>> call, Throwable t) {
                Log.d("Error service", "onFailure: "+t.getMessage());
            }
        });

    }*/

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        if(type==1){
            mImageUrls.add("https://khqa.com/resources/media/66f54f65-f2a2-4965-a694-97f5a397886d-large16x9_megadeth.jpg?1560788490704");
            mNames.add("Rock And Roll");
            mUrls.add("https://files.freemusicarchive.org/storage-freemusicarchive-org/music/WFMU/Jad_Fair/mp3/Jad_Fair_-_Above_The_Sun.mp3");
            mArtistNames.add("Megadeth");

            mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/9/92/Metallica_Live_at_The_O2%2C_London%2C_England%2C_22_October_2017.jpg");
            mNames.add("EnterSandMan");
            mUrls.add("https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/Monplaisir/Heat_of_the_Summer/Monplaisir_-_11_-_Estampe_Galactus_Barbare_Epaul_Giraffe_Ennui.mp3");
            mArtistNames.add("Metallica");
        }
        else if(type==2)
        {
            mImageUrls.add("data:image/jpeg;base64,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");
            mNames.add("when im gone");
            mUrls.add("https://files.freemusicarchive.org/storage-freemusicarchive-org/music/WFMU/Jad_Fair/mp3/Jad_Fair_-_Above_The_Sun.mp3");
            mArtistNames.add("Eminem");
        }
        else {

            mImageUrls.add("data:image/jpeg;base64,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");
            mNames.add("when the party is over");
            mUrls.add("https://files.freemusicarchive.org/storage-freemusicarchive-org/music/WFMU/Jad_Fair/mp3/Jad_Fair_-_Above_The_Sun.mp3");
            mArtistNames.add("Billie Eilish");

            mImageUrls.add("https://f4.bcbits.com/img/0014313202_10.jpg");
            mNames.add("Estampe Galactus");
            mUrls.add("https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/Monplaisir/Heat_of_the_Summer/Monplaisir_-_11_-_Estampe_Galactus_Barbare_Epaul_Giraffe_Ennui.mp3");
            mArtistNames.add("Monplaisir");

            mImageUrls.add("https://f4.bcbits.com/img/0014313202_10.jpg");
            mNames.add("A landmine");
            mArtistNames.add("Monplaisir");
            mUrls.add("https://files.freemusicarchive.org/storage-freemusicarchive-org/music/no_curator/Monplaisir/Surtout_ne_pas_se_perdre_2011-2016/Monplaisir_-_02_-_alandmine.mp3");

            mUrls.add("https://files.freemusicarchive.org/storage-freemusicarchive-org/music/Music_for_Video/Komiku/The_Binge_Watchers__Score_1/Komiku_-_10_-_Ambiant_Hope.mp3");
            mArtistNames.add("Komiku");
            mNames.add("Ambiant Hope");
            mImageUrls.add("https://files.freemusicarchive.org/storage-freemusicarchive-org/images/artists/Komiku_-_20160721122625292.jpg?width=55&height=55");
        }
      //  getTracks();
        initRecyclerView();
    }



    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        recyclerView = findViewById(R.id.songsView);
         adapter = new RecyclerViewAdapter(this, mNames, mImageUrls,mUrls,mArtistNames,true,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setHeadlines (String name, String url){
        ImageView image = findViewById(R.id.playlistImage);
        TextView listName = findViewById(R.id.listName);
        int d;
        listName.setText(name);
        try {
             d = Integer.parseInt(url);
        } catch (NumberFormatException nfe) {
            Glide.with(this)
                    .asBitmap()
                    .load(url)
                    .into(image);
            return;
        }

        image.setImageResource(d);
    }

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
            String name = intent.getStringExtra("name");
           textViewSong.setText(name);
            textViewArtist.setText(intent.getStringExtra("artistname"));
        }
    };
    private BroadcastReceiver mMessageReciever2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isPlaying = intent.getBooleanExtra("isPlaying",false);
            flipPlayPause(isPlaying);

        }
    };

    private BroadcastReceiver mMessageReciever3 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int position = intent.getIntExtra("position",0);
            if(position<mUrls.size() && position>=0) {
                currentPosition = position;
                startStreamingService(mUrls.get(currentPosition));
            }
        }
    };

    private void startStreamingService(String url)
    {
        Intent i = new Intent(this,PlayerService.class);
        i.putExtra("url",url);
        i.putExtra("songName",mNames.get(currentPosition));
        i.putExtra("artistname",mArtistNames.get(currentPosition));
        i.putExtra("imageurl",mImageUrls.get(currentPosition));
        i.putExtra("position",currentPosition);
        i.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        startService(i);
        if(!mServiceBound)
            bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceBound){
            unbindService(mServiceConnection);
            mServiceBound=false;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();       //recieve any message from a broadcast and give it to mMessageReceiver ONLY if it has the tag changePlayButton
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReciever, new IntentFilter("name"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReciever2, new IntentFilter("mediaProgress"));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReciever3, new IntentFilter("nextsong"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReciever);
    }

    private void flipPlayPause(boolean isPlaying)
    {
        if(isPlaying)
            flipPlayPauseButton.setImageResource(R.drawable.pausee);
        else
            flipPlayPauseButton.setImageResource(R.drawable.playy);

    }

}
