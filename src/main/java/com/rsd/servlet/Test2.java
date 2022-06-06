package com.rsd.servlet;

import com.rsd.bean.SysUser;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        SysUser sysUser1 = new SysUser();
        sysUser1.setRealName("杨治宇");
        sysUser1.setSex("0");

        SysUser sysUser2 = new SysUser();
        sysUser2.setRealName("拉拉啦啦啦");
        sysUser2.setSex("1");

        SysUser sysUser3 = new SysUser();
        sysUser3.setRealName("强强强强钱钱钱钱钱钱钱");
        sysUser3.setSex("0");

        List<SysUser> list = new ArrayList<>();
        list.add(sysUser1);
        list.add(sysUser2);
        list.add(sysUser3);

        for (SysUser sysUser : list) {
            sysUser.setSex(sysUser.getSex().equals("0")?"男":"女");
        }

        for (SysUser sysUser : list) {
            System.out.println(sysUser.getRealName()+"\t"+sysUser.getSex());
        }
    }
}
