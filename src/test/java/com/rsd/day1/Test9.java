package com.rsd.day1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.SysUser;
import com.rsd.util.JDBCUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test9 {
    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
        sysUser.setId(5);
        sysUser.setLoginName("www");
        sysUser.setRealName("哈哈哈哈");
        sysUser.setRoleId(2);
        sysUser.setCreateTime(new Date());
        sysUser.setFlag("AAAA");

        String sql = JDBCUtil.updateSQL(sysUser);
        System.out.println(sql);

        JsonMapper jsonMapper = new JsonMapper();
        try {
            String json = jsonMapper.writeValueAsString(sysUser);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
