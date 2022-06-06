package com.rsd.servlet;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test6 {
    public static void main(String[] args) {
        /**
         * 字节流
         * 输入流
         */
        InputStream in = null;
        try {
            in = new FileInputStream("D:/aa.txt");
            int i = in.read();//每次读的时候用一个字节，中文占2个字节就会乱码
            while (i != -1) {
                System.out.print((char) i);
                i = in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
