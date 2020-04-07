package com.example.spotify;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Spotify {
/*
    @Headers("x-auth: eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s")
    @GET("Search")
    Call<Search> getSearch(@Query("word") String word);

 */

// all Requests should be here

@GET("/playlists/me")              // to be changed for every special playlist     ;
Call<Search>  getPlaylists();


}
