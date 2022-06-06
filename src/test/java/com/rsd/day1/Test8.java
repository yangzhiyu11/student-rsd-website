package com.rsd.day1;

import com.rsd.bean.SysUser;
import com.rsd.util.JDBCUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test8 {
    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
        sysUser.setLoginName("www");
        sysUser.setRealName("哈哈哈哈");
        sysUser.setRoleId(2);
        sysUser.setCreateTime(new Date());

        String sql = JDBCUtil.insertSQL(sysUser);
        System.out.println(sql);
    }
}
