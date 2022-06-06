package com.rsd.day1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test3 {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.rsd.day1.Apple");

            Constructor<?> constructor = clazz.getConstructor();
            constructor.newInstance();

            Constructor<?> constructor1 = clazz.getConstructor(String.class);
            constructor1.newInstance("");//前面如果没加参数，后面这里就不用写

            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class,Integer.class);//能调用私有构造器
            declaredConstructor.setAccessible(true);
            Apple apple = (Apple)declaredConstructor.newInstance("wwww", 123);
            System.out.println(apple.eat());

            Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
            for (Constructor constructor2 : declaredConstructors) {
                System.out.println(constructor2.getModifiers() + "\t" + constructor2.getName() + "\t" + constructor2.getParameterCount());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
