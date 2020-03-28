package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        // requesting search list
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.alquran.cloud/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuranApi quranApi = retrofit.create(QuranApi.class);
        Call<Surah> call = quranApi.getSurah();

        call.enqueue(new Callback<Surah>() {
            @Override
            public void onResponse(Call<Surah> call, Response<Surah> response) {

                ArrayList<Data> dataList = response.body().getData();

                for (int i=0; i<dataList.size(); i++) {
                    Log.i("INFO", dataList.get(i).getName());
                    Log.i("NUMBER", "HI");
                }

            }

            @Override
            public void onFailure(Call<Surah> call, Throwable t) {
                Log.i("INFO", "Request Failed");
                Log.i("INFO", t.getMessage());
            }
        });

        //
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
