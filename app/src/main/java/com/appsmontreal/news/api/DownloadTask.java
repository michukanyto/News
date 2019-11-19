package com.appsmontreal.news.api;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask <String, Void, String> {

    private String result;
    private URL url;
    private HttpURLConnection urlConnection;


    @Override
    protected String doInBackground(String... urls) {
        result = "";
        urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();

            while (data != -1) {
                char current = (char) data;
                result += current;
                data = inputStreamReader.read();
                Log.i("URL CONTENT ====> ", result);
                return result;

            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
