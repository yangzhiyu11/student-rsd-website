package com.rsd.service.impl;

import com.rsd.bean.Diary;
import com.rsd.mapper.IDiaryMapper;
import com.rsd.service.IDiaryService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DiaryServiceImpl implements IDiaryService {

    private IDiaryMapper diaryMapper;
    @Override
    public List<Diary> queryList() {
        SqlSession session = getSqlSession();
        IDiaryMapper diaryMapper = session.getMapper(IDiaryMapper.class);
        List<Diary> list = diaryMapper.queryList();

        session.close();
        return list;
    }

    @Override
    public List<Diary> queryListByParam(Diary diary) {
        SqlSession session = getSqlSession();
        IDiaryMapper diaryMapper = session.getMapper(IDiaryMapper.class);
        List<Diary> list = diaryMapper.queryListByParam(diary);

        session.close();
        return list;
    }


    @Override
    public List<Diary> queryListByIds(Integer[] iArray) {
        SqlSession session = getSqlSession();
        IDiaryMapper diaryMapper = session.getMapper(IDiaryMapper.class);
        List<Diary> list = diaryMapper.queryListByIds(iArray);

        session.close();
        return list;
    }

    @Override
    public Diary getById(Integer id) {
        Diary diary = null;
        SqlSession session = getSqlSession();
        IDiaryMapper diaryMapper = session.getMapper(IDiaryMapper.class);
        diary = diaryMapper.getById(id);

        session.close();
        return diary;
    }

    @Override
    public void insert(Diary diary) {
        SqlSession session = getSqlSession();
        diaryMapper = session.getMapper(IDiaryMapper.class);
        diaryMapper.insert(diary);

        session.commit();
        session.close();
    }

    @Override
    public void insertList(List<Diary> diaryList) {
        SqlSession session = getSqlSession();
        diaryMapper = session.getMapper(IDiaryMapper.class);
        diaryMapper.insertList(diaryList);

        session.commit();
        session.close();
    }

    @Override
    public void update(Diary diary) {
        SqlSession session = getSqlSession();
        IDiaryMapper diaryMapper = session.getMapper(IDiaryMapper.class);
        diaryMapper.update(diary);

        session.commit();
        session.close();
    }

    @Override
    public void updateForNull(Diary diary) {
        SqlSession session = getSqlSession();
        IDiaryMapper diaryMapper = session.getMapper(IDiaryMapper.class);
        diaryMapper.updateForNull(diary);

        session.commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        SqlSession session = getSqlSession();
        diaryMapper = session.getMapper(IDiaryMapper.class);
        diaryMapper.deleteById(id);

        session.commit();
        session.close();
    }

    private SqlSession getSqlSession() {
        InputStream in = null;
        SqlSession session = null;
        try {
            in = Resources.getResourceAsStream("mybatis.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            session = factory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return session;
    }

}
