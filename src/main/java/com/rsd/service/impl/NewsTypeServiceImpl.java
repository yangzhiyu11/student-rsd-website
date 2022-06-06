package com.rsd.service.impl;

import com.rsd.bean.NewsType;
import com.rsd.mapper.INewsTypeMapper;
import com.rsd.service.INewsTypeService;
import com.rsd.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class NewsTypeServiceImpl implements INewsTypeService {
    @Override
    public List<NewsType> queryList() {
        SqlSession session = MybatisUtil.getSession();
        INewsTypeMapper iNewsTypeMapper = session.getMapper(INewsTypeMapper.class);
        List<NewsType> list = iNewsTypeMapper.queryList();

        session.close();
        return list;
    }
}
