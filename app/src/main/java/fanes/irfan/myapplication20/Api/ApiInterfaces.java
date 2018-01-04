package fanes.irfan.myapplication20.Api;

import android.icu.lang.UCharacter;

import fanes.irfan.myapplication20.DetailArtist.DetailArtis;
import fanes.irfan.myapplication20.TopAlbum.TopAlbum;
import fanes.irfan.myapplication20.TopArtistView.TopArtistView;
import fanes.irfan.myapplication20.TopSearch.SearchArtis;
import fanes.irfan.myapplication20.TrackByAlbum.TrackByAlbum;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Van Noval on 12/30/2017.
 */

public interface ApiInterfaces {
    @GET("/2.0/")
    Call<TopAlbum> getListAlbum(@Query("method") String method,
                                @Query("artist") String artis ,
                                @Query("api_key") String apikey,
                                @Query("format") String format );


    @GET("/2.0")
    Call<SearchArtis> getListArtis
            (@Query("method") String method,
             @Query("artist") String artist,
             @Query("api_key") String apikey,
             @Query("format") String format );


    @GET("/2.0")
    Call<TrackByAlbum> getListTrack
            (@Query("method") String method,
             @Query("artist") String artist,
             @Query("album") String album,
             @Query("api_key") String apikey,
             @Query("format") String format );

    @GET("/2.0")
    Call<TopArtistView> getListTopArtis
            (@Query("method") String method,
             @Query("country") String country,
             @Query("api_key") String apikey,
             @Query("format") String format );


    @GET("/2.0")
    Call<DetailArtis> getDetailArtis
            (@Query("method") String method,
             @Query("artist") String artist,
             @Query("api_key") String apikey,
             @Query("format") String format );

}



