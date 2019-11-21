package com.appsmontreal.news.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.appsmontreal.news.NewsActivity;
import com.appsmontreal.news.R;
import com.appsmontreal.news.controller.NewsController;
import com.appsmontreal.news.model.IModelListener;
import com.appsmontreal.news.model.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IModelListener{

    public static final String SOURCE = "https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty";
    public static final String NEWS_URL = "NEWS URL";
    private NewsController newsController;
    private ListView listview;
    private ArrayList<News> allNews;
    private ArrayList<String> newsTitles;
    private ArrayAdapter<String> arrayAdapter;
    private Intent intent;
    private EditText filterEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, NewsActivity.class);
        listview = findViewById(R.id.listView);
        newsController = new NewsController(SOURCE);
        newsController.readAllNews(this);//Initializing modelListener in DownloadTask

//        newsController.readAllNews(new IModelListener() {
//            @Override
//            public void onGetAllNews(List<News> news) {
//                allNews = (ArrayList<News>) news;
//                getTitles();
//                arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, allNews);
//                listenUpUserChoice();
//            }
//        });

    }

    private void prepareFilter() {
        filterEditText = findViewById(R.id.filterEditText);
        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                (MainActivity.this).arrayAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void getTitles() {
        newsTitles =  new ArrayList<>();
        for (News n : allNews) {
            newsTitles.add(n.getTitle());
        }
        Log.i("titles ======>" , newsTitles.toString());
    }


    private void listenUpUserChoice() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra(NEWS_URL, allNews.get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onGetAllNews(ArrayList<News> news) {
        allNews = news;
        getTitles();
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, newsTitles);
        listenUpUserChoice();
        listview.setAdapter(arrayAdapter);
        prepareFilter();

    }
}
