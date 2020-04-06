package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SongsList extends AppCompatActivity {

    private static final String TAG = "Song List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_songs_list);
        String playlistName = intent.getStringExtra("image_name");
        String playlistImage = intent.getStringExtra("image_url");
        setHeadlines(playlistName,playlistImage);
        initImageBitmaps();
    }

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://lh3.googleusercontent.com/proxy/qdsgtJ3L6KiK6osKY3IUEgUSP9lkp4ErzVG5H7GirZbzrhktcfJoChIGC5bP2jT1jLyD9yfW5_BIVFYbTio3PvoHRS2FhUrDs_J1ZelZI8IiOa7F_tFUydRbrzUYdbX7wRGEOBpeYp8Hc_eloecOWA");
        mNames.add("party favor");

        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.songsView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls,true,false);
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
}
