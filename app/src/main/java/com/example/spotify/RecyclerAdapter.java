package com.example.spotify;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView ;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private static final String TAG = "RecyclerAdapter";

    private ArrayList<String> names ;
    private ArrayList<String> picUrls ;
    private HomeScreen context;

    // data is passed into the constructor


    public RecyclerAdapter(ArrayList<String> names, ArrayList<String> picUrls, HomeScreen context) {
        this.names = names;
        this.picUrls = picUrls;
        this.context = context;
    }



   // private ItemClickListener mClickListener;

    // inflates the row layout from xml when needed
     @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


         LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.home_playlists, parent, false);
        return new ViewHolder(view);
    }




    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder. playlistTitle.setText(names.get(position));  // setting the names of the playlist
        ImageDownloader imageDownloader = new ImageDownloader();
        Bitmap playListImg = null;
        try {
            playListImg= imageDownloader.execute(picUrls.get(position)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        holder.playlistPhoto.setImageBitmap(playListImg);

        holder.playlistPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// GO TO PLAYLIST ACtivity

                // pass the holder with the name and image
                Log.i("done clicked","picture is clicked ");


            }
        });
        // seting the image
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return names.size();
    }


    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /// decleraing contents of home playlist of one item
        ImageView playlistPhoto;
        TextView playlistTitle;


        ViewHolder(View itemView) {
            super(itemView);
            playlistPhoto = itemView.findViewById(R.id.playlisPic);
            playlistTitle = itemView.findViewById(R.id.playlistName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i("done clicked", "VIEW is clicked ");
        }

     /*      @Override
        public void onClick(View v) {

        }

     @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return names.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
   public interface ItemClickListener {
        void onItemClick(View view, int position);}*/
    }
}