
package fanes.irfan.myapplication20.DetailArtist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailArtis {

    @SerializedName("artist")
    @Expose
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
