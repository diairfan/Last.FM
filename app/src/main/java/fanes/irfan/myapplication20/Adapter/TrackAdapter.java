package fanes.irfan.myapplication20.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import fanes.irfan.myapplication20.R;
import fanes.irfan.myapplication20.TrackByAlbum.Track;

/**
 * Created by Van Noval on 1/2/2018.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {


    int menit = 0;
    int detik = 0;
    private Context konteks;
    private List<Track> track = null;
    private String gambar3 = "";

    public TrackAdapter(Context konteks, List<Track> track, String gambar3) {
        this.konteks = konteks;
        this.track = track;
        this.gambar3 = gambar3;
    }

    public TrackAdapter.TrackViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(konteks).inflate(R.layout.item_track, parent, false);
        TrackAdapter.TrackViewHolder avh = new TrackAdapter.TrackViewHolder(view);
        return avh;
    }

    @Override
    public void onBindViewHolder(final TrackViewHolder holder, final int position) {


        holder.Track.setText("Track : "+track.get(position).getName());
        menit = Integer.parseInt(track.get(position).getDuration())/60;
        detik = Integer.parseInt(track.get(position).getDuration())%60;
        holder.tv_detail.setText("Duration : "+String.valueOf(menit)+":"+(String.valueOf(detik)));
        holder.tv_name_artis.setText("Artist : "+track.get(position).getArtist().getName());
        holder.no.setText(String.valueOf(position + 1));
        Glide.with(konteks).load(gambar3).into(holder.gambar3);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(track.get(position).getUrl()));
                konteks.startActivity(intent);
            }
        });

    }

    public int getItemCount() {
        return track.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {

        TextView tv_detail;
        LinearLayout trackLayout;
        TextView tv_name_artis;
        TextView Track;
        TextView no;
        ImageView gambar3;


        public TrackViewHolder(View itemView) {
            super(itemView);


            trackLayout = (LinearLayout) itemView.findViewById(R.id.track_layout);
            //DetileMovie = (TextView) itemView.findViewById(R.id.detail);
            // Image = (ImageView) itemView.findViewById(R.id.backbg2);
            Track = (TextView) itemView.findViewById(R.id.tv_track);
            tv_detail = (TextView) itemView.findViewById(R.id.tv_durasi);
            tv_name_artis = (TextView) itemView.findViewById(R.id.tv_name_artis);
            no = (TextView) itemView.findViewById(R.id.no);
            gambar3 = (ImageView) itemView.findViewById(R.id.iv_track);
        }

    }

}
