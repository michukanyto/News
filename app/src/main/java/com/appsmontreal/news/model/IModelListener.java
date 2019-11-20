package com.appsmontreal.news.model;

import java.util.ArrayList;
import java.util.List;

public interface IModelListener {
    void onGetAllNews(ArrayList<News> news);
}
