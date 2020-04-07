package com.example.spotify;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    private boolean playlistImageState;     // 1: for using it as urls, 0: for stored images
    private boolean isList;                 //used to check if this RecylcerView is a list of songs or a song in a list
    private boolean isCircular;             //check if list is for artists, then their icons will be circular

    public RecyclerViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images, boolean state,boolean islist) {
        mImageNames = imageNames;
        mImages = images;
        mContext = context;
        playlistImageState=state;
        isList=islist;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        Log.d(TAG, "onBindViewHolder: called.");

        if (playlistImageState) {
            if(isCircular){
                Glide.with(mContext)
                        .asBitmap()
                        .load(mImages.get(position))
                        .into(holder.imageC);
            }
            else{

            Glide.with(mContext)
                    .asBitmap()
                    .load(mImages.get(position))
                    .into(holder.image);
            }
        }

        else
        {
            holder.image.setImageResource(Integer.parseInt(mImages.get(position)));
        }
        holder.imageName.setText(mImageNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isList){
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));
                //Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, SongsList.class);
                intent.putExtra("image_url", mImages.get(position));
                intent.putExtra("image_name", mImageNames.get(position));
                mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public void setCircular(boolean x){
        isCircular=x;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        ImageView imageFrame;
        CircleImageView imageC;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            if(isCircular){
                imageC=itemView.findViewById(R.id.circularImage);
                imageC.setVisibility(View.VISIBLE);
            }
            else {
                image = itemView.findViewById(R.id.appIconIV);
                imageFrame = itemView.findViewById(R.id.frameImage);
                imageFrame.setVisibility(View.VISIBLE);
                image.setVisibility(View.VISIBLE);
            }
            imageName = itemView.findViewById(R.id.aNametxt);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}















