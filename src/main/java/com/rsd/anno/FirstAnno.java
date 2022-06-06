package com.rsd.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.CONSTRUCTOR})//元注解。 TYPE只能往类上面加  FIELD只能往属性上面加  METHOD只能往方法上面加  CONSTRUCTOR只能往构造器上面加  {}能把它们全部包含上
@Retention(RetentionPolicy.RUNTIME)//在程序运行中使用
public @interface FirstAnno {
    String name() default "啦啦啦";
    int age();
    String value() default "qqq";
}
