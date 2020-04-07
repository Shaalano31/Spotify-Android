package com.example.spotify;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchGridViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private String[] genreNames;
    private int[] colorNames;

    SearchGridViewAdapter(Context c, String[] genreNames, int[] colorNames) {
        context = c;
        this.genreNames = genreNames;
        this.colorNames = colorNames;
    }

    @Override
    public int getCount() {
        return genreNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.genre_item, null);
        }

        TextView genreTextView = convertView.findViewById(R.id.genreTextView);
        ImageView genreImageView = convertView.findViewById(R.id.imageView);
        genreTextView.setText(genreNames[position]);
        genreImageView.setImageResource(colorNames[position]);

        genreImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("info", genreNames[position]);
            }
        });


        return convertView;
    }
}
