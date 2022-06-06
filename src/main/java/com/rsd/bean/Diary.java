package com.rsd.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Diary {

    private Integer id;

    private String title;

    private String content;

    private Date createTime;

    private Integer userId;
}
