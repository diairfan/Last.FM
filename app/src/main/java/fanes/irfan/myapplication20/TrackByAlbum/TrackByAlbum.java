
package fanes.irfan.myapplication20.TrackByAlbum;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackByAlbum {

    @SerializedName("album")
    @Expose
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
