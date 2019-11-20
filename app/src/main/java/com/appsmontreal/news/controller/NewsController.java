package com.appsmontreal.news.controller;

import com.appsmontreal.news.api.DownloadTask;
import com.appsmontreal.news.model.IModelListener;
import com.appsmontreal.news.model.News;

import java.util.List;

public class NewsController {
    private DownloadTask task;
    private IModelListener modelListener;
    private List<News> news;

    public NewsController(String url) {
        task = new DownloadTask();
        try {
            task.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readAllNews(IModelListener modelListener) {
//        task.getAllNews(modelListener);
        task.setModelListener(modelListener);
    }



}
