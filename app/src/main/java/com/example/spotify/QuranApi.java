package com.example.spotify;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuranApi {

    @GET("surah")
    Call<Surah> getSurah();
}
