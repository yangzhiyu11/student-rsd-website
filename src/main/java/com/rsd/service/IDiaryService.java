package com.rsd.service;

import com.rsd.bean.Diary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDiaryService {

    List<Diary> queryList();

    List<Diary> queryListByParam(Diary diary);

    List<Diary> queryListByIds(Integer[] iArray);

    Diary getById(Integer id);

    void insert(Diary diary);

    void insertList(List<Diary> diaryList);

    void update(Diary diary);

    void updateForNull(Diary diary);

    void delete(Integer id);
}
