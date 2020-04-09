package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
  
    RecyclerView  recyclerView;
    RecyclerAdapterHome homeAdapter;

    private ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>() ;
    private ArrayList<ArrayList<String>> picUrls= new ArrayList<ArrayList<String>>() ;
    private ArrayList<String> Titles = new ArrayList<String>() ;

    ImageView settings ;
    userInfo user;

    public void libraryClick(View view){
        Intent intent = new Intent(getApplicationContext(), Playlist.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


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
}

