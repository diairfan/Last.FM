
package fanes.irfan.myapplication20.TopSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchArtis {

    @SerializedName("results")
    @Expose
    private Results results;
    @SerializedName("artist")
    @Expose
    private List<Artist> artist = null;
    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }
}
