package com.rsd.day1;

import java.lang.annotation.Annotation;

public class Test4 {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.rsd.day1.Apple");
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
