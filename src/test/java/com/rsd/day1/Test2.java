package com.rsd.day1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class Test2 {
    public static void main(String[] args) {
        try {
            Apple apple = new Apple();
            Class<?> clazz = Class.forName("com.rsd.day1.Apple");
            Method[] declaredMethods = clazz.getDeclaredMethods();//调取所有方法
            for (Method method : declaredMethods) {
                String mods = Modifier.toString(method.getModifiers());//getModifiers看修饰符的
                System.out.println(mods + "\t" +method.getReturnType() + "\t" + method.getName() + "\t" + method.getParameterCount());//getReturnType()看类型的,getParameterCount看方法构造器的参数个数
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    System.out.println(parameter.getType() + "\t" + parameter.getName());
                }
            }
            Method method_eat = clazz.getDeclaredMethod("eat");
            int modifiers = method_eat.getModifiers();//修饰符数字
            String modifiers_str = Modifier.toString(modifiers);//修饰符字符串
            Class<?> returnType = method_eat.getReturnType();//返回值类型
            String method_eatName = method_eat.getName();//得到方法名称
            Class<?>[] parameterTypes = method_eat.getParameterTypes();//得到参数列表
            Object o = method_eat.invoke(apple);//穿参数调方法
            System.out.println(o);//得到返回值

            clazz.getModifiers();//得到公共的方法
            clazz.getMethod("eat");//得到指定名字的公共方法
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
