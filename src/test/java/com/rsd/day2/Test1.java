package com.rsd.day2;

import com.rsd.anno.FirstAnno;
import com.rsd.bean.SysFunction;

import java.lang.annotation.Annotation;

public class Test1 {
    public static void main(String[] args) {
        Class<SysFunction> clazz = SysFunction.class;

        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            FirstAnno firstAnno = (FirstAnno) annotation;
            System.out.println(firstAnno.age());
            System.out.println(firstAnno.name());
            System.out.println(firstAnno.value());
        }
    }
}
