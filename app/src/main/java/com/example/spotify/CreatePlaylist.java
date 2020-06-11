package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreatePlaylist extends AppCompatActivity {

    private interface OKCallback {
        void onSuccess();

        void onFailure();
    }

    EditText playlistName;
    TextView set;
    TextView cancel;
    Intent resultIntent;
    SQLiteDatabase userInfoDatabase;
    String userToken;
    Spotify spotifyApi;
    public void onClick(View view){

        if(view.getTag().toString().equals("set")){
            createPlaylistApi(new OKCallback() {
                @Override
                public void onSuccess() {
                    String playlistNameStr = playlistName.getText().toString();
                    resultIntent.putExtra("playlist name", playlistNameStr);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }

                @Override
                public void onFailure() {
                    Toast.makeText(getApplicationContext(),"Error, Try again later.", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(view.getTag().toString().equals("cancel")){
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_playlist);
        set = findViewById(R.id.setButton);
        cancel = findViewById(R.id.cancelButton);
        playlistName = findViewById(R.id.playlistNameEditText);
        resultIntent = new Intent();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://560a8140-b758-4978-bd0a-dc372f8a99b0.mock.pstmn.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        spotifyApi = retrofit.create(Spotify.class);

        try {
            userInfoDatabase = this.openOrCreateDatabase("User", MODE_PRIVATE, null);
            Cursor c = userInfoDatabase.rawQuery("Select * FROM UserTokens WHERE token='UserToken'",null);

            int index=c.getColumnIndex("value");
            c.moveToFirst();
            userToken=  c.getString(index);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createPlaylistApi(final OKCallback okCallback){
        Playlists playlist = new Playlists();
        playlist.setPlaylistName(playlistName.getText().toString());
        Call<Playlists> call = spotifyApi.createPlaylist(userToken,playlist);
        Log.d("Error",userToken);
        Log.d("Error2",playlistName.getText().toString());
        call.enqueue(new Callback<Playlists>() {
            @Override
            public void onResponse(Call<Playlists> call, Response<Playlists> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Failed, Code:"+ response.code(), Toast.LENGTH_SHORT).show();

                    okCallback.onFailure();
                    return;
                }

                okCallback.onSuccess();
            }

            @Override
            public void onFailure(Call<Playlists> call, Throwable t) {
                Log.d("Error",t.getMessage());

                okCallback.onFailure();
                return;

            }
        });

    }
}

