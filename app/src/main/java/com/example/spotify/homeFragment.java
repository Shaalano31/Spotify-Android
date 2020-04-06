package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class homeFragment extends AppCompatActivity {

    RecyclerView  recyclerView;
   RecyclerAdapter recyclerAdapter;

    private ArrayList<String> names = new ArrayList<String>() ;
    private ArrayList<String> picUrls= new ArrayList<String>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment);



        initImageBitmaps();

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

        LinearLayoutManager LayoutManager =new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView = findViewById(R.id.myrecyclerView);
        recyclerAdapter= new RecyclerAdapter( names, picUrls,this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(LayoutManager);
    }
}
