package com.example.spotify;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StorageSongsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    Spotify spotify;

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

    class FileLayoutHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView songName;

        public FileLayoutHolder(@NonNull View itemView) {
            super(itemView);

            songName = itemView.findViewById(R.id.songs_layout_textView);
        }

        @Override
        public void onClick(View v) {
            Log.i("TEST", "CLICK");
            // if cell clicked, send a request to upload the song

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://c9e439fa-e868-48e8-b329-06131b75737c.mock.pstmn.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            spotify = retrofit.create(Spotify.class);
            UploadingSong uploadingSong = new UploadingSong("1", "Hello", "Adele", "123");
            Call<UploadingSong> call = spotify.uploadSong(uploadingSong);

            call.enqueue(new Callback<UploadingSong>() {
                @Override
                public void onResponse(Call<UploadingSong> call, Response<UploadingSong> response) {
                    if (!response.isSuccessful()) {
                        Log.i("INFO", "request failed");
                        return;
                    }

                    UploadingSong userResponse = response.body();
                    Log.i("INFO", "success");
                    Log.i("INFO", userResponse.toString());
                }

                @Override
                public void onFailure(Call<UploadingSong> call, Throwable t) {
                    Log.i("ERROR", t.getMessage());
                }
            });
        }
    }
}
