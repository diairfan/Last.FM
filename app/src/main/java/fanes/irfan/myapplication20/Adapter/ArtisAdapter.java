package fanes.irfan.myapplication20.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fanes.irfan.myapplication20.AlbumActivity;
import fanes.irfan.myapplication20.R;

public class ArtisAdapter extends RecyclerView.Adapter<ArtisAdapter.ArtisViewHolder> {

    private ArrayList<String> artist = new ArrayList<String>();
    private ArrayList<String> gambar = new ArrayList<String>();
 //   private ArrayList<String> detail = new ArrayList<String>();
    private ArrayList<String> lis = new ArrayList<String>();
   // private String bio ;
    private Context konteks;
    private ArrayList<String> link = new ArrayList<String>();


    public ArtisAdapter(Context context, ArrayList<String> Artist,
                        ArrayList<String> image, ArrayList<String> listener,
                        ArrayList<String> url) {

        konteks = context;
        artist = Artist;
        gambar = image;
        lis = listener;
        link = url;


    }

    public ArtisAdapter.ArtisViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(konteks).inflate(R.layout.item_artis, parent, false);
        ArtisViewHolder mvh = new ArtisViewHolder(view);
        return mvh;
    }

    public void onBindViewHolder(ArtisViewHolder holder, final int position) {
        holder.Artist.setText(artist.get(position));
        holder.lis.setText("Listener : " + lis.get(position));
        holder.link.setText(link.get(position));
        holder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(link.get(position)));
                konteks.startActivity(intent);
            }
        });
       // holder.bio.setText(bio.g
        Glide.with(konteks).load(gambar.get(position)).into(holder.Image);
        holder.Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(konteks, AlbumActivity.class);
                intent.putExtra("name", artist.get(position));
                intent.putExtra("image", gambar.get(position));
                intent.putExtra("listener", lis.get(position));
                //intent.putExtra("url",link.get(position));
                //               intent.putExtra("bio", biodata.getText());
              //  intent.putExtra("bio", bio.charAt(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                konteks.startActivity(intent);

            }
        });

    }

    public int getItemCount() {
        return artist.size();
    }

    public class ArtisViewHolder extends RecyclerView.ViewHolder {

//        LinearLayout artisLayout;

        TextView Artist;
        ImageView Image;

        TextView lis;
        TextView link;
      //  TextView bio;


        public ArtisViewHolder(View itemView) {
            super(itemView);


//            artisLayout = (LinearLayout) itemView.findViewById(R.id.artis_layout);

            Image = (ImageView) itemView.findViewById(R.id.backbg);
            Artist = (TextView) itemView.findViewById(R.id.artis);
            lis = (TextView) itemView.findViewById(R.id.lis);
            link = (TextView) itemView.findViewById(R.id.link);
        //    bio = (TextView) itemView.findViewById(R.id.tv_detail);
        }
    }
}
