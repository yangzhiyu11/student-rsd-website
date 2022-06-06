package com.rsd.day4;

import com.rsd.bean.Diary;
import com.rsd.mapper.IDiaryMapper;
import com.rsd.service.IDiaryService;
import com.rsd.service.impl.DiaryServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        DiaryServiceImpl diaryService = new DiaryServiceImpl();

//        Diary diary = new Diary();
//        diary.setTitle("dddd");
//        diary.setContent("44444");
//        diary.setCreateTime(new Date());
//        diary.setUserId(3);
//
//        diaryService.insert(diary);


//        Diary diary = new Diary();
//        diary.setId(4);
//        diary.setTitle("dddd");
//        diary.setContent("444444");
//        diary.setCreateTime(new Date());
//        diary.setUserId(3);
//
//        diaryService.update(diary);

//        Diary diary = new Diary();
//        diary.setId(4);
//        diary.setCreateTime(new Date());
//
//        diaryService.update(diary);

//        List<Diary> list = diaryService.queryList();
//        System.out.println(list);

//        Diary diary = diaryService.getById(2);
//        System.out.println(diary);

//        Diary diary = new Diary();
//        diary.setTitle("d");
//        List<Diary> list = diaryService.queryListByParam(diary);
//        System.out.println(list);


        List<Diary> list = diaryService.queryListByIds(new Integer[]{1,2});
        System.out.println(list);

        Diary diary1 = new Diary();
        diary1.setTitle("5555");

        Diary diary2 = new Diary();
        diary2.setTitle("5555");

        Diary diary3 = new Diary();
        diary3.setTitle("5555");


        List<Diary> diaryList = new ArrayList<>();
        diaryList.add(diary1);
        diaryList.add(diary2);
        diaryList.add(diary3);

        diaryService.insertList(diaryList);

//        diaryService.delete(3);

    }
}
