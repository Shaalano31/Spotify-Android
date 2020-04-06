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

    ImageView settings ;
    userInfo user;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    private ArrayList<String> names  ;
    private ArrayList<String> picUrls;
    private  ArrayList<fragmentHomeDisplay> fragmentHomeDisplaysLists;
    RecyclerAdapterHome recyclerAdapterHome  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


         names = new ArrayList<String>() ;
         picUrls= new ArrayList<String>() ;

         fragmentHomeDisplaysLists= new ArrayList<fragmentHomeDisplay>();



        initImageBitmaps(); //// getting parameters of images and urls


        fragmentHomeDisplay f1 = new fragmentHomeDisplay(recyclerView,recyclerAdapter,"Made for you ");
        fragmentHomeDisplay f2 = new fragmentHomeDisplay(recyclerView,recyclerAdapter,"top geners");
        fragmentHomeDisplay f3 = new fragmentHomeDisplay(recyclerView,recyclerAdapter,"popular Artist");
        fragmentHomeDisplay f4 = new fragmentHomeDisplay(recyclerView,recyclerAdapter,"popular new ");
        fragmentHomeDisplay f5 = new fragmentHomeDisplay(recyclerView,recyclerAdapter,"popular ablbum ");

        fragmentHomeDisplaysLists.add(f1);
        fragmentHomeDisplaysLists.add(f2);
        fragmentHomeDisplaysLists.add(f3);
        fragmentHomeDisplaysLists.add(f4);
        fragmentHomeDisplaysLists.add(f5);


        recyclerAdapterHome =new RecyclerAdapterHome( fragmentHomeDisplaysLists, this);




        /////////////////////////////////////////////

        settings= findViewById(R.id.settingsImageview);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, Settings.class);
                Intent oldIntent  = getIntent();  // getting the object we created in the last activity
                user= (userInfo)oldIntent.getParcelableExtra("userinfo") ;
                i.putExtra("userinfo", user);
                Log.i("done clicked"," " + user.password + " " + user.username + " " + user.email+ " " + user.gender +  " " +user.dateOfBirth);
                startActivity(i);
            }
        });



    }

    private void initImageBitmaps()
    {


        picUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        names.add("Havasu Falls");

        picUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        names.add("Trondheim");

        picUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        names.add("Portugal");

        picUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        names.add("Rocky Mountain National Park");


        picUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        names.add("Mahahual");

        picUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        names.add("Frozen Lake");


        picUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        names.add("White Sands Desert");

        picUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        names.add("Austrailia");

        picUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        names.add("Washington");

        initRecyclerView();






    }

    public  void initRecyclerView()
    {

        LinearLayoutManager LayoutManager =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.homeRecyclerViewer);
        recyclerAdapter= new RecyclerAdapter( names, picUrls, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(LayoutManager);
    }



}
