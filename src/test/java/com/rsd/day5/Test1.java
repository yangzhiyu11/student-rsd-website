package com.rsd.day5;

import com.rsd.bean.NewsInfo;
import com.rsd.service.INewsInfoService;
import com.rsd.service.impl.NewsInfoServiceImpl;
import com.rsd.service.impl.NewsTypeServiceImpl;

import java.util.Date;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        NewsInfoServiceImpl newsInfoService = new NewsInfoServiceImpl();
        List<NewsInfo> list = newsInfoService.queryList();

//        System.out.println(list);

        NewsInfo newsInfo = null;

        newsInfo = newsInfoService.getById(2);

//        System.out.println(newsInfo);

//        newsInfo.setTitle("疫情");
//        newsInfo.setTypeId(5);
//        newsInfo.setShortDesc("北京疫情");
//        newsInfo.setContent("北京又新增了好几例");
//        newsInfo.setShowTime("2022-06-05");
//        newsInfo.setPubUserId(3);
//        newsInfo.setCreateTime(new Date());
//        newsInfoService.insert(newsInfo);
//
//        newsInfo.setId(4);
//        newsInfo.setTitle("节假日");
//        newsInfo.setTypeId(1);
//        newsInfo.setShortDesc("端午节");
//        newsInfo.setContent("端午节放假3天");
//        newsInfo.setShowTime("2022-06-04 12:02");
//        newsInfo.setPubUserId(3);
//        newsInfo.setCreateTime(new Date());
//        newsInfo.setUpdateTime(new Date());
//        newsInfoService.update(newsInfo);

//        newsInfoService.deleteById(5);

//        NewsTypeServiceImpl newsTypeService = new NewsTypeServiceImpl();
//        newsTypeService.queryList();
//        newsTypeService.queryList();
//        newsTypeService.queryList();
//        newsTypeService.queryList();
//        newsTypeService.queryList();
    }
}
