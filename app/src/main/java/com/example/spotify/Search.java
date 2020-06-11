package com.example.spotify;

import android.provider.MediaStore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Search {

    @SerializedName("Artists")
    @Expose
    private ArrayList<Artists> artistsList;

    @SerializedName("Albums")
    @Expose
    private ArrayList<Albums> albumsList;

    @SerializedName("Tracks")
    @Expose
    private ArrayList<Tracks> tracksList;

    @SerializedName("Playlists")
    @Expose
    private ArrayList<Playlists> playlistsList;

    @SerializedName("Users")
    @Expose
    private ArrayList<Users> usersList;

    public ArrayList<Artists> getArtistsList() {
        return artistsList;
    }

    public void setArtistsList(ArrayList<Artists> artistsList) {
        this.artistsList = artistsList;
    }

    public ArrayList<Albums> getAlbumsList() {
        return albumsList;
    }

    public void setAlbumsList(ArrayList<Albums> albumsList) {
        this.albumsList = albumsList;
    }

    public ArrayList<Tracks> getTracksList() {
        return tracksList;
    }

    public void setTracksList(ArrayList<Tracks> tracksList) {
        this.tracksList = tracksList;
    }

    public ArrayList<Playlists> getPlaylistsList() {
        return playlistsList;
    }

    public void setPlaylistsList(ArrayList<Playlists> playlistsList) {
        this.playlistsList = playlistsList;
    }

    public ArrayList<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(ArrayList<Users> usersList) {
        this.usersList = usersList;
    }
}
