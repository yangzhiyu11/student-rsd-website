package com.rsd.bean;

import lombok.Data;

import java.util.Date;

@Data
public class NewsInfo {

    private Integer id;

    private String title;

    private Integer typeId;

    private  String shortDesc;

    private String content;

    private String showTime;

    private Integer pubUserId;

    private Date createTime;

    private Date updateTime;
}
