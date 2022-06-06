package com.rsd.day1;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@MultipartConfig
@WebServlet
public class Apple {
    private Integer id;

    public String name = "啦啦";

    protected Float price;

    String address;

    public Apple() {
        System.out.println("无参构造器");
    }

    public Apple(String name) {
        this.name = name;
        System.out.println("一个参数构造器");
    }

    public int eat() {
        System.out.println("吃苹果");
        return 1;
    }

    private Apple(String name, Integer id) {
        System.out.println("跑跑跑");
    }

    static {

    }

    public float sale(float money, float price) {
        return money - price;
    }

}
