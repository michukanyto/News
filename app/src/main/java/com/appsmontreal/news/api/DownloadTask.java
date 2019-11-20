package com.appsmontreal.news.api;

import android.os.AsyncTask;
import android.util.Log;

import com.appsmontreal.news.model.IModelListener;
import com.appsmontreal.news.model.News;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadTask extends AsyncTask <String, Void, String> {

    private String result;
    String articleInfo;
    private URL url;
    private HttpURLConnection urlConnection;
    private ArrayList<News> news;
    private IModelListener modelListener;

    public void setModelListener(IModelListener modelListener) {
        this.modelListener = modelListener;
    }


    @Override
    protected String doInBackground(String... urls) {
        news = new ArrayList();
        urlConnection = null;
        result = "";
        articleInfo = "";
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
            }
//            result = makeConnection(url);

            Log.i("URL CONTENT ====> ", result);

            JSONArray jsonArray = new JSONArray(result);
            int numberOfItem = 20;

            if (jsonArray.length() < 20) {
                numberOfItem = jsonArray.length();
            }

            for (int i= 0; i < numberOfItem; i++) {
                String articleId = jsonArray.getString(i);
                url = new URL("https://hacker-news.firebaseio.com/v0/item/" + articleId + ".json?print=pretty");

                urlConnection = (HttpURLConnection) url.openConnection();
                inputStream = urlConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);

                data = inputStreamReader.read();

                String articleInfo = "";

                while (data != -1) {
                    char current = (char) data;
                    articleInfo += current;
                    data = inputStreamReader.read();
                }

//                articleInfo = makeConnection(url);
//                Log.i("ArticleInfo  ====> ", articleInfo);
                JSONObject jsonObject = new JSONObject(articleInfo);

                if (!jsonObject.isNull("id") && !jsonObject.isNull("title") && !jsonObject.isNull("url")) {
                    String id = jsonObject.getString("id");
                    String title = jsonObject.getString("title");
                    String url = jsonObject.getString("url");
                    news.add(new News(id,title,url));
                    Log.i("URL  ====> ", url + " ===> " + title);
                }
            }
            modelListener.onGetAllNews(news);//////////prepare all news
            return result;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private String makeConnection(URL url) {
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();

            while (data != -1) {
                char current = (char) data;
                result += current;
                data = inputStreamReader.read();
            }

            return result;

        } catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }


    public void getAllNews(IModelListener modelListener) {
        modelListener.onGetAllNews(this.news);

    }

}
