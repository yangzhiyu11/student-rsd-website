package com.rsd.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class NewsType implements Serializable {

    private Integer id;

    private String name;
}
