package com.example.spotify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import static com.example.spotify.App.CHANNEL_1_ID;

public class MyFireBaseMessagingService extends FirebaseMessagingService{

    @Override
    public Context getApplicationContext()
    {
        return super.getApplicationContext();
    }

    private static final String TAG = "FirebaseMessagingService";

    public MyFireBaseMessagingService() { }

    @Nullable

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();

        Log.d(TAG, "onMessageReceived: Message Received: \n" + "Title: " + title + "\n" + "Message: " + message);
        sendNotification(title,message);
    }

    private void sendNotification(String title,String messageBody)
    {

        Intent activityIntent = new Intent(this, HomeScreen.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);




        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.preiumium_grey)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(messageBody)
                        .setBigContentTitle(title))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setColor(Color.GREEN)
                .build();


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    @Override
    public void onDeletedMessages() { }

}





