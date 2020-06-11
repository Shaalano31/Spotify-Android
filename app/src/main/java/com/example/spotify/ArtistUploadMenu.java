package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.File;

public class ArtistUploadMenu extends AppCompatActivity {

    /*ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> adapter;
    String songNames[];*/
    private File storage;
    private String[] allPath;

    private RecyclerView recyclerView;
    private StorageSongsRecyclerViewAdapter recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_upload_menu);
        allPath = StorageUtil.getStorageDirectories(this);

        for (String path: allPath) {
            storage = new File(path);
            com.example.spotify.Method.loadDirectoryFiles(storage);
        }

        recyclerView = findViewById(R.id.storageRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recycleViewAdapter = new StorageSongsRecyclerViewAdapter(ArtistUploadMenu.this);

        recyclerView.setAdapter(recycleViewAdapter);
    }
}
