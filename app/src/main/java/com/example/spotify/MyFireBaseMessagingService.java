package com.example.spotify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);




        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.preiumium_grey)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    @Override
    public void onDeletedMessages() { }

}





