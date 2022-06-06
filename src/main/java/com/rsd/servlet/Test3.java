package com.rsd.servlet;

import com.rsd.util.Md5Util;

public class Test3 {
    public static void main(String[] args) {
        String encrypt = null;
        try {
            encrypt = Md5Util.encrypt("123456");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(encrypt);
    }
}
