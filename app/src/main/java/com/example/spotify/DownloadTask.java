package com.example.spotify;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DownloadTask extends AsyncTask<String, Void, String> {


    ArrayList<String> listdata = new ArrayList<String>();

    public DownloadTask(ArrayList<String> list){
        listdata=list;
    }

    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;


        try {
            url = new URL(urls[0]);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1) {

                char current = (char) data;

                result += current;

                data = reader.read();

            }

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {

            JSONObject jsonObject = new JSONObject(result);

            String accounts = jsonObject.getString("accounts");


            JSONArray arr = new JSONArray(accounts);

            for (int i = 0; i < arr.length(); i++) {

                listdata.add(arr.getString(i));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}