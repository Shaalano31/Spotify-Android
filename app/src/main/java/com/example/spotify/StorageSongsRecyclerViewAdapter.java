package com.example.spotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StorageSongsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    StorageSongsRecyclerViewAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_layout,parent, false);
        return new FileLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FileLayoutHolder)holder).songName.setText(Method.arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return Method.arrayList.size();
    }

    class FileLayoutHolder extends RecyclerView.ViewHolder {

        TextView songName;

        public FileLayoutHolder(@NonNull View itemView) {
            super(itemView);

            songName = itemView.findViewById(R.id.songs_layout_textView);
        }
    }
}
