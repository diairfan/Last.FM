package fanes.irfan.myapplication20.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fanes.irfan.myapplication20.AlbumActivity;
import fanes.irfan.myapplication20.R;
import fanes.irfan.myapplication20.TopAlbum.Album;
import fanes.irfan.myapplication20.TrackActivity;

/**
 * Created by Van Noval on 12/30/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    // private ArrayList<String> detail = new ArrayList<String>();
//    private ArrayList<String> nama = new ArrayList<String>();

    private Context konteks;
    private String artis;
    private ArrayList<String> album = new ArrayList<String>();
    private ArrayList<String> gambar2 = new ArrayList<String>();

    public AlbumAdapter(Context konteks, String artis, ArrayList<String> album, ArrayList<String> gambar2) {
        this.konteks = konteks;
        this.artis = artis;
        this.album = album;
        this.gambar2 = gambar2;
    }

    public AlbumAdapter.AlbumViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(konteks).inflate(R.layout.item_album, parent, false);
        AlbumAdapter.AlbumViewHolder avh = new AlbumAdapter.AlbumViewHolder(view);
        return avh;
    }
    public void onBindViewHolder(AlbumAdapter.AlbumViewHolder holder, final int position) {
        holder.Album.setText(album.get(position));
        //holder.data.setText(movies.get(position));
        //holder.listeners.setText(detail.get(position));

        //holder.rating.setText(movies.get(position).getVoteAverage().toString());

        Glide.with(konteks).load(gambar2.get(position)).into(holder.Image);
        holder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(konteks, TrackActivity.class);
                intent.putExtra("album", album.get(position));
                intent.putExtra("artis", artis);
                konteks.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return album.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        LinearLayout albumLayout;

        TextView Album;
        ImageView Image;


        public AlbumViewHolder(View itemView) {
            super(itemView);


            albumLayout = (LinearLayout) itemView.findViewById(R.id.album_layout);
            //DetileMovie = (TextView) itemView.findViewById(R.id.detail);
            Image = (ImageView) itemView.findViewById(R.id.backbg2);
            Album = (TextView) itemView.findViewById(R.id.album);
        }

    }

}
