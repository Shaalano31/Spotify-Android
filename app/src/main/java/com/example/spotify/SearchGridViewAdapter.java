package com.example.spotify;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static com.facebook.FacebookSdk.getApplicationContext;

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
                if(position==0){
                    Intent i = new Intent(context,SongsList.class);
                    i.putExtra("image_name",genreNames[position]);
                    i.putExtra("image_url","https://oneroofstore.com/pub/media/catalog/product/cache/b66ee365b24b097094b341542fc7aa50/g/r/grx40_tfb_4h_02.png");
                    context.startActivity(i);
                }
                else if(position==1)
                {
                    Intent i = new Intent(context,SongsList.class);
                    i.putExtra("image_name",genreNames[position]);
                    i.putExtra("image_url","https://png.pngtree.com/png-clipart/20191128/ourlarge/pngtree-hiphop-hip-hop-color-gradient-theme-effect-creative-font-png-image_86092.jpg");
                    context.startActivity(i);
                }
                else {
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
        });


        return convertView;
    }
}
