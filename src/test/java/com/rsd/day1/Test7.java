package com.rsd.day1;

import com.rsd.bean.SysFunction;
import com.rsd.util.JDBCUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test7 {
    public static void main(String[] args) {
        SysFunction sysFunction = new SysFunction();
        sysFunction.setId(2);

        String sql = JDBCUtil.deleteSQL(sysFunction);
        System.out.println(sql);
    }
}

