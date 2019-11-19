package com.appsmontreal.news.controller;

import com.appsmontreal.news.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsController {
    private List<News> news;

    public NewsController() {
        news = new ArrayList<>();
    }

    public void addNews(String id, String title, String url) {
        news.add(new News(id,title,url));
    }

    public News readNews(String id) {
        return null;
    }

    public List<News> readAllNews() {
        return null;
    }
}
