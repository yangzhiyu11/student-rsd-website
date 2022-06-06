package com.rsd.mapper;

import com.rsd.bean.Diary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDiaryMapper {

    List<Diary> queryList();

    List<Diary> queryListByParam(Diary diary);

    List<Diary> queryListByIds(@Param("iArray") Integer[] iArray);

    Diary getById(Integer id);

    void insert(Diary diary);

    void insertList(@Param("diaryList") List<Diary> diaryList);

    void update(Diary diary);

    void updateForNull(Diary diary);

    void deleteById(Integer id);


}
