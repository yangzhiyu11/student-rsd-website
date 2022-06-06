package com.rsd.servlet;

import java.io.File;

public class Test4 {
    public static void main(String[] args) {
        File file = new File("D:/Yang.java");
        boolean b = file.isFile();//判断这个是不是文件
        System.out.println(file.exists());//判断这个文件存不存在
        System.out.println(b);

        File file1 = new File("D:/QQ");
        boolean b1 = file1.isDirectory();//判断是不是文件夹
        System.out.println(b1);

        File file2 = new File("D:/哈哈哈");
        boolean b2 = file2.isDirectory();
        if (!b2) {
//            file2.mkdir();只能创建单层目录
            file2.mkdirs();//能创建多层目录
        }


    }
}
