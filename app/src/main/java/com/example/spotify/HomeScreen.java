package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

       while (i<3)
       {
           initImageBitmaps(i);
           i++;
       }


        initRecyclerView();
    }

    public void goToSearch (View view) {

        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);

    private void initImageBitmaps(int i)
    {

       ArrayList<String> tempName = new ArrayList<String>() ;
        ArrayList<String> tempUrl = new ArrayList<String>() ;

        tempUrl.add("https://i.redd.it/j6myfqglup501.jpg");
        tempName.add("Rocky Mountain National Park");


        tempUrl.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        tempName.add("Mahahual");

        tempUrl.add("https://i.redd.it/k98uzl68eh501.jpg");
        tempName.add("Frozen Lake");


        tempUrl.add("https://i.redd.it/glin0nwndo501.jpg");
        tempName.add("White Sands Desert");

        tempUrl.add("https://i.redd.it/obx4zydshg601.jpg");
        tempName.add("Austrailia");

        tempUrl.add("https://i.imgur.com/ZcLLrkY.jpg");
        tempName.add("Washington");

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

