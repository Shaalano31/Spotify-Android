package com.example.spotify;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SearchRecyclerAdapter extends  RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> implements Filterable {


    List<String> queryResultName;
    List<String> queryResultDetails;
    List<String> queryResultImage;
    Context context;

    public SearchRecyclerAdapter(List<String> queryResultName, List<String> queryResultDetails, List<String> queryResultImage, Context context) {
        this.queryResultName = queryResultName;
        this.queryResultDetails = queryResultDetails;
        this.queryResultImage = queryResultImage;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.i("Info", "PRINTING");
        holder.name.setText(queryResultName.get(position));
        holder.details.setText(queryResultDetails.get(position));


        ImageDownloader imageDownloader = new ImageDownloader();
        Bitmap playListImg = null;
        try {
            playListImg= imageDownloader.execute(queryResultImage.get(position)).get(); // array URLs
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        holder.imageView.setImageBitmap(playListImg); //imageview
    }

    @Override
    public int getItemCount() {

        return queryResultName.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView name, details;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.homeImageView);
            name = itemView.findViewById(R.id.name);
            details = itemView.findViewById(R.id.details);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i("Info", queryResultName.get(getAdapterPosition()));

            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Notification");
            alert.setMessage("The screens will be added in the next update");
            alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.create().show();
        }
    }
}
