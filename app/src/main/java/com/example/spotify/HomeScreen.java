package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import static com.example.spotify.App.CHANNEL_2_ID;
import static com.example.spotify.App.CHANNEL_3_ID;

public class HomeScreen extends AppCompatActivity {


    /////////////////////////////////////////////////////////variables for static home screen
    RecyclerView  recyclerView;
    RecyclerAdapterHome homeAdapter;
    private ArrayList<ArrayList<String>> names = new ArrayList<ArrayList<String>>() ;
    private ArrayList<ArrayList<String>> picUrls= new ArrayList<ArrayList<String>>() ;
    private ArrayList<String> Titles = new ArrayList<String>() ;
    private NotificationManagerCompat notificationManager;
    TableLayout tab;////////*******************
    ArrayList<TableRow> trlist;   ////////*******************
//////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        String usertoken="HZAHLHAHY_USERTOKEN_GBSUGSUG";
        notificationManager= NotificationManagerCompat.from(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tab = (TableLayout)findViewById(R.id.tab);////////*******************
        trlist= new ArrayList<TableRow>(); ////////*******************
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("FCMAPP", "Token is "+token);
/////////////////////////////////////////////////////////////////////////STATIC HOME SCREEN
        Titles.add("Made for you ");
        Titles.add("Popular tracks ");
        Titles.add("Popular songs");
        Titles.add("Popular Artist ");
        Titles.add("Top genres ");

       int i = 0;
       while (i<5)
       { initImageBitmaps(i);
           i++;
       }
       initRecyclerView();
       ////////////////////////////////////////////////////End of the code STATIC HOME SCREEN

        NotificationWelcomeApi();////////*******************
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));////////*******************

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
            trlist.add(tr);

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


    ///////////////////////////////////////////////////////////////functions ofstatic home screen
    @Override
    public void onBackPressed() {
        Log.i("Info", "Back pressed");
        moveTaskToBack(true);
    }

    private void initImageBitmaps(int i)

    {

        ArrayList<String> tempName = new ArrayList<String>() ;
        ArrayList<String> tempUrl = new ArrayList<String>() ;

         if(i==0)
         {
             tempUrl.add("https://static.billboard.com/files/media/Solastalgia-missy-higgins-album-art-2018-billboard-1240-1024x1024.jpg");
             tempName.add(" Amazing Pop");

             tempUrl.add("https://static.billboard.com/files/media/ariana-grande-sweetner-album-art-2018-billboard-1240-1024x1024.jpg");
             tempName.add("chill");

             tempUrl.add("https://static.billboard.com/files/media/kacey-musgraves-golden-hour-album-2018-billboard-embed-1024x1024.jpg");
             tempName.add("Have fun ");

             tempUrl.add("https://static.billboard.com/files/media/janelle-monae-dirty-computer-album-2018-billboard-embed-1024x1024.jpg");
             tempName.add("Our best selection");

             tempUrl.add("https://static.billboard.com/files/media/Bad-Religion-Age-of-Unreason-album-art-2019-billboard-1240-1024x1024.jpg");
             tempName.add("Swag mood");

             tempUrl.add("https://static.billboard.com/files/media/Taylor-Swift-Lover-album-art-2019-billboard-1240-1024x1024.jpg");
             tempName.add("Fav pop");
         }

        if(i==1)
        {
            tempUrl.add("https://static.billboard.com/files/media/Bad-Religion-Age-of-Unreason-album-art-2019-billboard-1240-1024x1024.jpg");
            tempName.add("Swag mood");


            tempUrl.add("https://static.billboard.com/files/media/kacey-musgraves-golden-hour-album-2018-billboard-embed-1024x1024.jpg");
            tempName.add("Have fun ");

            tempUrl.add("https://static.billboard.com/files/media/Solastalgia-missy-higgins-album-art-2018-billboard-1240-1024x1024.jpg");
            tempName.add(" Amazing Pop");

            tempUrl.add("https://static.billboard.com/files/media/janelle-monae-dirty-computer-album-2018-billboard-embed-1024x1024.jpg");
            tempName.add("Our best selection");

            tempUrl.add("https://static.billboard.com/files/media/ariana-grande-sweetner-album-art-2018-billboard-1240-1024x1024.jpg");
            tempName.add("chill");


            tempUrl.add("https://static.billboard.com/files/media/Taylor-Swift-Lover-album-art-2019-billboard-1240-1024x1024.jpg");
            tempName.add("Fav pop");
        }
        if(i==2)
        {

            tempUrl.add("https://static.billboard.com/files/media/Taylor-Swift-Lover-album-art-2019-billboard-1240-1024x1024.jpg");
            tempName.add("Fav pop");

            tempUrl.add("https://static.billboard.com/files/media/kacey-musgraves-golden-hour-album-2018-billboard-embed-1024x1024.jpg");
            tempName.add("Have fun ");

            tempUrl.add("https://static.billboard.com/files/media/Bad-Religion-Age-of-Unreason-album-art-2019-billboard-1240-1024x1024.jpg");
            tempName.add("Swag mood");


            tempUrl.add("https://static.billboard.com/files/media/Solastalgia-missy-higgins-album-art-2018-billboard-1240-1024x1024.jpg");
            tempName.add(" Amazing Pop");

            tempUrl.add("https://static.billboard.com/files/media/janelle-monae-dirty-computer-album-2018-billboard-embed-1024x1024.jpg");
            tempName.add("Our best selection");

            tempUrl.add("https://static.billboard.com/files/media/ariana-grande-sweetner-album-art-2018-billboard-1240-1024x1024.jpg");
            tempName.add("chill");



        }
        if(i==3)
        {
            tempUrl.add("https://static.billboard.com/files/media/janelle-monae-dirty-computer-album-2018-billboard-embed-1024x1024.jpg");
            tempName.add("Our best selection");

            tempUrl.add("https://static.billboard.com/files/media/Solastalgia-missy-higgins-album-art-2018-billboard-1240-1024x1024.jpg");
            tempName.add(" Amazing Pop");

            tempUrl.add("https://static.billboard.com/files/media/Bad-Religion-Age-of-Unreason-album-art-2019-billboard-1240-1024x1024.jpg");
            tempName.add("Swag mood");

            tempUrl.add("https://static.billboard.com/files/media/Taylor-Swift-Lover-album-art-2019-billboard-1240-1024x1024.jpg");
            tempName.add("Fav pop");

            tempUrl.add("https://static.billboard.com/files/media/kacey-musgraves-golden-hour-album-2018-billboard-embed-1024x1024.jpg");
            tempName.add("Have fun ");

            tempUrl.add("https://static.billboard.com/files/media/ariana-grande-sweetner-album-art-2018-billboard-1240-1024x1024.jpg");
            tempName.add("chill");
        }
        if(i==4)
        {
            tempUrl.add("https://static.billboard.com/files/media/kacey-musgraves-golden-hour-album-2018-billboard-embed-1024x1024.jpg");
            tempName.add("Have fun ");

            tempUrl.add("https://static.billboard.com/files/media/ariana-grande-sweetner-album-art-2018-billboard-1240-1024x1024.jpg");
            tempName.add("chill");

            tempUrl.add("https://static.billboard.com/files/media/janelle-monae-dirty-computer-album-2018-billboard-embed-1024x1024.jpg");
            tempName.add("Our best selection");

            tempUrl.add("https://static.billboard.com/files/media/Solastalgia-missy-higgins-album-art-2018-billboard-1240-1024x1024.jpg");
            tempName.add(" Amazing Pop");

            tempUrl.add("https://static.billboard.com/files/media/Bad-Religion-Age-of-Unreason-album-art-2019-billboard-1240-1024x1024.jpg");
            tempName.add("Swag mood");

            tempUrl.add("https://static.billboard.com/files/media/Taylor-Swift-Lover-album-art-2019-billboard-1240-1024x1024.jpg");
            tempName.add("Fav pop");

        }


        picUrls.add(tempUrl);
         names.add(tempName);
    }

    public  void initRecyclerView()
    {
        LinearLayoutManager LayoutManager =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.theMainRV);
        homeAdapter= new RecyclerAdapterHome(Titles,names, picUrls,HomeScreen.this);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(LayoutManager);
    }
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   End of functions static home screen
    public void libraryClick(View view)
    {
        Intent intent = new Intent(getApplicationContext(), Playlist.class);
        startActivity(intent);

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void NotificationLogClick(View view)
{
   Intent intent = new Intent(getApplicationContext(), NotificationListener.class);
      //////////////////////////////// put tab here and add ton intends
   ///  intent.putExtra(" ArrayList<TableRow>", trlist);
    ////////////////////////////////
    startActivity(intent);
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public  void NotificationUserActionApi(View view)
    {
        String usertoken="HZAHLHAHY_USERTOKEN_GBSUGSUG";
        final String[] Result = new String[1];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://825c1451-4886-40aa-be4b-358a1bb44bbb.mock.pstmn.io/PushNot2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Spotify spotify = retrofit.create(Spotify.class);

        Call<Notifications> call= spotify.getPushnotification2(usertoken);
        call.enqueue(new Callback<Notifications>() {
            @Override
            public void onResponse(Call<Notifications> call, Response<Notifications> response) {
                if (!response.isSuccessful())

                {
                    Result[0] = String.valueOf(response.code());
                    Toast.makeText(getApplicationContext(),"Failed,Notification:"+ response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Notifications notification2= new Notifications();
                notification2 = response.body();

                String Body_Notifi =notification2.getBody();
                String Image_Notifi=notification2.getImage();
                String title_Notifi =notification2.getTitle();
                String UserType__Notifi =notification2.getUserType();

                // customizing the notification should be done here
                sendOnActions (notification2);
                ////////////////////////////////

                Log.d("recieved ",notification2.getBody()+"+"+notification2.getImage()+"+"+notification2.getTitle()+"+"+notification2.getUserType());
            }
            @Override
            public void onFailure(Call<Notifications> call, Throwable t) {
                String errorMessage =t.getMessage();
            }
        });
    }
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public  void NotificationShareApi(View view)
    {
        String usertoken="HZAHLHAHY_USERTOKEN_GBSUGSUG";

        final String[] Result = new String[1];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://825c1451-4886-40aa-be4b-358a1bb44bbb.mock.pstmn.io/ShareSong/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Spotify spotify = retrofit.create(Spotify.class);
        Call<Tracks> call= spotify.getSongLink(usertoken);
        call.enqueue(new Callback<Tracks>() {
            @Override
            public void onResponse(Call<Tracks> call, Response<Tracks> response) {
                if (!response.isSuccessful())

                {
                    Result[0] = String.valueOf(response.code());
                    Toast.makeText(getApplicationContext(),"Failed,Notification:"+ response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

               Tracks song ;
               song = response.body();

                String song_link = song.getSongLink();
                String song_Name=song.getTrackName();
                String artist_name = song.getArtistName();
                Log.d("recieved ", song.getSongLink()+"+"+song.getTrackName()+"+"+song.getArtistName());
                share(song_link);
                ////////////////////////////////


            }
            @Override
            public void onFailure(Call<Tracks> call, Throwable t) {
                String errorMessage =t.getMessage();
            }
        });
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////on click functions


    ///////////////////////////////////////////////1making the notification when user enter a certain activity
    public void sendOnWelcome( Notifications notification1)
    {
        String title =  notification1.getBody();
        String message = notification1.getTitle();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_3_ID)
                .setSmallIcon(R.drawable.preiumium_grey)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(3, notification);


    }
    ///////////////////////////////2 making the notification when user clicks a certain button
    public void sendOnActions( Notifications notification2)
    {
        String title =  notification2.getBody();
        String message = notification2.getTitle();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.preiumium_grey)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        notificationManager.notify(2, notification);
    }
/////////////////////////////////////////////////////////////////////
    public void share(String link) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
       // String link = "https://www.youtube.com/watch?v=YQHsXMglC9A";
        i.putExtra(Intent.EXTRA_TEXT,link);
        i.setType("text/plain");
        i=Intent.createChooser(i,"Share by");
        startActivity(i);
    }

//////////////////////////////////////////////////////////////////////////3  making the notification when entering a certain activity from mock server
void NotificationWelcomeApi()
{
    String usertoken="HZAHLHAHY_USERTOKEN_GBSUGSUG";

    final String[] Result = new String[1];

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl( "https://825c1451-4886-40aa-be4b-358a1bb44bbb.mock.pstmn.io/PushNot1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Spotify spotify = retrofit.create(Spotify.class);

    Call<Notifications> call= spotify.getPushnotification1(usertoken);
    call.enqueue(new Callback<Notifications>() {
        @Override
        public void onResponse(Call<Notifications> call, Response<Notifications> response) {
            if (!response.isSuccessful()) {
                Result[0] = String.valueOf(response.code());
                Toast.makeText(getApplicationContext(),"Failed,Notification:"+ response.code(), Toast.LENGTH_SHORT).show();
                return;
            }
            Notifications notification1= new Notifications();

            notification1 = response.body();

            String Body_Notifi=notification1.getBody();
            String Image_Notifi=notification1.getImage();
            String title_Notifi=notification1.getTitle();
            String UserType__Notifi=notification1.getUserType();

            // customizing the notification should be done here
            sendOnWelcome(notification1);
            ////////////////////////////////

            Log.d("recieved ",notification1.getBody()+"+"+notification1.getImage()+"+"+notification1.getTitle()+"+"+notification1.getUserType());
        }
        @Override
        public void onFailure(Call<Notifications> call, Throwable t) {
            String errorMessage =t.getMessage();
        }
    });
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////4

}




