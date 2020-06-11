package com.example.spotify;

public class Constants {
    public interface ACTION{
        public static String MAIN_ACTION="com.example.audioplayer.main";
        public static String PREV_ACTION="com.example.audioplayer.prev";
        public static String PLAY_ACTION="com.example.audioplayer.play";
        public static String NEXT_ACTION="com.example.audioplayer.next";
        public static String STARTFOREGROUND_ACTION="com.example.audioplayer.startforeground";
        public static String STOPFOREGROUND_ACTION="com.example.audioplayer.stopforeground";
    }

    public  interface NOTIFICATION_ID{
        public static int FOREGROUND_SERVICE = 101;
    }
}
