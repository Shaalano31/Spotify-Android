package com.example.spotify;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter  extends  RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {


    List<String> queryResultName;
    List<String> queryResultNameFull;

    public RecyclerAdapter(List<String> queryResultName) {
        this.queryResultName = queryResultName;
        this.queryResultNameFull = new ArrayList<>(queryResultName);
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

        holder.name.setText(queryResultName.get(position));
        holder.details.setText(String.valueOf(position));
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

            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            details = itemView.findViewById(R.id.details);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.i("Info", queryResultName.get(getAdapterPosition()));
        }
    }
}
