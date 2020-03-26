package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    List<String> queryResultName;
    List<String> queryResultDetails;
    List<ImageView> queryResultImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        queryResultName = new ArrayList<>();
        queryResultDetails = new ArrayList<>();
        queryResultImage = new ArrayList<>();

        queryResultName.add("One");
        queryResultName.add("Two");
        queryResultName.add("Three");
        queryResultName.add("Four");
        queryResultName.add("Five");
        queryResultName.add("Six");
        queryResultName.add("Seven");
        queryResultName.add("Eight");
        queryResultName.add("Nine");
        queryResultName.add("Ten");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(queryResultName);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onBackPressed() {
        Log.i("Info", "Back pressed");
        moveTaskToBack(true);
    }
}
