package com.example.spotify;
/*
import java.util.ArrayList;


import android.app.Notification;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Spotify {


import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.content.Context.MODE_PRIVATE;

public interface Spotify
{


   // @Headers("x-auth: eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s")
 //   @GET("Search")
  //  Call<Search> getSearch(@Query("word") String word);



// all Requests should be here

@GET("/playlists/me")              // to be changed for every special playlist     ;
Call<Search>  getPlaylists();



    @GET("Artists/{id}")
    Call<Artists> getArtist(@Path("id") String id );

    @Headers("x-auth: eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s")
    @POST("users/login")
    Call<Void> userLogIn(@Body Users user);



    @POST("playlists")
    Call<Playlists> createPlaylist(@Header("x-auth") String txt,
                                   @Body Playlists playlist);

    @GET("/artists")
    Call<List<Artists>> getSeveralArtists(@Header("x-auth") String txt);




}
*/ // final assement
import android.app.Notification;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Spotify {
  @GET("/PushNot1")
  Call<Notifications>  getPushnotification1(@Header("x-auth") String txt);

  @GET("/PushNot2")
  Call<Notifications>  getPushnotification2(@Header("x-auth") String txt);

  @GET("/ShareSong")
  Call<Tracks>  getSongLink(@Header("x-auth") String txt);




  @GET("Artists/{id}")
  Call<Artists> getArtist(@Path("id") String id );

  @Headers("x-auth: eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s")
  @POST("users/login")
  Call<Void> userLogIn(@Body Users user);



  @POST("playlists")
  Call<Playlists> createPlaylist(@Header("x-auth") String txt,
                                 @Body Playlists playlist);

  @GET("/artists")
  Call<List<Artists>> getSeveralArtists(@Header("x-auth") String txt);

}
