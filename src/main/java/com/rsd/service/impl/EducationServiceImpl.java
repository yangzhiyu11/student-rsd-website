package com.rsd.service.impl;

import com.rsd.bean.Education;
import com.rsd.mapper.IEducationMapper;
import com.rsd.service.IEducationService;
import com.rsd.util.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EducationServiceImpl implements IEducationService {
    @Override
    public List<Education> queryList() {
        SqlSessionFactory factory = getSqlSessionFactory();
        SqlSession session = MybatisUtil.getSession();
        IEducationMapper educationMapper = session.getMapper(IEducationMapper.class);

        List<Education> list = educationMapper.queryList();
//        System.out.println(list);
//        session.clearCache();//清除缓存第一种

        //insert,delete,update的时候都清除缓存

        //flushCache="true"在SQL标签上加flushCache="true"第三种清除缓存

//        List<Education> list2 = educationMapper.queryList();
//        System.out.println(list2);

        session.close();

        SqlSession session2 = MybatisUtil.getSession();
        IEducationMapper educationMapper2 = session2.getMapper(IEducationMapper.class);

        List<Education> list2 = educationMapper2.queryList();
        session2.close();
        return list;
    }

    private SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory factory = null;
        InputStream in = null;
        SqlSession session = null;
        try {
            in = Resources.getResourceAsStream("mybatis.xml");

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(in);
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
        return factory;
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
