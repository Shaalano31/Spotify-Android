package com.example.spotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
//////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        String usertoken="HZAHLHAHY_USERTOKEN_GBSUGSUG";
        notificationManager= NotificationManagerCompat.from(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

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

        NotificationWelcomeApi();


    }

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
//////////////////////////////////////////////////////////////////////////////////////////////
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




