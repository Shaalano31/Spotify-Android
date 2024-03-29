package com.example.spotify;


import android.database.sqlite.SQLiteDatabase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.content.Context.MODE_PRIVATE;

public interface Spotify {

  @Headers("x-auth: eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s")
  @GET("Search")
  Call<Search> getSearch(@Query("word") String word);


  @GET("/EnterHomescreen")
  Call<Notifications>  getPushnotification1(@Header("x-auth") String txt);

  @GET("/FollowArtist")
  Call<Notifications>  getPushnotification2(@Header("x-auth") String txt);

  @GET("/LikeAPlaylist")
  Call<Notifications>  getPushnotification3(@Header("x-auth") String txt);

  @GET("/AddAsong")
  Call<Notifications>  getPushnotification4(@Header("x-auth") String txt);




  @GET("/ShareSong")
  Call<Tracks>  getSongLink(@Header("x-auth") String txt);


  @GET("Artists/{id}")
  Call<Artists> getArtist(@Path("id") String id );


  @Headers("x-auth: eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s")
  @POST("users/login")
  Call<Void> userLogIn(@Body Users user);

  @POST("playlists")
  Call<Playlists> createPlaylist(@Header ("x-auth") String txt,
                                 @Body Playlists playlist);

  @GET("artists")
  Call<List<Artists>> getSeveralArtists(@Header("x-auth") String txt);




    @Headers("x-auth: eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s")
    @POST("/artist/signup")
    Call<Users> artistSignUp(@Body Users users);

    @Headers("x-auth: eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s")
    @POST("/artist/login")
    Call<Users> artistLogIn(@Body Users user);

    @Headers("x-auth: eyJhbGciOiJIUzI1NiJ9.QXV0aG9yaXphdGlvbmZvcmZyb250ZW5k.xEs1jjiOlwnDr4BbIvnqdphOmQTpkuUlTgJbAtQM68s")
    @POST("/artist/signup")
    Call<UploadingSong> uploadSong(@Body UploadingSong uploadingSong);



}
