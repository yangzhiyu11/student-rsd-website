package com.rsd.day1;

import com.rsd.bean.SysRole;
import com.rsd.util.BeanUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class Test12 {
    public static void main(String[] args) {
        SysRole sysRole = new SysRole();
        sysRole.setId(2);
        sysRole.setName("啦啦啦");
        sysRole.setCreateTime(new Date());

        SysRole sysRole2 = (SysRole) BeanUtil.cloneBean(sysRole);


        sysRole2.setName("qqqq");
        System.out.println(sysRole.getName());
        System.out.println(sysRole2.getName());
    }
}
