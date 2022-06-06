package com.rsd.day1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.SysUser;
import com.rsd.util.BeanUtil;

import java.util.Date;

public class Test14 {
    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
        sysUser.setId(2);
        sysUser.setLoginName("啦啦啦");
        sysUser.setPassword("123456");
        sysUser.setRealName("哈哈哈");
        sysUser.setRoleId(3);
        sysUser.setSex("男");
        sysUser.setTel("12222222234");
        sysUser.setCreateTime(new Date());

        JsonMapper jsonMapper = new JsonMapper();
        try {
            String json = jsonMapper.writeValueAsString(sysUser);
            System.out.println(json);

            System.out.println("---------------------------------");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String json = BeanUtil.beanToJson(sysUser);
        System.out.println(json);
    }
}