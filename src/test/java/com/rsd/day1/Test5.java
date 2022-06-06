package com.rsd.day1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test5 {
    public static void main(String[] args) {
        try {
            //第一种
            Apple apple1 = new Apple();
            System.out.println("第一种创建对象方式" + apple1.eat());

            //第二种
            Class<?> clazz = Class.forName("com.rsd.day1.Apple");
            Apple apple2 = (Apple)clazz.newInstance();
            System.out.println("第二种创建对象方式" + apple2);

            //第三种
            Constructor<?> constructor = clazz.getConstructor();
            Apple apple3 = (Apple)constructor.newInstance();
            System.out.println("第三种创建对象方式" + apple3);

            //第四种
            Class clazz2 = Apple.class;
            Apple apple4 = (Apple)clazz2.newInstance();
            System.out.println("第四种创建对象方式" + apple4);

            //第五种
            Class clazz3 = apple1.getClass();//调的第一个，可以得到类的所有元素
            Apple apple5 = (Apple)clazz3.newInstance();
            System.out.println("第五种创建对象方式" + apple5);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
