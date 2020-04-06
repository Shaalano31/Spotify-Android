package com.example.spotify;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerAdapterHome extends RecyclerView.Adapter<RecyclerAdapterHome.ViewHolder> {


        private static final String TAG = "RecyclerAdapterrHome";


        private ArrayList<fragmentHomeDisplay> fragments;
        private Context context;

        // data is passed into the constructor

        public RecyclerAdapterHome( ArrayList<fragmentHomeDisplay> fragments, Context context) {

            this.fragments = fragments;
            this.context = context;
        }




        // private ItemClickListener mClickListener;

        // inflates the row layout from xml when needed
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            LayoutInflater mInflater = LayoutInflater.from(context);
            View view = mInflater.inflate(R.layout.home_playlists_display, parent, false);
            return new com.example.spotify.RecyclerAdapterHome.ViewHolder(view);
        }


        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(com.example.spotify.RecyclerAdapterHome.ViewHolder holder, int position) {

             //

            LinearLayoutManager LayoutManager =new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            holder.recycle.setAdapter(this);
            holder.recycle.setLayoutManager(LayoutManager);

            holder.playlistTitle.setText(fragments.get(position).titleOfPlaylist);  // setting the names of the playlist



        }

        // total number of rows
        @Override
        public int getItemCount() {
            return fragments.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            /// decleraing contents of home playlist of one item
            //ImageView playlistPhoto;  // fragments
            TextView playlistTitle;
            RecyclerView recycle  ;

            ViewHolder(View itemView) {
                super(itemView);
                recycle = itemView.findViewById(R.id.myhomerecyclerView);
                playlistTitle = itemView.findViewById(R.id.playlistName);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Log.i("done clicked", "VIEW is clicked ");
            }
        }

    }


