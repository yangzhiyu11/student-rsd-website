package com.rsd.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {

    private static SqlSessionFactory factory;

    private MybatisUtil() {

    }

    private static SqlSessionFactory getFactory() {
        if (factory == null) {
            InputStream in = null;
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
        }
        return factory;
    }

    public static SqlSession getSession() {
        SqlSessionFactory sessionFactory = getFactory();
        SqlSession session = sessionFactory.openSession();
        return session;
    }
}
