package com.appsmontreal.news.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appsmontreal.news.NewsActivity;
import com.appsmontreal.news.R;
import com.appsmontreal.news.controller.NewsController;
import com.appsmontreal.news.model.News;

import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    public static final String SOURCE = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
    public static final String NEWS_URL = "NEWS URL";
    private NewsController newsController;
    private ListView listview;
    private ArrayList<News> news;
    private ArrayList<String> newsTitles;
    private ArrayAdapter<News> arrayAdapter;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, NewsActivity.class);
        listview = findViewById(R.id.listView);
        newsController = new NewsController(SOURCE);
        news = newsController.readAllNews();
        getTitles();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, news);
        listenUpUserChoice();
    }


    private void getTitles() {
        newsTitles =  new ArrayList<>();
        for (News n : news) {
            newsTitles.add(n.getTitle());
        }

    }


    private void listenUpUserChoice() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra(NEWS_URL,news.get(position).getUrl());
                startActivity(intent);
            }
        });
    }

}
