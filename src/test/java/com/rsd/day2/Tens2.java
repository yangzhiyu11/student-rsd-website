package com.rsd.day2;

public class Tens2 {
    public static void main(String[] args) {
        String str = ",国1 a. aaaaaaa";
        boolean b = str.matches("^\\W?\\D\\d\\b\\s\\b\\w.\\s\\b\\w*$");//w是弄字符的
        System.out.println(b);
    }
}
