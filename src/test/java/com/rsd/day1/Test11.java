package com.rsd.day1;

import com.rsd.bean.SysRole;
import com.rsd.util.BeanUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test11 {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",2);
        map.put("name","啦啦啦");
        map.put("createTime",new Date());

        SysRole sysRole = (SysRole) BeanUtil.mapToBean(SysRole.class,map);

        System.out.println(sysRole.getId());
        System.out.println(sysRole.getName());
        System.out.println(sysRole.getCreateTime());
    }
}
