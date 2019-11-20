package com.appsmontreal.news.controller;

import com.appsmontreal.news.api.DownloadTask;
import com.appsmontreal.news.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsController {
    private DownloadTask task;

    public NewsController(String url) {
        task = new DownloadTask();
        try {
            task.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<News> readAllNews() {

        return task.getNews();
    }

    public void addNews(String id, String title, String url) {

    }

    public News readNews(String id) {
        return null;
    }
}
