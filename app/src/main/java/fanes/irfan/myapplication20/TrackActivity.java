package fanes.irfan.myapplication20;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.util.ArrayList;

import fanes.irfan.myapplication20.Adapter.TrackAdapter;
import fanes.irfan.myapplication20.Api.ApiInterfaces;
import fanes.irfan.myapplication20.TrackByAlbum.TrackByAlbum;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class TrackActivity extends AppCompatActivity {
    String gbr2;
    ApiInterfaces ListApi;

    String apikey = "f792dabde0320d3a2476dd86d6c1418b";
    ImageView iv_gbr2;
    String method = "album.getinfo";
    String nama;
    RecyclerView recyclerview;
    Context kontek;
    private String artis;
    private String album;
    private ArrayList<String> gambar3 = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        Intent intent = getIntent();
        artis = intent.getStringExtra("artis");

        album = intent.getStringExtra("album");
        setTitle(artis+" - "+album);

        gambar3 = intent.getStringArrayListExtra("gambar3");
//
        kontek = TrackActivity.this;
        kontek = getApplicationContext();

        BufferedReader reader = null;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

                .create();

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("https://ws.audioscrobbler.com")

                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
        ListApi = retrofit.create(ApiInterfaces.class);
        Call<TrackByAlbum> call = ListApi.getListTrack(method, artis, album, apikey, "json");
        call.enqueue(new Callback<TrackByAlbum>() {
            @Override
            public void onResponse(Response<TrackByAlbum> response, Retrofit retrofit) {
                recyclerview = (RecyclerView) findViewById(R.id.vp_main3);
                recyclerview.setHasFixedSize(true);
                recyclerview.setLayoutManager(new LinearLayoutManager(TrackActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerview.setAdapter(new TrackAdapter(TrackActivity.this, response.body().getAlbum().getTracks().getTrack(),response.body().getAlbum().getImage().get(3).getText()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }
}
