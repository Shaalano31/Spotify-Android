package com.example.spotify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Albums {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("albumName")
    @Expose
    private String albumName;

    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("artistid")
    @Expose
    private String artistID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
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
