package com.example.spotify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadingSong {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("trackName")
    @Expose
    private String trackName;

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("artistId")
    @Expose
    private String artistID;

    public UploadingSong(String id, String trackName, String artistName, String artistID) {
        this.id = id;
        this.trackName = trackName;
        this.artistName = artistName;
        this.artistID = artistID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }
}
