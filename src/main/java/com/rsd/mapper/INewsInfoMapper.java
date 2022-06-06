package com.rsd.mapper;

import com.rsd.bean.NewsInfo;

import java.util.List;

public interface INewsInfoMapper {
    List<NewsInfo> queryList();

    NewsInfo getById(Integer id);

    void insert(NewsInfo newsInfo);

    void update(NewsInfo newsInfo);

    void deleteById(Integer id);
}
