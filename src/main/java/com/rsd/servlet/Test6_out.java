package com.rsd.servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Test6_out {
    public static void main(String[] args) {
        OutputStream output = null;
        try {
            output = new FileOutputStream("D:/ww.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
