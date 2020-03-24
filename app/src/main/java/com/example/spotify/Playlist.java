package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Playlist extends AppCompatActivity {

    int[] images = {R.drawable.createplaylistplus,R.drawable.likes};
    String[] playlistVersion = {"Create Playlist","Likes"};
    String[] artistVersion = {"Billie Eilish","Justin"};
    String[] albumVersion = {"V","J"};
    String[] versionNumber = {"Discover Yourself", "24 Songs"};
    ListView playlistView;
    ListView artistView;
    ListView albumView;

    ListAdapter playistAdapter;
    ListAdapter artistAdapter;
    ListAdapter albumAdapter;

    Button playlistButton;
    Button artistButton;
    Button albumButton;
    boolean playlistClicked=true, artistClicked=false, albumClicked=false;      //used to check last button clicked from playlist, artist, albums
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        playlistView = findViewById(R.id.playlistListView);
        artistView = findViewById(R.id.artistListView);
        albumView = findViewById(R.id.albumListView);

        playistAdapter = new ListAdapter(Playlist.this, playlistVersion, versionNumber, images);
        playlistView.setAdapter(playistAdapter);

        playlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(Playlist.this, playlistVersion[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();

            }
        });

        artistAdapter = new ListAdapter(Playlist.this, artistVersion, versionNumber, images);
        artistView.setAdapter(artistAdapter);

        artistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(Playlist.this, artistVersion[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();

            }
        });

        albumAdapter = new ListAdapter(Playlist.this, albumVersion, versionNumber, images);
        albumView.setAdapter(albumAdapter);

        albumView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(Playlist.this, albumVersion[i]+" "+versionNumber[i], Toast.LENGTH_SHORT).show();

            }
        });

        playlistButton = findViewById(R.id.playlistButton);
        artistButton = findViewById(R.id.artistButton);
        albumButton = findViewById(R.id.albumButton);
        playlistButton.setTextColor(Color.parseColor("#1DB954"));
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

                artistClicked=false;
            }
            else if(albumClicked){                                              //if playlist clicked while were on albums
                albumView.animate().alpha(0).setDuration(300);
                albumView.setVisibility(View.INVISIBLE);

                artistView.animate().translationXBy(1000).setDuration(300);

                playlistView.setVisibility(View.VISIBLE);
                playlistView.animate().translationXBy(1000).setDuration(300);
                playlistView.animate().alpha(100).setDuration(300);

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

}
