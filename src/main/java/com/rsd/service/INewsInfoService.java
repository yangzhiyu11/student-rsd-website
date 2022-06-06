package com.rsd.service;

import com.rsd.bean.Diary;
import com.rsd.bean.NewsInfo;

import java.util.List;

public interface INewsInfoService {

    List<NewsInfo> queryList();

    NewsInfo getById(Integer id);

    void insert(NewsInfo newsInfo);

    void update(NewsInfo newsInfo);

    void deleteById(Integer id);
}
