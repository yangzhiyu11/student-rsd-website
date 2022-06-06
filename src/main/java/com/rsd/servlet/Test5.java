package com.rsd.servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Test5 {
    public static void main(String[] args) {
        /**
         * 字符流
         * 输入流
         */
        Reader reader = null;
        try {
            reader = new FileReader("D:/aa.txt");
            int i = reader.read();
            while (i != -1) {
                System.out.print((char) i);//这个要写在上面否则就不会读出第一个
                i = reader.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
