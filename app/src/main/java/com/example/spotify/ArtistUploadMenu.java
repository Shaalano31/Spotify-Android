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
        /*Log.i("INFO", "One");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 111);
        } else {
            loadSong();
        }*/
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


    /*public void loadSong() {
        ContentResolver contentResolver= getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        Cursor songCursor = contentResolver.query(songUri, null, selection, null, null);
        Log.i("INFO", "Four");
        if(songCursor.moveToFirst()) {
            Toast.makeText(this, "First element found", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "27zan", Toast.LENGTH_SHORT).show();
        }

        if(songCursor != null) {
            Log.i("HI", "TESTING");
            while (songCursor.moveToNext()) {
                String url = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String author = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String title = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                Log.i("BYE", "SONGZZZZZ");
                Log.i("INFO",title + '\n' + author + "\n" + url );
                arrayList.add(title + '\n' + author + "\n" + url);
            }
            Log.i("SAD", "TESTING");
                //comment about testing failure

        }

        //adapter = new ArrayAdapter<String>(this, R.layout.songs_layout, arrayList);
        //listView.setAdapter(adapter);

        //listView.setOnItemClickListener();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            loadSong();
    }*/
}
