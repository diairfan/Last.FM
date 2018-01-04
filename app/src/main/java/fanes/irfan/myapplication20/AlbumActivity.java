package fanes.irfan.myapplication20;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.util.ArrayList;
import fanes.irfan.myapplication20.Adapter.AlbumAdapter;
import fanes.irfan.myapplication20.Adapter.ArtisAdapter;
import fanes.irfan.myapplication20.Api.ApiInterfaces;
import fanes.irfan.myapplication20.TopAlbum.Album;
import fanes.irfan.myapplication20.TopAlbum.TopAlbum;
import fanes.irfan.myapplication20.TopArtistView.Artist;
import fanes.irfan.myapplication20.TopArtistView.TopArtistView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class AlbumActivity extends AppCompatActivity {

    String gbr;
    ApiInterfaces ListApi;
    TextView tv_respond3;
    String apikey = "f792dabde0320d3a2476dd86d6c1418b";
    ImageView iv_gbr;
    String method = "artist.gettopalbums";
    String identitas;
    Context kontek;
    RecyclerView recyclerview;
    private ArrayList<String> album = new ArrayList<String>();
    private ArrayList<String> gambar2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        Intent intent = getIntent();
        identitas = intent.getStringExtra("name");

        setTitle(identitas);

        gbr = intent.getStringExtra("image");

        BufferedReader reader = null;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

                .create();

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("https://ws.audioscrobbler.com")

                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
        ListApi = retrofit.create(ApiInterfaces.class);
//        Call<TopArtistView> call = ListApi.getListTopArtis("geo.gettopartists", "indonesia", apikey, "json");
//        call.enqueue(new Callback<TopArtistView>() {
//            @Override
//            public void onResponse(Response<TopArtistView> response, Retrofit retrofit) {
//                int status = response.code();
//                Log.e("string", String.valueOf(status));
//
//
//                album.clear();
//                gambar2.clear();
//                //   listener.clear();
//                for (Album album__ : response.body().getTopartists().getArtist()) {
//                    album.add(album__.getName());
//                    gambar2.add(art.getImage().get(4).getText());
//
//                }
//                recyclerview = (RecyclerView) findViewById(R.id.vp_main2);
//                recyclerview.setHasFixedSize(true);
//                recyclerview.setLayoutManager(new GridLayoutManager(AlbumActivity.this, 2));
//                recyclerview.setAdapter(new ArtisAdapter(kontek, album, gambar2));
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                tv_respond3.setText(String.valueOf(t));
//            }
//        });
        Call<TopAlbum> call2 = ListApi.getListAlbum(method, identitas, apikey, "json");
        call2.enqueue(new Callback<TopAlbum>() {
            @Override
            public void onResponse(Response<TopAlbum> response, Retrofit retrofit) {

                for (Album album_ : response.body().getTopalbums().getAlbum()) {

                    album.add(album_.getName());
                    gambar2.add(album_.getImage().get(2).getText());

                }
                recyclerview = (RecyclerView) findViewById(R.id.vp_main2);
                recyclerview.setHasFixedSize(true);
                recyclerview.setLayoutManager(new LinearLayoutManager(AlbumActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerview.setAdapter(new AlbumAdapter(AlbumActivity.this,identitas, album, gambar2));
            }


            @Override
            public void onFailure(Throwable t) {

            }
        });


    }
}

