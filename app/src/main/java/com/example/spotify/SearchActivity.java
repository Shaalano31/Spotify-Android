package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchRecyclerAdapter searchRecyclerAdapter;

    List<String> queryResultName;
    List<String> queryResultDetails;
    List<ImageView> queryResultImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        queryResultName = new ArrayList<>();
        queryResultDetails = new ArrayList<>();
        queryResultImage = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        searchRecyclerAdapter = new SearchRecyclerAdapter(queryResultName);

        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(searchRecyclerAdapter);
        //recyclerView.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryResultName.clear();
                searchRequest(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void goToHome (View view){

        Intent intent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Log.i("Info", "Back pressed");
        moveTaskToBack(true);
    }



    public void searchRequest(String newText) {
        // requesting search list
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.14.190.202:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Spotify spotify = retrofit.create(Spotify.class);
        Call<Search> call = spotify.getSearch(newText);

        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {

                if (!response.isSuccessful()) {
                    Log.i("Info", "RIP");
                    return;
                }

                ArrayList<Artists> artistsList = response.body().getArtistsList();
                ArrayList<Albums> albumsList = response.body().getAlbumsList();
                ArrayList<Tracks> tracksList = response.body().getTracksList();
                ArrayList<Playlists> playlistsList = response.body().getPlaylistsList();
                ArrayList<Users> usersList = response.body().getUsersList();

                for (int i=0; i<artistsList.size(); i++) {
                    queryResultName.add(artistsList.get(i).getName());
                }

                for (int i=0; i<albumsList.size(); i++) {
                    queryResultName.add(albumsList.get(i).getAlbumName());
                }

                for (int i=0; i<tracksList.size(); i++) {
                    queryResultName.add(tracksList.get(i).getTrackName());
                }

                for (int i=0; i<playlistsList.size(); i++) {
                    queryResultName.add(playlistsList.get(i).getPlaylistName());
                }

                for (int i=0; i<usersList.size(); i++) {
                    queryResultName.add(usersList.get(i).getUserName());
                }

                searchRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                Log.i("INFO", "Request Failed");
                Log.i("INFO", t.getMessage());
            }
        });

        //
    }
}
