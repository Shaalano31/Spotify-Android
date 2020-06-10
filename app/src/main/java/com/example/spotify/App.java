package com.example.spotify;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    public static final String CHANNEL_3_ID = "channel3";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }
    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Welcome to Spotify",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Welcoming messages");
            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Latest News of Spotify ",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("Latest News of Spotify");

            NotificationChannel channel3 = new NotificationChannel(
                    CHANNEL_3_ID,
                    "Based on your action ",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel3.setDescription("Based on user action ");

            NotificationManager manager = getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);
        }
    }
}
