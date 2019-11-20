package com.appsmontreal.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.appsmontreal.news.view.MainActivity;

public class NewsActivity extends AppCompatActivity {
    private String newsUrl;
    private WebView newsWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        newsUrl = getIntent().getStringExtra(MainActivity.NEWS_URL);
        newsWebView = findViewById(R.id.newsWebView);
        newsWebView.getSettings().setJavaScriptEnabled(true);
        newsWebView.setWebViewClient(new WebViewClient());
        newsWebView.loadUrl(newsUrl);
    }
}
