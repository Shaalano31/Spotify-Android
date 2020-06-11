package com.example.spotify;

import java.io.File;
import java.util.ArrayList;

public class Method {
    public static ArrayList<File> arrayList = new ArrayList<>();

    public static void loadDirectoryFiles(File directory) {
        File[] fileList = directory.listFiles();

        if(fileList != null && fileList.length>0) {

            for(int i=0; i<fileList.length; i++) {

                if(fileList[i].isDirectory()) {
                    loadDirectoryFiles(fileList[i]);
                }
                else {
                    String name = fileList[i].getName().toLowerCase();

                    if(name.endsWith("mp3")){
                        //add to arraylist
                        arrayList.add(fileList[i]);
                        break;
                    }
                }
            }
        }
    }
}
