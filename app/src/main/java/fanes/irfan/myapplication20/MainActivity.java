package fanes.irfan.myapplication20;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.util.ArrayList;

import fanes.irfan.myapplication20.Adapter.ArtisAdapter;
import fanes.irfan.myapplication20.Adapter.TrackAdapter;
import fanes.irfan.myapplication20.Api.ApiInterfaces;
import fanes.irfan.myapplication20.DetailArtist.DetailArtis;
import fanes.irfan.myapplication20.TopArtistView.Artist;
import fanes.irfan.myapplication20.TopArtistView.TopArtistView;
import fanes.irfan.myapplication20.TrackByAlbum.TrackByAlbum;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    TextView tv_respond2;
    Button btn_search;
    ApiInterfaces ListApi;
    EditText edittext;
    String apikey = "f792dabde0320d3a2476dd86d6c1418b";
    RecyclerView recyclerview;
    Context kontek;
    String method = "artist.search";
    String artis;

    TextView top;
    //    TextView tv_bio;
//    TextView biodata;
    private ArrayList<String> artist = new ArrayList<String>();
    private ArrayList<String> gambar = new ArrayList<String>();
    private ArrayList<String> lis = new ArrayList<String>();
    private ArrayList<String> link = new ArrayList<String>();
  //  private String bio;
    //private ArrayList<String> bio = new ArrayList<String>();

    //private ArrayList<Integer> listener = new ArrayList<Integer>();

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  tv_respond2 = (TextView) findViewById(R.id.tv_respond2);

        btn_search = (Button) findViewById(R.id.btn_search);
        edittext = (EditText) findViewById(R.id.edittext);
        kontek = MainActivity.this;
        top = (TextView) findViewById(R.id.top);

        BufferedReader reader = null;
        Gson gson = new GsonBuilder()

                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

                .create();

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("https://ws.audioscrobbler.com")

                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();

        ListApi = retrofit.create(ApiInterfaces.class);
        Call<TopArtistView> call = ListApi.getListTopArtis("geo.gettopartists", "indonesia", apikey, "json");
        call.enqueue(new Callback<TopArtistView>() {
            @Override
            public void onResponse(Response<TopArtistView> response, Retrofit retrofit) {
                int status = response.code();
                Log.e("string", String.valueOf(status));


                artist.clear();
                gambar.clear();
                lis.clear();
                link.clear();

                for (Artist art : response.body().getTopartists().getArtist()) {
                    artist.add(art.getName());
                    gambar.add(art.getImage().get(4).getText());
                    lis.add(art.getListeners());
                    link.add(art.getUrl());


                }
                recyclerview = (RecyclerView) findViewById(R.id.vp_main);
                recyclerview.setHasFixedSize(true);
                recyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                recyclerview.setAdapter(new ArtisAdapter(kontek, artist, gambar, lis, link));
            }

            @Override
            public void onFailure(Throwable t) {
                tv_respond2.setText(String.valueOf(t));
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              artis = edittext.getText().toString();
                                              Call<fanes.irfan.myapplication20.TopSearch.SearchArtis> call = ListApi.getListArtis(method, artis, apikey, "json");
                                              call.enqueue(new Callback<fanes.irfan.myapplication20.TopSearch.SearchArtis>() {


                                                  @Override
                                                  public void onResponse(Response<fanes.irfan.myapplication20.TopSearch.SearchArtis> response, Retrofit retrofit) {
                                                      int status = response.code();
                                                      Log.e("string", String.valueOf(status));

                                                      top.setVisibility(View.GONE);
                                                      gambar.clear();
                                                      artist.clear();
                                                      lis.clear();
                                                      link.clear();
                                                      for (fanes.irfan.myapplication20.TopSearch.Artist art : response.body().getResults().getArtistmatches().getArtist()) {
                                                          artist.add(art.getName());
                                                          gambar.add(art.getImage().get(4).getText());
                                                          // biodata.add(art.getbio().getBytes().length);
                                                          lis.add(art.getListeners());
                                                          link.add(art.getUrl());


                                                      }
                                                      recyclerview = (RecyclerView) findViewById(R.id.vp_main);
                                                      recyclerview.setHasFixedSize(true);
                                                      recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this, 1, false));
                                                      recyclerview.setAdapter(new ArtisAdapter(kontek, artist, gambar, lis, link));
                                                  }

                                                  @Override
                                                  public void onFailure(Throwable t) {
                                                      tv_respond2.setText(String.valueOf(t));
                                                  }
                                              });

//                                              Call<fanes.irfan.myapplication20.DetailArtist.DetailArtis> call2 = ListApi.getDetailArtis("artist.getinfo", "indonesia", apikey, "json");
//                                              call2.enqueue(new Callback<DetailArtis>() {
//
//                                                  public void onResponse(Response<DetailArtis> response, Retrofit retrofit) {
//                                                      int status = response.code();
//                                                      Log.e("string", String.valueOf(status));
//
//
//                                                      artist.clear();
//                                                      gambar.clear();
//                                                      lis.clear();
//                                                      link.clear();
//                                                      Intent intent = getIntent();
//                                                      bio = intent.getStringExtra("bio");;
//
//
//
//                                                      recyclerview = (RecyclerView) findViewById(R.id.vp_main3);
//                                                      recyclerview.setHasFixedSize(true);
//                                                      recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
//                                                      recyclerview.setAdapter(new ArtisAdapter(MainActivity.this, response.body().getArtist().getBio());
//                                                  }
//                                                  @Override
//                                                  public void onFailure(Throwable t) {
//                                                      tv_respond2.setText(String.valueOf(t));
//                                                  }
//                                              });
//
//
                                          }
                                      }
        );
    }
}