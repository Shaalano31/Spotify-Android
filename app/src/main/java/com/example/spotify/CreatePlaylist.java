package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreatePlaylist extends AppCompatActivity {

    EditText playlistName;
    TextView set;
    TextView cancel;
    Intent resultIntent;
    public void onClick(View view){

        if(view.getTag().toString().equals("set")){
            String playlistNameStr = playlistName.getText().toString();
            resultIntent.putExtra("playlist name",playlistNameStr);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
        else if(view.getTag().toString().equals("cancel")){
            setResult(RESULT_CANCELED);
            finish();
        }
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_playlist);
        set = findViewById(R.id.setButton);
        cancel = findViewById(R.id.cancelButton);
        playlistName = findViewById(R.id.playlistNameEditText);
        resultIntent = new Intent();

    }
}
