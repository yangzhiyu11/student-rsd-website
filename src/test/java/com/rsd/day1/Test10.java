package com.rsd.day1;

import com.rsd.bean.SysRole;
import com.rsd.util.BeanUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test10 {
    public static void main(String[] args) {
        SysRole sysRole = new SysRole();
        sysRole.setId(2);
        sysRole.setName("啦啦啦");
        sysRole.setCreateTime(new Date());

        Map<String, Object> map = BeanUtil.beanToMap(sysRole);
        System.out.println(map.get("id"));
        System.out.println(map.get("name"));
        System.out.println(map.get("createTime"));
    }
}
