package com.rsd.day3;

import com.rsd.bean.SysRole;
import com.rsd.mapper.ISysRoleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("mybatis.xml");//加载主配置文件

            SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = factoryBuilder.build(in);

            SqlSession session = factory.openSession();

            ISysRoleMapper sysRoleMapper = session.getMapper(ISysRoleMapper.class);
            List<SysRole> list = sysRoleMapper.queryList();
            System.out.println(list);

            SysRole sysRole = sysRoleMapper.getById(1);
            System.out.println(sysRole);
//            List<SysRole> list = session.selectList("com.rsd.mapper.ISysRoleMapper.queryList");
//            System.out.println(list);

            session.close();
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
}
