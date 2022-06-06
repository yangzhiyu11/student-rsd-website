package com.rsd.day1;

import com.rsd.bean.SysFiles;
import com.rsd.util.JDBCUtil;

public class Test6 {
    public static void main(String[] args) {
        String sql = JDBCUtil.selectSQL(SysFiles.class);
        System.out.println(sql);
    }
}
