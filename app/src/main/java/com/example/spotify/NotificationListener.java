package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class NotificationListener extends AppCompatActivity {
    TableLayout tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_listener);
        tab = (TableLayout)findViewById(R.id.tab);

        /////////extracting  intent extra here
        /*
        Intent oldIntent  = getIntent();  // getting the object we created in the last activity
       ArrayList<TableRow> R =(ArrayList<TableRow>)  oldIntent.getParcelableExtra("ArrayList<TableRow>") ;
        int i = 0;
        while (i<R.size())
        { tab.addView(R.get(i));
            i++;
        }
         */
        /////////////////////////////////




        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
    }

    private BroadcastReceiver onNotice= new BroadcastReceiver()
    {

        @Override
        public void onReceive(Context context, Intent intent) {

            String pack = intent.getStringExtra("package");
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");



            TableRow tr = new TableRow(getApplicationContext());
            tr.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            TextView textview = new TextView(getApplicationContext());
            textview.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,1.0f));
            textview.setTextSize(20);
            textview.setTextColor(Color.parseColor("#0B0719"));
            ///////////////////////////////////
            String html =pack +"<br><b>" + title + " : </b>" + text;
            textview.setText(fromHtml(html));
            ////////////////////////////////////////
            tr.addView(textview);
            tab.addView(tr);




        }

        @SuppressWarnings("deprecation")
        public Spanned fromHtml(String html){
            if(html == null){
                // return an empty spannable if the html is null
                return new SpannableString("");
            }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
                // we are using this flag to give a consistent behaviour
                return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
            } else {
                return Html.fromHtml(html);
            }
        }

    };





}







