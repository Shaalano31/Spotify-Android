package com.example.spotify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Playlist extends AppCompatActivity {


    static ArrayList<String> playlistImages;
    static ArrayList<String> playlistVersion;
    static ArrayList<String> artistImages;
    static ArrayList<String>  artistVersion;
    static ArrayList<String> albumImages;
    static ArrayList<String>  albumVersion;
    static ArrayList<String>  versionNumber;

    RelativeLayout createPlaylist;

    RecyclerView playlistView;
    RecyclerView artistView;
    RecyclerView albumView;

    RecyclerViewAdapter playistAdapter;
    RecyclerViewAdapter artistAdapter;
    RecyclerViewAdapter albumAdapter;

    Button playlistButton;
    Button artistButton;
    Button albumButton;
    boolean playlistClicked=true, artistClicked=false, albumClicked=false;      //used to check last button clicked from playlist, artist, albums
    Spotify spotifyApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        playlistView = findViewById(R.id.playlistListView);
        artistView = findViewById(R.id.artistListView);
        albumView = findViewById(R.id.albumListView);
        createPlaylist = findViewById(R.id.createPlaylistButtonLayout);

        playlistVersion= new ArrayList<>();
        artistVersion = new ArrayList<>();
        albumVersion = new ArrayList<>();
        versionNumber= new ArrayList<>();
        playlistImages = new ArrayList<>();
        artistImages = new ArrayList<>();
        albumImages = new ArrayList<>();

        playlistVersion.add("Likes");

        versionNumber.add("1.0");
        versionNumber.add("1.0");

        artistVersion.add("Billie Eilish");
        artistImages.add("https://i.insider.com/5e40b4aedf2f662c42129883?width=1100&format=jpeg&auto=webp");

        albumVersion.add("Where do we go");
        albumImages.add("https://images-eu.ssl-images-amazon.com/images/I/41yZ9kFXtdL._SX342_QL70_ML2_.jpg");

        playlistImages.add(Integer.toString(R.drawable.likes));

        RecyclerViewAdapter playlistAdapter = new RecyclerViewAdapter(this, playlistVersion, playlistImages,false,true);
        playlistView.setAdapter(playlistAdapter);
        playlistView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerViewAdapter artistAdapter = new RecyclerViewAdapter(this, artistVersion, artistImages,true,true);
        artistAdapter.setCircular(true);
        artistView.setAdapter(artistAdapter);
        artistView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerViewAdapter albumAdapter = new RecyclerViewAdapter(this, albumVersion, albumImages,true,true);
        albumAdapter.setCircular(true);
        albumView.setAdapter(albumAdapter);
        albumView.setLayoutManager(new LinearLayoutManager(this));


        playlistButton = findViewById(R.id.playlistButton);
        artistButton = findViewById(R.id.artistButton);
        albumButton = findViewById(R.id.albumButton);
        playlistButton.setTextColor(Color.parseColor("#1DB954"));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.14.190.202:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        spotifyApi = retrofit.create(Spotify.class);

    }


    public void goToSettings (View view) {
        //Intent intent = new Intent(getApplicationContext(), Settings.class);
        //startActivity(intent);
    }

    public void goToSearch (View view) {
      //  Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        //startActivity(intent);
    }

    public void goToHome (View view) {
        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
    }

    public void createPlaylistButtonClick(View v){
        Intent intent = new Intent(this, CreatePlaylist.class);
        this.startActivityForResult(intent,1);
    }


    public void addItem(String item,int key){       //key is used to identify which string array to add in 1:playlist 2:artist 3:album
        if(key==0){
            playlistVersion.add(item);
            playlistImages.add(Integer.toString(R.drawable.likes));
            versionNumber.add("");
            playistAdapter = new RecyclerViewAdapter(this, playlistVersion, playlistImages,false,true);
            playlistView.setAdapter(playistAdapter);
        }
        else if(key==1){
            artistVersion.add(item);
        }
        else
        {
            albumVersion.add(item);
        }
    }

    public void onClick(View v){
        playlistButton.setTextColor(Color.WHITE);
        artistButton.setTextColor(Color.WHITE);
        albumButton.setTextColor(Color.WHITE);
        Log.i(v.getTag().toString(),"lol");
        if(v.getTag().toString().equals("0")){      //if playlist clicked
            playlistButton.setTextColor(Color.parseColor("#1DB954"));
            if(artistClicked){                                                  //if playlist clicked while were on artist
                artistView.animate().alpha(0).setDuration(300);
                artistView.setVisibility(View.INVISIBLE);

                playlistView.setVisibility(View.VISIBLE);
                playlistView.animate().translationXBy(1000).setDuration(300);     //view the playlist and translate it to right, and hide artist alpha
                playlistView.animate().alpha(100).setDuration(300);

                createPlaylist.setVisibility(View.VISIBLE);
                createPlaylist.animate().translationXBy(1000).setDuration(300);     //view the playlist and translate it to right, and hide artist alpha
                createPlaylist.animate().alpha(100).setDuration(300);

                artistClicked=false;
            }
            else if(albumClicked){                                              //if playlist clicked while were on albums
                albumView.animate().alpha(0).setDuration(300);
                albumView.setVisibility(View.INVISIBLE);

                artistView.animate().translationXBy(1000).setDuration(300);

                playlistView.setVisibility(View.VISIBLE);
                playlistView.animate().translationXBy(1000).setDuration(300);
                playlistView.animate().alpha(100).setDuration(300);

                createPlaylist.setVisibility(View.VISIBLE);
                createPlaylist.animate().translationXBy(1000).setDuration(300);
                createPlaylist.animate().alpha(100).setDuration(300);

                albumClicked=false;
            }
            playlistClicked=true;
        }
        else if(v.getTag().toString().equals("1")){     //if artist clicked
            artistButton.setTextColor(Color.parseColor("#1DB954"));
            if(playlistClicked){                                               //if artist clicked while were on playlist
                playlistView.animate().translationXBy(-1000).setDuration(300);
                playlistView.animate().alpha(0).setDuration(300);
                playlistView.setVisibility(View.INVISIBLE);
                createPlaylist.animate().translationXBy(-1000).setDuration(300);
                createPlaylist.animate().alpha(0).setDuration(300);
                createPlaylist.setVisibility(View.INVISIBLE);

                artistView.setVisibility(View.VISIBLE);
                artistView.animate().alpha(100).setDuration(300);

                playlistClicked=false;
            }
            else if(albumClicked){                                              //if artist clicked while were on album
                albumView.animate().alpha(0).setDuration(300);
                albumView.setVisibility(View.INVISIBLE);

                artistView.setVisibility(View.VISIBLE);
                artistView.animate().alpha(100).setDuration(300);
                artistView.animate().translationXBy(1000).setDuration(300);

                albumClicked=false;
            }
            artistClicked=true;
        }
        else {                                           // if album clicked
            albumButton.setTextColor(Color.parseColor("#1DB954"));
            if(playlistClicked){                                                //if album clicked while were on playlist
                playlistView.animate().translationXBy(-1000).setDuration(300);
                playlistView.animate().alpha(0).setDuration(300);
                playlistView.setVisibility(View.INVISIBLE);
                createPlaylist.animate().translationXBy(-1000).setDuration(300);
                createPlaylist.animate().alpha(0).setDuration(300);
                createPlaylist.setVisibility(View.INVISIBLE);

                artistView.animate().translationXBy(-1000).setDuration(300);

                albumView.setVisibility(View.VISIBLE);
                albumView.animate().alpha(100).setDuration(300);

                playlistClicked=false;
            }
            else if(artistClicked){
                artistView.animate().translationXBy(-1000).setDuration(300);
                artistView.animate().alpha(0).setDuration(300);
                artistView.setVisibility(View.INVISIBLE);

                albumView.setVisibility(View.VISIBLE);
                albumView.animate().alpha(100).setDuration(300);

                artistClicked=false;
            }
            albumClicked=true;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if(resultCode==RESULT_OK){
                String newPlaylist = data.getStringExtra("playlist name");
                addItem(newPlaylist,0);
            }
            else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
