package com.example.spotify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracks {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("trackName")
    @Expose
    private String trackName;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("artistId")
    @Expose
    private String artistID;

    @SerializedName("songLink")
    @Expose
    private String songLink;

    public String getSongLink() {
        return songLink;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
